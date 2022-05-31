package group_project.main.emotionalspectrum;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class LineChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_chart);

        LineChart lineChart = findViewById(R.id.lineChart);

        ArrayList<Entry> line = new ArrayList<>();
        line.add(new Entry(14, 2));
        line.add(new Entry(15, 1));
        line.add(new Entry(16, 4));
        line.add(new Entry(17, 2));
        line.add(new Entry(18, 1));
        line.add(new Entry(20, 4));
        line.add(new Entry(21, 2));
        line.add(new Entry(28, 1));
        line.add(new Entry(30, 4));

        LineDataSet lineDataSet = new LineDataSet(line, "blabla");
        lineDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        lineDataSet.setValueTextColor(Color.BLACK);
        lineDataSet.setValueTextSize(16f);

        LineData lineData = new LineData(lineDataSet);

        lineChart.setData(lineData);
        lineChart.getDescription().setEnabled(false);
        lineChart.animate();
    }

    public void buttonClick(View view) {
        Intent i = new Intent(LineChartActivity.this, StatsActivity.class);
        LineChartActivity.this.startActivity(i);
    }
}