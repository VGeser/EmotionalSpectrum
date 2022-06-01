package group_project.main.emotionalspectrum;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

public class SettingsActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private boolean needApply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        SwitchCompat switch1 = findViewById(R.id.switch1);
        SwitchCompat switch2 = findViewById(R.id.switch2);
        SwitchCompat switch3 = findViewById(R.id.switch3);
        sharedPreferences = getSharedPreferences("EmotionPrefs", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        switch1.setChecked(sharedPreferences.getBoolean("name_setting", false));
        switch2.setChecked(sharedPreferences.getBoolean("graph_setting", false));
        switch3.setChecked(sharedPreferences.getBoolean("text_setting", false));
        switch1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            editor.putBoolean("name_setting", isChecked);
            needApply = true;
        });
        switch2.setOnCheckedChangeListener((buttonView, isChecked) -> {
            editor.putBoolean("graph_setting", isChecked);
            needApply = true;
        });
        switch3.setOnCheckedChangeListener((buttonView, isChecked) -> {
            editor.putBoolean("text_setting", isChecked);
            needApply = true;
        });
    }

    @Override
    public void onStop() {
        super.onStop();
        if (needApply) {
            editor.apply();
        }
    }

    public void buttonClick(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        SettingsActivity.this.startActivity(intent);
    }

    public void deleteClick(View view) {
        String str = sharedPreferences.getString("data", "");
        GsonEditor gsonEditor = GsonEditor.getInstance(str);
        gsonEditor.clearCache();
        editor.clear();
        editor.apply();
    }
}