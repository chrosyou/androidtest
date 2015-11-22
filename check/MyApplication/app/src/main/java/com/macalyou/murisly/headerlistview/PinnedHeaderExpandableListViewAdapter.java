package com.macalyou.murisly.headerlistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by macalyou on 2015/11/22.
 */
public class PinnedHeaderExpandableListViewAdapter extends BaseExpandableListAdapter {
    private Context context;
    private LayoutInflater inflater;

    private ArrayList<Group> groupList;
    private ArrayList<List<Child>> childList;


    public PinnedHeaderExpandableListViewAdapter(Context context) {
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

    public void setChildList(ArrayList<List<Child>> clist) {
        childList = clist;
    }

    public ArrayList<List<Child>> getChildList(){
        return childList;
    }

    public void setGroupList(ArrayList<Group> glist) {
        groupList = glist;
    }

    public ArrayList<Group> getGroupList() {
        return groupList;
    }

    public void setGroupState(int position, int state){
        List<Child> child = childList.get(position);
        for (Child c : child) {
            c.setSelectState(state);
        }
    }

    public int calcGroupState(int position) {
        boolean hasSelect = false;
        boolean hasNoSelect = false;
        List<Child> child = childList.get(position);
        for (int i = 0; i < child.size(); i++) {
            if (Group.STATE_SELECTED == child.get(i).getSelectState()) {
                hasSelect = true;
            }
            else if (Group.STATE_NOTSELECTED == child.get(i).getSelectState()) {
                hasNoSelect = true;
            }

            if (hasSelect && hasNoSelect) {
                return Group.STATE_PARTSELECTED;
            }
        }

        if (hasSelect) {
            return Group.STATE_SELECTED;
        }
        else{
            return Group.STATE_NOTSELECTED;
        }
    }


    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupHolder groupHolder = null;
        final int fgrouppos = groupPosition;

        if (convertView == null) {
            groupHolder = new GroupHolder();
            convertView = inflater.inflate(R.layout.groupitem, null);
            groupHolder.textView = (TextView) convertView.findViewById(R.id.group);
            groupHolder.multbutton = (Button)convertView.findViewById(R.id.multbutton);

            convertView.setTag(groupHolder);
        } else {
            groupHolder = (GroupHolder) convertView.getTag();
        }

        groupHolder.textView.setText(((Group) getGroup(groupPosition)).getTitle());
        groupHolder.multbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selecttemp = groupList.get(fgrouppos).getSelectState();
                if (Group.STATE_SELECTED == selecttemp) {
                    groupList.get(fgrouppos).setSelectState(Group.STATE_NOTSELECTED);
                    setGroupState(fgrouppos, Group.STATE_NOTSELECTED);
                } else {
                    groupList.get(fgrouppos).setSelectState(Group.STATE_SELECTED);
                    setGroupState(fgrouppos, Group.STATE_SELECTED);
                }
                notifyDataSetChanged();
            }
        });

        int selectState = ((Group)getGroup(groupPosition)).getSelectState();
        if (Group.STATE_SELECTED == selectState){
            groupHolder.multbutton.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.common_select_all, 0);
        } else if (Group.STATE_NOTSELECTED == selectState) {
            groupHolder.multbutton.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.common_select_null, 0);
        } else {
            groupHolder.multbutton.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.common_select_mult, 0);
        }

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildHolder childHolder = null;
        final int fgrouppos = groupPosition;
        final int fchildpos = childPosition;

        if (convertView == null) {
            childHolder = new ChildHolder();
            convertView = inflater.inflate(R.layout.childitem, null);

            childHolder.appImage = (ImageView) convertView.findViewById(R.id.appImage);
            childHolder.appName = (TextView) convertView.findViewById(R.id.appName);
            childHolder.appCleanSize = (TextView) convertView.findViewById(R.id.appCleanSize);
            childHolder.selectView = (Button) convertView.findViewById(R.id.isSelect);

            convertView.setTag(childHolder);
        } else {
            childHolder = (ChildHolder) convertView.getTag();
        }

        childHolder.appName.setText(((Child) getChild(groupPosition, childPosition)).getAppName());
        childHolder.appCleanSize.setText(String.valueOf(((Child) getChild(groupPosition, childPosition)).getCleanSize()) + " MB");
        childHolder.selectView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selecttemp = childList.get(fgrouppos).get(fchildpos).getSelectState();
                if (Group.STATE_SELECTED == selecttemp) {
                    childList.get(fgrouppos).get(fchildpos).setSelectState(Group.STATE_NOTSELECTED);
                } else {
                    childList.get(fgrouppos).get(fchildpos).setSelectState(Group.STATE_SELECTED);
                }
                groupList.get(fgrouppos).setSelectState(calcGroupState(fgrouppos));
                notifyDataSetChanged();
            }
        });
        //int selecttmp = ((Child)getChild(groupPosition, childPosition)).
        //childHolder.isSelect.setBackground(((Child) getChild(groupPosition, childPosition)).getAddress());
        int selectState = ((Child)getChild(groupPosition, childPosition)).getSelectState();
        if (Group.STATE_SELECTED == selectState){
            childHolder.selectView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.common_select_all, 0);
        } else {
            childHolder.selectView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.common_select_null, 0);
        }
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    class GroupHolder {
        TextView textView;
        ImageView imageView;
        Button multbutton;
    }

    class ChildHolder {
        ImageView appImage;
        TextView appName;
        TextView appCleanSize;
        Button selectView;
    }
}

