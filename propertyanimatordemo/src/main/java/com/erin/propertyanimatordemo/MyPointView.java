package com.erin.propertyanimatordemo;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by mamiaomiao on 2017/10/13.
 */

public class MyPointView extends View {
    private float radius=50;
    private MyPoint currenpoint;
    private Paint paint;

    @Override
    protected void onDraw(Canvas canvas) {
        if(currenpoint==null){
            currenpoint=new MyPoint(radius,radius);//屏幕左上角
            drawCircle(canvas);
            startAnimation();
        }else{
            drawCircle(canvas);
        }



    }

    private void startAnimation(){
        MyPoint startPoint=new MyPoint(radius,radius);
        MyPoint endPoint=new MyPoint(getWidth()-radius,getHeight()-radius);
        ValueAnimator animator=ValueAnimator.ofObject(new PontEvaluator(),startPoint,endPoint);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                currenpoint=(MyPoint)valueAnimator.getAnimatedValue();
                invalidate();
            }
        });
        animator.setDuration(5000);
        animator.start();
    }

private void drawCircle(Canvas canvas){
    float x=currenpoint.getX();
    float y=currenpoint.getY();
    canvas.drawCircle(x,y,radius,paint);

}
    public MyPointView(Context context) {
        super(context);
        init();
    }

    public MyPointView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    public MyPointView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init(){
       paint=new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLUE);
    }

}
