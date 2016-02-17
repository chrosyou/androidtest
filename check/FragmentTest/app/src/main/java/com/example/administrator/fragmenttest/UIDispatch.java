package com.example.administrator.fragmenttest;


import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

/**
 * Created by Administrator on 2016/2/17.
 */
public class UIDispatch implements IContentFragment.ContentFragmentEventListener {

    private static UIDispatch instance = new UIDispatch();
    private FragmentManager mFragementManager;
    private FragmentStack mFragmentStack;
    private Activity mActivity;

    private UIDispatch() {
        mFragmentStack = new FragmentStack();
    }

    public void Init(Activity activity) {
        mActivity = activity;
        mFragementManager = mActivity.getFragmentManager();
    }

    @Override
    public void onViewEvent(Fragment fragment, IContentFragment.EventFlag eventFlag) {

        IContentFragment.FragmentFlag flag = mFragmentStack.getFlagbyFragment(fragment);
        IContentFragment.FragmentFlag nextFragmentFlag = IContentFragment.FragmentFlag.FIRST;
        FragmentContent nextFragment = null;

        switch (flag) {
            case FIRST:
                switch (eventFlag) {
                    case SECOND:
                        nextFragmentFlag = IContentFragment.FragmentFlag.SECOND;
                        break;
                }
                break;
            case SECOND:
                break;

        }

        nextFragment = mFragmentStack.getFragmentByFlag(nextFragmentFlag);
        if (nextFragment == null) {
            throw new RuntimeException(nextFragmentFlag + " Fragment is not created in FragmentContent.java");
        }

        FragmentTransaction transaction = mFragementManager.beginTransaction();
        transaction.setCustomAnimations(nextFragment.getAnimEnter(), nextFragment.getAnimLeave());
        transaction.commit();
    }
}
