package com.macalyou.murisly.imageview;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {

    private float alpha = 255;
    final Button btplus = (Button)findViewById(R.id.plus);
    final Button btminus = (Button)findViewById(R.id.minus);
    final ImageView image1 = (ImageView)findViewById(R.id.image1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        OnClickListener listerner = new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == btplus)
                {
                    alpha += 20;
                }
                if (v == btminus)
                {
                    alpha -= 20;
                }
                if (alpha > 255)
                {
                    alpha = 255;
                }
                if (alpha < 0)
                {
                    alpha = 0;
                }
                image1.setAlpha(alpha);
            }
        };

        btplus.setOnClickListener(listerner);
        btminus.setOnClickListener(listerner);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
