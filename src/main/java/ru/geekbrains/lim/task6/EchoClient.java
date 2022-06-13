package ru.geekbrains.lim.task6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class EchoClient {
    private static final String SERVER_ADDR = "localhost";
    private static final int SERVER_PORT = 8189;
    private static final String SERVER_TO_TERMINATE = "/end";
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    public static void main(String[] args) {
        new EchoClient().start();
    }

    private void start() {
        openConnection();
    }

    private void openConnection() {
        try {
            socket = new Socket(SERVER_ADDR, SERVER_PORT);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        PrintThread printThread = new PrintThread(out);
        printThread.setDaemon(true);
        printThread.start();
        Thread thread = new Thread(() -> {
            try {
                while (true) {
                    final String message = in.readUTF();
                    if (message.equalsIgnoreCase(SERVER_TO_TERMINATE)) {
                        out.writeUTF(SERVER_TO_TERMINATE);
                        break;
                    }
                    System.out.println("Сообщение от сервера " + message);
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            } finally {
                closeConnection();
            }
        });
        thread.setPriority(Thread.MAX_PRIORITY);
        thread.start();
    }

    private void closeConnection() {
        if (in != null) {
            try {
                in.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        if (out != null) {
            try {
                out.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
