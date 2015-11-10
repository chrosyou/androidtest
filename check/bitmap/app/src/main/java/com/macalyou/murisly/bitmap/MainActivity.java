package com.macalyou.murisly.bitmap;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends Activity {

    String[] images = null;
    AssetManager assets = null;
    int currentImg = 0;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image = (ImageView)findViewById(R.id.image);
        try{
            assets = getAssets();
            // 获取 / assers 目录下的所有文件

            images = assets.list("");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        //获取的bn按钮
        final Button btNext = (Button) findViewById(R.id.btNext);

        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //如果数组越界
                if (currentImg >= images.length)
                {
                    currentImg = 0;
                }

                //找下一个图片
                while (!images[currentImg].endsWith(".png")
                        && !images[currentImg].endsWith(".jpg")
                        && !images[currentImg].endsWith(".gif"))
                {
                    currentImg++;
                    //如果已发生数组越界
                    if (currentImg >= images.length)
                    {
                        currentImg = 0;
                    }
                }

                InputStream assetFile = null;
                try
                {
                    //打开指定资源对应的输入流
                    assetFile = assets.open(images[currentImg++]);
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }

                BitmapDrawable bitmapDrawable = (BitmapDrawable) image.getDrawable();

                //如果图片还未回收，先强制回收
                if (bitmapDrawable != null && !bitmapDrawable.getBitmap().isRecycled())
                {
                    bitmapDrawable.getBitmap().recycle();
                }

                //改变imageView显示的图片
                image.setImageBitmap(BitmapFactory.decodeStream(assetFile));
                //image.setImageBitmap(R.drawable.gowalla);
            }
        });
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
