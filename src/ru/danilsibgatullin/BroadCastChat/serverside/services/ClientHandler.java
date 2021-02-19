package ru.danilsibgatullin.BroadCastChat.serverside.services;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;


public class ClientHandler {
    private MyServer myServer;
    private Socket socket;
    private DataInputStream dis;
    private DataOutputStream dos;

    private String name;



    public ClientHandler(MyServer myServer, Socket socket) {
        try {

            this.myServer = myServer;
            this.socket = socket;
            this.dis = new DataInputStream(socket.getInputStream());
            this.dos = new DataOutputStream(socket.getOutputStream());
            this.name = "";

            new Thread(() -> {
                try {
                    authentication();
                    readMessage();
                } catch (IOException ignored) {
                    ignored.printStackTrace();
                } finally {
                    closeConnection();
                }

            }).start();


        } catch (IOException e) {
            closeConnection();
            throw new RuntimeException("Problem with ClientHandler");
        }
    }

    public void authentication() throws IOException {

            String str = dis.readUTF();
            if (str.startsWith("/auth")) { //  /auth login password
                String[] arr = str.split("\\s");
                String nick = myServer
                        .getAuthService()
                        .getNickByLoginAndPassword(arr[1], arr[2]);
                if (nick != null) {
                    if (!myServer.isNickBusy(nick)) {
                        sendMessage("/authok " + nick);
                        name = nick;
                        myServer.broadcastMessage("Hello " + name);
                        myServer.subscribe(this);
                        return;
                    } else {
                        sendMessage("Nick is busy");
                    }

                } else {
                    System.out.println("Client access deny");
                    sendMessage("Wrong login and password");
                }
            }
    }

    public void readMessage() throws IOException {
        while (true) {
            try {
                String messageFromClient = dis.readUTF();
                System.out.println(name + " send message " + messageFromClient);
                if (messageFromClient.equals("/end")) {
                    System.out.println("Client live server");
                    return;
                }
                if (messageFromClient.startsWith("/w")) {
                    String[] arr = messageFromClient.split("\\s");
                    myServer.whisperMessage(arr[1], name + "(whispered): " + messageFromClient.substring(3 + arr[1].length()));
                } else {
                    myServer.broadcastMessage(name + ": " + messageFromClient);
                }
            }catch (SocketException e){
                System.out.println("Client live server");
                return;
            }
        }
    }

    public void sendMessage(String message) {
        try {
            dos.writeUTF(message);
        } catch (IOException ignored) {
        }
    }

    private void closeConnection() {
        myServer.unsubscribe(this);
        myServer.broadcastMessage(name + " Leave chat");
        try {
            dis.close();
            dos.close();
            socket.close();
        } catch (IOException ignored) {
        }
    }

    public String getName() {
        return name;
    }
}
