package sample.Model;

import java.io.Serializable;

public class Student implements Serializable {

    private int id;
    private String name;
    private String surname;
    private String middleName;
    private int age;
    private int groupNum;

    public Student() {
    }

    public Student(int id, String name, String surname, String middleName, int age, int groupNum) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.middleName = middleName;
        this.age = age;
        this.groupNum = groupNum;
    }

    public Student(String name, String surname, String middleName, int age, int groupNum) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.middleName = middleName;
        this.age = age;
        this.groupNum = groupNum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getGroupNum() {
        return groupNum;
    }

    public void setGroupNum(int groupNum) {
        this.groupNum = groupNum;
    }

    @Override

    public String toString() {
        return "Student {" + "id=" + id + ", Name='" + name + '\'' + ", age='" + age + '\'' + '}';
    }
}