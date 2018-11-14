package com.li.imitationuc.behavior.behavior;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.li.imitationuc.R;

public class SampleHeaderBehavior extends CoordinatorLayout.Behavior<View> {

    private boolean upReach;
    private boolean downReach;

    private int lastPosition = -1;


    public SampleHeaderBehavior() {
    }

    public SampleHeaderBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(@NonNull CoordinatorLayout parent, @NonNull View child, @NonNull MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                upReach = false;
                downReach = false;
                break;
        }
        return super.onInterceptTouchEvent(parent, child, ev);
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
//        return super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, axes, type);
        return (axes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0 ;

    }

    @Override
    public boolean onDependentViewChanged(@NonNull CoordinatorLayout parent, @NonNull View child, @NonNull View dependency) {
//        return super.onDependentViewChanged(parent, child, dependency);
//        return dependency instanceof RecyclerView && child instanceof TextView ;
        return child.getId() == R.id.recyclerView;

    }

    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type);

        if (target instanceof RecyclerView){
            RecyclerView recyclerView = (RecyclerView)target;
            int pos = ((LinearLayoutManager)recyclerView.getLayoutManager()).findFirstCompletelyVisibleItemPosition();
            if (pos == 0 && pos < lastPosition){
                downReach = true;
            }

            if (canScroll(child,dy) && pos == 0){
                float finalY = child.getTranslationY() - dy ;
                if (finalY < - child.getHeight()){
                    finalY = -child.getHeight();
                    upReach = true ;
                }else if (finalY > 0){
                    finalY = 0 ;
                }
                child.setTranslationY(finalY);
                consumed[1] = dy ;
            }
            lastPosition = pos ;

        }
    }

    private boolean canScroll(View view,float scrollY){
        if (scrollY > 0 && view.getTranslationY() == -view.getHeight() && !upReach){
            return false;
        }
        if (downReach){
            return  false;
        }
        return true;
    }
}
