package ru.geekbrains.lim.task6;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class PrintThread extends Thread {

    private final DataOutputStream out;

    public PrintThread(DataOutputStream out) {
        this.out = out;
    }

    public void run() {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                sendMessage(scanner.nextLine());
            }
        }

    }

    private void sendMessage(String message) {
        try {
            out.writeUTF(message);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

