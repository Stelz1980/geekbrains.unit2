package ru.geekbrains.lim.task3;

import java.util.*;

public class CountWords {

    public static void main(String[] args) {
        final String sourceText = "Написать простой класс Телефонный Справочник, который хранит в себе список" +
                " фамилий и телефонных номеров. В этот телефонный справочник с помощью метода add() можно добавлять записи, а с помощью метода get() искать номер телефона по фамилии. Следует учесть, что под одной фамилией может быть несколько телефонов (в случае однофамильцев) тогда при запросе такой фамилии должны выводиться все телефоны";
        List<String> arr = Arrays.asList(sourceText.toLowerCase(Locale.ROOT).replace(".", "").replace(",", "").replace("(", "").replace(")", "").split(" "));
        Map<String, Integer> result = new HashMap<>();
        for (String word : arr) {
            if (result.containsKey(word)) {
                result.replace(word, result.get(word) + 1);
            } else {
               result.put(word, 1);
            }
        }
        System.out.println(result);
    }

}
