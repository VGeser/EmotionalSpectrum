package group_project.main.emotionalspectrum;

import androidx.appcompat.app.AppCompatActivity;

<<<<<<< Updated upstream
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
=======
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
>>>>>>> Stashed changes
import android.view.View;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class PieChartActivity extends AppCompatActivity {

<<<<<<< Updated upstream
=======
    SharedPreferences sharedPreferences;
    private GsonEditor gsonEditor;

>>>>>>> Stashed changes
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);

<<<<<<< Updated upstream
        PieChart pieChart = findViewById(R.id.pieChart);

=======
        sharedPreferences = getSharedPreferences("EmotionPrefs", Context.MODE_PRIVATE);
        gsonEditor = GsonEditor.getInstance(sharedPreferences.getString("data", ""));

        PieChart pieChart = findViewById(R.id.pieChart);

        String json;

        json = sharedPreferences.getString("data", "");
        gsonEditor = GsonEditor.getInstance();
        gsonEditor.parseGson(json);

>>>>>>> Stashed changes
        ArrayList<PieEntry> topThree = new ArrayList<>();
        topThree.add(new PieEntry(34, "Happiness"));
        topThree.add(new PieEntry(26, "Sadness"));
        topThree.add(new PieEntry(15, "Anger"));

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

    public void buttonClick(View view) {
        Intent i = new Intent(PieChartActivity.this, StatsActivity.class);
        PieChartActivity.this.startActivity(i);
    }
}