package group_project.main.emotionalspectrum;

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
            float xSq = (x - R) * (x - R);
            float ySq = (y - R) * (y - R);
            float RSq = R * R;

            if (xSq+ ySq < RSq) {
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


                if ((xSq + ySq) < ((8 * RSq) / 10)) {
                    Int = 1;
                }
                if ((xSq + ySq) < ((7 * RSq) / 10)) {
                    Int = 2;
                }
                if ((xSq + ySq) < ((5.5 * RSq) / 10)) {
                    Int = 3;
                }
                if ((xSq + ySq) < ((4 * RSq) / 10)) {
                    Int = 4;
                }
                if ((xSq + ySq) < ((2.5 * RSq) / 10)) {
                    Int = 5;
                }
                if ((xSq + ySq) < ((1.5 * RSq) / 10)) {
                    Int = 6;
                }
                if ((xSq + ySq) < ((1 * RSq) / 10)) {
                    Int = 7;
                }
                if ((xSq + ySq) < ((0.65 * RSq) / 10)) {
                    Int = 8;
                }
                if ((xSq + ySq) < ((0.4 * RSq) / 10)) {
                    Int = 9;
                }
                if ((xSq + ySq) < ((0.2 * RSq) / 10)) {
                    Int = 10;
                }

                if (y > R) {
                    Int--;
                }
                ID = (float) ((x + y) * 0.36 * S * Int);

            }
            return new Record(ID,S,Int,Emotion,"");
        }
}

