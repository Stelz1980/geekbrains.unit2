package ru.geekbrains.lim.task6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    private static final int PORT = 8189;

    public static void main(String[] args) throws IOException {
        Socket socket;
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Сервер запущен. Ждем подключения клиента ");
            socket = serverSocket.accept();
            System.out.println("Клиент подключился");
            try (DataInputStream in = new DataInputStream(socket.getInputStream());
                 DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {
                Thread listenThread = new Thread(() -> {
                    while (true) {
                        String str;
                        try {
                            str = in.readUTF();
                            if (str.equalsIgnoreCase("/end")) {
                                break;
                            }
                            out.writeUTF("Echo: " + str);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }

                });
                listenThread.setDaemon(true);
                listenThread.start();
            }
        }
    }
}
