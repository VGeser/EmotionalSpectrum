package com.example.myapplication;

public class Record {
    public double getID() {
        return ID;
    }

    public void setID(double ID) {
        this.ID = ID;
    }

    public int getS() {
        return S;
    }

    public int getIntensity() {
        return intensity;
    }

    public String getEmotionName() {
        return emotionName;
    }

    public String getDate() {
        return date;
    }

    private double ID;

    public void setS(int s) {
        S = s;
    }

    public void setIntensity(int intensity) {
        this.intensity = intensity;
    }

    public void setEmotionName(String emotionName) {
        this.emotionName = emotionName;
    }

    public void setDate(String date) {
        this.date = date;
    }

    private int S;
    private int intensity;
    private String emotionName, date;

    public Record(double ID, int s, int intensity, String emotionName, String date) {
        this.ID = ID;
        this.S = s;
        this.intensity = intensity;
        this.emotionName = emotionName;
        this.date = date;
    }
}
