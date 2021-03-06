package com.macalyou.murisly.mylisten;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static android.view.View.*;

public class MainActivity extends Activity {

    private EditText tvView;
    private Button btClick;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btClick = (Button)findViewById(R.id.bttest);
        btClick.setOnClickListener(new MyClickListener());
    }

    //时间监听函数
    public void clickHandle(View scource)
    {
        EditText show = (EditText)findViewById(R.id.tvshow);
        show.setText("直接绑定");
    }

    //时间监听器
    class MyClickListener implements OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            EditText txt = (EditText)findViewById(R.id.tvshow);
            txt.setText("按钮被单击");
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
