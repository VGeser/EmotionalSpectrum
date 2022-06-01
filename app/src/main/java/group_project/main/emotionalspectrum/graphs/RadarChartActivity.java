package group_project.main.emotionalspectrum.graphs;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;

import group_project.main.emotionalspectrum.GsonEditor;
import group_project.main.emotionalspectrum.R;
import group_project.main.emotionalspectrum.Record;
import group_project.main.emotionalspectrum.StatsActivity;

public class RadarChartActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    int last;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radar_chart);
        sharedPreferences = getSharedPreferences("EmotionPrefs", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        last = sharedPreferences.getInt("cur_id", 0);

        RadarChart radarChart = findViewById(R.id.radarChart);

        //val - freq    order - day
        ArrayList<RadarEntry> correlationFr = makeFreqList();

        RadarDataSet radarDataSetForCorrelationFr = new RadarDataSet(correlationFr, "Frequency");
        radarDataSetForCorrelationFr.setColor(Color.GREEN);
        radarDataSetForCorrelationFr.setLineWidth(3f);
        radarDataSetForCorrelationFr.setValueTextColor(Color.BLACK);
        radarDataSetForCorrelationFr.setValueTextSize(14f);

        //val - intens    order - day
        ArrayList<RadarEntry> correlationIn = makeIntensList();

        RadarDataSet radarDataSetForCorrelationIn = new RadarDataSet(correlationIn, "Frequency");
        radarDataSetForCorrelationIn.setColor(Color.BLUE);
        radarDataSetForCorrelationIn.setLineWidth(3f);
        radarDataSetForCorrelationIn.setValueTextColor(Color.BLACK);
        radarDataSetForCorrelationIn.setValueTextSize(14f);

        RadarData radarData = new RadarData();
        radarData.addDataSet(radarDataSetForCorrelationFr);
        radarData.addDataSet(radarDataSetForCorrelationIn);

        String[] labels = {"Disguise", "Suffering", "Shame", "Fear", "Guilt", "Anger", "Joy", "Pride", "Anticipation", "Contempt"};

        XAxis xAxis = radarChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));

        radarChart.getDescription().setText("Correlation between frequency and intensity of emotions");
        radarChart.setData(radarData);
    }

    private ArrayList<RadarEntry> makeFreqList() {
        ArrayList<RadarEntry> res = new ArrayList<>();
        GsonEditor gsonEditor = GsonEditor.getInstance();
        ArrayList<Record> recs = gsonEditor.perMonth(last);
        int[] freqMonth = new int[10];
        for (Record r : recs) {
            byte sector = (byte) r.getS();
            freqMonth[gsonEditor.relateF(sector)]++;
        }
        for (int f : freqMonth) {
            res.add(new RadarEntry(f));
        }
        return res;
    }

    private ArrayList<RadarEntry> makeIntensList() {
        ArrayList<RadarEntry> res = new ArrayList<>();
        GsonEditor gsonEditor = GsonEditor.getInstance();
        ArrayList<Record> recs = gsonEditor.perMonth(last);
        int i = 0;
        for (Record r : recs) {
            res.add(new RadarEntry(r.getIntensity()));
            i++;
        }
        return res;
    }

    public void buttonClick(View view) {
        Intent i = new Intent(RadarChartActivity.this, StatsActivity.class);
        RadarChartActivity.this.startActivity(i);
    }
}