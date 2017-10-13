package com.erin.propertyanimatordemo;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView=(TextView)findViewById(R.id.mytest);
        ValueAnimator anim = ValueAnimator.ofFloat(0f, 1f);
//        ValueAnimator anim = ValueAnimator.ofFloat(0f, 5f, 3f, 10f);
      //  ValueAnimator.ofInt()<animator android:valueType="intType"/>
        anim.setDuration(300);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                System.out.println("current="+valueAnimator.getAnimatedValue());
            }
        });
        anim.start();
        //objectanimator
        ObjectAnimator animator = ObjectAnimator.ofFloat(textView, "alpha", 1f, 0f, 1f);
//        <objectAnimator xmlns:android="http://schemas.android.com/apk/res/android"
//        android:valueFrom="1"
//        android:valueTo="0"
//        android:valueType="floatType"
//        android:propertyName="alpha"/>
        animator.setDuration(5000);
        animator.start();
    }
}
