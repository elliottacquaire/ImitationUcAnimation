package com.li.imitationuc.behavior.behavior;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class SampleTitleBehavior extends CoordinatorLayout.Behavior<View> {

    private final static String TAG = SampleTitleBehavior.class.getSimpleName();

    // 列表顶部和title底部重合时，列表的滑动距离。
    private float deltaY;

    public SampleTitleBehavior() {
    }

    public SampleTitleBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
    * 其中参数parant代表CoordinatorLayout，child代表使用该Behavior的View，dependency代表要监听的View。这里要监听RecyclerView。
    * */

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency instanceof RecyclerView;
    }

    /**
     * 当RecyclerView的位置变化时，进而改变title的位置。
     * @param parent
     * @param child
     * @param dependency
     * @return
     */
    @Override
    public boolean onDependentViewChanged(@NonNull CoordinatorLayout parent, @NonNull View child, @NonNull View dependency) {
//        return super.onDependentViewChanged(parent, child, dependency);
        if (deltaY == 0){
            deltaY = dependency.getY() - child.getHeight();
            Log.i(TAG,"====dependency.getY()====="+dependency.getY()+"----"+dependency.getX()+"=="+child.getHeight());
        }

        //head 随着滑动而上下移动
        float dy = dependency.getY() - child.getHeight();
        dy = dy < 0 ? 0 : dy;
        float y = -(dy / deltaY) * child.getHeight(); //按照比例设置child 移动的距离，实现两个正好碰头
        child.setTranslationY(y);


        //透明度变化
//        float dy = dependency.getY() - child.getHeight();
//        dy = dy < 0 ? 0 : dy;
//        float alpha = 1 - (dy / deltaY);
//        child.setAlpha(alpha);

        return true;
    }
}
