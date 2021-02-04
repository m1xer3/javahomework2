package ru.danilsibgatullin.homeworklesson4.exercise1.models;


import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;


public class ChatInterface extends JFrame {

    private Set<Contact> contactListArr =new HashSet<>(); // аналог справочника
    private Contact currentContact =new Contact(); //текущий контакт с которым ведем диалог , нужно что бы подтянуть историю общения
    final Color elementsColor = Color.lightGray; // константа для стиля
    final Color backgroundColor = Color.DARK_GRAY; // константа для стиля


    public ChatInterface(){

        //задаем параметры окна
        setTitle("My cool chat!");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(900,700);
        setLocation(20,20);
        setResizable(false);
        BoxLayout gLine = new BoxLayout(getContentPane(),BoxLayout.X_AXIS);
        setLayout(gLine);


        //Основные панели group1 и group2 делят экран на две части
        JPanel group1 = new JPanel();
        group1.setBackground(backgroundColor);
        group1.setSize(200,700);
        group1.setLayout(new BoxLayout(group1,BoxLayout.Y_AXIS)) ;
        group1.setAutoscrolls(false);
        JPanel group2 = new JPanel();
        group2.setSize(700,700);
        group2.setLayout(new BoxLayout(group2,BoxLayout.Y_AXIS)) ;
        group2.setBackground(backgroundColor);
        group2.setAutoscrolls(false);

        //Вспомогательные панели распологаются
        JPanel leftPanTop = new JPanel();
        JPanel panCentr = new JPanel();
        JPanel panFoot= new JPanel();
        BoxLayout leftFootLayout = new BoxLayout(leftPanTop,BoxLayout.Y_AXIS);
        leftPanTop.setLayout(leftFootLayout);
        leftPanTop.setAutoscrolls(false);
        panFoot.setBackground(backgroundColor);
        leftPanTop.setBackground(backgroundColor);
        panCentr.setBackground(backgroundColor);

        //Набор кнопок
        JButton okButton =new JButton("ОК");
        okButton.setBackground(elementsColor);
        JButton delButton =new JButton("Clear");
        delButton.setBackground(elementsColor);
        JButton addContactButton =new JButton("Add Contact");
        addContactButton.setBackground(elementsColor);

        //Настройка текстового поля
        JTextArea textArea = new JTextArea(40,40);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBackground(elementsColor);
        scrollPane.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );
        scrollPane.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );
        textArea.setEditable(false);
        textArea.setMaximumSize(new Dimension(40,40));
        textArea.setAutoscrolls(true);
        textArea.setBackground(elementsColor);

        //Настройка панели с контактами
        DefaultListModel<String>  defList = new DefaultListModel<>();
        JList<String> jList = new JList<>(defList);
        jList.setVisibleRowCount(10);
        jList.setVisible(true);
        jList.setBackground(elementsColor);
        JScrollPane scrollPaneList = new JScrollPane(jList);
        scrollPaneList.setBackground(elementsColor);
        scrollPaneList.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );
        scrollPaneList.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );

        //Строка ввода
        JTextField messageLine = new JTextField(30);
        messageLine.setBackground(elementsColor);


        //Если не выбран собеседник кнопки делаем не доступными
        if(defList.isEmpty()){
            messageLine.setEnabled(false);
            okButton.setEnabled(false);
            delButton.setEnabled(false);
        }

        //Отправка по кнопке
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(!messageLine.getText().equals("")) {
                    textArea.append("Я : " + messageLine.getText() + "\n");
                    currentContact.addMessageToDialog("Я : " + messageLine.getText() + "\n");
                    messageLine.setText("");
                }
            }
        });

        //Очитска ввода
        delButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                messageLine.setText("");
            }
        });

        //Листенер на добавление сообщения через Enter
        messageLine.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(!messageLine.getText().equals("")) {
                    textArea.append("Я : " + messageLine.getText() + "\n");
                    currentContact.addMessageToDialog("Я : " + messageLine.getText() + "\n");
                    messageLine.setText("");
                }
            }
        });

        //Листенер добавления контактов
        addContactButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    String name = JOptionPane.showInputDialog("Enter name");
                    if(!name.equals("")) {
                        Contact c = new Contact(name);
                        contactListArr.add(c);
                        if(!defList.contains(c.getName())){
                            defList.addElement(c.getName());
                        }
                        jList.updateUI();
                    }
                    else throw new NullPointerException();
                }
                catch (NullPointerException e){
                    JOptionPane.showMessageDialog(new JDialog(),"Try agane");
                };
            }
        });

        //Листенер нажатия на элементы листа
        jList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                messageLine.setEnabled(true);
                okButton.setEnabled(true);
                delButton.setEnabled(true);
                for (Contact c: contactListArr) {
                    if(c.getName().equals(jList.getSelectedValue())){
                        currentContact=c;
                        break;
                    }
                }
                textArea.setText("");
                for (String message:currentContact.getDialog()) {
                    textArea.append(message);
                }
                textArea.repaint();
            }
        });

        //добавляем элементы в контейнеры
        panCentr.add(scrollPane);
        panFoot.add(messageLine);
        panFoot.add(okButton);
        panFoot.add(delButton);
        leftPanTop.add(addContactButton);
        leftPanTop.add(scrollPaneList);
        group1.add(leftPanTop);
        group2.add(panCentr);
        group2.add(panFoot);
        add(group1);
        add(group2);
        pack();
        setVisible(true);
    }
}
