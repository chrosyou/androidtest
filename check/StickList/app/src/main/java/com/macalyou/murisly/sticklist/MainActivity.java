package com.macalyou.murisly.sticklist;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.WeakHashMap;

import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

public class MainActivity extends Activity implements StickyListHeadersListView.OnHeaderClickListener {

    //private StickyListHeadersListView mListView;
    //MyAdapter mTestBaseAdapter;
    //WeakHashMap<View,Integer> mOriginalViewHeightPool = new WeakHashMap<View, Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StickyListHeadersListView stickyList = (StickyListHeadersListView) findViewById(R.id.list);
        MyAdapter adapter = new MyAdapter(this);
        stickyList.setAdapter(adapter);

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

    @Override
    public void onHeaderClick(StickyListHeadersListView l, View header, int itemPosition, long headerId, boolean currentlySticky) {

    }
}
