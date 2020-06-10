package thread.book1;

import java.util.Date;
import java.util.PrimitiveIterator;

/**
 * 1、synchronized 对象监视器为 Object时的使用
 * 2、synchronized 对象监视器为 Class时的使用
 */
public class SynchronizedTest {

    public static void main(String []args){
//        test1();
//        test2();
//        testt3();
//        test4();
        test5();
    }


    private static void test1(){
        /**
         * synchronized 修饰某一非静态方法，其实是为这个类添加对象锁
         * 当某个类中  有多个 被synchronized修饰的同步方法，谁先持有了对象锁，谁先执行， 其他线程需要等待，
         * 即便线程调用的是不同的同步方法，也需要等待。
         */
        MyObject1 myObject1 = new MyObject1();
        MyObject1 myObject2 = new MyObject1();

        new Thread(new Runnable() {
            @Override
            public void run() {
                myObject1.methodA(Thread.currentThread().getName());
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                myObject2.methodA(Thread.currentThread().getName());
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                myObject2.methodA(Thread.currentThread().getName());
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                myObject2.methodB(Thread.currentThread().getName());
            }
        }).start();
    }

    private static void test2(){
        /**
         *  synchronized 具有锁重入的能力，  也就是说当某一个线程进入同步方法后，还可以执行其他同步方法
         */
        MyObject1 myObject2 = new MyObject1();
        new Thread(new Runnable() {
            @Override
            public void run() {
                myObject2.methodC(Thread.currentThread().getName());
            }
        }).start();
    }

    /**
     * 同步代码块
     */
    private static void test3(){

        /**
         * 同步代码块和同步方法一样， 同一时刻，只有一个线程持有该锁
         */
        MyObject1 myObject1 = new MyObject1();

        new Thread(new Runnable() {
            @Override
            public void run() {
                myObject1.methodD(Thread.currentThread().getName());
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                myObject1.methodDD(Thread.currentThread().getName());
            }
        }).start();
    }

    private static void test4(){

        /**
         * synchronized 不同的对象， 相互之间无影响
         */
        MyObject1 myObject2 = new MyObject1();
        new Thread(new Runnable() {
            @Override
            public void run() {
                myObject2.methodE(Thread.currentThread().getName());
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                myObject2.methodEE(Thread.currentThread().getName());
            }
        }).start();
    }

    private static void test5(){
        /**
         * 测试内部类同步问题
         * 只要是相同的锁，都会同步
         */
        MyObject1.MyInnerObject myInnerObject = new MyObject1.MyInnerObject();
        new Thread(new Runnable() {
            @Override
            public void run() {
                myInnerObject.innerMethodA(Thread.currentThread().getName());
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                myInnerObject.innerMethodB(Thread.currentThread().getName());
            }
        }).start();
    }

    private static class MyObject1{

        synchronized public void methodA(String name){
            System.out.println(name+": methodA  start sleep...");
            ThreadUtils.sleep(1000);
            System.out.println(name+": methodA  end sleep...");
        }

        synchronized public void methodB(String name){
            System.out.println(name+": methodB  start sleep...");
            ThreadUtils.sleep(2000);
            System.out.println(name+": methodB  end sleep...");
        }

        synchronized public void methodC(String name){
            System.out.println(name+": methodC  start sleep...");
            ThreadUtils.sleep(2000);
            System.out.println(name+": methodC  end sleep...");
            methodA(name);
        }


        public void methodD(String name){
            synchronized (this){
                System.out.println(name+": methodD  start sleep..."+new Date().toString());
                ThreadUtils.sleep(2000);
                System.out.println(name+": methodD  end sleep..."+new Date().toString());
            }
        }
        public void methodDD(String name){
            synchronized (this){
                System.out.println(name+": methodDD  start sleep..."+new Date().toString());
                ThreadUtils.sleep(2000);
                System.out.println(name+": methodDD  end sleep..."+new Date().toString());
            }
        }

        private String syncString = new String();
        public void methodE(String name){
            synchronized (syncString){
                System.out.println(name+": methodE  start sleep..."+new Date().toString());
                ThreadUtils.sleep(2000);
                System.out.println(name+": methodE  end sleep..."+new Date().toString());
            }
        }
        public void methodEE(String name){
            synchronized (this){
                System.out.println(name+": methodEE  start sleep..."+new Date().toString());
                ThreadUtils.sleep(2000);
                System.out.println(name+": methodEE  end sleep..."+new Date().toString());
            }
        }

        private static class MyInnerObject{
            synchronized public void innerMethodA(String name){
                System.out.println(name+": innerMethodA  start sleep...");
                ThreadUtils.sleep(1000);
                System.out.println(name+": innerMethodA  end sleep...");
            }

            synchronized public void innerMethodB(String name){
                System.out.println(name+": innerMethodB  start sleep...");
                ThreadUtils.sleep(2000);
                System.out.println(name+": innerMethodB  end sleep...");
            }
        }
    }

}
