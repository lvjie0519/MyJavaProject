import design.patterns.single_instance.SingletonType3;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Created by Administrator on 2017/9/3 0003.
 */
public class TestMain {


    private static class MyStudent{
        private static int id = 10001;
        private String name;
        private int age;

        static {
            id = 10002;
            System.out.println("static id = "+id);
        }

        public MyStudent(String name, int age) {
            this.name = name;
            this.age = age;
            System.out.println("MyStudent... id = "+id);
        }

        @Override
        public String toString() {
            return "id="+id+"  name="+name+"  age="+age;
        }
    }

    public static void main(String []args){
        MyStudent myStudent = new MyStudent("jack", 20);
        System.out.println(myStudent);

        SingletonType3.getInstance().sayHi();
    }
}