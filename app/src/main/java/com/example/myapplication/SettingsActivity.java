package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

@SuppressLint("UseSwitchCompatOrMaterialCode")
public class SettingsActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Button backButton = (Button) this.findViewById(R.id.button_back);
        Switch switch1 = (Switch) findViewById(R.id.switch1);
        sharedPreferences = getSharedPreferences("EmotionPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        switch1.setChecked(sharedPreferences.getBoolean("name_setting",false));
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                editor.putBoolean("name_setting",isChecked);
                editor.apply();
            }
        });
    }

    public void buttonClick(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        SettingsActivity.this.startActivity(intent);
    }
}