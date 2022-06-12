package ru.geekbrains.lim.task6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    private static final int PORT = 8189;
    private static final String SERVER_TO_TERMINATE = "/end";
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    public static void main(String[] args) {
        new EchoServer().start();
    }

    private void start() {
        try {
            startServer();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    private void startServer() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Сервер запущен. Ждем подключения клиента ");
            socket = serverSocket.accept();
            System.out.println("Клиент подключился");
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            PrintThread printThread = new PrintThread( out);
            printThread.setDaemon(true);
            printThread.start();
            Thread listenClient = new Thread(() -> {
                try {
                    while (true) {
                        String str;
                        str = in.readUTF();
                        if (str.equalsIgnoreCase(SERVER_TO_TERMINATE)) {
                            out.writeUTF(str);
                            break;
                        }
                        System.out.println("Сообщение от клиента: " + str);
                        out.writeUTF(str);
                    }
                } catch (IOException  e) {
                    System.out.println(e.getMessage());
                } finally {
                    closeConnection();
                }
            });
            listenClient.start();
        }
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
