package com.company.hwjavacore23;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Collection<Person> persons100 = new ArrayList<>();
        personCollectionGen(persons100, 100);
        System.out.println("Processing time: " + calculate(persons100));
        System.out.println("Processing time: " + calculateParallel(persons100) + "\n");

        Collection<Person> persons1000 = new ArrayList<>();
        personCollectionGen(persons1000, 1_000);
        System.out.println("Processing time: " + calculate(persons1000));
        System.out.println("Processing time: " + calculateParallel(persons1000) + "\n");

        Collection<Person> persons10k = new ArrayList<>();
        personCollectionGen(persons10k, 10_000);
        System.out.println("Processing time: " + calculate(persons10k));
        System.out.println("Processing time: " + calculateParallel(persons10k) + "\n");

        Collection<Person> persons100k = new ArrayList<>();
        personCollectionGen(persons100k, 100_000);
        System.out.println("Processing time: " + calculate(persons100k));
        System.out.println("Processing time: " + calculateParallel(persons100k) + "\n");

        Collection<Person> persons1M = new ArrayList<>();
        personCollectionGen(persons1M, 1_000_000);
        System.out.println("Processing time: " + calculate(persons1M));
        System.out.println("Processing time: " + calculateParallel(persons1M) + "\n");

        Collection<Person> persons10M = new ArrayList<>();
        personCollectionGen(persons10M, 10_000_000);
        System.out.println("Processing time: " + calculate(persons10M));
        System.out.println("Processing time: " + calculateParallel(persons10M) + "\n");
    }

    static void personCollectionGen(Collection<Person> persons, int numberOfPersons) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        for (int i = 0; i < numberOfPersons; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Gender.values()[new Random().nextInt(Gender.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
    }

    static double calculate(Collection<Person> persons) {
        long startTime = System.nanoTime();
        System.out.println("Started processing " + persons.size() + " records in single-stream mode");
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

        long stopTime = System.nanoTime();
        return (double) (stopTime - startTime) / 1_000_000_000.0;
    }

    static double calculateParallel(Collection<Person> persons) {
        long startTime = System.nanoTime();
        System.out.println("Started processing " + persons.size() + " records in parallel-stream mode");
        Stream<Person> streamUnderage = persons.parallelStream();
        long underageCount = streamUnderage
                .filter(person -> person.getAge() < 18)
                .count();

        Stream<Person> streamRecruit = persons.parallelStream();
        List<String> recruitList = streamRecruit
                .filter(person -> person.getGender().equals(Gender.MAN))
                .filter(person -> (person.getAge() <= 27) && (person.getAge() >= 18))
                .map(Person::getFamilyName)
                .collect(Collectors.toList());

        Stream<Person> streamEmployees = persons.parallelStream();
        List<Person> employeesList = streamEmployees
                .filter(person -> person.getEducation().equals(Education.HIGHER))
                .filter(person -> person.getAge() >= 18)
                .filter(person -> (((person.getGender().equals(Gender.MAN)) &&
                        (person.getAge() <= 65)) ||
                        ((person.getGender().equals(Gender.WOMAN)) &&
                                (person.getAge() <= 60))))
                .sorted(Comparator.comparing(Person::getFamilyName))
                .collect(Collectors.toList());

        long stopTime = System.nanoTime();
        return (double) (stopTime - startTime) / 1_000_000_000.0;
    }
}
