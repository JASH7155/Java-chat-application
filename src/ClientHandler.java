import java.io.*;
import java.net.*;

public class ClientHandler extends Thread {
    private Socket socket;
    private ChatServer server;
    private BufferedReader in;
    private PrintWriter out;

    public ClientHandler(Socket socket, ChatServer server) {
        this.socket = socket;
        this.server = server;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException ignored) {
        }
    }

    public void run() {
        try {
            String name = in.readLine();
            server.broadcast(name + " joined the chat", this);

            String msg;
            while ((msg = in.readLine()) != null) {
                server.broadcast(name + ": " + msg, this);
            }
        } catch (IOException ignored) {
        } finally {
            server.removeClient(this);
            try {
                socket.close();
            } catch (IOException ignored) {
            }
        }
    }

    public void send(String msg) {
        out.println(msg);
    }
}
