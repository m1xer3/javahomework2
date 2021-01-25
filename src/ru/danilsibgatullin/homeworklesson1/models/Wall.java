package ru.danilsibgatullin.homeworklesson1.models;

import ru.danilsibgatullin.homeworklesson1.interfeices.Actions;

public class Wall extends Chaleng {

    public Wall(double distance,String name) {
        super( distance, name);
    }

    @Override
    public boolean isPassChelenge(Actions act) {
        return act.jump(this.getQuantity());
    }
}
