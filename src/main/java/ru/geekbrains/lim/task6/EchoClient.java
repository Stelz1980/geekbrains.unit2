package ru.geekbrains.lim.task6;

import javax.sound.sampled.Port;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class EchoClient {
    private static final String SERVER_ADDR = "localhost";
    private static final int SERVER_PORT = 8189;


    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(SERVER_ADDR, SERVER_PORT);
        try (

             Scanner scanner = new Scanner(System.in);
        ) {
            Thread listenThread = new Thread(() -> {
                System.out.println(Thread.currentThread().getName());
                while (true) {
                    try {

                        DataInputStream in = new DataInputStream(socket.getInputStream());
                        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                        String strFromServer = in.readUTF();
                        if (strFromServer.equalsIgnoreCase("Echo: \\end")) {
                            break;
                        }
                        System.out.println(strFromServer);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            listenThread.setDaemon(true);
            listenThread.start();

            Thread sendThread = new Thread(() -> {
                System.out.println(Thread.currentThread().getName());
                while (true) {
                    String sendStr = scanner.nextLine();
                    if (sendStr.equalsIgnoreCase("/end")) {
                        break;
                    }
                    //out.writeUTF(sendStr);
                }
            });
            sendThread.setDaemon(true);
            sendThread.start();
        }

    }


}
