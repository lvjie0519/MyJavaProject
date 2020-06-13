package thread.book1;

import java.util.ArrayList;
import java.util.List;

/**
 * 生产者消费者问题
 */
public class ProducerConsumerTest {

    public static void main(String []args){
        test();
    }

    private static void test(){
        List<ProductModel> list = new ArrayList<>();
        MyLock myLock = new MyLock(true);
        Producer producer = new Producer("Producer-1", list, myLock);
        Consumer consumer1 = new Consumer("Consumer-1", list, myLock);
        Consumer consumer2 = new Consumer("Consumer-2", list, myLock);

        producer.start();
        consumer1.start();
        consumer2.start();
    }

    /**
     * 生产者
     */
    private static class Producer extends Thread{

        private List<ProductModel> mProducts;
        private MyLock myLock;

        public Producer(String name, List<ProductModel> products, MyLock lock){
            super(name);
            this.mProducts = products;
            this.myLock = lock;
        }


        @Override
        public void run() {

            int len = 10;
            for(int i=0; i<len; i++){

                synchronized (this.myLock){

                    this.mProducts.add(new ProductModel("name"+i));
                    System.out.println(ThreadUtils.getName()+"  Produce a product, name is name"+i);

                    if(i == len-1){
                        this.myLock.mIsAvalible = false;
                        this.myLock.notifyAll();
                    }else{
                        this.myLock.notify();
                    }
                }

                if(i < len-1){
                    ThreadUtils.sleep(1000);
                }
            }
            System.out.println(ThreadUtils.getName()+ "  Produce exit... ");
        }
    }

    /**
     * 消费者
     */
    private static class Consumer extends Thread{
        private List<ProductModel> mProducts;
        private MyLock myLock;

        public Consumer(String name, List<ProductModel> products, MyLock lock){
            super(name);
            this.mProducts = products;
            this.myLock = lock;
        }

        @Override
        public void run() {
            while (this.myLock.mIsAvalible){
                synchronized (this.myLock){
                    if(this.mProducts.size() == 0){
                        try {
                            this.myLock.wait();
                        } catch (InterruptedException e) {

                        }
                    }
                    if(this.mProducts.size() > 0){
                        ProductModel productModel = this.mProducts.remove(0);
                        System.out.println(ThreadUtils.getName()+" Consumer a product, name is "+ productModel.getName());
                    }

                }
            }

            System.out.println(ThreadUtils.getName()+"  Consumer exit... ");
        }
    }

    private static class ProductModel{
        private String mName;

        public ProductModel(String mName) {
            this.mName = mName;
        }

        public String getName() {
            return mName;
        }
    }

    private static class MyLock{

        public MyLock(boolean mIsAvalible) {
            this.mIsAvalible = mIsAvalible;
        }

        public boolean mIsAvalible;
    }

}
