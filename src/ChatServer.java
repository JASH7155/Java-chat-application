import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
    private ServerSocket serverSocket;
    private final List<ClientHandler> clients = new ArrayList<>();

    public ChatServer(int port) {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server running on port " + port);
            acceptClients();
        } catch (IOException e) {
            System.out.println("Unable to start server");
        }
    }

    private void acceptClients() {
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                ClientHandler handler = new ClientHandler(socket, this);
                clients.add(handler);
                handler.start();
            } catch (IOException e) {
                System.out.println("Error accepting client");
            }
        }
    }

    public void broadcast(String msg, ClientHandler sender) {
        synchronized (clients) {
            for (ClientHandler c : clients) {
                if (c != sender) c.send(msg);
            }
        }
    }

    public void removeClient(ClientHandler handler) {
        synchronized (clients) {
            clients.remove(handler);
        }
    }

    public static void main(String[] args) {
        new ChatServer(5000);
    }
}
