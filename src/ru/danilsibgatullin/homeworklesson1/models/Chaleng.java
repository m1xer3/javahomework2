package ru.danilsibgatullin.homeworklesson1.models;

import ru.danilsibgatullin.homeworklesson1.interfeices.Actions;

public abstract class Chaleng {

    private double quantity;
    private String chalengeName;

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getChalengeName() {
        return chalengeName;
    }

    public void setChalengeName(String chalengeName) {
        this.chalengeName = chalengeName;
    }

    public Chaleng(double quantity, String chalengeName) {
        this.quantity = quantity >= 0 ? quantity : 0 ;
        this.chalengeName = chalengeName;
    }


    public abstract boolean isPassChelenge(Actions act);

}
