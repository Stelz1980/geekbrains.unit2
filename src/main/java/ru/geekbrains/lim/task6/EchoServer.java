package ru.geekbrains.lim.task6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class EchoServer {
    private static final int PORT = 8189;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    public static void main(String[] args) {
        new EchoServer().start();
    }

    private void start() {
        try {
            startServer();
            Scanner scanner = new Scanner(System.in);
            while (socket != null) {
                sendMessage(scanner.nextLine());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void sendMessage(String message) {
        try {
            out.writeUTF(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void startServer() throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Сервер запущен. Ждем подключения клиента ");
        socket = serverSocket.accept();
        System.out.println("Клиент подключился");
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
        Thread listenClient = new Thread(() -> {
            try {
                while (true) {
                    String str;
                    str = in.readUTF();
                    if (str.equalsIgnoreCase("/end")) {
                        out.writeUTF(str);
                        break;
                    }
                    System.out.println("Сообщение от клиента: " + str);
                    out.writeUTF(str);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                closeConnection();
            }
        });
        listenClient.start();
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
