package design.patterns.proxy;

public class Student implements PersonInterface {

    private String name;
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public void sayHello() {
        System.out.println("My name is "+name+" and age is "+age);
    }
}
