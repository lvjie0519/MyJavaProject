package design.patterns.single_instance;

/**
 * Created by Administrator on 2017/11/1 0001.
 */
public class Main {

    public static void main(String []args){
        System.out.println("hello world");


        Person.getInstance().setName("lvjie");
        System.out.println(Person.getInstance().getName());
    }

}
