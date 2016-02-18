package com.example.administrator.fragmenttest;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Administrator on 2016/2/16.
 */
public class FragmentFirst extends FragmentContent {

    private Button tosecond;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.first_fragment, container, false);
        Init(view);
        return view;
    }

    public void Init(View view) {
        tosecond = (Button)view.findViewById(R.id.id_first);
        tosecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIDispatch.getInstance().onViewEvent(IContentFragment.FragmentFlag.FIRST, IContentFragment.EventFlag.FIRST_EVENT);
            }
        });
    }

}
