package com.li.imitationuc.behavior.customhead;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.li.imitationuc.R;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;

public class BuyerRunHeader extends LinearLayout implements RefreshHeader {

    ImageView mIvBack1;
    ImageView mIvBack2;
    ImageView mIvIcon;

    private Animation mBackAnim1;
    private Animation mBackAnim2;
    private AnimationDrawable mAnimationDrawable;
    private boolean isRunAnimation = false;
    private int limitX;

    public BuyerRunHeader(Context context) {
        super(context);
        initView(context);
    }

    public BuyerRunHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public BuyerRunHeader(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context){
       View view = LayoutInflater.from(context).inflate(R.layout.layout_buyer_run,this);
        mIvBack1 = (ImageView) findViewById(R.id.iv_background_1);
        mIvBack2 = (ImageView) findViewById(R.id.iv_background_2);
        mIvIcon = (ImageView) findViewById(R.id.iv_refresh_icon);

        mAnimationDrawable = (AnimationDrawable) mIvIcon.getDrawable();
        mBackAnim1 = AnimationUtils.loadAnimation(getContext(), R.anim.first_trans_in);
        mBackAnim2 = AnimationUtils.loadAnimation(getContext(), R.anim.second_trans_out);

    }


    @NonNull
    @Override
    public View getView() {
        return this;
    }

    @NonNull
    @Override
    public SpinnerStyle getSpinnerStyle() {
        return SpinnerStyle.Translate;
    }

    @Override
    public void setPrimaryColors(int... colors) {

    }

    @Override
    public void onInitialized(@NonNull RefreshKernel kernel, int height, int maxDragHeight) {

    }

    @Override
    public void onMoving(boolean isDragging, float percent, int offset, int height, int maxDragHeight) {

    }

    @Override
    public void onReleased(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {

    }

    @Override
    public void onStartAnimator(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {
        startAnimation();
    }

    @Override
    public int onFinish(@NonNull RefreshLayout refreshLayout, boolean success) {
        stopAnimation();
        return 1000;
    }

    @Override
    public void onHorizontalDrag(float percentX, int offsetX, int offsetMax) {

    }

    @Override
    public boolean isSupportHorizontalDrag() {
        return false;
    }

    @Override
    public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState oldState, @NonNull RefreshState newState) {

    }

    /**
     * 开始刷新动画。
     */
    private void startAnimation() {
        if (!isRunAnimation) {
            isRunAnimation = true;
            mIvBack1.startAnimation(mBackAnim1);
            mIvBack2.startAnimation(mBackAnim2);
            mIvIcon.setImageDrawable(mAnimationDrawable);
            mAnimationDrawable.start();
        }
    }

    /**
     * 停止刷新动画。
     */
    private void stopAnimation() {
        if (isRunAnimation) {
            isRunAnimation = false;
            mIvBack1.clearAnimation();
            mIvBack2.clearAnimation();
            mAnimationDrawable.stop();
        }
    }

}
