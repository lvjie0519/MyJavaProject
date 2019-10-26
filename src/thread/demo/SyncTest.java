package thread.demo;

/**
 * Created by Administrator on 2017/12/14 0014.
 */
public class SyncTest {

    public static void main(String []args){


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    long startTime = System.currentTimeMillis();
                    System.out.println("start sleep...");
                    Thread.sleep(2000);
                    System.out.println("stop sleep, sleep cost: "+(System.currentTimeMillis()-startTime));
                    System.out.println("isInterrupted: "+Thread.currentThread().isInterrupted());

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();



//        MySync mySync = new MySync();
//
//        MyThread t1 = new MyThread(mySync);
//        MyThread t2 = new MyThread(mySync);
//
//        t1.start();
//        t2.start();
    }


}

//class MySync{
//
//    private int count = 0;
//
//    public void execute(){
//
//        synchronized (this){
//            for(int i=0; i<5; i++){
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println(Thread.currentThread().getName()+":  count="+count++);
//            }
//        }
//
//    }
//
//
//}
//
//class MyThread extends Thread
//{
//    private MySync mySync;
//
//    public MyThread(MySync sync)
//    {
//        this.mySync = sync;
//    }
//
//    @Override
//    public void run()
//    {
//        mySync.execute();
//    }
//
//}
