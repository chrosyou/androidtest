package com.macalyou.murisly.alterdlg;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.MenuItem;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

    Button btnShowDialog;
    Button btnShowDialog_Layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //定义按钮
        btnShowDialog=(Button)this.findViewById(R.id.Button01);
        btnShowDialog.setOnClickListener(new ClickEvent());
        btnShowDialog_Layout=(Button)this.findViewById(R.id.Button02);
        btnShowDialog_Layout.setOnClickListener(new ClickEvent());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    //统一处理按键事件
    class ClickEvent implements OnClickListener {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            if(v==btnShowDialog)
                showDialog(MainActivity.this);

            /*
            else if(v==btnShowDialog_Layout)
                showDialog_Layout(MainActivity.this);
                */

        }

    }


    //显示基本的AlertDialog
    private void showDialog(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setIcon(R.drawable.filer);
        builder.setTitle("Title标题");
        builder.setMessage("Message");
        builder.setPositiveButton("Button1",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        setTitle("点击了对话框上的Button1");
                    }
                });
        builder.setNeutralButton("Button2",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        setTitle("点击了对话框上的Button2");
                    }
                });
        builder.setNegativeButton("Button3",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        setTitle("点击了对话框上的Button3");
                    }
                });
        builder.show();
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
