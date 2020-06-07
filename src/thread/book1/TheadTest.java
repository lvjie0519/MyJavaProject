package thread.book1;

/**
 * 1、线程启动  -- start
 * 2、线程暂停  -- suspend and resume
 * 3、线程停止  -- 中断 异常
 * 4、线程优先级 -- setPriority
 * 5、线程安全  -- 同步
 */
public class TheadTest {

    public static void main(String []args){

//        testThread1();
//        testThread5();

        testThread6();
    }

    /**
     * Thread 类中一些方法
     */
    private static void testThread1(){
        /**
         * 当前代码块被哪个线程执行，返回当前线程名
         */
        Thread1 thread1 = new Thread1("thread-1");
        thread1.start();
        System.out.println("1-thread1.isAlive: "+thread1.isAlive());
        ThreadUtils.sleep(1000);
        System.out.println("2-thread1.isAlive: "+thread1.isAlive());
        System.out.println("==========================================");

        /**
         * 如何停止线程
         */
        Thread11 thread11 = new Thread11("thread11");
        thread11.start();
        ThreadUtils.sleep(100);
        thread11.interrupt();       //  中断一个线程
        System.out.println("isInterrupted: "+thread11.isInterrupted());     // true
        System.out.println("isInterrupted: "+thread11.isInterrupted());     // true

    }

    private static class Thread1 extends Thread{

        public Thread1(String name){
            super(name);
        }

        @Override
        public void run() {
            System.out.println("Thread.currentThread().getName 线程名： "+Thread.currentThread().getName());
            System.out.println("this.getName 线程名： "+this.getName());
        }
    }

    private static class Thread11 extends Thread{

        public Thread11(String name){
            super(name);
        }

        @Override
        public void run(){
            long total = 0;
            int i=0;
            for(i=0; i<500000000; i++){
                total+=i;
                if(this.isInterrupted()){
                    // 只是退出for循环，for循环后面的语句仍然会执行
                    // 如果真的要实现线程停止，  可以通过抛异常的方式解决
                    break;
//                    throw new Exception("");
                }
            }
            System.out.println("total: "+total+"  i="+i);
        }
    }

    private static void testThread5(){
        Thread5 thread5 = new Thread5();
        Thread thread51 = new Thread(thread5, "thread51");
        Thread thread52 = new Thread(thread5, "thread52");
        Thread thread53 = new Thread(thread5, "thread53");
        Thread thread54 = new Thread(thread5, "thread54");
        Thread thread55 = new Thread(thread5, "thread55");

        thread51.start();
        thread52.start();
        thread53.start();
        thread54.start();
        thread55.start();
    }

    /**
     * 5、线程安全
     */
    private static class Thread5 implements Runnable{

        private int count = 5;

        /**
         * 线程不安全
         */
        @Override
        public void run() {
            count--;
            System.out.println(Thread.currentThread().getName()+":  count="+count);
        }

        /**
         * 线程安全
         */
//        @Override
//        public void run() {
//            synchronized (this){
//                count--;
//                System.out.println(Thread.currentThread().getName()+":  count="+count);
//            }
//        }
    }


    /**
     * 测试守护线程
     */
    private static void testThread6(){
        Thread6 thread6 = new Thread6();
        /**
         * 如果设置为守护线程， 三秒后程序退出
         * 反正，需要等子线程运行完毕，才退出
         */
        thread6.setDaemon(true);        // 设置为守护线程
        thread6.start();
        ThreadUtils.sleep(3000);
        System.out.println(Thread.currentThread().getName()+" will exist...");
    }
    private static class Thread6 extends Thread{
        @Override
        public void run() {
            for(int i=0; i<10; i++){
                ThreadUtils.sleep(1000);
                System.out.println(Thread.currentThread().getName()+": i="+i);
            }
        }
    }
}
