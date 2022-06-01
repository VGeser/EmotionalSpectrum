package group_project.main.emotionalspectrum;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import group_project.main.emotionalspectrum.graphs.BarChartActivity;
import group_project.main.emotionalspectrum.graphs.BarIntensActivity;
import group_project.main.emotionalspectrum.graphs.LineChartActivity;
import group_project.main.emotionalspectrum.graphs.PieChartActivity;
import group_project.main.emotionalspectrum.graphs.RadarChartActivity;
import group_project.main.emotionalspectrum.graphs.RawActivity;

public class StatsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        findViewById(R.id.buttonRaw).setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), RawActivity.class)));

        findViewById(R.id.buttonBarChart).setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), BarChartActivity.class)));

        findViewById(R.id.buttonBarChartI).setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), BarIntensActivity.class)));

        findViewById(R.id.buttonRadarChart).setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), RadarChartActivity.class)));

        findViewById(R.id.buttonPieChart).setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), PieChartActivity.class)));

        findViewById(R.id.buttonLineChart).setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), LineChartActivity.class)));
    }

    public void buttonClick(View view) {
        Intent i = new Intent(StatsActivity.this, MainActivity.class);
        StatsActivity.this.startActivity(i);
    }
}
