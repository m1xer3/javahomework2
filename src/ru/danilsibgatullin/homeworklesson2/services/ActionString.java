package ru.danilsibgatullin.homeworklesson2.services;

import ru.danilsibgatullin.homeworklesson2.exceptions.MyArrayDataException;
import ru.danilsibgatullin.homeworklesson2.exceptions.MyExceptionArraySize;

import java.util.Random;

public class ActionString {

    //основной метод
    public int sumArrayStringNumbers(String[][] strArr) throws MyExceptionArraySize{
        int sum =0;
        if (!isValidArr(strArr)) throw new MyExceptionArraySize("Error. Entered not valid array size. Size must be 4*4"); //проверяем на вилидность полученный массив
        for (int rowsIndex=0;rowsIndex<strArr.length;rowsIndex++){
            for(int colIndex=0;colIndex<strArr[rowsIndex].length;colIndex++){
                try{
                    sum+=sumStringNumbers(strArr[rowsIndex][colIndex],rowsIndex,colIndex);
                }
                catch (MyArrayDataException  e){
                    System.out.println(e.getMessage());
                }
            }
        }
        return sum;
    }

    //метод получает на вход строку и адрес в массиве для преобразование в число. Адрес используется для формирования исключения.
    private int sumStringNumbers(String oneStr,int rowsIndex,int colIndex) throws  MyArrayDataException{
        try {
            return Integer.parseInt(oneStr);
        }
        catch (NumberFormatException e){
            throw new MyArrayDataException("По индексу массива [" + rowsIndex + "] [" + colIndex+"] значение "+oneStr+" не удалось преобразовать в число");
        }
    }

    //проверка на соответствие входного массива размеру 4*4
       private boolean isValidArr(String[][] strArr){
        boolean isValid=false;
        for (String[] strings : strArr) {
            isValid = strings.length==4;
            if (!isValid) break;
        }
        return strArr.length==4 && isValid;
    }

    //инициализация рандомного массива для удобства
    public String[][] initRandSquareArrStr (int size) {
        String[][] resultArr =new String[size][size];
        Random r =new Random();
        for(int rowIndex=0;rowIndex<size;rowIndex++){
            for(int colIndex=0;colIndex<size;colIndex++){
                if(colIndex==2){
                    resultArr[rowIndex][colIndex]="test!%"+rowIndex;
                    continue;
                }
                resultArr[rowIndex][colIndex] = ""+r.nextInt(100);
            }
        }
        return resultArr;
    }

    // вывод на печать двумерного строчного массива
    public void printStringArr(String[][] strArr){
        for (String[] strings : strArr) {
            for (String string : strings) {
                System.out.print(string+" ");
            }
            System.out.println();
        }
    }

}
