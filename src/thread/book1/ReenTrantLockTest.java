package thread.book1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 重入锁
 */
public class ReenTrantLockTest {

    public static void main(String []args){
//        test();
//        test1();
        test2();
    }

    /**
     *  测试 ReentrantLock 基本用发
     *  ReentrantLock支持更加灵活的同步代码块，但是使用synchronized时，只能在同一个synchronized块结构中获取和释放。
     *  注：ReentrantLock的锁释放一定要在finally中处理，否则可能会产生严重的后果。
     */
    private static void test1(){

        MyServer1 myServer1 = new MyServer1();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                myServer1.testMethod();
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                myServer1.testMethod();
            }
        });

        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                myServer1.testMethod();
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();
    }

    private static class MyServer1{
        private Lock mLock = new ReentrantLock();

        public void testMethod(){
            mLock.lock();
            for(int i=0; i<5; i++){
                System.out.println(ThreadUtils.getName()+"  testMethod i="+i);
                ThreadUtils.sleep(100);
            }
            mLock.unlock();
        }
    }


    /**
     *  ReentrantLock 结合 Condition使用
     *  Condition是在java 1.5中才出现的，它用来替代传统的Object的wait()、notify()实现线程间的协作，
     *  相比使用Object的wait()、notify()，使用Condition的await()、signal()这种方式实现线程间协作更加安全和高效。
     *  因此通常来说比较推荐使用Condition。
     *
     *  Condition类能实现synchronized和wait、notify搭配的功能，另外比后者更灵活，
     *  Condition可以实现多路通知功能，也就是在一个Lock对象里可以创建多个Condition（即对象监视器）实例，
     *  线程对象可以注册在指定的Condition中，从而可以有选择的进行线程通知，在调度线程上更加灵活。
     *  而synchronized就相当于整个Lock对象中只有一个单一的Condition对象，所有的线程都注册在这个对象上。
     *  线程开始notifyAll时，需要通知所有的WAITING线程，没有选择权，会有相当大的效率问题。
     *
     *  Condition是个接口，基本的方法就是await()和signal()方法。
     *  Condition依赖于Lock接口，生成一个Condition的基本代码是lock.newCondition()
     *  调用Condition的await()和signal()方法，都必须在lock保护之内，就是说必须在lock.lock()和lock.unlock之间才可以使用。
     *
     */
    private static void test2(){
        MyServer2 myServer2 = new MyServer2();

        Thread producer = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0; i<10; i++){
                    System.out.println(ThreadUtils.getName()+":  product name"+i);
                    myServer2.produceData("product name"+i, i%3);
                    ThreadUtils.sleep(1000);
                }
            }
        }, "producer");

        Thread consumer1 = new Thread(new Runnable() {
            @Override
            public void run() {
                String result = myServer2.consumeData1();
                System.out.println(ThreadUtils.getName()+":  consume "+result);
            }
        }, "consumer1");
        Thread consumer2 = new Thread(new Runnable() {
            @Override
            public void run() {
                String result = myServer2.consumeData2();
                System.out.println(ThreadUtils.getName()+":  consume "+result);
            }
        }, "consumer2");

        producer.start();
        ThreadUtils.sleep(2000);
        consumer1.start();
        consumer2.start();
    }

    private static class MyServer2{

        private Lock mLock = new ReentrantLock();;
        private Condition mCondition1 = mLock.newCondition();
        private Condition mCondition2 = mLock.newCondition();
        private List<String> mDatas = new ArrayList<>();

        public MyServer2() {
        }

        // 生产者
        public void produceData(String s, int consumerType){
            mLock.lock();
            try {
                mDatas.add(s);
                if(consumerType == 1){
                    mCondition1.signal();
                }else if(consumerType == 2){
                    mCondition2.signal();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                mLock.unlock();
            }

        }

        // 消费者1
        public String consumeData1(){
            return consumeData(1);
        }

        // 消费者2
        public String consumeData2(){
            return consumeData(2);
        }

        // 消费者
        public String consumeData(int consumerType){

            Condition tempCondition = null;
            if(consumerType == 1){
                tempCondition = mCondition1;
            }else if(consumerType == 2){
                tempCondition = mCondition2;
            }

            if(tempCondition == null){
                return null;
            }

            String result = null;
            mLock.lock();
            try {
                if (mDatas.size() == 0) {
                    try {
                        tempCondition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    result = mDatas.remove(0);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                mLock.unlock();
            }
            return result;
        }

    }

}
