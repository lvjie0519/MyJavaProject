package design.patterns.single_instance;

/**
 * double check
 */
public class SingletonType1 {

    /**
     * 声明私有对象
     * 这里设置为volatile， 防止getInstance 多线程不安全
     *  instance = new SingletonType1() 这行代码  并不是原子操作，这行代码最终会被编译成多条汇编指令，它大致的执行流程为以下三个步骤：
     *
     * 1、给对象实例分配内存空间；
     * 2、调用对象的构造方法、初始化成员字段；
     * 3、将 instance 对象指向分配的内存空间。
     *
     * 但由于 CPU 的优化会对执行指令进行重排序，也就说上面的执行流程的执行顺序有可能是 1-2-3，也有可能是 1-3-2。
     */
    private volatile static SingletonType1 instance;
    // 获取实例（单例对象）
    public static SingletonType1 getInstance() {
        // 第一次判断
        if (instance == null) {
            synchronized (SingletonType1.class) {
                // 第二次判断
                if (instance == null) {
                    instance = new SingletonType1();
                }
            }
        }
        return instance;
    }
    private SingletonType1() {
    }
    // 类方法
    public void sayHi() {
        System.out.println("Hi,Java.");
    }

}
