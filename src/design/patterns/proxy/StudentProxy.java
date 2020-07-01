package design.patterns.proxy;

public class StudentProxy implements PersonInterface {

    private PersonInterface helloInterface = new Student("lvjie", 30);

    @Override
    public void sayHello() {
        System.out.println("Before invoke sayHello" );
        helloInterface.sayHello();
        System.out.println("After invoke sayHello");
    }
}
