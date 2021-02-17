package ru.danilsibgatullin.homeworklesson7.clientside.ui;

import javax.swing.*;
import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class ChatInterface extends JFrame {

    final Color elementsColor = Color.lightGray; // константа для стиля
    final Color backgroundColor = Color.DARK_GRAY; // константа для стиля
    private JTextArea textArea = new JTextArea(40,40);
    private JTextField messageLine = new JTextField(30);
    DataInputStream  dis;
    DataOutputStream dos;

    public ChatInterface(Socket socket, String serverAddress, Integer serverPort) {

        try {
            connection(socket,serverAddress,serverPort);
        }
        catch (IOException ignore){

        }

        //задаем параметры окна
        setTitle("Broadcast chat");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(900,700);
        setLocation(20,20);
        setResizable(false);
        BoxLayout gLine = new BoxLayout(getContentPane(),BoxLayout.X_AXIS);
        setLayout(gLine);
        JPanel group2 = new JPanel();
        group2.setSize(700,700);
        group2.setLayout(new BoxLayout(group2,BoxLayout.Y_AXIS)) ;
        group2.setBackground(backgroundColor);
        group2.setAutoscrolls(false);

        //Вспомогательные панели

        JPanel panCentr = new JPanel();
        JPanel panFoot= new JPanel();
        panFoot.setBackground(backgroundColor);
        panCentr.setBackground(backgroundColor);

        //Набор кнопок
        JButton okButton =new JButton("ОК");
        okButton.setBackground(elementsColor);
        JButton endButton =new JButton("End session");
        endButton.setBackground(elementsColor);
        //JButton addContactButton =new JButton("Add Contact");
        //addContactButton.setBackground(elementsColor);

        //Настройка текстового поля

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBackground(elementsColor);
        scrollPane.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );
        scrollPane.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );
        textArea.setEditable(false);
        textArea.setMaximumSize(new Dimension(40,40));
        textArea.setAutoscrolls(true);
        textArea.setBackground(elementsColor);


        //Строка ввода
        messageLine.setBackground(elementsColor);

        //Отправка по кнопке
        okButton.addActionListener(e -> send());

        //Закрываем сессию
        endButton.addActionListener(e-> {
        send("/end");
        dispose();
            try {
                 new AutorityInterface();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        //Листенер на добавление сообщения через Enter
        messageLine.addActionListener(e -> send() );

        //добавляем элементы в контейнеры
        panCentr.add(scrollPane);
        panFoot.add(messageLine);
        panFoot.add(okButton);
        panFoot.add(endButton);
        group2.add(panCentr);
        group2.add(panFoot);
        add(group2);
        pack();
        setVisible(true);
    }

    public void connection (Socket socketAuth,String serverAddress,Integer serverPort) throws IOException {
        dis = new DataInputStream(socketAuth.getInputStream());
        dos = new DataOutputStream(socketAuth.getOutputStream());
        new Thread(() -> {

            try {
                while (true) {
                    String message = dis.readUTF();
                    if (message.equalsIgnoreCase("/finish")) {
                        this.dispose();
                        break;
                    }
                    if (!message.equals("")){
                    textArea.append(""+message + "\n");
                    }
                }
            } catch (IOException ignored){
            }

        }).start();
    }

    public void send() {
        if (messageLine.getText() != null && !messageLine.getText().trim().isEmpty()) {
            try {
                dos.writeUTF(messageLine.getText());
                messageLine.setText("");
            } catch (IOException ignored) {
            }
        }
    }

    public void send(String endMessage) {
            try {
                dos.writeUTF(endMessage);
                messageLine.setText("");
            } catch (IOException ignored) {
            }
    }
}
