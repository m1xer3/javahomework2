package ru.danilsibgatullin.homeworklesson5.services;

public class ActionMethodsNoMultiStream {

    static final int size = 10000000;
    static float[] arr = new float[size];

    static long arrAddNumbers(){
        long a = System.currentTimeMillis();
        for (int i =0;i<arr.length;i++) {
            arr[i] = 1f;
        }
        return System.currentTimeMillis()-a;
    }

    static long calcArrElements(){
        long a = System.currentTimeMillis();
        for (int i=0;i<arr.length;i++) {
            arr[i]=(float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        return System.currentTimeMillis()-a;
    }

    public static void printResult(){
        arrAddNumbers();
        System.out.println("Обновление массива без использования потоков по формуле в милисекундах " + calcArrElements());
    }


}
