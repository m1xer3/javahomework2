package ru.danilsibgatullin.homeworklesson4.exercise23456;

import ru.danilsibgatullin.homeworklesson4.exercise23456.interfaces.IndexLamda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;


public class AppRunLamda {

    public static void main(String[] args) {
        Integer[] list = {100,32,34,45,2,34,344};
        List<Integer> listArr = new ArrayList<>();
        listArr.add(12);
        listArr.add(2);
        listArr.add(10);
        listArr.add(5);
        listArr.add(7);

        System.out.println(search(32,list));
        System.out.println(reverse("Прикол"));
        System.out.println(maximum(list));
        System.out.println(average(listArr));

        List<String> arrString = new ArrayList<>();
        arrString.add("aka");
        arrString.add("akka");
        arrString.add("mar");
        arrString.add("ara");
        arrString.add("aaa");
        arrString.add("akkaa");

        search(arrString).stream().peek((str) -> System.out.print(str+" ")).collect(Collectors.toList()); // Вывод полученного листа
        System.out.println();


    }

    /*
2. Напишите метод, который возвращает индекс первого вхождения данного целого числа в списке.
Предположим, что индекс первого элемента в списке равен нулю.
Если числа не существует в списке, верните -1.
public int search(Integer n, Integer[] list)
 */
    static int search(Integer n, Integer[] list){
        IndexLamda indexLamda;
        indexLamda=(Integer num,Integer[] listnum)->{
            for (int i=0;i<list.length;i++){
                if(n.equals(list[i]))return i;
            }
            return -1;
        };
        return indexLamda.search(n,list);
    }

    /*
    3 Напишите метод, переворачивающий строку.
Например, «java interview» превращается в «weivretni avaj».
public
     */
    static  String reverse(String s){
        UnaryOperator<String> unaryOperator;
        unaryOperator=(String str) -> {
            StringBuilder stringBuilder = new StringBuilder(str);
            return String.valueOf(stringBuilder.reverse());
        };
        return unaryOperator.apply(s);
    }
    /*
    4 Напишите метод, который возвращает наибольшее целое число в списке.
public Integer maximum(Integer[] list)
     */

    static Integer maximum(Integer[] list){
        Function<Integer[],Integer> function;
        function=(Integer[] arr) ->{
            return Arrays.stream(arr).max(Integer::compareTo).get(); //максимальный элемент массива
        };
        return  function.apply(list);
    }

    /*
    5. Напишите метод, который возвращает среднее значение из списка целых чисел.
public Double average(List<Integer> list)
     */

    static Double average(List<Integer> list){
        Function<List<Integer>,Double> function;
        function = (List<Integer> arr) -> {
            return  Double.valueOf((double)(arr.stream().reduce((num1,num2)->num1+num2).get())/arr.size());
        };
        return function.apply(list);
    }

    /*
    6. Имея список строк, напишите метод, который возвращает список всех строк, которые начинаются с буквы «а» (нижний регистр) и имеют ровно 3 буквы.
public List<String> search(List<String> list)
     */
    static List<String> search(List<String> list){
        UnaryOperator<List<String>> unaryOperator;
        unaryOperator=(List<String> strArr) -> {
         return strArr.stream().filter((onestr)->onestr.charAt(0)=='a').filter((onestr) -> onestr.length() == 3).collect(Collectors.toList());
        };
        return unaryOperator.apply(list);
    }





}
