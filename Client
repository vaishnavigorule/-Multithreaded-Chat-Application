package Multithreaded;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws Exception {

        Socket socket =
                new Socket("localhost", 1234);

        BufferedReader in =
                new BufferedReader(
                        new InputStreamReader(
                                socket.getInputStream()));

        PrintWriter out =
                new PrintWriter(
                        socket.getOutputStream(), true);

        Scanner sc = new Scanner(System.in);

        // Thread for receiving messages
        Thread receive = new Thread(() -> {

            try {

                String msg;

                while ((msg = in.readLine()) != null) {

                    System.out.println("\nMessage: " + msg);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        receive.start();

        // Send messages
        while (true) {

            String message = sc.nextLine();

            out.println(message);
        }
    }
}
