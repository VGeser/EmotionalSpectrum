package group_project.main.emotionalspectrum.graphs;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import group_project.main.emotionalspectrum.GsonEditor;
import group_project.main.emotionalspectrum.R;
import group_project.main.emotionalspectrum.StatsActivity;

public class BarChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart);

        BarChart barChart = findViewById(R.id.barChart);
        //x - id   y - freq
        ArrayList<BarEntry> frequency = makeDataList();

        BarDataSet barDataSet = new BarDataSet(frequency, "Frequency of emotions");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);

        BarData barData = new BarData(barDataSet);

        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setText("Frequency");
        barChart.animateY(2000);
    }

    private ArrayList<BarEntry> makeDataList() {
        ArrayList<BarEntry> frequency = new ArrayList<>();
        GsonEditor gsonEditor = GsonEditor.getInstance();
        int[] freqs = gsonEditor.getFreqCash();
        for (int i = 0; i < 10; i++) {
            frequency.add(new BarEntry(i, freqs[i]));
        }
        return frequency;
    }

    public void buttonClick(View view) {
        Intent i = new Intent(BarChartActivity.this, StatsActivity.class);
        BarChartActivity.this.startActivity(i);
    }
}