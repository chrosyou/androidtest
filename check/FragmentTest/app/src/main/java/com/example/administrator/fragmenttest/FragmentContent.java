package com.example.administrator.fragmenttest;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2016/2/16.
 */
public class FragmentContent extends Fragment {

    private int animEnter = 0;
    private int animLeave = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.content_fragment, container, false);
        return view;
    }

    public int getAnimEnter() {
        if (animEnter == 0) {
            return R.anim.fragment_slide_right_enter;
        } else {
            return animEnter;
        }
    }

    public void setAnimEnter(int enter) {
        animEnter = enter;
    }

    public int getAnimLeave() {
        if (animLeave == 0) {
            return R.anim.fragment_slide_left_leave;
        } else {
            return animLeave;
        }
    }

    public void setAnimLeave(int leave) {
        animLeave = leave;
    }
}
