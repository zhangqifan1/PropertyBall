package com.erin.propertyanimatordemo;

import android.animation.TimeInterpolator;

import static android.R.id.input;

/**
 * Created by mamiaomiao on 2017/10/13.
 */

public class DecelerateAccelerateInterpolator implements TimeInterpolator {
    @Override
    public float getInterpolation(float v) {
        float result;
        if (input <= 0.5) {
            result = (float) (Math.sin(Math.PI * input)) / 2;
        } else {
            result = (float) (2 - Math.sin(Math.PI * input)) / 2;
        }
        System.out.println(result+"  result  ="+input);
        return result;
    }
}
