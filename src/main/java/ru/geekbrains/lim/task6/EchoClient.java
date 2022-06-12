package ru.geekbrains.lim.task6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class EchoClient {
    private static final String SERVER_ADDR = "localhost";
    private static final int SERVER_PORT = 8189;
    private static final String SERVER_TO_TERMINATE = "/end";
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;



    public static void main(String[] args) {
        EchoClient echoClient = new EchoClient();
        echoClient.start();
    }

    private void start() {
        try {
            openConnection();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void openConnection() throws IOException {
        socket = new Socket(SERVER_ADDR, SERVER_PORT);
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
        PrintThread printThread = new PrintThread(true, out);
        printThread.start();
        Thread thread = new Thread(() -> {
            try {
                while (true) {
                    final String message = in.readUTF();
                    if (message.equalsIgnoreCase(SERVER_TO_TERMINATE)) {
                        out.writeUTF(SERVER_TO_TERMINATE);
                        printThread.setServerActive(false);
                        printThread.join();
                        break;
                    }
                    System.out.println("Сообщение от сервера " + message);
                }
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
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
                throw new RuntimeException(e);
            }
        }
        if (out != null) {
            try {
                out.close();
                out = null;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
