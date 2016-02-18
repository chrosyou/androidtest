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
public class FragmentThird extends FragmentContent {
    private Button third;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.third_fragment, container, false);
        Init(view);
        return view;
    }

    public void Init(View view) {
        third = (Button)view.findViewById(R.id.id_third);
        third.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIDispatch.getInstance().onViewEvent(IContentFragment.FragmentFlag.THIRD, IContentFragment.EventFlag.THIRD_EVENT);
            }
        });
    }

}
