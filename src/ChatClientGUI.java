import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;

public class ChatClientGUI extends Application {
    private TextArea chatArea;
    private TextField inputField;
    private PrintWriter out;
    private BufferedReader in;
    private String userName;

    @Override
    public void start(Stage stage) {
        chatArea = new TextArea();
        chatArea.setEditable(false);
        chatArea.setWrapText(true);
        chatArea.setPrefHeight(350);

        inputField = new TextField();
        inputField.setPromptText("Type a message...");
        inputField.setOnAction(e -> sendMessage());

        Button sendButton = new Button("Send");
        sendButton.setPrefWidth(70);
        sendButton.setOnAction(e -> sendMessage());

        HBox inputBar = new HBox(10, inputField, sendButton);
        inputBar.setPadding(new Insets(10));

        VBox root = new VBox(10, chatArea, inputBar);
        root.setPadding(new Insets(10));

        askName();
        Scene scene = new Scene(root, 430, 500);
        stage.setScene(scene);
        stage.setTitle("Java Chat - You are: " + userName);
        stage.show();

        connectToServer();
        startReaderThread();
    }

    private void askName() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Enter Your Name");
        dialog.setHeaderText("Join the chat");
        dialog.setContentText("Name:");

        userName = dialog.showAndWait().orElse("User");
    }

    private void connectToServer() {
        try {
            Socket socket = new Socket("localhost", 5000);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            out.println(userName);
        } catch (Exception e) {
            Platform.runLater(() -> chatArea.appendText("Unable to connect to server\n"));
        }
    }

    private void startReaderThread() {
        Thread listener = new Thread(() -> {
            try {
                String msg;
                while ((msg = in.readLine()) != null) {
                    String finalMsg = msg;
                    Platform.runLater(() -> chatArea.appendText(finalMsg + "\n"));
                }
            } catch (Exception ignored) {}
        });

        listener.setDaemon(true);
        listener.start();
    }

    private void sendMessage() {
        String msg = inputField.getText().trim();
        if (msg.isEmpty()) return;

        // Send to server
        out.println(msg);

        // ALSO show your own message in your window
        chatArea.appendText("You: " + msg + "\n");

        inputField.clear();
    }

    public static void main(String[] args) {
        launch();
    }
}
