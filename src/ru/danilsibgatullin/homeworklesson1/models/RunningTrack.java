package ru.danilsibgatullin.homeworklesson1.models;

import ru.danilsibgatullin.homeworklesson1.interfeices.Actions;
import ru.danilsibgatullin.homeworklesson1.interfeices.PassChelenge;

public class RunningTrack implements PassChelenge {

    private double distance;
    private String trackName;

    @Override
    public String getChelengeName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    @Override
    public double getQuantity() {
        return distance;
    }

    @Override
    public void setQuantity(int quantity) {
        this.distance=quantity;
    }

    public RunningTrack(double distance,String name) {
        this.distance=distance >=0 ? distance : 0;
        this.trackName=name;
    }

    @Override
    public boolean isPassChelenge(Actions act) {
        return act.run(this.getQuantity());
    }
}
