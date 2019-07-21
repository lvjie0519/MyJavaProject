package thread.demo;

import java.util.concurrent.*;

/**
 * Created by Administrator on 2018/1/9 0009.
 */
public class ThreadPoolTest {

    public static void main(String []args){



//        testNewCachedThreadPool();
//        testNewFixedThreadPool();
//        testNewScheduledThreadPool();
//        testNewSingleThreadExecutor();

        System.out.println(Thread.currentThread().getName());
//        new MyThread(1).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+" start...");

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(Thread.currentThread().getName()+" start...");
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(Thread.currentThread().getName()+" end...");
                    }
                }).start();

                System.out.println(Thread.currentThread().getName()+" end...");
            }
        }).start();


    }


    private static void testNewCachedThreadPool(){

        // 创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();

        for(int i=0; i<10; i++){
            executor.execute(new MyThread(i));
        }

    }

    private static void testNewFixedThreadPool(){
        // 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
        ExecutorService  executor =  Executors.newFixedThreadPool(5);

        for(int i=0; i<10; i++){
            executor.execute(new MyThread(i));
        }
    }

    private static void testNewScheduledThreadPool(){
        // 创建一个定长线程池，支持定时及周期性任务执行。
        ScheduledExecutorService executor =  Executors.newScheduledThreadPool(5);

        for(int i=0; i<10; i++){
            // 延迟3秒执行。
            executor.schedule(new MyThread(i), 3, TimeUnit.SECONDS);

            // 延迟1秒后每3秒执行一次, 比Timer更安全，功能更强大
//            executor.scheduleAtFixedRate(new MyThread(i), 1,3, TimeUnit.SECONDS);
        }
    }

    private static void testNewSingleThreadExecutor(){
        ExecutorService  executor =  Executors.newSingleThreadExecutor();
        for(int i=0; i<10; i++){
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(Thread.currentThread().getName()+"  开始处理数据：");
                        Thread.sleep(400);
                        System.out.println(Thread.currentThread().getName()+"  处理结束.....");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }


    static class MyThread extends Thread{

        private int count;

        public MyThread(int count) {
            this.count = count;
        }

        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName()+"  开始处理数据："+count);
                Thread.sleep(500);
                System.out.println(Thread.currentThread().getName()+"  处理结束.....");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}


