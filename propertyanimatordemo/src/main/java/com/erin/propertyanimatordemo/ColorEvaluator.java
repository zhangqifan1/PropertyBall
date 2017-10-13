package com.erin.propertyanimatordemo;

import android.animation.TypeEvaluator;

/**
 * Created by mamiaomiao on 2017/10/13.
 */

public class ColorEvaluator implements TypeEvaluator {
    private int mRed=-1,mGreen=-1,mBlue=-1;

    @Override
    public Object evaluate(float fraction, Object startValue, Object endValue) {
        // "#0000FF", "#FF0000"
        String startColor=(String)startValue;
        String endColor=(String)endValue;
        int startRed=Integer.parseInt(startColor.substring(1,3),16);
        int startGreen = Integer.parseInt(startColor.substring(3, 5), 16);
        int startBlue = Integer.parseInt(startColor.substring(5, 7), 16);
        int endRed = Integer.parseInt(endColor.substring(1, 3), 16);
        int endGreen = Integer.parseInt(endColor.substring(3, 5), 16);
        int endBlue = Integer.parseInt(endColor.substring(5, 7), 16);
        //初始化颜色的值
        if(mRed==-1){
            mRed=startRed;
        }
        if(mGreen==-1){
            mGreen=startGreen;
        }
        if(mBlue==-1){
            mBlue=startBlue;
        }
        //计算初始颜色和结束颜色之间的差值
        int redDiff=Math.abs(startRed-endRed);
        int greenDiff=Math.abs(startGreen-endGreen);
        int blueDiff=Math.abs(startBlue-endBlue);
        int colorDiff=redDiff+greenDiff+blueDiff;
        if(mRed!=endRed){
            mRed=getCurrentColor(startRed,endRed,colorDiff,0,fraction);
        }else if(mGreen!=endGreen){
            mGreen=getCurrentColor(startGreen,endGreen,colorDiff,redDiff,fraction);
        }else if(mBlue!=endBlue){
            mBlue=getCurrentColor(startBlue,endBlue,colorDiff,redDiff+greenDiff,fraction);
        }
        // 将计算出的当前颜色的值组装返回
        String currentColor = "#" + getHexString(mRed)
                + getHexString(mGreen) + getHexString(mBlue);
        return currentColor;
    }


    /**
     * 根据fraction值来计算当前的颜色。
     */
    private int getCurrentColor(int startColor, int endColor, int colorDiff,
                                int offset, float fraction) {
        int currentColor;
        if (startColor > endColor) {
            currentColor = (int) (startColor - (fraction * colorDiff - offset));
            if (currentColor < endColor) {
                currentColor = endColor;
            }
        } else {
            currentColor = (int) (startColor + (fraction * colorDiff - offset));
            if (currentColor > endColor) {
                currentColor = endColor;
            }
        }
        return currentColor;
    }

    /**
     * 将10进制颜色值转换成16进制。
     */
    private String getHexString(int value) {
        String hexString = Integer.toHexString(value);
        if (hexString.length() == 1) {
            hexString = "0" + hexString;
        }
        return hexString;
    }

}


