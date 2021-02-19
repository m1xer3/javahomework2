package ru.danilsibgatullin.BroadCastChat.clientside.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Locale;

public class AutorityInterface extends JFrame {
    private final Color elementsColor = Color.lightGray; // константа для стиля
    private final Color backgroundColor = Color.DARK_GRAY; // константа для стиля
    private JTextField logginField = new JTextField();
    private JTextField passwordField = new JTextField();
    DataInputStream  dis;
    DataOutputStream dos;
    Socket socket;
    private boolean isAutorized = false;
    Thread authTread;
    //Image logo;

    public AutorityInterface() throws IOException {


        //logo = ImageIO.read();
        //задаем параметры окна
        setTitle("Authoruty to Broadcast chat");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(410,210));
        setLocation(20,20);
        setResizable(false);
        setBackground(backgroundColor);

        //Вспомогательная панель
        //TopPanel topPanel =new TopPanel(logo);
        JPanel footPanel =new JPanel();
        footPanel.setBackground(backgroundColor);


        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(backgroundColor);
        centerPanel.setPreferredSize(new Dimension(400,200));

        //Набор label
        JLabel serverLabel =new JLabel("Сервер");
        serverLabel.setBackground(elementsColor);
        serverLabel.setForeground(elementsColor);
        JLabel portLabel =new JLabel("Порт");
        portLabel.setBackground(elementsColor);
        portLabel.setForeground(elementsColor);
        JLabel logginLabel =new JLabel("Логин");
        logginLabel.setBackground(elementsColor);
        logginLabel.setForeground(elementsColor);
        JLabel passwordLabel =new JLabel("Пароль");
        passwordLabel.setBackground(elementsColor);
        passwordLabel.setForeground(elementsColor);

        //Набор TextField
        JTextField serverField = new JTextField();
        serverField.setText("localhost");
        serverField.setBackground(elementsColor);
        serverField.setPreferredSize(new Dimension(50,200));

        JTextField portField = new JTextField();
        portField.setText("8081");
        portField.setBackground(elementsColor);
        portField.setPreferredSize(new Dimension(50,200));

        logginField.setText("");
        logginField.setBackground(elementsColor);
        logginField.setPreferredSize(new Dimension(50,200));


        passwordField.setText("");
        passwordField.setBackground(elementsColor);
        passwordField.setPreferredSize(new Dimension(50,200));

        //Набор кнопок
        JButton connectButton = new JButton("Connect");
        connectButton.setBackground(elementsColor);
        JButton closeButton = new JButton("Close");
        closeButton.setBackground(elementsColor);

        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                String server = serverField.getText().toLowerCase(Locale.ROOT);
                Integer port = Integer.parseInt(portField.getText());

                try {
                    connection(server,port);
                    if(isAutorized){
                    new ChatInterface(socket,server,port);
                    setVisible(false);
                    }

                } catch (IOException  e) {
                    JOptionPane.showMessageDialog(new JDialog(),"Wrong server or port.Check your data.");
                }
            }
        });

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
                System.exit(0);
            }
        });



        //Задаем расположение
        setLayout(new BorderLayout());
        centerPanel.setLayout(new GridLayout(4,2));

        //Добавляем на экран элементы
        centerPanel.add(serverLabel);
        centerPanel.add(serverField);
        centerPanel.add(portLabel);
        centerPanel.add(portField);
        centerPanel.add(logginLabel);
        centerPanel.add(logginField);
        centerPanel.add(passwordLabel);
        centerPanel.add(passwordField);
        footPanel.add(connectButton);
        footPanel.add(closeButton);
       // add(topPanel,BorderLayout.NORTH);
        add(centerPanel,BorderLayout.CENTER);
        add(footPanel,BorderLayout.SOUTH);
        pack();
        setVisible(true);
    }

    public void connection (String serverAddress,Integer serverPort) throws IOException {
        socket = new Socket(serverAddress, serverPort);
        dis = new DataInputStream(socket.getInputStream());
        dos = new DataOutputStream(socket.getOutputStream());
            try {
                dos.writeUTF("/auth"+" "+logginField.getText() + " "+passwordField.getText());
                String message = dis.readUTF();
                System.out.println(message);
                if (message.startsWith("/authok")) {
                    isAutorized = true;
                } else {
                    JOptionPane.showMessageDialog(new JDialog(), "Wrong Login or Password try agane");
                }
            } catch (IOException ignored){
            }
    }

}
