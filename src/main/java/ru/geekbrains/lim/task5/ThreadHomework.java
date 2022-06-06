package ru.geekbrains.lim.task5;

import java.util.Arrays;

public class ThreadHomework {
    static final int SIZE = 10_000_000;
    static final int HALF = SIZE / 2;

    public static void main(String[] args) {
        System.out.println(Arrays.equals(firstMethod(),secondMethod()));
    }

    private static float[] fillArrayWith1(float[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1.0f;
        }
        return arr;
    }

    private static float[] fillArrayWithComplexFormula(float[] arr, int startPosition) {
        for (int i = 0; i < arr.length ; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + (i + startPosition) / 5) * Math.cos(0.2f + (i + startPosition)
                    / 5) * Math.cos(0.4f + (i + startPosition) / 2));
        }
        return arr;
    }

    private static float[]  firstMethod() {
        float[] arr = new float[SIZE];
        arr = fillArrayWith1(arr);
        long startTime = System.currentTimeMillis();
        arr = fillArrayWithComplexFormula(arr, 0);
        System.out.println("One thread time: " + (System.currentTimeMillis() -
                startTime) + " ms.");
        return arr;
    }

    public static float[]  secondMethod() {
        float[] arr = new float[SIZE];
        float[] leftArr = new float[HALF];
        float[] rightArr = new float[HALF];
        arr = fillArrayWith1(arr);
        long startTime = System.currentTimeMillis();
        System.arraycopy(arr, 0, leftArr, 0, HALF);
        System.arraycopy(arr, HALF, rightArr, 0, HALF);
        Thread leftThread = new Thread(() -> fillArrayWithComplexFormula(leftArr, 0));
        Thread rightThread =  new Thread(() -> fillArrayWithComplexFormula(rightArr, HALF));
        try {
            leftThread.start();
            rightThread.start();
            leftThread.join();
            rightThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.arraycopy(leftArr, 0, arr, 0, HALF);
        System.arraycopy(rightArr, 0, arr, HALF, HALF);
        System.out.println("Two thread time: " + (System.currentTimeMillis() - startTime) + " ms.");
        return arr;
    }


}
