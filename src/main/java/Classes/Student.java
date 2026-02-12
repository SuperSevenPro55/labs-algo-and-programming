package Classes;

public class Student implements Comparable<Student> {
    private final String name;
    private final int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

    public int compareTo(Student other) {
        return this.getAge() != other.getAge() ? Integer.compare(this.getAge(), other.getAge()) : this.getName().compareTo(other.getName());
    }

    public String toString() {
        String var10000 = this.getName();
        return var10000 + " " + this.getAge();
    }
}