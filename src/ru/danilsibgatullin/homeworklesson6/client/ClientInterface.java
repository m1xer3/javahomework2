package ru.danilsibgatullin.homeworklesson6.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;


public class ClientInterface extends JFrame {

    final Color elementsColor = Color.lightGray; // константа для стиля
    final Color backgroundColor = Color.DARK_GRAY; // константа для стиля
    private JTextArea textArea = new JTextArea(40,40);
    private JTextField messageLine = new JTextField(30);
    DataInputStream  dis;
    DataOutputStream dos;

    public ClientInterface(String serverAddress,Integer serverPort) {

        try {
            connection(serverAddress,serverPort);
        }
        catch (IOException ignore){

        }

        //задаем параметры окна
        setTitle("My cool chat!");
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

        //Вспомогательные панели распологаются

        JPanel panCentr = new JPanel();
        JPanel panFoot= new JPanel();
        panFoot.setBackground(backgroundColor);
        panCentr.setBackground(backgroundColor);

        //Набор кнопок
        JButton okButton =new JButton("ОК");
        okButton.setBackground(elementsColor);
        JButton delButton =new JButton("Clear");
        delButton.setBackground(elementsColor);
        JButton addContactButton =new JButton("Add Contact");
        addContactButton.setBackground(elementsColor);

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

        //Очитска ввода
        delButton.addActionListener(e->messageLine.setText(""));

        //Листенер на добавление сообщения через Enter
        messageLine.addActionListener(e -> send() );

        //добавляем элементы в контейнеры
        panCentr.add(scrollPane);
        panFoot.add(messageLine);
        panFoot.add(okButton);
        panFoot.add(delButton);
        group2.add(panCentr);
        group2.add(panFoot);
        add(group2);
        pack();
        setVisible(true);
    }

    public void connection (String serverAddress,Integer serverPort) throws IOException {
        Socket socket;
        socket = new Socket(serverAddress, serverPort);
        dis = new DataInputStream(socket.getInputStream());
        dos = new DataOutputStream(socket.getOutputStream());
        new Thread(() -> {

            try {
                while (true) {
                    String message = dis.readUTF();
                    if (message.equalsIgnoreCase("/finish")) {
                        this.dispose();
                        break;
                    }
                    if (!message.equals("")){
                    textArea.append("Сервер :"+message + "\n");
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
                textArea.append("Я :"+messageLine.getText() + "\n");
                messageLine.setText("");
            } catch (IOException ignored) {
            }
        }
    }



}
