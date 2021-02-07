package ru.danilsibgatullin.homeworklesson5.services;

public class ActionMethodsMultiStream {
    static final int size = 10000000;
    static final int h = size / 2;
    static float[] arr = new float[size];
    static float[] arr1 = new float[h];
    static float[] arr2 = new float[h];
    static float[] arrOut =new float[size];



    static long arrAddNumbers(){
        long a = System.currentTimeMillis();
        for (int index =0;index<arr.length;index++) {
            arr[index] = 1f;
        }
        return System.currentTimeMillis()-a;
    }

    static void calcArrElementsArr1(){
        System.arraycopy(arr,0,arr1,0,h);
        for (int i=0;i<arr1.length;i++) {
            arr1[i]=(float)(arr1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
    }

    static void calcArrElementsArr2(){
        System.arraycopy(arr,h,arr2,0,h);
        for (int i=0;i<arr2.length;i++) {
            int s = h+i;// так как нам надо рассчитать вторую половину , а в фомруле есть индекс.
            arr2[i]=(float)(arr2[i] * Math.sin(0.2f + s / 5) * Math.cos(0.2f + s / 5) * Math.cos(0.4f + s / 2));
        }
    }

    public static void printResult() {
        arrAddNumbers();
        Thread s1 = new Thread(()->{
            calcArrElementsArr1();
        });

        Thread s2 = new Thread(()->{
            calcArrElementsArr2();
        });

        long a=System.currentTimeMillis();
        s1.start();
        s2.start();
        System.arraycopy(arr1,0,arrOut,0,h);
        System.arraycopy(arr2,0,arrOut,h,h);
        try{
            s1.join();
            s2.join();
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        long time = (System.currentTimeMillis()-a);
        System.out.println("Обновление массива c использования потоков по формуле в милисекундах :" + time);
    }
}

