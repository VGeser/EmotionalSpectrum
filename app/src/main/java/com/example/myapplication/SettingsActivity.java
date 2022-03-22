package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

@SuppressLint("UseSwitchCompatOrMaterialCode")
public class SettingsActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Button backButton = (Button) this.findViewById(R.id.button_back);
        SwitchCompat switch1 = (SwitchCompat) findViewById(R.id.switch1);
        SwitchCompat switch2 = (SwitchCompat) findViewById(R.id.switch2);
        SwitchCompat switch3 = (SwitchCompat) findViewById(R.id.switch3);
        sharedPreferences = getSharedPreferences("EmotionPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        switch1.setChecked(sharedPreferences.getBoolean("name_setting", false));
        switch2.setChecked(sharedPreferences.getBoolean("graph_setting", false));
        switch3.setChecked(sharedPreferences.getBoolean("text_setting", false));
        switch1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            editor.putBoolean("name_setting", isChecked);
            editor.apply();
        });
        switch2.setOnCheckedChangeListener((buttonView, isChecked) -> {
            editor.putBoolean("graph_setting", isChecked);
            editor.apply();
        });
        switch3.setOnCheckedChangeListener((buttonView, isChecked) -> {
            editor.putBoolean("text_setting", isChecked);
            editor.apply();
        });
    }

    public void buttonClick(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        SettingsActivity.this.startActivity(intent);
    }
}