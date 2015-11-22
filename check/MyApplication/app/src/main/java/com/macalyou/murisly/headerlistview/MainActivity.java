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

    private MyexpandableListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        expandableListView = (PinnedHeaderExpandableListView) findViewById(R.id.expandablelist);
        stickyLayout = (StickyLayout)findViewById(R.id.sticky_layout);
        initData();

        adapter = new MyexpandableListAdapter(this);
        expandableListView.setAdapter(adapter);

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

    /***
     * 数据源
     *
     * @author Administrator
     *
     */
    class MyexpandableListAdapter extends BaseExpandableListAdapter {
        private Context context;
        private LayoutInflater inflater;

        public MyexpandableListAdapter(Context context) {
            this.context = context;
            inflater = LayoutInflater.from(context);
        }

        // 返回父列表个数
        @Override
        public int getGroupCount() {
            return groupList.size();
        }

        // 返回子列表个数
        @Override
        public int getChildrenCount(int groupPosition) {
            return childList.get(groupPosition).size(); }

        @Override
        public Object getGroup(int groupPosition) {
            return groupList.get(groupPosition);
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return childList.get(groupPosition).get(childPosition);
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {

            return true;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            GroupHolder groupHolder = null;
            if (convertView == null) {
                groupHolder = new GroupHolder();
                convertView = inflater.inflate(R.layout.groupitem, null);
                groupHolder.textView = (TextView) convertView.findViewById(R.id.group);
                groupHolder.multbutton = (MultButton)convertView.findViewById(R.id.multbutton);
                groupHolder.multbutton.setOnClickListener(groupHolder.multbutton);
                //groupHolder.multbutton.setSelectState();
                convertView.setTag(groupHolder);
            } else {
                groupHolder = (GroupHolder) convertView.getTag();
            }

            groupHolder.textView.setText(((Group) getGroup(groupPosition)).getTitle());

            //if (isExpanded)// ture is Expanded or false is not isExpanded
            //    groupHolder.imageView.setImageResource(R.drawable.expanded);
            //else
            //    groupHolder.imageView.setImageResource(R.drawable.collapse);
            return convertView;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            ChildHolder childHolder = null;
            if (convertView == null) {
                childHolder = new ChildHolder();
                convertView = inflater.inflate(R.layout.childitem, null);

                childHolder.appImage = (ImageView) convertView.findViewById(R.id.appImage);
                childHolder.appName = (TextView) convertView.findViewById(R.id.appName);
                childHolder.appCleanSize = (TextView) convertView.findViewById(R.id.appCleanSize);
                final Button button = (Button) convertView.findViewById(R.id.isSelect);
                final int fgrouppos = groupPosition;
                final int fchildpos = childPosition;

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int selecttemp = childList.get(fgrouppos).get(fchildpos).getSelectState();
                        if (Group.STATE_SELECTED == selecttemp){
                            button.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.common_select_null, 0);
                            childList.get(fgrouppos).get(fchildpos).setSelectState(Group.STATE_NOTSELECTED);
                        } else {
                            button.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.common_select_all, 0);
                            childList.get(fgrouppos).get(fchildpos).setSelectState(Group.STATE_SELECTED);
                        }

                    }
                });

                convertView.setTag(childHolder);
            } else {
                childHolder = (ChildHolder) convertView.getTag();
            }

            childHolder.appName.setText(((Child) getChild(groupPosition, childPosition)).getAppName());
            childHolder.appCleanSize.setText(String.valueOf(((Child) getChild(groupPosition, childPosition)).getCleanSize()) + " MB");
            //int selecttmp = ((Child)getChild(groupPosition, childPosition)).
            //childHolder.isSelect.setBackground(((Child) getChild(groupPosition, childPosition)).getAddress());

            return convertView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
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

    class GroupHolder {
        TextView textView;
        ImageView imageView;
        MultButton multbutton;
    }

    class ChildHolder {
        ImageView appImage;
        TextView appName;
        TextView appCleanSize;
        ImageView selectView;
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
        Group firstVisibleGroup = (Group) adapter.getGroup(firstVisibleGroupPos);
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
