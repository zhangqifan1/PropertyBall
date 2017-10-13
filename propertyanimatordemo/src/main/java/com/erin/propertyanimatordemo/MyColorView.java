package com.erin.propertyanimatordemo;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Created by mamiaomiao on 2017/10/13.
 */

public class MyColorView extends View {
    private float radius=50;
    private MyPoint currenpoint;
    private Paint paint;
    private String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
        paint.setColor(Color.parseColor(color));
        invalidate();
    }
    @Override
    protected void onDraw(Canvas canvas) {
        if(currenpoint==null){
            currenpoint=new MyPoint(radius,radius);
            drawCircle(canvas);
            startAnimation();
        }else{
            drawCircle(canvas);
        }



    }
    private void startAnimation() {
        //MyPoint startPoint = new MyPoint(radius,radius);
       // MyPoint endPoint = new MyPoint(getWidth()/2, getHeight() - radius);
        MyPoint startPoint = new MyPoint(getWidth()/2,radius);
        MyPoint endPoint = new MyPoint(getWidth()/2, getHeight() - radius);
        ValueAnimator anim = ValueAnimator.ofObject(new PontEvaluator(), startPoint, endPoint);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currenpoint = (MyPoint) animation.getAnimatedValue();
                invalidate();
            }
        });
        anim.setInterpolator(new LinearInterpolator());//匀速运动
       // anim.setInterpolator(new AccelerateInterpolator(2));//以加速度为2的速度做自由落体
       // anim.setInterpolator(new BounceInterpolator());//物理效果，自由落体回弹；
       // anim.setInterpolator(new DecelerateAccelerateInterpolator());
        ObjectAnimator anim2 = ObjectAnimator.ofObject(this, "color", new ColorEvaluator(),
                "#0000FF", "#FF0000");

        AnimatorSet animSet = new AnimatorSet();
        animSet.play(anim).with(anim2);
        animSet.setDuration(5000);

        animSet.start();
    }

    private void drawCircle(Canvas canvas){
        float x=currenpoint.getX();
        float y=currenpoint.getY();
        canvas.drawCircle(x,y,radius,paint);

    }
    public MyColorView(Context context) {
        super(context);
        init();
    }

    public MyColorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    public MyColorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init(){
        paint=new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLUE);
    }

}
