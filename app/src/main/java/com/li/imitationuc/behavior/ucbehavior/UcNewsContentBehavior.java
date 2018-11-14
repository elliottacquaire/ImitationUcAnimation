package com.li.imitationuc.behavior.ucbehavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import com.li.imitationuc.BuildConfig;
import com.li.imitationuc.ImitationUCApplication;
import com.li.imitationuc.R;
import com.li.imitationuc.behavior.ucbehavior.helper.HeaderScrollingViewBehavior;

import java.util.List;




/**
 * 可滚动的新闻列表Behavior
 * <p/>
 * Created by chensuilun on 16/7/24.
 */
public class UcNewsContentBehavior extends HeaderScrollingViewBehavior {
    private static final String TAG = "UcNewsContentBehavior";
    private float lastX, lastY;

    public UcNewsContentBehavior() {
    }

    public UcNewsContentBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return isDependOn(dependency);
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "onDependentViewChanged");
        }
        offsetChildAsNeeded(parent, child, dependency);
        return false;
    }

    private void offsetChildAsNeeded(CoordinatorLayout parent, View child, View dependency) {
        child.setTranslationY((int) (-dependency.getTranslationY() / (getHeaderOffsetRange() * 1.0f) * getScrollRange(dependency)));

    }

    @Override
    public boolean onInterceptTouchEvent(CoordinatorLayout parent, View child, MotionEvent ev) {
        if (ev.getActionMasked() == MotionEvent.ACTION_DOWN) {
            lastX = ev.getX();
            lastY = ev.getY();
        }

        if (ev.getActionMasked() == MotionEvent.ACTION_MOVE) {
            float currX = ev.getX();
            float currY = ev.getY();
            boolean horizontalScroll = Math.abs(currX - lastX) / Math.abs(currY - lastY) > 1;
            if (horizontalScroll && child.getTranslationY() == 0)
                return true;
        }

        return super.onInterceptTouchEvent(parent, child, ev);
    }

    @Override
    protected View findFirstDependency(List<View> views) {
        for (int i = 0, z = views.size(); i < z; i++) {
            View view = views.get(i);
            if (isDependOn(view))
                return view;
        }
        return null;
    }

    @Override
    protected int getScrollRange(View v) {
        if (isDependOn(v)) {
            return Math.max(0, v.getMeasuredHeight() - getFinalHeight());
        } else {
            return super.getScrollRange(v);
        }
    }

    private int getHeaderOffsetRange() {
        return ImitationUCApplication.getInstance().getResources().getDimensionPixelOffset(R.dimen.uc_news_header_pager_offset);
    }

    private int getFinalHeight() {
        return ImitationUCApplication.getInstance().getResources().getDimensionPixelOffset(R.dimen.uc_news_tabs_height)
                + ImitationUCApplication.getInstance().getResources().getDimensionPixelOffset(R.dimen.uc_news_header_title_height);
    }


    private boolean isDependOn(View dependency) {
        return dependency != null && dependency.getId() == R.id.id_uc_news_header_pager;
    }
}
