package com.example.administrator.fragmenttest;

import android.app.Fragment;

/**
 * Created by Administrator on 2016/2/17.
 */
public interface IContentFragment {

    enum FragmentFlag {
        FIRST,
        SECOND,
        THIRD,
        FOUR,
    }

    enum EventFlag {
        FIRST_EVENT,
        SECOND_EVENT,
        THIRD_EVENT,
        FOUR_EVENT,
    }

    interface ContentFragmentEventListener {
        void onViewEvent(FragmentFlag fragmentFlag, EventFlag eventFlag);
    }
}
