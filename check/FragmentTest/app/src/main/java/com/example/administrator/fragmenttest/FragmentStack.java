package com.example.administrator.fragmenttest;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Administrator on 2016/2/17.
 */
public class FragmentStack {

    private Map<IContentFragment.FragmentFlag, FragmentContent> contentFragmentStack;

    public FragmentStack() {
        contentFragmentStack = new TreeMap<>();
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
        return fragment;
    }

    public FragmentContent createFlagment(IContentFragment.FragmentFlag flag) {
        FragmentContent newFlagment = null;

        switch (flag) {
            case FIRST:
                newFlagment = new FragmentFirst();
                break;
            case SECOND:
                newFlagment = new FragmentSecond();
                break;
            case THIRD:
                newFlagment = new FragmentThird();
                break;
            case FOUR:
                newFlagment = new FragmentFour();
                break;
            default:
                break;
        }

        contentFragmentStack.put(flag, newFlagment);
        return newFlagment;
    }

    public void hideAll(FragmentTransaction transaction) {
        for (Map.Entry<IContentFragment.FragmentFlag, FragmentContent> entry : contentFragmentStack.entrySet()) {
            FragmentContent fragmentContent = entry.getValue();
            if (fragmentContent != null) {
                transaction.hide(entry.getValue());
            }
        }
    }
}
