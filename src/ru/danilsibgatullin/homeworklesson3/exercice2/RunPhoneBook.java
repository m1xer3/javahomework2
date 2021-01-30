package ru.danilsibgatullin.homeworklesson3.exercice2;

import ru.danilsibgatullin.homeworklesson3.exercice2.models.PhoneBook;

/*2. Написать простой класс ТелефонныйСправочник, который хранит в себе список фамилий и телефонных номеров.
В этот телефонный справочник с помощью метода add() можно добавлять записи. С помощью метода get() искать номер телефона по фамилии.
Следует учесть, что под одной фамилией может быть несколько телефонов (в случае однофамильцев),
тогда при запросе такой фамилии должны выводиться все телефоны.
Желательно как можно меньше добавлять своего, чего нет в задании
(т.е. не надо в телефонную запись добавлять еще дополнительные поля (имя, отчество, адрес),
делать взаимодействие с пользователем через консоль и т.д.).
Консоль желательно не использовать (в том числе Scanner), тестировать просто из метода main() прописывая add() и get().
 */

public class RunPhoneBook {
    public static void main(String[] args) {
        PhoneBook myPhoneBook = new PhoneBook();
        myPhoneBook.add("Петя","+79071245685");
        myPhoneBook.add("Петя","+79032245685");
        myPhoneBook.add("Петя","+79032245115");
        myPhoneBook.add("Вася","+12322341234");
        myPhoneBook.add("Полиция","012");
        myPhoneBook.add("Лена","+79696666666");
        myPhoneBook.add("Лена","+74978542777");
        myPhoneBook.add("Cвета","+1");
        myPhoneBook.add("Cвета","+1");
        myPhoneBook.add("Cвета","+1");
        myPhoneBook.add("Cвета","+11");

        myPhoneBook.get("Cвета");
        myPhoneBook.get("Петя");
        myPhoneBook.get("Лена");
        myPhoneBook.get("Борис");
    }
}
