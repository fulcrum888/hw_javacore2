package com.company.hwjavacore22;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Collection<Person> persons = Arrays.asList(
                new Person("Jack", "Evans", 16, Gender.MAN, Education.SECONDARY),
                new Person("Connor", "Young", 23, Gender.MAN, Education.FURTHER),
                new Person("Emily", "Harris", 42, Gender.WOMAN, Education.HIGHER),
                new Person("Harry", "Wilson", 69, Gender.MAN, Education.HIGHER),
                new Person("George", "Davies", 35, Gender.MAN, Education.FURTHER),
                new Person("Samuel", "Adamson", 40, Gender.MAN, Education.HIGHER),
                new Person("John", "Brown", 44, Gender.MAN, Education.HIGHER)
        );

        Stream<Person> streamUnderage = persons.stream();
        long underageCount = streamUnderage
                .filter(person -> person.getAge() < 18)
                .count();

        Stream<Person> streamRecruit = persons.stream();
        List<String> recruitList = streamRecruit
                .filter(person -> person.getGender().equals(Gender.MAN))
                .filter(person -> (person.getAge() <= 27) && (person.getAge() >= 18))
                .map(Person::getFamilyName)
                .collect(Collectors.toList());

        Stream<Person> streamEmployees = persons.stream();
        List<Person> employeesList = streamEmployees
                .filter(person -> person.getEducation().equals(Education.HIGHER))
                .filter(person -> person.getAge() >= 18)
                .filter(person -> (((person.getGender().equals(Gender.MAN)) &&
                        (person.getAge() <= 65)) ||
                                ((person.getGender().equals(Gender.WOMAN)) &&
                        (person.getAge() <= 60))))
                .sorted(Comparator.comparing(Person::getFamilyName))
                .collect(Collectors.toList());

        System.out.println("Всего несовершеннолетних: " + underageCount + "\n");
        System.out.println("Список призывников: " + recruitList + "\n");
        System.out.println("Работоспособные люди с ВО: " + employeesList);
    }
}
