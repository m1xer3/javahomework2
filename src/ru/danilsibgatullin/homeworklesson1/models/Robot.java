package ru.danilsibgatullin.homeworklesson1.models;

import ru.danilsibgatullin.homeworklesson1.interfeices.Actions;

public class Robot implements Actions {


    private final static int DEFAULT_MAX_RUN = 150;
    private final static double DEFAULT_MAX_JUMP = 1.5;

    private final static String TYPE = "Робот";

    private String name;
    private int maxRunDistance;
    private double maxJumpDistance;

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxRunDistance() {
        return maxRunDistance;
    }

    public void setMaxRunDistance(int maxRunDistance) {
        this.maxRunDistance = maxRunDistance;
    }

    public double getMaxJumDistance() {
        return maxJumpDistance;
    }

    public void setMaxJumDistance(int maxJumDistance) {
        this.maxJumpDistance = maxJumDistance;
    }

    public Robot(String name, int maxRunDistance, double maxJumpDistance) {
        this.name = name;
        this.maxRunDistance = maxRunDistance >=0 ? maxRunDistance : DEFAULT_MAX_RUN; // если было указано отрицательное значение считаем значение дефолтным
        this.maxJumpDistance = maxJumpDistance >=0 ? maxJumpDistance : DEFAULT_MAX_JUMP; // если было указано отрицательное значение считаем значение дефолтным
    }

    public Robot(){
        this(TYPE,DEFAULT_MAX_RUN,DEFAULT_MAX_JUMP);
    }

    @Override
    public boolean run(double distance) {
        System.out.printf("%s по имени %s побежал \n",TYPE,getName());
        return this.getMaxRunDistance()>=distance;
    }

    @Override
    public boolean jump(double distance) {
        System.out.printf("%s по имени %s прыгнул \n",TYPE,getName());
        return this.getMaxJumDistance()>=distance;
    }

    @Override
    public String whoAreYou() {
        return TYPE;
    }
}
