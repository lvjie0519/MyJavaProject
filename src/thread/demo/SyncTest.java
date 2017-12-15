package thread.demo;

/**
 * Created by Administrator on 2017/12/14 0014.
 */
public class SyncTest {

    public static void main(String []args){

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
