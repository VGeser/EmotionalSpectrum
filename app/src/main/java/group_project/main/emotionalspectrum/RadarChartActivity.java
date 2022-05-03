package group_project.main.emotionalspectrum;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;

public class RadarChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radar_chart);

        RadarChart radarChart = findViewById(R.id.radarChart);
// FREQUENCY
        ArrayList<RadarEntry> correlationFr = new ArrayList<>();
        correlationFr.add(new RadarEntry(34));
        correlationFr.add(new RadarEntry(23));
        correlationFr.add(new RadarEntry(25));
        correlationFr.add(new RadarEntry(15));
        correlationFr.add(new RadarEntry(0));
        correlationFr.add(new RadarEntry(1));
        correlationFr.add(new RadarEntry(10));
        correlationFr.add(new RadarEntry(3));
        correlationFr.add(new RadarEntry(12));
        correlationFr.add(new RadarEntry(5));

        RadarDataSet radarDataSetForCorrelationFr = new RadarDataSet(correlationFr, "Frequency");
        radarDataSetForCorrelationFr.setColor(Color.GREEN);
        radarDataSetForCorrelationFr.setLineWidth(3f);
        radarDataSetForCorrelationFr.setValueTextColor(Color.BLACK);
        radarDataSetForCorrelationFr.setValueTextSize(14f);

        // INTENSE
        ArrayList<RadarEntry> correlationIn = new ArrayList<>();
        correlationIn.add(new RadarEntry(40));
        correlationIn.add(new RadarEntry(12));
        correlationIn.add(new RadarEntry(13));
        correlationIn.add(new RadarEntry(10));
        correlationIn.add(new RadarEntry(0));
        correlationIn.add(new RadarEntry(39));
        correlationIn.add(new RadarEntry(14));
        correlationIn.add(new RadarEntry(4));
        correlationIn.add(new RadarEntry(17));
        correlationIn.add(new RadarEntry(18));

        RadarDataSet radarDataSetForCorrelationIn = new RadarDataSet(correlationIn, "Frequency");
        radarDataSetForCorrelationIn.setColor(Color.BLUE);
        radarDataSetForCorrelationIn.setLineWidth(3f);
        radarDataSetForCorrelationIn.setValueTextColor(Color.BLACK);
        radarDataSetForCorrelationIn.setValueTextSize(14f);

        RadarData radarData = new RadarData();
        radarData.addDataSet(radarDataSetForCorrelationFr);
        radarData.addDataSet(radarDataSetForCorrelationIn);

        String[] labels = {"Disguise", "Suffering", "Shame", "Fear", "Guilt", "Anger",  "Joy", "Pride", "Anticipation", "Contempt"};

        XAxis xAxis = radarChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));

        radarChart.getDescription().setText("Correlation between frequency and intensity of emotions");
        radarChart.setData(radarData);
    }

    public void buttonClick(View view) {
        Intent i = new Intent(RadarChartActivity.this, StatsActivity.class);
        RadarChartActivity.this.startActivity(i);
    }
}