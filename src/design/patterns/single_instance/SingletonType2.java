package design.patterns.single_instance;

/**
 * 内部类的实现方式
 * 只有当调用getInstance 方法，才会加载SingletonInstance类
 * 此方式也是较为推荐的单例实现方式。
 */
public class SingletonType2 {

    // 静态内部类
    private static class SingletonInstance {
        private static final SingletonType2 instance = new SingletonType2();
    }
    // 获取实例（单例对象）
    public static SingletonType2 getInstance() {
        return SingletonInstance.instance;
    }
    private SingletonType2() {
    }
    // 类方法
    public void sayHi() {
        System.out.println("Hi,Java.");
    }

}
