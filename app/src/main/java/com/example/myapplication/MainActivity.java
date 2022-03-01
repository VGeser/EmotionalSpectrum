package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import java.util.concurrent.atomic.AtomicReference;

public class MainActivity extends FragmentActivity{
    private ImageView image;
    private TextView textView;
    private float curX;
    private float curY;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.image= (ImageView)this.findViewById(R.id.spectrum);
        this.textView=(TextView)this.findViewById(R.id.text);
        this.image.setImageResource(R.drawable.not_transparent);
        ConfirmationFragment cf = new ConfirmationFragment();

        image.setOnTouchListener((view, motionEvent) -> {
            if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                view.performClick();
                //Calculator calculator = new Calculator();
                curX = motionEvent.getX();
                curY = motionEvent.getY();
                //byte res_id = calculator.emotion(x,y);
                //String text = String.valueOf(res_id);
                cf.show(getSupportFragmentManager(),"confirm");
            } return view.onTouchEvent(motionEvent);
        });
    }

    public void doPositiveClick(){
        String str = String.valueOf(curX);
        textView.append("Current X: " + str + " ");
        str = String.valueOf(curY);
        textView.append("Current Y: " + str + "\n");
        //Context context = getApplicationContext();
        //Toast toast = Toast.makeText(context, "Positive", Toast.LENGTH_SHORT);
        //toast.show();
    }

    public void doNegativeClick(){
        Context context = getApplicationContext();
        Toast toast = Toast.makeText(context, "Negative", Toast.LENGTH_SHORT);
        toast.show();
    }

}