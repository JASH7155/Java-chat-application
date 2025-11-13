# Java Chat Application (JavaFX + Sockets)

A simple and clean multi-client chat application built using **Java**, **Sockets**, and **JavaFX**.  
Each client connects to a central server, and messages are broadcast to all connected users in real-time.

This project demonstrates networking, threading, and GUI development using pure Java without external frameworks.

---

## 🚀 Features

### ✔ Multi-Client Chat  
Multiple users can connect and chat simultaneously.

### ✔ Real-Time Message Broadcasting  
Every message from a client is instantly broadcast to all other clients.

### ✔ Clean JavaFX UI  
Simple, neat, easy-to-understand interface built using JavaFX.

### ✔ Username Support  
Each user enters a unique chat name before joining.

### ✔ Threaded Server  
The server uses a separate thread for each connected client.

### ✔ Reliable I/O  
Handles client disconnects safely and prevents server crashes.

---

## 🛠 Tech Stack

- **Java 17+**
- **JavaFX (controls + fxml modules)**
- **Java Sockets (TCP)**
- **Multithreading**
- **IntelliJ IDEA**

---

## 🏗 Project Structure

ChatApplication/
├── src/
│ ├── ChatServer.java
│ ├── ClientHandler.java
│ ├── ChatClient.java
│ └── ChatClientGUI.java
├── lib/ (JavaFX SDK)
└── README.md

yaml
Copy code

---

## ▶ How to Run the Project

### **1️⃣ Run the Server**
Open `ChatServer.java` and click **Run**.

A console window will show:
Server started on port 1234

shell
Copy code

### **2️⃣ Run the Client GUI**
Before running, configure JavaFX VM options:

--module-path "C:\javafx-sdk\lib" --add-modules javafx.controls,javafx.fxml

yaml
Copy code

Then run:  
`ChatClientGUI.java`

---

## 🧪 Sample Chat Flow

1. Run server  
2. Run multiple clients  
3. Enter username  
4. Start chatting  

Example log:

Mike: hello everyone!
Jash: hey Mike!

yaml
Copy code

---

## 📸 Screenshots (Add after uploading)

- Chat window UI  
- Server console  
- Multiple clients chatting  

---

## 💡 What You Learn

✔ Client-Server networking  
✔ Java threading  
✔ Sending/receiving data using sockets  
✔ JavaFX event handling  
✔ Designing clean GUI layouts  

---

## 📄 License

This project is open-source and free to use.

---

## 👤 Author

**Sai Jashwanth**  
Java Developer | Student | Tech Enthusiast

---

