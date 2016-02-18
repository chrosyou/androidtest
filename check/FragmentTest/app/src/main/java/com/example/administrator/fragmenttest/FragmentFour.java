package com.example.administrator.fragmenttest;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Administrator on 2016/2/16.
 */
public class FragmentFour extends FragmentContent {
    private Button four;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.four_fragment, container, false);
        Init(view);
        return view;
    }

    public void Init(View view) {
        four = (Button)view.findViewById(R.id.id_four);
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIDispatch.getInstance().onViewEvent(IContentFragment.FragmentFlag.FOUR, IContentFragment.EventFlag.FOUR_EVENT);
            }
        });
    }

    @Override
    public int getAnimEnter() {
        return super.getAnimEnter();
    }

    @Override
    public int getAnimLeave() {
        return super.getAnimLeave();
    }
}
