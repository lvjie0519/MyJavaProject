package thread.book1;

public class NotifyWaitTest {

    public static void main(String []args){
        test1();
    }

    /**
     * wait notify 都必须在同步方法种执行， 否则会报java.lang.IllegalMonitorStateException 错误
     */
    private static void test1(){
        Object lock = new Object();
        MyThread myThread0 = new MyThread("thread-0", lock, 0);
        MyThread myThread1 = new MyThread("thread-1", lock, 1);

        myThread1.start();
        ThreadUtils.sleep(1000);
        myThread0.start();

    }

    private static class MyThread extends Thread{

        private Object mOwerLock;
        private Object mLock;
        private int mType;

        public MyThread(String name, Object mLock, int type) {
            super(name);
            this.mLock = mLock;
            this.mType = type;
            mOwerLock = new Object();
        }

        @Override
        public void run() {
            System.out.println(ThreadUtils.getName()+" will run...");
            if(mType == 1){
                try {
                    System.out.println(ThreadUtils.getName()+" stat wait...");
                    this.mLock.wait();
                    System.out.println(ThreadUtils.getName()+" end wait...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else{
                System.out.println(ThreadUtils.getName()+" notify...");
                this.mLock.notify();
            }
        }
    }

}
