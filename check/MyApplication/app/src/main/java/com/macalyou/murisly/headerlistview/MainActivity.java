package com.macalyou.murisly.headerlistview;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AbsListView.LayoutParams;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements
        ExpandableListView.OnChildClickListener,
        ExpandableListView.OnGroupClickListener,
        PinnedHeaderExpandableListView.OnHeaderUpdateListener, StickyLayout.OnGiveUpTouchEventListener {

    private PinnedHeaderExpandableListView expandableListView;
    private StickyLayout stickyLayout;
    private ArrayList<Group> groupList;
    private ArrayList<List<Child>> childList;

    private PinnedHeaderExpandableListViewAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        expandableListView = (PinnedHeaderExpandableListView) findViewById(R.id.expandablelist);
        stickyLayout = (StickyLayout)findViewById(R.id.sticky_layout);
        initData();

        myAdapter = new PinnedHeaderExpandableListViewAdapter(this);
        myAdapter.setChildList(childList);
        myAdapter.setGroupList(groupList);
        expandableListView.setAdapter(myAdapter);

        // 展开所有group
        for (int i = 0, count = expandableListView.getCount(); i < count; i++) {
            expandableListView.expandGroup(i);
        }

        expandableListView.setOnHeaderUpdateListener(this);
        expandableListView.setOnChildClickListener(this);
        expandableListView.setOnGroupClickListener(this);
        stickyLayout.setOnGiveUpTouchEventListener(this);
    }


    /***
     * InitData
     */
    void initData() {
        groupList = new ArrayList<Group>();
        Group group = null;
        for (int i = 0; i < 3; i++) {
            group = new Group();
            group.setTitle("group-" + i);
            group.setSelectState(Group.STATE_SELECTED);
            groupList.add(group);
        }

        childList = new ArrayList<List<Child>>();
        for (int i = 0; i < groupList.size(); i++) {
            ArrayList<Child> childTemp;

            childTemp = new ArrayList<Child>();
            for (int j = 0; j < 13; j++) {
                Child appchild = new Child();
                appchild.setAppName("name-" + String.valueOf(i) + j);
                appchild.setCleanSize(j);
                appchild.setSelectState(Group.STATE_SELECTED);

                childTemp.add(appchild);
            }

            childList.add(childTemp);
        }

    }

    @Override
    public boolean onGroupClick(final ExpandableListView parent, final View v, int groupPosition, final long id) {
        Toast.makeText(MainActivity.this, groupList.get(groupPosition).getTitle(), 1).show();
        return false;
    }

    @Override
    public boolean onChildClick(ExpandableListView parent, View v,
                                int groupPosition, int childPosition, long id) {
        Toast.makeText(MainActivity.this, childList.get(groupPosition).get(childPosition).getAppName(), 1).show();

        return false;
    }

    @Override
    public View getPinnedHeader() {
        View headerView = (ViewGroup) getLayoutInflater().inflate(R.layout.groupitem, null);
        headerView.setLayoutParams(new LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

        return headerView;
    }

    @Override
    public void updatePinnedHeader(View headerView, int firstVisibleGroupPos) {
        Group firstVisibleGroup = (Group) myAdapter.getGroup(firstVisibleGroupPos);
        TextView textView = (TextView) headerView.findViewById(R.id.group);
        textView.setText(firstVisibleGroup.getTitle());
    }

    @Override
    public boolean giveUpTouchEvent(MotionEvent event) {
        if (expandableListView.getFirstVisiblePosition() == 0) {
            View view = expandableListView.getChildAt(0);
            if (view != null && view.getTop() >= 0) {
                return true;
            }
        }
        return false;
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
