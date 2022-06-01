package group_project.main.emotionalspectrum.graphs;

import androidx.appcompat.app.AppCompatActivity;

import group_project.main.emotionalspectrum.GsonEditor;
import group_project.main.emotionalspectrum.R;
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

import group_project.main.emotionalspectrum.StatsActivity;

public class BarIntensActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_intens);

        BarChart barChart = findViewById(R.id.barChartI);

        //x - id   y - intens
        ArrayList<BarEntry> intensity = makeDataList();

        BarDataSet barDataSet = new BarDataSet(intensity, "Intense of emotions");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);

        BarData barData = new BarData(barDataSet);

        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setText("Intensity");
        barChart.animateY(2000);
    }

    private ArrayList<BarEntry> makeDataList(){
        ArrayList<BarEntry> intensity = new ArrayList<>();
        GsonEditor gsonEditor = GsonEditor.getInstance();
        int [] intens = gsonEditor.getIntensCash();
        for (int i = 0; i < 10; i++) {
            intensity.add(new BarEntry(i,intens[i]));
        }
        return intensity;
    }

    public void buttonClick(View view) {
        Intent i = new Intent(BarIntensActivity.this, StatsActivity.class);
        BarIntensActivity.this.startActivity(i);
    }
}