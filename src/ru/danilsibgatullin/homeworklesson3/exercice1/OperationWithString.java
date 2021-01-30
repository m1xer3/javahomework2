package ru.danilsibgatullin.homeworklesson3.exercice1;

import java.util.*;

public class OperationWithString {
    public static void main(String[] args) {

        String [] arrString = {"один","два","один","один","три"};
        Set<String> ditinctArrString = new HashSet<>();
        ditinctArrString = distinctArr(arrString);
        for (String s : ditinctArrString) {
            System.out.println(s);
        }
        for (String s : ditinctArrString) {
            System.out.printf("Количество слов %s в массиве равно %d \n", s ,counWord(arrString,s));
        }




    }

    /*1. Создать массив с набором слов (10-20 слов, должны встречаться повторяющиеся).
    Найти и вывести список уникальных слов, из которых состоит массив (дубликаты не считаем).
    Посчитать сколько раз встречается каждое слово.
     */


    static Set<String> distinctArr (String[] arrString){
        Set<String> bufSet = new HashSet<String>();
        for (String s : arrString) {
            bufSet.add(s);
        }
        return bufSet;
    }

    static int counWord (String[] arrString,String word){
        int count =0;
        for (String s : arrString) {
            if (s.equals(word)){
                count++;
            }
        }
        return count;
    }

}
