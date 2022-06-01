package group_project.main.emotionalspectrum.graphs;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import group_project.main.emotionalspectrum.GsonEditor;
import group_project.main.emotionalspectrum.R;
import group_project.main.emotionalspectrum.StatsActivity;
import group_project.main.emotionalspectrum.calc.Calculator;

public class PieChartActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    private GsonEditor gsonEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);

        PieChart pieChart = findViewById(R.id.pieChart);
        sharedPreferences = getSharedPreferences("EmotionPrefs", Context.MODE_PRIVATE);
        gsonEditor = GsonEditor.getInstance(sharedPreferences.getString("data", ""));

        ArrayList<PieEntry> topThree = makePieData();

        PieDataSet pieDataSet = new PieDataSet(topThree, "Top three");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet.setValueTextColor(Color.BLACK);
        pieDataSet.setValueTextSize(16f);

        PieData pieData = new PieData(pieDataSet);

        pieChart.setData(pieData);
        pieChart.getDescription().setEnabled(false);
        pieChart.setCenterText("Top three");
        pieChart.animate();
    }

    private ArrayList<PieEntry> makePieData() {
        ArrayList<PieEntry> res = new ArrayList<>();
        GsonEditor gsonEditor = GsonEditor.getInstance();
        int[] popular = gsonEditor.getFreqCash();
        int sum = gsonEditor.getDataSize();
        TreeMap<Byte, Integer> connected = new TreeMap<>();
        byte id = 0;
        for (int i : popular) {
            connected.put(id, i);
            id++;
        }
        Calculator calculator = Calculator.getInstance();
        List<Map.Entry<Byte, Integer>> list = new ArrayList<>(connected.entrySet());
        list.sort(Map.Entry.comparingByValue());
        List<Map.Entry<Byte, Integer>> list1 = list.subList(6, 9);
        for (Map.Entry<Byte, Integer> byteIntegerEntry : list1) {
            byte curI = byteIntegerEntry.getKey();
            int val = byteIntegerEntry.getValue();
            res.add(new PieEntry(val, calculator.sectorToName(curI)));
        }
        return res;
    }

    public void buttonClick(View view) {
        Intent i = new Intent(PieChartActivity.this, StatsActivity.class);
        PieChartActivity.this.startActivity(i);
    }
}