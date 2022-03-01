package ru.nsu.yadryshnikova;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class EmotionCharts extends Application {
    @Override public void start(Stage stage) {
        stage.setTitle("Line Chart Sample");
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Date");

        final LineChart<String,Number> lineChart =
                new LineChart<String,Number>(xAxis,yAxis);

        lineChart.setTitle("Emotion chart");

        XYChart.Series series = new XYChart.Series();
        series.setName("My portfolio");

        series.getData().add(new XYChart.Data("1 Feb", 23));
        series.getData().add(new XYChart.Data("4 Feb", 14));
        series.getData().add(new XYChart.Data("5 Feb", 15));
        series.getData().add(new XYChart.Data("6 Feb", 24));
        series.getData().add(new XYChart.Data("7 Feb", 34));
        series.getData().add(new XYChart.Data("8 Feb", 36));
        series.getData().add(new XYChart.Data("9 Feb", 22));
        series.getData().add(new XYChart.Data("11 Feb", 45));
        series.getData().add(new XYChart.Data("13 Feb", 43));
        series.getData().add(new XYChart.Data("14 Feb", 17));
        series.getData().add(new XYChart.Data("15 Feb", 29));
        series.getData().add(new XYChart.Data("16 Feb", 25));
        series.getData().add(new XYChart.Data("17 Feb", 23));
        series.getData().add(new XYChart.Data("18 Feb", 14));
        series.getData().add(new XYChart.Data("19 Feb", 15));
        series.getData().add(new XYChart.Data("21 Feb", 24));
        series.getData().add(new XYChart.Data("22 Feb", 34));
        series.getData().add(new XYChart.Data("23 Feb", 36));
        series.getData().add(new XYChart.Data("24 Feb", 22));
        series.getData().add(new XYChart.Data("26 Feb", 45));
        series.getData().add(new XYChart.Data("27 Feb", 43));
        series.getData().add(new XYChart.Data("28 Feb", 17));

        Scene scene  = new Scene(lineChart,800,600);
        lineChart.getData().add(series);

        stage.setScene(scene);
        stage.show();
    }

}
