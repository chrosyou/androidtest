package com.macalyou.murisly.listview;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;

/**
 * Created by Arthur.J on 2015/9/23.
 */
public class PinableFrameLayout extends FrameLayout {

    private View mPinnedView;
    private ViewGroup mPinnedViewParent;
    private FrameLayout mTopViewFrameLayout;

    public PinableFrameLayout(Context context) { this(context, null); }
    public PinableFrameLayout(Context context, AttributeSet attrs) { this(context, attrs, 0); }
    public PinableFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    public void initFrame() {
        // create top frame view
        mTopViewFrameLayout = new FrameLayout( getContext() );
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        mTopViewFrameLayout.setLayoutParams(lp);
        mTopViewFrameLayout.setBackgroundResource(android.R.color.transparent);
        mTopViewFrameLayout.setVisibility( INVISIBLE );
        addView(mTopViewFrameLayout);
    }

    public void setPinnedView( View view ) {
        // recover original view
        pinView( true );
        // cache the new view
        mPinnedView = view;
        mPinnedViewParent = (ViewGroup) mPinnedView.getParent();
    }

    public void onScroll() {
        if ( mPinnedView == null ) return;

        int[] posPinnedView = new int[2];
        mPinnedViewParent.getLocationInWindow( posPinnedView );
        int[] posLayout = new int[2];
        getLocationInWindow(posLayout);
        pinView( posPinnedView[1] > posLayout[1] );

/*        int topPinnedView = mPinnedView.getTop();
        int topLayout = mTopViewFrameLayout.getTop();
        pinView( topPinnedView >= topLayout );*/
    }

    private void pinView( boolean recover ) {
        if ( mPinnedView == null ) return;

        if ( recover ) {
            if ( mTopViewFrameLayout != null && mTopViewFrameLayout.getChildCount() > 0 ) {
                mTopViewFrameLayout.removeAllViews();
                mTopViewFrameLayout.setVisibility(View.INVISIBLE);
            }
            if ( mPinnedViewParent != null && mPinnedViewParent.getChildCount() == 0 ) {
                mPinnedViewParent.addView( mPinnedView );
            }
        }
        else {
            if ( mPinnedViewParent != null && mPinnedViewParent.getChildCount() > 0 ) {
                mPinnedViewParent.removeAllViews();
            }
            if ( mTopViewFrameLayout != null && mTopViewFrameLayout.getChildCount() == 0 ) {
                mTopViewFrameLayout.addView(mPinnedView);
                mTopViewFrameLayout.setVisibility( View.VISIBLE );
            }
        }
    }
}
