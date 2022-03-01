package yadryshnikova;

import java.util.Arrays;

public class AverageVal {

 /*   public static float main() {
        //получает ?? коорд. и время(дату);
        //будет доступ к функ., которая классифицирует эмоции по координатам;
        //спросить у Влады
    } */

    public static double avg(int[] array) {
        return Arrays.stream(array).average().getAsDouble();
    }

}
// получает shared preferences
// Тут среднее значение
