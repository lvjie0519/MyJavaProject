package thread.book1;

import java.util.Date;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 */
public class ReenTrantWriteReadLockTest {

    public static void main(String []args){
//        testRead();
//        testWrite();
        testWriteRead();
    }


    /**
     * 读， 多个线程之间不互斥， 即不同步；
     */
    private static void testRead(){
        MyServer myServer = new MyServer();
        Thread []readThreads = new Thread[5];
        for(int i=0; i<readThreads.length; i++){
            readThreads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    myServer.testRead();
                }
            }, "thread"+i);
        }

        for(int i=0; i<readThreads.length; i++){
            readThreads[i].start();
        }
    }

    /**
     * 写， 相互互斥，即多个线程同步执行
     */
    private static void testWrite(){
        MyServer myServer = new MyServer();
        Thread []readThreads = new Thread[5];
        for(int i=0; i<readThreads.length; i++){
            readThreads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    myServer.testWrite();
                }
            }, "thread"+i);
        }

        for(int i=0; i<readThreads.length; i++){
            readThreads[i].start();
        }
    }

    /**
     * 读和写  也具有互斥性， 即相互之间具有同步作用
     */
    private static void testWriteRead(){
        MyServer myServer = new MyServer();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                myServer.testRead();
            }
        },"thread1");
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                myServer.testWrite();
            }
        },"thread2");

        thread1.start();
        thread2.start();
    }

    private static class MyServer{
        private ReentrantReadWriteLock mLock;

        public MyServer() {
            mLock = new ReentrantReadWriteLock();
        }

        public void testRead(){
            mLock.readLock().lock();
            System.out.println(ThreadUtils.getName()+":  will read..."+new Date());
            ThreadUtils.sleep(2000);
            System.out.println(ThreadUtils.getName()+":  complete read..."+new Date());
            mLock.readLock().unlock();
        }

        public void testWrite(){
            mLock.writeLock().lock();
            System.out.println(ThreadUtils.getName()+":  will write..."+new Date());
            ThreadUtils.sleep(2000);
            System.out.println(ThreadUtils.getName()+":  complete write..."+new Date());
            mLock.writeLock().unlock();
        }
    }

}
