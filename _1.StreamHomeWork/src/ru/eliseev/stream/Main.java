package ru.eliseev.stream;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        // First homework
        List<Integer> integerList = Arrays.asList(1, 2, 5, 16, -1, -2, 0, 32, 3, 5, 8, 23, 4);
        integerList.stream()
                .filter(number -> number > 0 )
                .filter(number -> number % 2 == 0)
                .sorted(Comparator.naturalOrder())
                .forEach(System.out::println);

        // Second homework
        List<Person> personList = generatePersons();

        // Найти количество несовершеннолетних (т.е. людей младше 18 лет).
        long countUnderage = personList.stream()
                .filter(person -> person.getAge() < 18)
                .count();
        System.out.println("Количество несовершенолетних: " + countUnderage);

        // Получить список фамилий призывников (т.е. мужчин от 18 и до 27 лет).
        List<String> surnames = personList.stream()
                .filter(person -> person.getAge() >= 18 && person.getAge() < 27)
                .map(person -> person.getFamily())
                .collect(Collectors.toList());
//        System.out.println(surnames);

        // Получить отсортированный по фамилии список потенциально работоспособных
        // людей с высшим образованием в выборке
        // (т.е. людей с высшим образованием от 18 до 60 лет для женщин
        // и до 65 лет для мужчин).
        System.out.println(personList);
        List<String> surnamesList = personList.stream()
                .filter(person -> person.getEducation() == Education.HIGHER)
                .filter(person -> person.getAge() >= 18)
                .filter(person ->
                        (person.getSex() == Sex.MAN && person.getAge() < 65) ||
                        (person.getSex() == Sex.WOMAN && person.getAge() < 60))
                .sorted(Comparator.comparing(Person::getFamily))
                .map(person -> person.getFamily() + " " + person.getName())
                .collect(Collectors.toList());
        System.out.println(surnamesList);
    }

    private static List<Person> generatePersons() {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        List<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        return persons;
    }
}
