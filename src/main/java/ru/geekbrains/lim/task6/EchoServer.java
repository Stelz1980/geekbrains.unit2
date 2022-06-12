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
            throw new RuntimeException(e);
        }

    }

    private void startServer() throws IOException {

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Сервер запущен. Ждем подключения клиента ");
            socket = serverSocket.accept();
            System.out.println("Клиент подключился");
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            PrintThread printThread = new PrintThread(true, out);
            printThread.start();
            Thread listenClient = new Thread(() -> {
                try {
                    while (true) {
                        String str;
                        str = in.readUTF();
                        if (str.equalsIgnoreCase(SERVER_TO_TERMINATE)) {
                            out.writeUTF(str);
                            printThread.setServerActive(false);
                            printThread.join();
                            break;
                        }
                        System.out.println("Сообщение от клиента: " + str);
                        out.writeUTF(str);
                    }
                } catch (IOException | InterruptedException e) {
                    throw new RuntimeException(e);
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
