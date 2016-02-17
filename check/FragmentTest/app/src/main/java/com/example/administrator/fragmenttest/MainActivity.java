package com.example.administrator.fragmenttest;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    private Button first;
    private Button second;

    FirstFragment mFitst;
    SecondFragment mSecond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        first = (Button) findViewById(R.id.id_first);
        second = (Button) findViewById(R.id.id_second);
        first.setOnClickListener(this);
        second.setOnClickListener(this);

        init();
    }

    public void init() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();


        mFitst = new FirstFragment();
        transaction.replace(R.id.id_fragment_content, mFitst);
        transaction.commit();
    }

    @Override
    public void onClick(View v) {

        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        //hideFragments(transaction);
        transaction.setCustomAnimations(R.anim.fragment_slide_left_enter, R.anim.fragment_slide_right_exit);


        switch (v.getId()) {
            case R.id.id_first:
                if (mFitst == null) {
                    mFitst = new FirstFragment();
                    transaction.add(R.id.id_fragment_content, mFitst);
                }

                transaction.show(mFitst);
                transaction.hide(mSecond);
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                Log.w(TAG, "first");
                break;
            case R.id.id_second:
                if (mSecond == null) {
                    mSecond = new SecondFragment();
                    transaction.add(R.id.id_fragment_content, mSecond);
                }
                transaction.show(mSecond);
                transaction.hide(mFitst);
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                Log.w(TAG, "second");
                break;
        }

        transaction.commit();
    }

    private void hideFragments(FragmentTransaction transaction)
    {
        if (mFitst != null)
        {
            transaction.hide(mFitst);
        }
        if (mSecond != null)
        {
            transaction.hide(mSecond);
        }
    }
}
