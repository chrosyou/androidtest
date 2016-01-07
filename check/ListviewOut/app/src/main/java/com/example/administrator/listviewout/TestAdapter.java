package com.example.administrator.listviewout;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Transformation;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/1/6.
 */
public class TestAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private Animation push_left_in;
    List<String> listName;

    public TestAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);

        listName = new ArrayList<String>();
        push_left_in = AnimationUtils.loadAnimation(context, R.anim.left);
    }

    public List<String> getList() {
        return listName;
    }

    public void add(String s) {
        listName.add(s);
    }

    public void remove(int position) {
        listName.remove(position);
    }

    @Override
    public int getCount() {
        return listName.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = new TestListItem(context);
        }

        ((TestListItem)convertView).freshData(listName.get(position));
        return convertView;
    }

    private void deletePattern(final View view, final int position) {

        Animation.AnimationListener al = new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //deleteCustomPattern(mPatternList.get(position));
                //listName.remove(position);
                //notifyDataSetChanged();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        };
        collapse(view, al);

    }

    private void collapse(final View view, Animation.AnimationListener al) {
        final int originHeight = view.getMeasuredHeight();

        Animation animation = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (interpolatedTime == 1.0f) {
                    view.setVisibility(View.GONE);
                } else {
                    view.getLayoutParams().height = originHeight - (int) (originHeight * interpolatedTime);
                    view.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };
        if (al != null) {
            animation.setAnimationListener(al);
        }
        animation.setDuration(300);
        view.startAnimation(animation);
    }

    public void ItemAnim(int position) {
        //deletePattern(getView(position, null, null), position);
        View test = getView(position, null, null);
        int i = test.getMeasuredHeight();

        ObjectAnimator anim = ObjectAnimator.ofFloat(test, "translationX", -200F, 0);
        anim.setDuration(500);
        anim.start();
    }
}
