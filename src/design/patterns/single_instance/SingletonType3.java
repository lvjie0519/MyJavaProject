package design.patterns.single_instance;

/**
 * 枚举类型
 * 它也是《Effective Java》作者极力推荐地单例实现方式，
 * 因为枚举的实现方式不仅是线程安全的，而且只会装载一次，无论是序列化、反序列化、反射还是克隆都不会新创建对象.
 *
 * 枚举类最终会被编译为 final 类型的并且将枚举值标识为 static 类型的，因为 static 类型的属性会在类被加载之后被初始化，所以枚举类就是线程安全的了。
 *
 * https://blog.csdn.net/zhushuai1221/article/details/51780769
 */
public class SingletonType3 {

    // 枚举类型是线程安全的，并且只会装载一次
    private enum SingletonEnum {
        INSTANCE;
        // 声明单例对象
        private final SingletonType3 instance;
        // 实例化
        SingletonEnum() {
            instance = new SingletonType3();
        }
        private SingletonType3 getInstance() {
            return instance;
        }
    }
    // 获取实例（单例对象）
    public static SingletonType3 getInstance() {
        return SingletonEnum.INSTANCE.getInstance();
    }
    private SingletonType3() {
    }
    // 类方法
    public void sayHi() {
        System.out.println("Hi,Java.");
    }


}
