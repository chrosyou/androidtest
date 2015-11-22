package com.macalyou.murisly.headerlistview;

public class Group {
    public static final int STATE_SELECTED = 1;    //全选
    public static final int STATE_NOTSELECTED = 2; //全不选
    public static final int STATE_PARTSELECTED = 3;//部分选

    private String title;
    private int selectState;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSelectState() { return selectState; }

    public void setSelectState(int selectstate) { this.selectState = selectstate; }
}
