package ru.danilsibgatullin.homeworklesson1.models;

import ru.danilsibgatullin.homeworklesson1.interfeices.Actions;


public class RunningTrack extends Chaleng {


    public RunningTrack(int distance,String name) {
        super( distance, name);
    }

    @Override
    public boolean isPassChelenge(Actions act) {
        return act.run(this.getQuantity());
    }
}
