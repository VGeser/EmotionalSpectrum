package com.example.myapplication;

import android.app.Activity;
import android.content.Context;

public class Calculator {
    public static Calculator instance = null;

    public static Calculator getInstance() {
        if (instance == null)
            instance = new Calculator();

        return instance;
    }

    static String calculate(float x, float y, float R) {
        String Emotion;
        int S = 0, Int = 0;
        double ID = 0;
        Emotion = "out_of_circle";

        if (Math.pow((x - R), 2) + Math.pow((y - R), 2) < Math.pow((R), 2)) {
            if ((x > R) && (y > (R / 392.441860465) * x - (R / 2.64864864865))) {
                S = 13;
                Emotion = "disguise";
            } else if ((x > R) && (y > (R / 1661.53846154) * x + (R / 1.48171275672))) {
                S = 3;
                Emotion = "suffering";
            } else if ((x > R) && (y > -(R / 1661.53846154) * x + (R / 0.75476234071))) {
                S = 7;
                Emotion = "shame";
            } else if ((x > R) && (y > -(R / 392.441860465) * x + (R / 0.42080785757))) {
                S = 23;
                Emotion = "fear";
            } else if (x > R) {
                S = 19;
                Emotion = "guilt";
            } else if ((x <= R) && (y < (R / 392.441860465) * x - (R / 2.64864864865))) {
                S = 5;
                Emotion = "anger";
            } else if ((x <= R) && (y < (R / 1661.53846154) * x + (R / 1.48171275672))) {
                S = 2;
                Emotion = "joy";
            } else if ((x <= R) && (y < -(R / 1661.53846154) * x + (R / 0.75476234071))) {
                S = 11;
                Emotion = "pride";
            } else if ((x <= R) && (y < -(R / 392.441860465) * x + (R / 0.42080785757))) {
                S = 29;
                Emotion = "anticipation";
            } else {
                S = 17;
                Emotion = "contempt";
            }

            for (int i = 1; i < 11; i++) {
                if (Math.pow((x - R), 2) + Math.pow((y + R), 2) < (i * Math.pow((R), 2)) / 10)
                    Int = 11 - i;
                else
                    Int = 1;
            }

            ID = (x + y) * 0.36 * S * Int;

        }
        return Emotion;
    }
}

