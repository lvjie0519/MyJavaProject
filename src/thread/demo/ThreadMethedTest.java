package thread.demo;

/**
 * Created by Administrator on 2017/12/14 0014.
 * wait()方法    notify()方法  sleep()  yield()  join()
 */
public class ThreadMethedTest {

    public static void main(String []args){
        MyThreadExec mySync = new MyThreadExec();

        MyThread2 t1 = new MyThread2("thread-1", mySync, 1);
        MyThread2 t2 = new MyThread2("thread-2",mySync, 2);

        t1.start();
        t2.start();

        try {
            t2.join();
            System.out.println("complete");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("complete");

    }


}

class MyThreadExec{

    private int count = 0;

    public void increase(){

        synchronized (this){
            try {
                Thread.sleep(1000);
                count++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count++;
            System.out.println(Thread.currentThread().getName()+":  count="+count);
        }

    }

    public void decrease(){

        synchronized (this){
            try {
                Thread.sleep(1000);
                count--;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+":  count="+count);
        }

    }


}

class MyThread2 extends Thread
{
    private MyThreadExec myThreadExec;
    private int mFlag;

    public MyThread2(String threadName, MyThreadExec sync, int flag)
    {
        this.setName(threadName);
        this.myThreadExec = sync;
        this.mFlag = flag;
    }

    @Override
    public void run()
    {
        if(this.mFlag == 1){
            this.myThreadExec.increase();
        }else if(this.mFlag == 2){
            this.myThreadExec.decrease();
        }
    }

}
