package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class MainActivity extends FragmentActivity {
    private ImageView image;
    private TextView textView;
    private String current = "";
    //private Button menuButton;
    private int currentID;
    private GsonEditor gsonEditor;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.image = (ImageView) this.findViewById(R.id.spectrum1);
        this.textView = (TextView) this.findViewById(R.id.text);
        //this.menuButton = (Button) this.findViewById(R.id.menu);
        this.image.setImageResource(R.drawable.round_without_emots);
        Button button = (Button) this.findViewById(R.id.button_next);
        ConfirmationFragment cf = new ConfirmationFragment();
        sharedPreferences = getSharedPreferences("EmotionPrefs", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        image.setOnTouchListener((view, motionEvent) -> {
            if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                view.performClick();
                Calculator calculator = Calculator.getInstance();
                float curX = motionEvent.getX();
                float curY = motionEvent.getY();
                DisplayMetrics metrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(metrics);
                int width = metrics.widthPixels;
                cf.currentRecord = calculator.calculate(curX, curY, width / 2);

                if (!cf.currentRecord.getEmotionName().equals("out_of_circle"))
                    cf.show(getSupportFragmentManager(), "confirm");
            }
            return view.onTouchEvent(motionEvent);
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (sharedPreferences.contains("most_recent")) {
            current = sharedPreferences.getString("most_recent", "");
            Log.println(Log.ERROR, "Reached", "has most recent");
            textView.setText(current);
        } else {
            editor.putString("most_recent", current);
            Log.println(Log.ERROR, "Reached", "no recent");
        }
        if (sharedPreferences.contains("current_id")) {
            currentID = sharedPreferences.getInt("cur_id", 0);
            Log.println(Log.ERROR, "Reached", "has id");
        } else {
            editor.putInt("cur_id", currentID);
        }
        if (sharedPreferences.contains("name_setting")) {
            boolean nameOn = sharedPreferences.getBoolean("name_setting", false);
            if (nameOn) {
                image.setImageResource(R.drawable.round);
            } else {
                image.setImageResource(R.drawable.round_without_emots);
            }
        } else {
            editor.putBoolean("name_setting", false);
        }
        if (sharedPreferences.contains("next_id")) {
            this.currentID = sharedPreferences.getInt("next_id", 0);
        } else {
            currentID = 0;
            editor.putInt("next_id", 0);
        }
        String json;
        if (!sharedPreferences.contains("data")) {
            editor.putString("data", "");
        }
        editor.apply();
        json = sharedPreferences.getString("data", "");
        Log.println(Log.ERROR, "Current MA data", json);
        Log.println(Log.ERROR, "Current MA ID", String.valueOf(currentID));
        Log.println(Log.ERROR, "Current MA str", current);
        gsonEditor = GsonEditor.getInstance(json);
        gsonEditor.parseGson();
    }

    void doPositiveClick(Record next) {
        LocalTime time = LocalTime.now();
        LocalDate date = LocalDate.now();
        String dateS = date.toString();
        next.setDate(dateS);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        String formatted = time.format(dtf);
        current = ("You were feeling " + next.getEmotionName() + " at " + formatted + "\n");
        textView.append(current);
        gsonEditor.addRecord(currentID, next);
        currentID++;
    }

    void doNegativeClick() {
        Context context = getApplicationContext();
        Toast toast = Toast.makeText(context, "Cancelled", Toast.LENGTH_SHORT);
        toast.show();
    }

    /*public void menuButton(View view) {
        Intent i = new Intent(MainActivity.this, SecondActivity.class);
        MainActivity.this.startActivity(i);
    }*/

    public void statsClick(View view) {
        Intent i = new Intent(MainActivity.this, StatsActivity.class);
        MainActivity.this.startActivity(i);
    }

    public void settingsClick(View view) {
        Intent i = new Intent(MainActivity.this, SettingsActivity.class);
        MainActivity.this.startActivity(i);
    }

    @Override
    protected void onStop() {
        super.onStop();
        editor.putInt("next_id", currentID);
        Log.println(Log.ERROR, "Putting", String.valueOf(currentID));
        String savedAs = gsonEditor.saveData();
        if(!savedAs.equals("{}")){
            editor.putString("data", savedAs);
            Log.println(Log.ERROR, "Putting", savedAs);
        }
        editor.putString("most_recent", current);
        Log.println(Log.ERROR, "Putting", current);
        editor.putInt("cur_id", currentID);
        editor.apply();
        Log.println(Log.ERROR, "Action", "Saved");
        Toast toast = new Toast(getApplicationContext());
        toast.setText("Now you can delete");
        toast.show();
    }

}