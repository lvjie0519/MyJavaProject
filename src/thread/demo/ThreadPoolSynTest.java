package thread.demo;


import java.util.Locale;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by Administrator on 2018/1/9 0009.
 */
public class ThreadPoolSynTest {

    private static final Random random = new Random();




    public static void main(String []args){

//        testCountDownLatch();
        testCountDownLatch1();
//        testCyclicBarrier();

    }

    private static void testCountDownLatch(){

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        // 一个任务A，它要等待其他4个任务执行完毕之后才能执行，此时就可以利用CountDownLatch来实现这种功能了。
        CountDownLatch cdl = new CountDownLatch(10);  // 参数count为计数值

        for (int i = 0; i < 10; i++) executorService.execute(new MyTaskCountDownLatch(i, cdl));

        try {
            cdl.await();  // 调用await()方法的线程会被挂起，它会等待直到count值为0才继续执行
        } catch (InterruptedException e) {
        }
        System.out.println("它们已经插完啦..............................");
        executorService.shutdown();

    }

    private static void testCountDownLatch1(){

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        Future<Boolean>[] futures=new Future[10];

        for (int i = 0; i < 10; i++) {
            futures[i] = executorService.submit(new CallableThreadTest(i));
        }

        for (int i = 0; i < 10; i++) {
            try {
                boolean success= futures[i].get();
                System.out.println(String.format(Locale.CHINA,"执行完毕，结果为%s",success));
            } catch (InterruptedException|ExecutionException e) {
                e.printStackTrace();
            }
        }


        System.out.println("它们已经插完啦..............................");
        executorService.shutdown();

    }

    static class CallableThreadTest implements Callable<Boolean>{

        private int count;

        public CallableThreadTest(int count) {
            this.count = count;
        }

        @Override
        public Boolean call() throws Exception {
            System.out.println(Thread.currentThread().getName()+"  开始插入数据 "+count);
            Thread.sleep(2000);
            return true;
        }
    }


    static class MyTaskCountDownLatch implements Runnable{
        private int taskNum;
        private CountDownLatch cdl;

        public MyTaskCountDownLatch(int num, CountDownLatch cdl) {
            this.taskNum = num;
            this.cdl = cdl;
        }

        @Override
        public void run() {
            //执行插入数据操作  每次插入一条
            // 模拟耗时
            int time = random.nextInt(10000);
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
            }
            System.out.println("taskNum="+taskNum+"   "+Thread.currentThread().getName() + "执行完了，耗时：" + time / 1000 + "秒");
            cdl.countDown();  // 将count值减1
        }
    }



    // 通过它可以实现让一组线程等待至某个状态之后再全部同时执行。
    private static void testCyclicBarrier(){
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        int size = 5;
        CyclicBarrier barrier  = new CyclicBarrier(size);
        for (int i = 0; i < size; i++) executorService.execute(new MyTaskCyclicBarrier(barrier));

        System.out.println("所有线程写入完毕...");
    }

    static class MyTaskCyclicBarrier extends Thread{

        private CyclicBarrier cyclicBarrier;

        public MyTaskCyclicBarrier(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            System.out.println("线程"+Thread.currentThread().getName()+"  正在写入数据...");
            try {
                Thread.sleep(5000);      //以睡眠来模拟写入数据操作
                System.out.println("线程"+Thread.currentThread().getName()+"  写入数据完毕，等待其他线程写入完毕");
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }catch(BrokenBarrierException e){
                e.printStackTrace();
            }
            System.out.println("线程"+Thread.currentThread().getName()+"  所有线程写入完毕，继续处理其他任务...");
        }
    }




}


