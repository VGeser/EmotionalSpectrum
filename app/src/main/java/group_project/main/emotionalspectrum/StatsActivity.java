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

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Iterator;
import java.util.Map;

public class StatsActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private GraphView graph;
    private TextView textView;
    private String displayedData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        graph = findViewById(R.id.graph);
        textView = findViewById(R.id.text_data);
        sharedPreferences = getSharedPreferences("EmotionPrefs", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    /*Это просто какойто пример. По идее я получаю id эмоции, ее интенсивнось
         и дату отметки*/

        int[] Data = {10, 46, 53, 58, 63, 67, 69, 72, 75, 78, 82, 85,
                90, 95, 99, 105, 110, 115, 121, 126, 132, 137, 143,
                148, 153, 157, 162, 165, 168, 170, 173, 174, 175, 176,
                177, 177, 177, 176, 176, 175, 173, 172, 171, 169, 168,
                167, 166, 164, 161, 155, 147, 136, 123, 111, 101, 92, 84,
                78, 74, 70, 67, 65, 64, 62, 61, 61, 60, 60, 59, 59, 58, 58,
                58, 57, 57, 57, 56, 56, 56, 56, 56, 56, 56, 55, 55, 55, 55,
                55, 54, 54,};

        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>();
        for (int i = 0; i < 90; i++) {
            series.appendData(new DataPoint(i, Data[i]), true, 90);
        }
        //
        series.setColor(Color.rgb(0, 80, 100)); // set the curve color
        series.setTitle("Curve 1"); // set the curve name for the legend
        series.setDrawDataPoints(true); // draw points
        series.setDataPointsRadius(5); // radius of the data point
        series.setThickness(2); //line thickness
        graph.addSeries(series);

        //Chart name
        graph.setTitle("Expenses");
        graph.setTitleTextSize(50);
        graph.setTitleColor(Color.RED);
        //Legend
        graph.getLegendRenderer().setVisible(true);
        graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);

        //Axis signatures
        GridLabelRenderer gridLabel = graph.getGridLabelRenderer();
        gridLabel.setHorizontalAxisTitle("X Axis Title");
        gridLabel.setVerticalAxisTitle("Y Axis Title");
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

    private void textSetter(){
        Context context = getApplicationContext();
        Resources resources = context.getResources();
        GsonEditor gsonEditor = GsonEditor.getInstance();
        gsonEditor.parseGson(displayedData);
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
