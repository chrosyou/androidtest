package com.example.administrator.listviewout;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.ListView;

import java.util.List;

/**
 * Created by Administrator on 2016/1/7.
 */


public class AnimationBuilde {
    public static final int ANIMATION_DURATION = 200;
    protected static final long DEFAULTANIMATIONDELAYMILLIS = 150;

    public static AnimatorSet buildListRemoveAnimator(final View view, final List list,
                                                      final TestAdapter adapter, final int index) {
        final int height = view.getMeasuredHeight();
        Animator.AnimatorListener al = new Animator.AnimatorListener() {

            @Override
            public void onAnimationStart(Animator animation) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                // TODO Auto-generated method stub
                list.remove(index);
                view.setAlpha(1);
                //view.getLayoutParams().height = height;
                //RecyclerView.ViewHolder vh = (RecyclerView.ViewHolder) view.getTag();
                //vh.needInflate = true;

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                // TODO Auto-generated method stub

            }
        };


        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator anim = ObjectAnimator.ofFloat(view, "translationX", 0, 90);
        ObjectAnimator animb = ObjectAnimator.ofFloat(view, "alpha", 1, 0);
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // TODO Auto-generated method stub
                if (animation.getAnimatedFraction() >= 1) {
                    //view.setVisibility(View.GONE);
                    //list.remove(index);
                    //adapter.notifyDataSetChanged();
                } else {
                    //view.getLayoutParams().height = height - (int) (height * animation.getAnimatedFraction());
                    //view.requestLayout();
                }
            }
        });

        anim.setDuration(ANIMATION_DURATION);
        animb.setDuration(ANIMATION_DURATION);
        valueAnimator.setDuration(ANIMATION_DURATION + ANIMATION_DURATION + 100);
        animatorSet.playTogether(anim, animb, valueAnimator);
        animatorSet.addListener(al);
        return animatorSet;
    }

    public static AnimatorSet buildShowAnimatorList(ViewGroup parent, ListView list, View view, long mAnimationStartMillis,
                                                    int mLastAnimatedPosition, int mFirstAnimatedPosition) {
        if (mAnimationStartMillis == -1) {
            mAnimationStartMillis = System.currentTimeMillis();
        }
        //ViewHelper.setAlpha(view, 0);
        Animator alphaAnimator = ObjectAnimator.ofFloat(view, "alpha", 0, 1);
        Animator rx = ObjectAnimator.ofFloat(view, "rotationX", -90, 0);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(alphaAnimator, rx);
        set.setStartDelay(calculateAnimationDelay(list, mLastAnimatedPosition, mFirstAnimatedPosition, mAnimationStartMillis));
        set.setDuration(DEFAULTANIMATIONDELAYMILLIS);
        set.start();
        return set;
    }

    private static long calculateAnimationDelay(ListView list, int last, int first, long starmill) {
        long delay;

        int lastVisiblePosition = list.getLastVisiblePosition();
        int firstVisiblePosition = list.getFirstVisiblePosition();

        int numberOfItemsOnScreen = lastVisiblePosition - firstVisiblePosition;
        int numberOfAnimatedItems = last - first;

        if (numberOfItemsOnScreen + 1 < numberOfAnimatedItems) {
            delay = DEFAULTANIMATIONDELAYMILLIS;

        } else {
            long delaySinceStart = (last - first + 1)
                    * DEFAULTANIMATIONDELAYMILLIS;
            delay = starmill + DEFAULTANIMATIONDELAYMILLIS + delaySinceStart
                    - System.currentTimeMillis();
        }
        return Math.max(0, delay);
    }
}

