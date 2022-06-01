package group_project.main.emotionalspectrum.graphs;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import group_project.main.emotionalspectrum.GsonEditor;
import group_project.main.emotionalspectrum.R;
import group_project.main.emotionalspectrum.Record;
import group_project.main.emotionalspectrum.StatsActivity;

public class LineChartActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    int last;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_chart);
        sharedPreferences = getSharedPreferences("EmotionPrefs", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        last = sharedPreferences.getInt("cur_id", 0);

        LineChart lineChart = findViewById(R.id.lineChart);

        //x - id   y - intens  over month
        ArrayList<Entry> monthlyIntens = makeDataList();

        LineDataSet lineDataSet = new LineDataSet(monthlyIntens, "Overall intensity");
        lineDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        lineDataSet.setValueTextColor(Color.BLACK);
        lineDataSet.setValueTextSize(16f);

        LineData lineData = new LineData(lineDataSet);

        lineChart.setData(lineData);
        lineChart.getDescription().setEnabled(false);
        lineChart.animate();
    }

    private ArrayList<Entry> makeDataList() {
        ArrayList<Entry> monthIntens = new ArrayList<>();
        GsonEditor gsonEditor = GsonEditor.getInstance();
        ArrayList<Record> recs = gsonEditor.perMonth(last);
        int i = 0;
        for (Record r : recs) {
            monthIntens.add(new Entry(i, r.getIntensity()));
            i++;
        }
        return monthIntens;
    }

    public void buttonClick(View view) {
        Intent i = new Intent(LineChartActivity.this, StatsActivity.class);
        LineChartActivity.this.startActivity(i);
    }
}