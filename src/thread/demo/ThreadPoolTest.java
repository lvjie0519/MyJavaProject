package thread.demo;

import thread.book1.ThreadUtils;

import java.util.Date;
import java.util.concurrent.*;

/**
 * Created by Administrator on 2018/1/9 0009.
 * 在阿里巴巴的《Java 开发手册》中是这样规定线程池的：
 * 线程池不允许使用 Executors 去创建，而是通过 ThreadPoolExecutor 的方式，
 * 这样的处理方式让写的读者更加明确线程池的运行规则，规避资源耗尽的风险。
 *
 * Executors 返回的线程池对象的弊端如下：
 *
 * FixedThreadPool 和 SingleThreadPool：允许的请求队列长度为 Integer.MAX_VALUE，
 * 可能会堆积大量的请求，从而导致 OOM。
 * CachedThreadPool 和 ScheduledThreadPool：允许的创建线程数量为 Integer.MAX_VALUE，
 * 可能会创建大量的线程，从而导致 OOM。
 *
 */
public class ThreadPoolTest {

    public static void main(String []args){



//        testNewCachedThreadPool();
//        testNewFixedThreadPool();
//        testNewScheduledThreadPool();
//        testNewSingleThreadExecutor();

//        testThreadPoolExecutor();
        testThreadPoolExecutor2();

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

    /**
     * 线程池都推荐直接通过 ThreadPoolExecutor 来new
     */
    private static void testThreadPoolExecutor(){
        /**
         * corePoolSize 表示线程池的常驻核心线程数。如果设置为 0，则表示在没有任何任务时，销毁线程池；
         * 如果大于 0，即使没有任务时也会保证线程池的线程数量等于此值。但需要注意，此值如果设置的比较小，
         * 则会频繁的创建和销毁线程（创建和销毁的原因会在本课时的下半部分讲到）；如果设置的比较大，
         * 则会浪费系统资源，所以开发者需要根据自己的实际业务来调整此值。
         *
         * maximumPoolSize 表示线程池在任务最多时，最大可以创建的线程数。官方规定此值必须大于 0，
         * 也必须大于等于 corePoolSize，此值只有在任务比较多，且不能存放在任务队列时，才会用到。
         *
         * keepAliveTime 表示线程的存活时间，当线程池空闲时并且超过了此时间，多余的线程就会销毁，
         * 直到线程池中的线程数量销毁的等于 corePoolSize 为止，如果 maximumPoolSize 等于 corePoolSize，
         * 那么线程池在空闲的时候也不会销毁任何线程。
         *
         * unit 表示存活时间的单位，它是配合 keepAliveTime 参数共同使用的。
         *
         * workQueue 表示线程池执行的任务队列，当线程池的所有线程都在处理任务时，
         * 如果来了新任务就会缓存到此任务队列中排队等待执行。
         *
         * threadFactory 表示线程的创建工厂，此参数一般用的比较少，我们通常在创建线程池时不指定此参数，
         * 它会使用默认的线程创建工厂的方法来创建线程。
         *
         * RejectedExecutionHandler 表示指定线程池的拒绝策略，当线程池的任务已经在缓存队列 workQueue
         * 中存储满了之后，并且不能创建新的线程来执行此任务时，就会用到此拒绝策略，它属于一种限流保护的机制。
         */
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 10,
                10L, TimeUnit.SECONDS, new LinkedBlockingQueue(20));

        Future<String> future = executor.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println(ThreadUtils.getName()+" start ThreadPoolExecutor submit  "+new Date());
                ThreadUtils.sleep(2000);
                System.out.println(ThreadUtils.getName()+" end ThreadPoolExecutor submit  "+new Date());
                return "success ThreadPoolExecutor submit";
            }
        });

        try {
            /**
             * get 方法会阻塞主线程
             */
            System.out.println(ThreadUtils.getName()+" "+future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        executor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(ThreadUtils.getName()+" start ThreadPoolExecutor execute  "+new Date());
                ThreadUtils.sleep(2000);
                System.out.println(ThreadUtils.getName()+" end ThreadPoolExecutor execute  "+new Date());
            }
        });

        System.out.println(ThreadUtils.getName()+"  main end...");

    }

    /**
     * 测试拒绝策略
     */
    private static void testThreadPoolExecutor2(){
        // 常驻核心线程:1, 最多两个线程， 线程池执行的任务队列为2
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 2,
                10L, TimeUnit.SECONDS, new LinkedBlockingQueue(3));

        for(int i=0; i<5; i++){
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    ThreadUtils.sleep(1000);
                    System.out.println(ThreadUtils.getName()+" exec... ");
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


