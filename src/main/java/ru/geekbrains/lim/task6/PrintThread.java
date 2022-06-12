package ru.geekbrains.lim.task6;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

public  class PrintThread extends Thread {
    private volatile boolean isServerActive;
    private final DataOutputStream out;

    public PrintThread(boolean isServerActive, DataOutputStream out ) {
        this.isServerActive = isServerActive;
        this.out = out;
    }

    public boolean isServerActive() {
        return isServerActive;
    }

    public void setServerActive(boolean serverActive) {
        isServerActive = serverActive;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                if (isServerActive() ) {
                    final String message = scanner.nextLine();
                    sendMessage(message);
                } else {
                    System.out.println("Сервер погас");
                    break;
                }
                sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void sendMessage(String message) {
        try {
            out.writeUTF(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

