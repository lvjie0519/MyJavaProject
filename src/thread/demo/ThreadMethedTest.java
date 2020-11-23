package thread.demo;

/**
 * Created by Administrator on 2017/12/14 0014.
 * wait()方法： 会释放锁
 * notify()方法
 * sleep()： 不会释放锁
 * yield()：不会释放锁
 * yield 使当前线程让出 CPU 时间片，线程从运行状态（Running）变为可执行状态（Runnable），
 * 处于可执行状态的线程有可能会再次获取到时间片继续执行，也有可能处于等待状态，直到再次获取到时间片。也就是说，后续会有两种情况：
 * 1、当前线程让出 CPU 时间片后，又立即获取到 CPU 时间片，进而继续执行当前方法。
 * 2、当前线程让出 CPU 时间片后，等待一段时间后获取到 CPU 时间片，进而继续执行当前方法。
 * join()：让一个线程等待另外一个线程完成才继续执行
 * 如果线程A执行体中调用B线程的join()方法，则A线程将会被阻塞，直到B线程执行完为止，A才能得以继续执行。
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
