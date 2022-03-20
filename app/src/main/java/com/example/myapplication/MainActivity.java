package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import java.time.LocalTime;

public class MainActivity extends FragmentActivity{
    private ImageView image;
    private TextView textView;
    private double res_id;
    private Button button;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.image= (ImageView)this.findViewById(R.id.spectrum);
        this.textView=(TextView)this.findViewById(R.id.text);
        this.image.setImageResource(R.drawable.round);//change source
        this.button = (Button)this.findViewById(R.id.button_next);
        ConfirmationFragment cf = new ConfirmationFragment();

        image.setOnTouchListener((view, motionEvent) -> {
            if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                view.performClick();
                Calculator calculator = Calculator.getInstance();
                float curX = motionEvent.getX();
                Context context = getApplicationContext();
                Toast toast = Toast.makeText(context, String.valueOf(curX), Toast.LENGTH_SHORT);
                toast.show();
                float curY = motionEvent.getY();
                res_id = calculator.calculate(curX,curY);
                cf.out_text = String.valueOf(res_id);

                cf.show(getSupportFragmentManager(),"confirm");
            } return view.onTouchEvent(motionEvent);
        });
    }

    public void doPositiveClick(){
        LocalTime time = LocalTime.now();
        textView.append("You were feeling " + res_id + " at " + time);
    }

    public void doNegativeClick(){
        Context context = getApplicationContext();
        Toast toast = Toast.makeText(context, "Cancelled", Toast.LENGTH_SHORT);
        toast.show();
    }

    public void statsClick(View view) {
        Intent i = new Intent(MainActivity.this, StatsActivity.class);
        MainActivity.this.startActivity(i);
    }

    public void settingsClick(View view) {
        Intent i = new Intent(MainActivity.this, SettingsActivity.class);
        MainActivity.this.startActivity(i);
    }

}