package reflect.demo;

import java.lang.reflect.Method;

/**
 * Created by Administrator on 2017/9/3 0003.
 */
public class Main {

    public static void main(String []args) {
        System.out.println("hello world");

        Person person = new Person("lvjie");
        Class c = person.getClass();


    }


    public static void reflectMethod(){
        Person per = new Person();
        Person per2 = new Person();
        System.out.println(per);
        Class c = Person.class;
        Method printStr = null;

        try {
            printStr = c.getDeclaredMethod("setName", String.class);
            printStr.invoke(per, "hello reflect");
            System.out.println(per);
        } catch (Exception e) {
            e.printStackTrace();
        }


        System.out.println(per);
        System.out.println(per2);
    }

}
