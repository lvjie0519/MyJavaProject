package thread.book1;

/**
 * 线程组
 * 一个项目中很好的去控制线程
 */
public class ThreadGroupTest {

    public static void main(String []args){
        test();
    }

    private static void test(){


        ThreadGroup threadGroup = new ThreadGroup("lvjie thread group");
        MyThread myThread1 = new MyThread(threadGroup, "myThread1");
        MyThread myThread2 = new MyThread(threadGroup, "myThread2");
        System.out.println("getName="+threadGroup.getName());
        System.out.println("1、activeCount="+threadGroup.activeCount());
        myThread1.start();
        myThread2.start();
        System.out.println("2、activeCount="+threadGroup.activeCount());
        ThreadUtils.sleep(1000);
        System.out.println("3、activeCount="+threadGroup.activeCount());
//        threadGroup.interrupt();      // 可以让线程组中的所有线程 中断
//        System.out.println("4、activeCount="+threadGroup.activeCount());

        /**
         * 拿到线程组中的线程，并让某一线程中断
         */
        Thread [] threads = new Thread[threadGroup.activeCount()];
        threadGroup.enumerate(threads);
        if(threads[0].isAlive()){
            threads[0].interrupt();
        }
        System.out.println("4、activeCount="+threadGroup.activeCount());

    }

    private static class MyThread extends Thread{


        public MyThread(ThreadGroup group, String name) {
            super(group, name);
        }

        @Override
        public void run() {
            System.out.println(ThreadUtils.getName()+" start sleep...");
            ThreadUtils.sleep(2000);
            System.out.println(ThreadUtils.getName()+" end sleep...");
        }
    }
}
