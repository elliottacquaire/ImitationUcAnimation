package com.li.imitationuc.behavior;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.li.imitationuc.R;

public class TestBehaviorActivity extends AppCompatActivity {

    private final static String TAG = TestBehaviorActivity.class.getSimpleName();

    private static final int MAX_TOUCH_POINT = 4; // 最多监听4个触点；

    private Button dependencyButton ;
    private ImageView img ;

    //初始触摸点x,y 值
    private int initX = 0;
    private int initY = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_behavior);

        dependencyButton = (Button)findViewById(R.id.dependencyButton);
        img = (ImageView)findViewById(R.id.img);

        img.setOnTouchListener(shopCarSettleTouch);

        dependencyButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                DisplayMetrics dm = getResources().getDisplayMetrics();
                int screenWidth = dm.widthPixels;
//            int screenHeight = dm.heightPixels - 100;//需要减掉图片的高度
                int screenHeight = dm.heightPixels;//需要减掉图片的高度

                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        Log.i(TAG,"---------------ACTION_DOWN");
                        Log.i(TAG,"---------------"+event.getRawX()+"==="+event.getRawY());
                        initX = (int)event.getRawX();
                        initY = (int)event.getRawY();

                        break;
                    case MotionEvent.ACTION_MOVE:
                        Log.i(TAG,"---------------ACTION_MOVE");
//                        v.setX(event.getRawX()-v.getWidth()/2);
//                        v.setY(event.getRawY()-v.getHeight()/2);

//                        v.setX(v.getLeft()+event.getRawX()-initX);
//                        v.setY(v.getTop()+event.getRawY()-initY);

                        int dx = (int)event.getRawX() - initX;
                        int dy = (int)event.getRawY() - initY;

                        int l = v.getLeft() + dx;
                        int b = v.getBottom() + dy;
                        int r = v.getRight() + dx;
                        int t = v.getTop() +dy;

                        //下面判断移动是否超出屏幕
                        if (l < 0) {
                            l = 0;
                            r = l + v.getWidth();
                        }
                        if (t < 0) {
                            t = 0;
                            b = t + v.getHeight();
                        }
                        if (r > screenWidth) {
                            r = screenWidth;
                            l = r - v.getWidth();
                        }
                        if (b > screenHeight) {
                            b = screenHeight;
                            t = b - v.getHeight();
                        }

//                        v.layout(l,t,r,b);

                        Log.i(TAG,"---------------ACTION_MOVE"+l+"=="+t+"=="+r+"=="+b);

//                        initX = (int)event.getRawX();
//                        initY = (int)event.getRawY();

//                        v.postInvalidate();
//                        v.invalidate();
                        ViewCompat.offsetTopAndBottom(v,dy);
                        ViewCompat.offsetLeftAndRight(v,dx);
                        initY = (int)event.getRawY();
                        initX = (int)event.getRawX();

                        break;
                    case MotionEvent.ACTION_UP:
                        Log.i(TAG,"---------------ACTION_UP");
                        Log.i(TAG,"---------------"+event.getRawX()+"==="+event.getRawY());
                        break;
                }

                return true;
            }
        });

    }


    // 获取当前所有触摸点的位置；
    public static Point[] getCurrentPoints(MotionEvent event){
        int pointerCount = event.getPointerCount();
        if (pointerCount > MAX_TOUCH_POINT) {
            pointerCount = MAX_TOUCH_POINT;
        }
        Point[] points = new Point[pointerCount];
        for (int i = 0; i < pointerCount; i++) {
            points[i] = new Point((int) event.getX(i), (int) event.getY(i));
        }

        return points;
    }

    // 判断一组触摸点是否在 view上；
    public static boolean isTouchPointInView(View view, Point[] points) {
        if (view == null && points == null) {
            throw new NullPointerException();
        }

        int len = points.length;

        boolean result = false;

        for(int i = 0; i < len; i++) {
            if (isTouchPointInView(view, points[i])) {
                result = true;
                break;
            }
        }
        return result;
    }


    // 判断一个具体的触摸点是否在 view 上；
    public static boolean isTouchPointInView(View view, Point point) {
        if (view == null && point == null) {
            throw new NullPointerException();
        }

        int[] location = new int[2];
        view.getLocationOnScreen(location);
        int left = location[0];
        int top = location[1];
        int right = left + view.getMeasuredWidth();
        int bottom = top + view.getMeasuredHeight();
        if (point.x >= left && point.x <= right && point.y >= top && point.y <= bottom) {
            return true;
        }
        return false;
    }

    //(x,y)是否在view的区域内
    private boolean isTouchPointInView(View view, int x, int y) {
        if (view == null) {
            return false;
        }
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        int left = location[0];
        int top = location[1];
        int right = left + view.getMeasuredWidth();
        int bottom = top + view.getMeasuredHeight();
        //view.isClickable() &&
        if (y >= top && y <= bottom && x >= left
                && x <= right) {
            return true;
        }
        return false;
    }

    private View.OnTouchListener shopCarSettleTouch = new View.OnTouchListener() {
        int lastX, lastY;

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            int ea = event.getAction();
            DisplayMetrics dm = getResources().getDisplayMetrics();
            int screenWidth = dm.widthPixels;
//            int screenHeight = dm.heightPixels - 100;//需要减掉图片的高度
            int screenHeight = dm.heightPixels;//需要减掉图片的高度
            switch (ea) {
                case MotionEvent.ACTION_DOWN:
                    lastX = (int) event.getRawX();//获取触摸事件触摸位置的原始X坐标
                    lastY = (int) event.getRawY();
                case MotionEvent.ACTION_MOVE:
                    //event.getRawX();获得移动的位置
                    int dx = (int) event.getRawX() - lastX;
                    int dy = (int) event.getRawY() - lastY;
                    int l = v.getLeft() + dx;
                    int b = v.getBottom() + dy;
                    int r = v.getRight() + dx;
                    int t = v.getTop() + dy;

                    //下面判断移动是否超出屏幕
                    if (l < 0) {
                        l = 0;
                        r = l + v.getWidth();
                    }
                    if (t < 0) {
                        t = 0;
                        b = t + v.getHeight();
                    }
                    if (r > screenWidth) {
                        r = screenWidth;
                        l = r - v.getWidth();
                    }
                    if (b > screenHeight) {
                        b = screenHeight;
                        t = b - v.getHeight();
                    }
                    v.layout(l, t, r, b);
                    Log.e(TAG, "onTouch: " + l + "==" + t + "==" + r + "==" + b);
                    lastX = (int) event.getRawX();
                    lastY = (int) event.getRawY();
//                    v.postInvalidate();
                    break;
                case MotionEvent.ACTION_UP:
                    break;
            }
            return true;
        }
    };

    }
