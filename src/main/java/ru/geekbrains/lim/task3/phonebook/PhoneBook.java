package ru.geekbrains.lim.task3.phonebook;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class PhoneBook {

    private Map<String, HashSet<String>> phoneBook = new HashMap<>();

    public void add(String secondName, String phoneNumber) {
        if (phoneBook.containsKey(secondName)) {
            phoneBook.get(secondName).add(phoneNumber);
        } else {
            HashSet<String> newPhoneNumber = new HashSet<>();
            newPhoneNumber.add(phoneNumber);
            phoneBook.put(secondName, newPhoneNumber);
        }
    }

    public void get(String secondName) {
        if (phoneBook.containsKey(secondName)) {
            System.out.print("Абонент " + secondName + ": ");
            System.out.println(phoneBook.get(secondName));
        } else {
            System.out.println("Такого абонента нет: " + secondName);
        }
    }

}
