package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import java.time.LocalTime;

public class MainActivity extends FragmentActivity {
    private ImageView image;
    private TextView textView;
    private double res_id;
    private Button button;
    private String emotion;
    SharedPreferences sharedPreferences;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.image = (ImageView) this.findViewById(R.id.spectrum1);
        this.textView = (TextView) this.findViewById(R.id.text);
        this.image.setImageResource(R.drawable.round_without_emots);//change source
        this.button = (Button) this.findViewById(R.id.button_next);
        ConfirmationFragment cf = new ConfirmationFragment();
        sharedPreferences = getSharedPreferences("EmotionPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (sharedPreferences.contains("name_setting")){
            boolean nameOn = sharedPreferences.getBoolean("name_setting",false);
            if(nameOn){
                image.setImageResource(R.drawable.round);
            }else{
                image.setImageResource(R.drawable.round_without_emots);
            }
        }else{
            editor.putBoolean("name_setting",false);
            editor.apply();
        }
        /*boolean imageOption = getIntent().getExtras().getBoolean("name_choice");
        if(imageOption){
            image.setImageResource(R.drawable.round);
        }else {
            image.setImageResource(R.drawable.round_without_emots);
        }*/

        image.setOnTouchListener((view, motionEvent) -> {
            if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                view.performClick();
                Calculator calculator = Calculator.getInstance();
                float curX = motionEvent.getX();
                float curY = motionEvent.getY();
                DisplayMetrics metrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(metrics);
                int width = metrics.widthPixels;
                emotion = calculator.calculate(curX, curY, width / 2);
                cf.out_text = emotion;
                //cf.out_text = String.valueOf(res_id);

                if (!emotion.equals("out_of_circle"))
                    cf.show(getSupportFragmentManager(), "confirm");
            }
            return view.onTouchEvent(motionEvent);
        });
    }

    public void doPositiveClick() {
        LocalTime time = LocalTime.now();
        textView.append("You were feeling " + emotion + " at " + time + "\n");
    }

    public void doNegativeClick() {
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