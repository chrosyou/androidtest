package com.macalyou.murisly.gridlayout;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.GridLayout;

public class MainActivity extends Activity {

    GridLayout gridlayout;
    String[] chars = new String[]{
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            ".", "0", "=", "+",
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridlayout = (GridLayout)findViewById(R.id.root);
        for (int i = 0; i < chars.length; i++)
        {
            Button bn = new Button(this);
            bn.setText(chars[i]);
            bn.setTextSize(40);
            GridLayout.Spec rowSpec = GridLayout.spec(i / 4 + 2);
            GridLayout.Spec colSpec = GridLayout.spec(i % 4);
            GridLayout.LayoutParams param = new GridLayout.LayoutParams(rowSpec, colSpec);

            param.setGravity(Gravity.FILL);
            gridlayout.addView(bn, param);
        }
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
