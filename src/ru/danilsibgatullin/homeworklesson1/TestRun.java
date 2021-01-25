package ru.danilsibgatullin.homeworklesson1;

import ru.danilsibgatullin.homeworklesson1.interfeices.*;
import ru.danilsibgatullin.homeworklesson1.models.*;

import java.util.ArrayList;


public class TestRun {
    public static void main(String[] args) {

        ArrayList<Actions> members = new ArrayList<>();
        members.add(new Human("Вася",600, 2.1));
        members.add(new Robot("Руби", 1100,3.4));
        members.add(new Cat("Барсик",300,2.9));
        members.add(new Human("Петя",1600,3));
        members.add(new Robot("R2D2", -1000,-2.3));

        ArrayList<Chaleng> chalenges =new ArrayList<>();
        chalenges.add(new Wall(2,"Забор"));
        chalenges.add(new RunningTrack(600,"Дорожка из камня"));
        chalenges.add(new Wall(2.5,"Стена из камня"));
        chalenges.add(new RunningTrack(1500,"Асфальтная дорога"));

        for (Actions member : members) {
            for (Chaleng chalenge : chalenges) {
                if (!isPassChalenge(member,chalenge)) break;
            }
        }
    }

    private static boolean isPassChalenge (Actions act,Chaleng chalenge){
        if(chalenge.isPassChelenge(act)){
            System.out.printf("%s по имени %s смог пройти испытание %s \n",act.whoAreYou(),act.getName(),chalenge.getChalengeName());
            return true;
        }
        System.out.printf("%s по имени %s провалил испытание %s  \n",act.whoAreYou(),act.getName(),chalenge.getChalengeName());
        return false;
    }
}
