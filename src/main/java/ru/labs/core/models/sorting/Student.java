package ru.labs.core.models.sorting;

import lombok.Getter;

@Getter
public class Student implements Comparable<Student> {
    private final String name;
    private final int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int compareTo(Student other) {
        return this.getAge() != other.getAge() ? Integer.compare(this.getAge(), other.getAge()) : this.getName().compareTo(other.getName());
    }

    @Override
    public String toString() {
        String var10000 = this.getName();
        return var10000 + " " + this.getAge();
    }
}