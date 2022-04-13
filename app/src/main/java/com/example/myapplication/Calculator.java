package com.example.myapplication;

public class Calculator {
    public static Calculator instance = null;

    public static Calculator getInstance() {
        if (instance == null)
            instance = new Calculator();

        return instance;
    }

    static Record calculate(float x, float y, float R) {
        String Emotion;
        int S = 0, Int = 0;
        float ID = 0;
        Emotion = "out_of_circle";
        float xSq = (x - R)*(x-R);
        float ySq = (y - R)*(y-R);
        float RSq = R*R;

        if ( xSq+ ySq < RSq) {
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
            float ySq1 = (y + R)*(y + R);
            for (int i = 1; i < 11; i++) {
                if (xSq + ySq1 < (i * RSq) / 10)
                    Int = 11 - i;
                else
                    Int = 1;
            }

            ID = (float) ((x + y) * 0.36 * S * Int);

        }
        return new Record(ID,S,Int,Emotion,"");
    }
}

