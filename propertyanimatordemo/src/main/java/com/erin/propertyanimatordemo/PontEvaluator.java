package com.erin.propertyanimatordemo;

import android.animation.TypeEvaluator;

/**
 * Created by mamiaomiao on 2017/10/13.
 */

public class PontEvaluator implements TypeEvaluator {
    @Override
    public Object evaluate(float fraction, Object startValue, Object endValue) {
//fraction代表执行的进度；startValue起始值；endValue结束值；
        //（通过结束值-起始值）*进度=当前值；
        MyPoint startPoint=(MyPoint)startValue;
        MyPoint endPont=(MyPoint)endValue;
        float x=startPoint.getX()+fraction*(endPont.getX()-startPoint.getX());
        float y=startPoint.getY()+fraction*(endPont.getY()-startPoint.getY());
        MyPoint point=new MyPoint(x,y);
        return point;
    }
}
