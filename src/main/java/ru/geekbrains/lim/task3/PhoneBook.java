package ru.geekbrains.lim.task3;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class PhoneBook {


    public static void main(String[] args) {

        Map<String, HashSet<String>> phoneBook = new HashMap<>();
        add(phoneBook, "Ivanov", "81234567");
        add(phoneBook, "Ivanov", "8123w567");
        add(phoneBook, "Ivanov", "8123w564");
        add(phoneBook, "Ivanov", "8123w564");
        add(phoneBook, "Petrov", "8123w564");
        add(phoneBook, "Petrov", "8123w56e");
        get(phoneBook, "Ivanov");
        get(phoneBook, "Smirnov");
    }

    private static void add(Map<String, HashSet<String>> phoneBook, String secondName, String phoneNumber) {
        if (phoneBook.containsKey(secondName)) {
            phoneBook.get(secondName).add(phoneNumber);
        } else {
            HashSet<String> newPhoneNumber = new HashSet<>();
            newPhoneNumber.add(phoneNumber);
            phoneBook.put(secondName, newPhoneNumber);
        }
    }

    private static void get(Map<String, HashSet<String>> phoneBook, String secondName) {
        if (phoneBook.containsKey(secondName)) {
            System.out.print("Абонент " + secondName + ": ");
            System.out.println(phoneBook.get(secondName));
        } else {
            System.out.println("Такого абонента нет: " + secondName);
        }
    }

}
