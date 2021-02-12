package ru.danilsibgatullin.homeworklesson6.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public class ServerRun {


    public static void main(String[] args) throws IOException {
        final Scanner scan =new Scanner(System.in);
        Socket socket;
        try (ServerSocket serverSocket = new ServerSocket(8080)){
            System.out.println("Server start");
            socket = serverSocket.accept(); // ждем подключения клиента на порт 8080
            System.out.println("Client join");
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            //Поток для вывода сообщений от клиента
            Thread inputStream = new Thread(()->{
                System.out.println("Stream in up");
                try{
                    while (true){
                        try {
                            String message = dis.readUTF();
                            if (message.equalsIgnoreCase("/finish")) {
                                dos.writeUTF(message);
                                break;
                            }
                            System.out.println("Client say:"+message);
                        }
                        catch (SocketException e){
                            break;
                        }
                    }
                    System.out.println("Stream in down");
                }catch (IOException e){
                    e.printStackTrace();
                }
            });

            inputStream.start();

            //Поток для отправки сообщений клиенту
            Thread outputStream = new Thread(()->{
                try {
                    System.out.println("Stream out up");
                    while (true){
                        try{
                            dos.writeUTF(scan.nextLine());
                        }
                        catch (SocketException e){
                            break;
                        }
                    }
                   System.out.println("Stream out down");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            outputStream.start();

        }catch (IOException  ignore){
        }
    }
}
