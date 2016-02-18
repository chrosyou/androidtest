package com.example.administrator.fragmenttest;


import android.app.Activity;
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
    }

    public void Init(Activity activity) {
        mActivity = activity;
        mFragmentStack = new FragmentStack();
        mFragementManager = mActivity.getFragmentManager();

        FragmentContent first = mFragmentStack.createFlagment(IContentFragment.FragmentFlag.FIRST);
        FragmentTransaction transaction = mFragementManager.beginTransaction();
        transaction.add(R.id.id_fragment_content, first);
        transaction.show(first);
        transaction.commit();
    }

    public static UIDispatch getInstance() {
        return instance;
    }

    @Override
    public void onViewEvent(IContentFragment.FragmentFlag fragmentFlag, IContentFragment.EventFlag eventFlag) {

        //IContentFragment.FragmentFlag flag = mFragmentStack.getFlagbyFragment(fragment);
        IContentFragment.FragmentFlag nextFragmentFlag = IContentFragment.FragmentFlag.FIRST;
        FragmentContent nextFragment = null;

        switch (fragmentFlag) {
            case FIRST:
                switch (eventFlag) {
                    case FIRST_EVENT:
                        nextFragmentFlag = IContentFragment.FragmentFlag.SECOND;
                        break;
                }
                break;
            case SECOND:
                switch (eventFlag) {
                    case SECOND_EVENT:
                        nextFragmentFlag = IContentFragment.FragmentFlag.FOUR;
                        break;
                }
                break;
            case THIRD:
                switch (eventFlag) {
                    case THIRD_EVENT:
                        nextFragmentFlag = IContentFragment.FragmentFlag.FOUR;
                        break;
                }
                break;
            case FOUR:
                switch (eventFlag) {
                    case FOUR_EVENT:
                        nextFragmentFlag = IContentFragment.FragmentFlag.FIRST;
                        break;
                }
                break;

        }

        FragmentTransaction transaction = mFragementManager.beginTransaction();
        nextFragment = mFragmentStack.getFragmentByFlag(nextFragmentFlag);
        if (nextFragment == null) {
            nextFragment = mFragmentStack.createFlagment(nextFragmentFlag);
            if (nextFragment == null) {
                throw new RuntimeException(nextFragmentFlag + " Fragment is not created in FragmentContent.java");
            }
            transaction.setCustomAnimations(nextFragment.getAnimEnter(), nextFragment.getAnimLeave());
            transaction.add(R.id.id_fragment_content, nextFragment);
        }

        transaction.setCustomAnimations(nextFragment.getAnimEnter(), nextFragment.getAnimLeave());
        mFragmentStack.hideAll(transaction);
        transaction.show(nextFragment);
        transaction.commit();
    }

}
