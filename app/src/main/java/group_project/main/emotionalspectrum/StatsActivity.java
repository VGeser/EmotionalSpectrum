<<<<<<< Updated upstream
package group_project.main.emotionalspectrum;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import java.util.Iterator;
import java.util.Map;

public class StatsActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
 //   private GraphView graph;
    private TextView textView;
    private String displayedData;

/*    @Override
    protected void onStart() {
        super.onStart();
        if(sharedPreferences.contains("data")){
            displayedData = sharedPreferences.getString("data", "");
        }else {
            displayedData = String.valueOf(R.string.filler);
        }
        textSetter();
        if (sharedPreferences.contains("graph_setting")) {
            boolean graphOn = sharedPreferences.getBoolean("graph_setting", false);
            if (graphOn) {
                graph.setVisibility(View.VISIBLE);
            } else {
                graph.setVisibility(View.INVISIBLE);
            }
        }else{
            editor.putBoolean("graph_setting",false);
            editor.apply();
        }
        if (sharedPreferences.contains("text_setting")) {
            boolean graphOn = sharedPreferences.getBoolean("text_setting", false);
            if (graphOn) {
                textView.setVisibility(View.VISIBLE);
            } else {
                textView.setVisibility(View.INVISIBLE);
            }
        }else{
            editor.putBoolean("text_setting",false);
            editor.apply();
        }
    }
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((R.layout.activity_stats));

        findViewById(R.id.buttonBarChart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), BarChartActivity.class));
            }
        });

        findViewById(R.id.buttonBarChartI).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), BarChartIActivity.class));
            }
        });

        findViewById(R.id.buttonRadarChart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), RadarChartActivity.class));
            }
        });

        findViewById(R.id.buttonPieChart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), PieChartActivity.class));
            }
        });
    }
    private void textSetter(){
        Context context = getApplicationContext();
        Resources resources = context.getResources();
        GsonEditor gsonEditor = GsonEditor.getInstance();
        //gsonEditor.parseGson(displayedData);
        Map<Integer,Record> vals = gsonEditor.flatten();
        Iterator<Record> iterator = vals.values().iterator();
        Record r;
        textView.append(resources.getString(R.string.raw0)+"\n");
        while(iterator.hasNext()){
            r= iterator.next();
            textView.append(resources.getString(R.string.raw1));
            textView.append(r.getEmotionName()+"\n");
            textView.append(resources.getString(R.string.raw2));
            textView.append(r.getDate()+"\n");
            textView.append(resources.getString(R.string.raw3));
            textView.append(r.getIntensity() +"\n");
            textView.append(resources.getString(R.string.separator)+"\n");
        }
    }

    public void buttonClick(View view) {
        Intent i = new Intent(StatsActivity.this, MainActivity.class);
        StatsActivity.this.startActivity(i);
    }

}
=======
package group_project.main.emotionalspectrum;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Iterator;
import java.util.Map;

public class StatsActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private TextView textView;
    private String displayedData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        textView = findViewById(R.id.text_data);
        sharedPreferences = getSharedPreferences("EmotionPrefs", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        findViewById(R.id.buttonBarChart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), BarChartActivity.class));
            }
        });

        findViewById(R.id.buttonBarChartI).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), BarChartIActivity.class));
            }
        });

        findViewById(R.id.buttonRadarChart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), RadarChartActivity.class));
            }
        });
        findViewById(R.id.buttonPieChart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), PieChartActivity.class));
            }
        });

        findViewById(R.id.buttonLineChart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), LineChartActivity.class));
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        if(sharedPreferences.contains("data")){
            displayedData = sharedPreferences.getString("data", "");
        }else {
            displayedData = String.valueOf(R.string.filler);
        }
        textSetter();

        if (sharedPreferences.contains("text_setting")) {
            boolean graphOn = sharedPreferences.getBoolean("text_setting", false);
            if (graphOn) {
                textView.setVisibility(View.VISIBLE);
            } else {
                textView.setVisibility(View.INVISIBLE);
            }
        }else{
            editor.putBoolean("text_setting",false);
            editor.apply();
        }
    }

    private void textSetter(){
        Context context = getApplicationContext();
        Resources resources = context.getResources();
        GsonEditor gsonEditor = GsonEditor.getInstance();
        //gsonEditor.parseGson(displayedData);
        Map<Integer,Record> vals = gsonEditor.flatten();
        Iterator<Record> iterator = vals.values().iterator();
        Record r;
        textView.append(resources.getString(R.string.raw0)+"\n");
        while(iterator.hasNext()){
            r= iterator.next();
            textView.append(resources.getString(R.string.raw1));
            textView.append(r.getEmotionName()+"\n");
            textView.append(resources.getString(R.string.raw2));
            textView.append(r.getDate()+"\n");
            textView.append(resources.getString(R.string.raw3));
            textView.append(r.getIntensity() +"\n");
            textView.append(resources.getString(R.string.separator)+"\n");
        }
    }

    public void buttonClick(View view) {
        Intent i = new Intent(StatsActivity.this, MainActivity.class);
        StatsActivity.this.startActivity(i);
    }
}
>>>>>>> Stashed changes
