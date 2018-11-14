package com.li.imitationuc.behavior.behavior;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EasyBehavior extends CoordinatorLayout.Behavior<TextView> {

    public EasyBehavior() {
    }

    public EasyBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //是否是依赖布局 dependency 类型
    //代表寻找被观察View
    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull TextView child, @NonNull View dependency) {
//        return super.layoutDependsOn(parent, child, dependency);
        return dependency instanceof Button;
    }

    //当 dependency(Button)变化的时候，可以对child(TextView)进行操作
    //被观察View变化的时候回调用的方法
    @Override
    public boolean onDependentViewChanged(@NonNull CoordinatorLayout parent, @NonNull TextView child, @NonNull View dependency) {
//        return super.onDependentViewChanged(parent, child, dependency);
//        child.setX(dependency.getX()+200);
//        child.setY(dependency.getY()+250);
//        child.setText(dependency.getX()+",===="+dependency.getY());
        if (dependency.getTop() == 0){
            child.setY(dependency.getHeight() + 20);
        }

        int offsety = dependency.getTop() - child.getTop() ;
        int offsetx = dependency.getLeft() - child.getLeft();
        ViewCompat.offsetTopAndBottom(child,offsety);
        ViewCompat.offsetLeftAndRight(child,offsetx);
        return true;

    }
}
