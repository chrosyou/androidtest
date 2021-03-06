package com.example.administrator.fragmenttest;

import android.app.Activity;
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
    private Button third;
    private Button four;

    FragmentFirst mFirst;
    FragmentSecond mSecond;
    FragmentThird mThird;
    FragmentFour mFour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        first = (Button) findViewById(R.id.id_first);
        second = (Button) findViewById(R.id.id_second);
        third = (Button) findViewById(R.id.id_third);
        four = (Button) findViewById(R.id.id_four);
        first.setOnClickListener(this);
        second.setOnClickListener(this);
        third.setOnClickListener(this);
        four.setOnClickListener(this);

        UIDispatch.getInstance().Init(this);
    }

    public void init() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();


        mFirst = new FragmentFirst();
        transaction.replace(R.id.id_fragment_content, mFirst);
        transaction.commit();
    }

    @Override
    public void onClick(View v) {

        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.setCustomAnimations(R.anim.fragment_slide_right_enter, R.anim.fragment_slide_left_leave);

        switch (v.getId()) {
            case R.id.id_first:

                break;
            case R.id.id_second:
                if (mSecond == null) {
                    mSecond = new FragmentSecond();
                    transaction.add(R.id.id_fragment_content, mSecond);
                }
                transaction.show(mSecond);
                transaction.hide(mFirst);
                Log.w(TAG, "second");
                break;
            case R.id.id_third:
                if (mThird == null) {
                    mThird = new FragmentThird();
                    transaction.add(R.id.id_fragment_content, mThird);
                }

                transaction.show(mThird);
                transaction.hide(mSecond);
                Log.w(TAG, "third");
                break;

            case R.id.id_four:
                transaction.setCustomAnimations(R.anim.fragment_slide_left_enter, R.anim.fragment_slide_right_leave);
                if (mFour == null) {
                    mFour = new FragmentFour();
                    transaction.add(R.id.id_fragment_content, mFour);
                }

                transaction.show(mFour);
                transaction.hide(mThird);
                Log.w(TAG, "four");
                break;
        }

        transaction.commit();
    }

    private void hideFragments(FragmentTransaction transaction)
    {
        if (mFirst != null)
        {
            transaction.hide(mFirst);
        }
        if (mSecond != null)
        {
            transaction.hide(mSecond);
        }
    }
}
