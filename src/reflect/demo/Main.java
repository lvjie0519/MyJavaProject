package reflect.demo;

import java.lang.reflect.Method;

/**
 * Created by Administrator on 2017/9/3 0003.
 */
public class Main {

    public static void main(String []args) {
        System.out.println("hello world");

        reflectMethod();

    }


    public static void reflectMethod(){
        Person per = new Person();
        Class c = per.getClass();
        Method printStr = null;
        try {
            printStr = c.getDeclaredMethod("printStr", String.class);
            printStr.invoke(per, "hello reflect");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
