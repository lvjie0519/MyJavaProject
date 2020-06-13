package thread.book1;

import javax.management.relation.RelationNotFoundException;
import java.util.Date;

/**
 * 3、关键字volatile 的作用
 * 4、synchronized 和 volatile的区别
 */
public class VolatileTest {

    public static void main(String []args){

        test1();
    }

    private static void test1(){

        /**
         * 如果  continueRun 不添加volatile 修饰， 则runMethod 会出现死循环
         * 方法1： continueRun 添加volatile 修饰
         * 方法2：while循环里面添加 synchronized (this){} ，  同步块
         * synchronized 不仅可以解决同一时刻，只有一个线程执行该同步代码，
         * 还可以解决一个线程看到对象处于不一致的状态，保证进入同步方法或同步代码块之前的
         * 每个线程 修改的变量的效果
         */
        MyService myService = new MyService();

        new Thread(new Runnable() {
            @Override
            public void run() {
                myService.runMethod();
            }
        }).start();
        ThreadUtils.sleep(100);
        new Thread(new Runnable() {
            @Override
            public void run() {
                myService.setContinueRun(false);
            }
        }).start();
        ThreadUtils.sleep(100);
        System.out.println(Thread.currentThread().getName()+" isContinueRun: "+myService.isContinueRun()+"...  "+new Date());
    }

    private static class MyService{
        private boolean isContinueRun = true;
//        private volatile boolean isContinueRun = true;   //  方法1

        public void runMethod(){
            String str = new String();
            while (isContinueRun){
                synchronized (this){}         // 方法2
            }
            System.out.println(Thread.currentThread().getName()+" is stop ..."+new Date());
        }

        public void setContinueRun(boolean continueRun) {
            System.out.println(Thread.currentThread().getName()+" set continueRun is "+continueRun+"..."+new Date());
            isContinueRun = continueRun;
        }

        public boolean isContinueRun() {
            return isContinueRun;
        }
    }
}
