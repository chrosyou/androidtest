package com.example.administrator.animationall;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class MainActivity extends Activity {

    Context mContext;
    private Button button_alpha;
    private Button button_scale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;
        init();
    }

    public void init() {
        button_alpha = (Button)findViewById(R.id.id_alpha);
        button_alpha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation  anim_alpha = AnimationUtils.loadAnimation(mContext, R.anim.alpha);
                button_alpha.startAnimation(anim_alpha);
            }
        });

        button_scale = (Button)findViewById(R.id.id_scale);
        button_scale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim_scale = AnimationUtils.loadAnimation(mContext, R.anim.scale);
                button_scale.startAnimation(anim_scale);
            }
        });
    }
}
