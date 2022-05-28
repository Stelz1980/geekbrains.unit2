package ru.geekbrains.lim.task2;

import ru.geekbrains.lim.task2.exceptions.ArrayLengthException;

public class Executor {
    private static final int ARRAY_LENGTH = 4;

    public static void main(String[] args) {
        String[][] s = {
                {"12", "2", "3", "4" },
                {"12", "2", "3", "4" },
                {"12", "2A", "3", "4" },
                {"12", "2", "3", "4" }
        };

        try {
            System.out.println(sumArray(s));
        } catch (ArrayLengthException e) {
            e.printStackTrace();
        }

    }

    private static int sumArray(String[][] arr) throws ArrayLengthException {

        int s = 0;

        if (arr.length != ARRAY_LENGTH || arr[0].length != ARRAY_LENGTH) {
            throw new ArrayLengthException(String.format("Размер массива не равен %d x %d", ARRAY_LENGTH, ARRAY_LENGTH));
        }

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                try {
                    int a = Integer.parseInt(arr[i][j]);
                    s += a;
                } catch (NumberFormatException e) {
                    System.out.println(String.format("Значение массима [%d][%d]: %s не является числом ", i, j, arr[i][j]));
                    throw new RuntimeException();
                }
            }
        }
        return s;
    }

}
