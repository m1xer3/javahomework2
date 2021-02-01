package ru.danilsibgatullin.homeworklesson3.exercice1;

import java.util.Set;
import java.util.HashSet;

    /*1. Создать массив с набором слов (10-20 слов, должны встречаться повторяющиеся).
    Найти и вывести список уникальных слов, из которых состоит массив (дубликаты не считаем).
    Посчитать сколько раз встречается каждое слово.
     */

public class OperationWithString {
    public static void main(String[] args) {

        String [] arrString = {"один","два","один","один","три","111","222","333","од1ин","111","1234","333","333","333","333","1","два","один","один","три","111","222","333","од1ин","111","1234","333",};
        Set<String> ditinctArrString = distinctArr(arrString) ;
        System.out.print("Массив с уникальными значениями из исходного массива: ");
        for (String s : ditinctArrString) {
            System.out.print(" "+s);
        }
        System.out.println();
        for (String s : ditinctArrString) {
            System.out.printf("Количество слов %s в массиве равно %d \n", s ,counWord(arrString,s));
        }
    }

    static Set<String> distinctArr (String[] arrString){
        Set<String> bufSet = new HashSet<>();
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
