package ru.danilsibgatullin.homeworklesson1.models;

import ru.danilsibgatullin.homeworklesson1.interfeices.Actions;
import ru.danilsibgatullin.homeworklesson1.interfeices.PassChelenge;

public class Wall implements PassChelenge {

    private double height;
    private String wallName;

    @Override
    public String getChelengeName() {
        return wallName;
    }

    public void setWallName(String wallName) {
        this.wallName = wallName;
    }

    @Override
    public double getQuantity() {
        return this.height;
    }

    @Override
    public void setQuantity(int quantity) {
        this.height=quantity;
    }

    public Wall(double height,String name) {
        this.height=height >=0 ? height : 0;
        this.wallName=name;
    }

    @Override
    public boolean isPassChelenge(Actions act) {
        return act.jump(this.getQuantity());
    }
}
