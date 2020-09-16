package com.company.hwjavacore22;

public class Person {
    private String name;
    private String familyName;
    private Integer age;
    private Gender gender;
    private Education education;

    public Person(String name, String familyName, Integer age, Gender gender, Education education) {
        this.name = name;
        this.familyName = familyName;
        this.age = age;
        this.gender = gender;
        this.education = education;
    }

    public String getName() {
        return name;
    }

    public String getFamilyName() {
        return familyName;
    }

    public Integer getAge() {
        return age;
    }

    public Gender getGender() {
        return gender;
    }

    public Education getEducation() {
        return education;
    }

    @Override
    public String toString() {
        return "\nPerson{" +
                "name='" + name + '\'' +
                ", familyName='" + familyName + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", education=" + education +
                '}';
    }
}
