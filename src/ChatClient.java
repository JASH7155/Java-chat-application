import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ChatClient {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public ChatClient(String host, int port) {
        try {
            socket = new Socket(host, port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            startChat();
        } catch (IOException e) {
            System.out.println("Unable to connect to server");
        }
    }

    private void startChat() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String name = sc.nextLine();
        out.println(name);

        Thread reader = new Thread(() -> {
            try {
                String msg;
                while ((msg = in.readLine()) != null) {
                    System.out.println(msg);
                }
            } catch (IOException ignored) {
            }
        });
        reader.start();

        while (true) {
            String msg = sc.nextLine();
            out.println(msg);
        }
    }

    public static void main(String[] args) {
        new ChatClient("localhost", 5000);
    }
}
