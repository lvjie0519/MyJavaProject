package design.patterns.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by Administrator on 2017/11/1 0001.
 * 代理模式， 静态代理 & 动态代理
 */
public class MainTest {

    public static void main(String []args){
//        testStaticProxy();
        testDynamicProxy();
    }

    /**
     * 测试静态代理
     * 使用静态代理很容易就完成了对一个类的代理操作。
     * 但是静态代理的缺点也暴露了出来：由于代理只能为一个类服务，如果需要代理的类很多，
     * 那么就需要编写大量的代理类，比较繁琐。
     */
    private static void testStaticProxy(){
        StudentProxy helloProxy = new StudentProxy();
        helloProxy.sayHello();
    }

    /**
     * 测试动态代理
     */
    private static void testDynamicProxy(){

        Student student = new Student("jack", 29);
        InvocationHandler handler = new ProxyHandler(student);
        PersonInterface proxyHello = (PersonInterface) Proxy.newProxyInstance(student.getClass().getClassLoader(),
                student.getClass().getInterfaces(), handler);
        student.setAge(20);
        student.setName("lvjie");
        proxyHello.sayHello();
    }

    /**
     * 动态代理类
     * 优点：解决了静态代理中冗余的代理实现类问题。
     * 缺点：JDK 动态代理是基于接口设计实现的，如果没有接口，会抛异常。
     */
    public static class ProxyHandler implements InvocationHandler {
        private Object object;
        public ProxyHandler(Object object){
            this.object = object;
        }
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("Before invoke "  + method.getName());
            Object result = method.invoke(object, args);
            System.out.println("After invoke " + method.getName());
            return result;
        }
    }
}
