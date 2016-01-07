package com.example.administrator.listviewout;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.view.animation.Transformation;
import android.view.animation.TranslateAnimation;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import junit.framework.Test;

public class MainActivity extends Activity {

    private ListView listviewout;
    private Button testbt;
    TestAdapter test;
    private int durtime = 100;
    AnimationSet as = new AnimationSet(true);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listviewout = (ListView)findViewById(R.id.listviewout);
        testbt = (Button)findViewById(R.id.buttontest);
        testbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlphaAnimation aa = new AlphaAnimation(1, 1);
                aa.setDuration(durtime);

                TranslateAnimation ta = new TranslateAnimation(0, 500, 0, 0);
                ta.setDuration(durtime);


                ta.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        test.remove(0);
                        test.notifyDataSetChanged();

//                        View tempview = listviewout.getChildAt(0);
//                        if (tempview != null) {
//                            tempview.startAnimation(as);
//                        }
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

                as.addAnimation(aa);
                as.addAnimation(ta);

                View tempview = listviewout.getChildAt(0);
                //View tempview = test.getView(0, null, null);
                if (tempview != null) {
                    tempview.startAnimation(as);
                }
            }
        });

        String[] items = {"1", "2", "3"};
        //ArrayAdapter tempAdapter = new ArrayAdapter(this, R.layout.item, items);
        test = new TestAdapter(this);
        test.add("1");
        test.add("2");
        test.add("3");
        test.add("4");
        test.add("5");
        test.add("6");


        /*
        //通过加载XML动画设置文件来创建一个Animation对象；
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.slide_right);   //得到一个LayoutAnimationController对象；
        LayoutAnimationController controller = new LayoutAnimationController(animation);   //设置控件显示的顺序；
        controller.setOrder(LayoutAnimationController.ORDER_REVERSE);   //设置控件显示间隔时间；
        controller.setDelay((float) 0.30);   //为ListView设置LayoutAnimationController属性；
        listviewout.setLayoutAnimation(controller);
        listviewout.startLayoutAnimation();
*/

//        int duration=300;
//        AnimationSet set = new AnimationSet(true);
//
//        Animation animation = new AlphaAnimation(1f, 0f);
//        animation.setDuration(duration);
//        set.addAnimation(animation);
//
//
//        animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 2f, Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0.0f);
//        animation.setDuration(duration);
//        set.addAnimation(animation);
//
//        LayoutAnimationController controller = new LayoutAnimationController(set, 0.5f);
//        controller.setOrder(LayoutAnimationController.ORDER_NORMAL);
//        listviewout.setLayoutAnimation(controller);

        listviewout.setAdapter(test);
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

    private void deleteCell(final View v, final int index) {
        if (v == null) return;

        AnimatorSet animatorSet = AnimationBuilde.buildListRemoveAnimator(v, test.getList(), test, index);
        animatorSet.start();
    }
}
