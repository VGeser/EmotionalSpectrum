package group_project.main.emotionalspectrum.ui.settings;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import com.example.myapplication.GsonEditor;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;

@SuppressLint("UseSwitchCompatOrMaterialCode")
public class SettingsActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private boolean needApply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Button backButton = (Button) this.findViewById(R.id.button_back);
        SwitchCompat switch1 = (SwitchCompat) findViewById(R.id.switch1);
        SwitchCompat switch2 = (SwitchCompat) findViewById(R.id.switch2);
        SwitchCompat switch3 = (SwitchCompat) findViewById(R.id.switch3);
        sharedPreferences = getSharedPreferences("EmotionPrefs", Context.MODE_PRIVATE);
        String json = sharedPreferences.getString("data", "");
        Log.println(Log.ERROR, "Current Setting prefs:", json);
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
    public void onStart() {
        super.onStart();
        String json = sharedPreferences.getString("data", "");
        Log.println(Log.ERROR, "Current Setting prefs:", json);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (needApply) {
            editor.apply();
        }
    }

    public void buttonClick(View view) {
        String json = sharedPreferences.getString("data", "");
        Log.println(Log.ERROR, "Current Setting prefs:", json);
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        com.example.myapplication.ui.settings.SettingsActivity.this.startActivity(intent);
    }

    public void deleteClick(View view) {
        String str = sharedPreferences.getString("data", "");
        GsonEditor gsonEditor = GsonEditor.getInstance(str);
        Log.println(Log.ERROR, "Current cache:", String.valueOf(gsonEditor.flatten().size()));
        editor.clear();
        editor.apply();
        Log.println(Log.ERROR, "Action", "Deleted");
    }
}