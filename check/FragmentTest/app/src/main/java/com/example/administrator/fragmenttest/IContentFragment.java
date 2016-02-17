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
        FIRST,
        SECOND,
        THIRD,
        FOUR,
    }

    interface ContentFragmentEventListener {
        void onViewEvent(Fragment view, EventFlag eventFlag);
    }
}
