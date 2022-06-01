package ru.geekbrains.lim.task3.phonebook;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Executor {
    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();
        phoneBook.add("Ivanov", "81234567");
        phoneBook.add("Ivanov", "8123w567");
        phoneBook.add("Ivanov", "8123w564");
        phoneBook.add("Ivanov", "8123w564");
        phoneBook.add("Petrov", "8123w564");
        phoneBook.add("Petrov", "8123w56e");
        phoneBook.get("Ivanov");
        phoneBook.get("Smirnov");
    }

}
