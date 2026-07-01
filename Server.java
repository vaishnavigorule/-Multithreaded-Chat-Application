package Multithreaded;

import java.io.*;
import java.net.*;
import java.util.*;

public class server {

    static Vector<ClientHandler> clients = new Vector<>();

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(1234);

        System.out.println("Server Started...");

        while (true) {

            Socket socket = serverSocket.accept();

            System.out.println("New Client Connected");

            ClientHandler client =
                    new ClientHandler(socket);

            clients.add(client);

            Thread t = new Thread(client);
            t.start();
        }
    }
}

class ClientHandler implements Runnable {

    private static final String Server = null;
	Socket socket;
    BufferedReader in;
    PrintWriter out;

    public ClientHandler(Socket socket) throws IOException {

        this.socket = socket;

        in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));

        out = new PrintWriter(
                socket.getOutputStream(), true);
    }

    @Override
    public void run() {

        String message;

        try {

            while ((message = in.readLine()) != null) {

                for (ClientHandler client : server.clients) {

                    if (client != this) {
                        client.out.println(message);
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("Client Disconnected");
        }
    }
}
