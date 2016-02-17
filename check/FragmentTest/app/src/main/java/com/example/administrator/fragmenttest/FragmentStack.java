package com.example.administrator.fragmenttest;

import android.app.Fragment;

import java.util.Map;

/**
 * Created by Administrator on 2016/2/17.
 */
public class FragmentStack {

    private Map<IContentFragment.FragmentFlag, FragmentContent> contentFragmentStack;

    public FragmentStack() {
        for (IContentFragment.FragmentFlag each : IContentFragment.FragmentFlag.values()) {
            contentFragmentStack.put(each, null);
        }
    }

    public IContentFragment.FragmentFlag getFlagbyFragment(Fragment fragment) {
        for (Map.Entry<IContentFragment.FragmentFlag, FragmentContent> entry : contentFragmentStack.entrySet()) {
            Fragment temp = entry.getValue();
            if (temp == fragment) {
                return entry.getKey();
            }
        }

        return IContentFragment.FragmentFlag.FIRST;
    }

    public FragmentContent getFragmentByFlag(IContentFragment.FragmentFlag flag) {
        FragmentContent fragment = contentFragmentStack.get(flag);
        if (fragment == null) {
            fragment = createFlagment(flag);
        }

        if (fragment != null) {
            contentFragmentStack.put(flag, fragment);
        }
        return fragment;
    }

    private FragmentContent createFlagment(IContentFragment.FragmentFlag flag) {
        FragmentContent newFlagment = null;

        switch (flag) {
            case FIRST:
                newFlagment = new FragmentFirst();
                break;
            case SECOND:
                newFlagment = new FragmentFirst();
                break;
            case THIRD:
                newFlagment = new FragmentThird();
                break;
            case FOUR:
                newFlagment = new FragmentFour();
                break;
        }

        return newFlagment;
    }
}
