package ru.danilsibgatullin.homeworklesson4.exercise1.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Contact {
    private String name;
    private List<String> dialog =new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getDialog() {
        return dialog;
    }

    public Contact(String name) {
        this.name = name;
    }

    public Contact(){
        this.name="Default";
    }

    public void addMessageToDialog (String message){
        this.dialog.add(message);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return Objects.equals(name, contact.name);
    }

    @Override
    public int hashCode() {
        return 31*name.hashCode();
    }
}
