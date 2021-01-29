package ru.danilsibgatullin.homeworklesson2;

import ru.danilsibgatullin.homeworklesson2.exceptions.MyExceptionArraySize;
import ru.danilsibgatullin.homeworklesson2.services.ActionString;


public class AppRun {
    public static void main(String[] args) {
        ActionString a = new ActionString();
        String[][] checkArr;

        String array1[][] = {{"*фыв", "i", "4", "0"}, {"1", "3", "4", "1"}, {"1", "3", "4", "1"}, {"1", "3", "4", ";"}};
        checkArr = a.initRandSquareArrStr(4); //инициируем случайный массив

        try{

            a.printStringArr(array1); // печатаем входящий массив
            System.out.println("Сумма чисел в массиве "+a.sumArrayStringNumbers(array1));
        }
        catch (MyExceptionArraySize e){
            System.out.println(e.getMessage());
        }

    }
}
