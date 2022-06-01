package group_project.main.emotionalspectrum.graphs;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Iterator;
import java.util.Map;

import group_project.main.emotionalspectrum.GsonEditor;
import group_project.main.emotionalspectrum.R;
import group_project.main.emotionalspectrum.Record;
import group_project.main.emotionalspectrum.StatsActivity;

public class RawActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raw);
        textView = findViewById(R.id.text_data);
        sharedPreferences = getSharedPreferences("EmotionPrefs", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    private void textSetter() {
        Context context = getApplicationContext();
        Resources resources = context.getResources();
        GsonEditor gsonEditor = GsonEditor.getInstance();
        Map<Integer, Record> vals = gsonEditor.flatten();
        Iterator<Record> iterator = vals.values().iterator();
        Record r;
        textView.append(resources.getString(R.string.raw0) + "\n");
        while (iterator.hasNext()) {
            r = iterator.next();
            textView.append(resources.getString(R.string.raw1));
            textView.append(r.getEmotionName() + "\n");
            textView.append(resources.getString(R.string.raw2));
            textView.append(r.getDate() + "\n");
            textView.append(resources.getString(R.string.raw3));
            textView.append(r.getIntensity() + "\n");
            textView.append(resources.getString(R.string.separator) + "\n");
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (sharedPreferences.contains("data")) {
            sharedPreferences.getString("data", "");
        }
        textSetter();

        if (sharedPreferences.contains("text_setting")) {
            boolean graphOn = sharedPreferences.getBoolean("text_setting", false);
            if (graphOn) {
                textView.setVisibility(View.VISIBLE);
            } else {
                textView.setVisibility(View.INVISIBLE);
            }
        } else {
            editor.putBoolean("text_setting", false);
            editor.apply();
        }
    }

    public void buttonClick(View view) {
        Intent i = new Intent(RawActivity.this, StatsActivity.class);
        RawActivity.this.startActivity(i);
    }
}