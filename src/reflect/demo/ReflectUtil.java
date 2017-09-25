package reflect.demo;

import java.lang.reflect.Method;

/**
 * Created by Administrator on 2017/9/25 0025.
 */
public class ReflectUtil {

    public Method[] getDeclaredMethods(Class<?> clazz){
        return clazz.getDeclaredMethods();
    }


}
