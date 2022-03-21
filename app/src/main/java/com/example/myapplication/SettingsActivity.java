package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SettingsActivity extends AppCompatActivity {
    private Button backButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        this.backButton = (Button)this.findViewById(R.id.button_back);
    }
    public void buttonClick(View view) {
        Intent i = new Intent(SettingsActivity.this, MainActivity.class);
        SettingsActivity.this.startActivity(i);
    }
}