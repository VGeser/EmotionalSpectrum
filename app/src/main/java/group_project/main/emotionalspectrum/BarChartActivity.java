package group_project.main.emotionalspectrum;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class BarChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart);

        BarChart barChart = findViewById(R.id.barChart);

        ArrayList<BarEntry> frequency = new ArrayList<>();
        frequency.add(new BarEntry(1, 21));
        frequency.add(new BarEntry(3, 14));
        frequency.add(new BarEntry(4, 30));
        frequency.add(new BarEntry(8, 1));
        frequency.add(new BarEntry(21, 0));
        frequency.add(new BarEntry(5, 3));
        frequency.add(new BarEntry(6, 16));
        frequency.add(new BarEntry(13, 8));

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

    public void buttonClick(View view) {
        Intent i = new Intent(BarChartActivity.this, StatsActivity.class);
        BarChartActivity.this.startActivity(i);
    }
}