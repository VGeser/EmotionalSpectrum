package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends FragmentActivity{
    public CurrentValue currentValue;
    private ImageView image;
    public void setCurrentValue(CurrentValue currentValue) {
        this.currentValue = currentValue;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.image= (ImageView)this.findViewById(R.id.spectrum);
        this.image.setImageResource(R.drawable.not_transparent);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ConfirmationFragment cf = new ConfirmationFragment();
        ft.add(R.id.place_holder, cf);
        ft.commit();

        image.setOnTouchListener((view, motionEvent) -> {
            if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                view.performClick();
                Calculator calculator = new Calculator();
                /*final float x = motionEvent.getX();
                final float y = motionEvent.getY();
                byte res_id = calculator.emotion(x,y);
                currentValue.setCurrentMood(res_id);*/
                cf.show(ft,"confirm");
            } return view.onTouchEvent(motionEvent);
        });
    }

}