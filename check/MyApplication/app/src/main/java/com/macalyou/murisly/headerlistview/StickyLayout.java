/**
The MIT License (MIT)

Copyright (c) 2014 singwhatiwanna
https://github.com/singwhatiwanna
http://blog.csdn.net/singwhatiwanna

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

package com.macalyou.murisly.headerlistview;

import java.util.NoSuchElementException;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.LinearLayout;
import android.widget.TextView;


public class StickyLayout extends LinearLayout {
    private static final String TAG = "StickyLayout";
    private static final boolean DEBUG = true;

    public interface OnGiveUpTouchEventListener {
        public boolean giveUpTouchEvent(MotionEvent event);
    }

    private View mHeader;
    private View mContent;
    private OnGiveUpTouchEventListener mGiveUpTouchEventListener;

    // header的高度  单位：px
    private int mOriginalHeaderHeight;
    private int mHeaderHeight;
    //头部最小的高度
    private int mMinHeaderHeight = 50;
    //计算头部的高度，用于判断向那边滑动
    private int mSlideHeight = 0;

    private int mStatus = STATUS_EXPANDED;
    public static final int STATUS_EXPANDED = 1;
    public static final int STATUS_COLLAPSED = 2;

    //判断是否滑动的距离
    private int mTouchSlop;

    // 分别记录上次滑动的坐标
    private int mLastX = 0;
    private int mLastY = 0;

    // 分别记录上次滑动的坐标(onInterceptTouchEvent)
    private int mLastXIntercept = 0;
    private int mLastYIntercept = 0;

    // 用来控制滑动角度，仅当角度a满足如下条件才进行滑动：tan a = deltaX / deltaY > 2
    private static final int TAN = 2;

    //字体动画总时间
    private int mAnimationInterval = 200;
    //字体动画绘制间隔
    private int mDrawInterval = 15;

    private boolean mIsSticky = true;
    private boolean mInitDataSucceed = false;
    private boolean mDisallowInterceptTouchEventOnHeader = true;

    public StickyLayout(Context context) {
        super(context);
    }

    public StickyLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public StickyLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        if (hasWindowFocus && (mHeader == null || mContent == null)) {
            initData();
        }
    }

    private void initData() {
        int headerId= getResources().getIdentifier("sticky_header", "id", getContext().getPackageName());
        int contentId = getResources().getIdentifier("sticky_content", "id", getContext().getPackageName());
        if (headerId != 0 && contentId != 0) {
            mHeader = findViewById(headerId);
            mContent = findViewById(contentId);
            mOriginalHeaderHeight = mHeader.getMeasuredHeight();
            mHeaderHeight = mOriginalHeaderHeight;
            mTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
            if (mHeaderHeight > 0) {
                mInitDataSucceed = true;
            }
            if (DEBUG) {
                Log.d(TAG, "mTouchSlop = " + mTouchSlop + "mHeaderHeight = " + mHeaderHeight);
            }
        } else {
            throw new NoSuchElementException("Did your view with id \"sticky_header\" or \"sticky_content\" exists?");
        }
    }

    public void setOnGiveUpTouchEventListener(OnGiveUpTouchEventListener l) {
        mGiveUpTouchEventListener = l;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        int intercepted = 0; // 0表示向下发送消息，1本层消耗
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()) {
        case MotionEvent.ACTION_DOWN: {
            mLastXIntercept = x;
            mLastYIntercept = y;
            mLastX = x;
            mLastY = y;
            mSlideHeight = y;
            intercepted = 0;
            break;
        }
        case MotionEvent.ACTION_MOVE: {
            int deltaX = x - mLastXIntercept;
            int deltaY = y - mLastYIntercept;
            if (mDisallowInterceptTouchEventOnHeader && y <= getHeaderHeight()) {
                intercepted = 0;
            } else if (Math.abs(deltaY) <= Math.abs(deltaX)) {
                intercepted = 0;
            } else if (mStatus == STATUS_EXPANDED && deltaY <= -mTouchSlop) {
                intercepted = 1;
            } else if (mGiveUpTouchEventListener != null) {
                if (mGiveUpTouchEventListener.giveUpTouchEvent(event) && deltaY >= mTouchSlop) {
                    intercepted = 1;
                }
            }
            break;
        }
        case MotionEvent.ACTION_UP: {
            intercepted = 0;
            mLastXIntercept = mLastYIntercept = 0;
            break;
        }
        default:
            break;
        }

        if (DEBUG) {
            Log.d(TAG, "intercepted=" + intercepted);
        }
        return intercepted != 0 && mIsSticky;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!mIsSticky) {
            return true;
        }
        int x = (int) event.getX();
        int y = (int) event.getY();
        int deltaX = x - mLastX;
        int deltaY = y - mLastY;

        switch (event.getAction()) {
        case MotionEvent.ACTION_DOWN: {
            break;
        }
        case MotionEvent.ACTION_MOVE: {
            if (DEBUG) {
                Log.d(TAG, "mHeaderHeight=" + mHeaderHeight + "  deltaY=" + deltaY + "  mlastY=" + mLastY);
            }
            mHeaderHeight += deltaY;
            setHeaderHeight(mHeaderHeight);
            break;
        }
        case MotionEvent.ACTION_UP: {
            // 这里做了下判断，当松开手的时候，会自动向两边滑动，具体向哪边滑，要看当前所处的位置
            int destHeight = mMinHeaderHeight;
            if (y - mSlideHeight < 0) {
                destHeight = mMinHeaderHeight;
                mStatus = STATUS_COLLAPSED;
            }
            else if (y - mSlideHeight > 0){
                destHeight = mOriginalHeaderHeight;
                mStatus = STATUS_EXPANDED;
            }
            // 慢慢滑向终点
            this.smoothSetHeaderHeight(mHeaderHeight, destHeight, mAnimationInterval);
            break;
        }
        default:
            break;
        }
        mLastX = x;
        mLastY = y;
        return true;
    }

    public void smoothSetHeaderHeight(final int from, final int to, long duration) {
        smoothSetHeaderHeight(from, to, duration, false);
    }

    public void smoothSetHeaderHeight(final int from, final int to, long duration, final boolean modifyOriginalHeaderHeight) {
        final int frameCount = ((int) (mAnimationInterval / mDrawInterval) + 1) ;
        final float partation = (to - from) / (float) frameCount ;

        new Thread("Thread#smoothSetHeaderHeight") {

            @Override
            public void run() {
                for (int i = 0; i < frameCount; i++) {
                    final int height;
                    if (i == frameCount - 1) {
                        height = to;
                    } else {
                        height = (int) (from + partation * i);
                        //采用正弦曲线
                        //height = (int) (from + (to - from) * Math.sin((double)i / frameCount * (Math.PI / 2)));
                    }
                    post(new Runnable() {
                        public void run() {
                            setHeaderHeight(height);
                        }
                    });
                    try {
                        sleep(mDrawInterval);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                if (modifyOriginalHeaderHeight) {
                    setOriginalHeaderHeight(to);
                }
            };

        }.start();
    }

    public void setOriginalHeaderHeight(int originalHeaderHeight) {
        mOriginalHeaderHeight = originalHeaderHeight;
    }

    public void setHeaderHeight(int height, boolean modifyOriginalHeaderHeight) {
        if (modifyOriginalHeaderHeight) {
            setOriginalHeaderHeight(height);
        }
        setHeaderHeight(height);
    }

    public void setHeaderHeight(int height) {
        if (!mInitDataSucceed) {
            initData();
        }

        if (DEBUG) {
            Log.d(TAG, "setHeaderHeight height=" + height);
        }
        if (height <= mMinHeaderHeight) {
            height = mMinHeaderHeight;
        } else if (height > mOriginalHeaderHeight) {
            height = mOriginalHeaderHeight;
        }

        if (height == mMinHeaderHeight) {
            mStatus = STATUS_COLLAPSED;
        } else {
            mStatus = STATUS_EXPANDED;
        }

        if (mHeader != null && mHeader.getLayoutParams() != null) {
            mHeader.getLayoutParams().height = height;
            TextView tvTitle = (TextView)mHeader.findViewById(R.id.tvTitle);
            tvTitle.setTextSize((int)(height * 0.4));
            mHeader.requestLayout();
            mHeaderHeight = height;
        } else {
            if (DEBUG) {
                Log.e(TAG, "null LayoutParams when setHeaderHeight");
            }
        }
    }

    public int getHeaderHeight() {
        return mHeaderHeight;
    }

    public void setSticky(boolean isSticky) {
        mIsSticky = isSticky;
    }

    public void requestDisallowInterceptTouchEventOnHeader(boolean disallowIntercept) {
        mDisallowInterceptTouchEventOnHeader = disallowIntercept;
    }

}