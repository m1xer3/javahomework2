package ru.danilsibgatullin.homeworklesson3.exercice2.models;

import java.util.HashSet;
import java.util.Set;

public class PhoneBook {
    /*Решил взять HashSet так как в справочнике не может быть одинакового и имени и номера одновременно
    в классе кронктов переопределил методы hasCode и equals
     */
    private Set<Contact> contactList =new HashSet<>();

    public void add (String name , String phoneNumber){
        Contact contact =new Contact(name,phoneNumber);
        contactList.add(contact);
    }

    public void get (String name){
        boolean isFind =false;
        System.out.printf("Результат поиска номеров, относящихся к контакту %s следующий:",name);
        for (Contact contact : contactList) {
            if(contact.getName().equals(name)){
                System.out.print(" "+contact.getPhoneNumber());
                isFind = true;
            }
        }
        if (!isFind) System.out.println(" Ни чего не найдено!");
        System.out.println();
    }

}
