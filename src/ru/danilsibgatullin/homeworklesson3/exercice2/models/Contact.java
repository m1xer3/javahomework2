package ru.danilsibgatullin.homeworklesson3.exercice2.models;

public class Contact {
    private String name;
    private String phoneNumber;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Contact(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber=phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return (name.equals(contact.name)) && (phoneNumber.equals(contact.phoneNumber));
    }

    @Override
    public int hashCode() {
        return 31*(this.name.hashCode()+this.phoneNumber.hashCode());
    }
}
