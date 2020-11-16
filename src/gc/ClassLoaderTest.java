package gc;

import java.io.IOException;
import java.io.InputStream;

public class ClassLoaderTest {

    public static void main(String[] args){
        ClassLoader myLoader = new ClassLoader() {

            /**
             * 自定义加载器  通常是覆写该方法
             * @param name
             * @return
             * @throws ClassNotFoundException
             */
            @Override
            protected Class<?> findClass(String name) throws ClassNotFoundException {
                return super.findClass(name);
            }

            /**
             * 一般不建议覆写 该方法
             * @param name
             * @return
             * @throws ClassNotFoundException
             */
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {

                String fileName = name.substring(name.lastIndexOf(".")+1)+".class";
                InputStream is = getClass().getResourceAsStream(fileName);
                if(is == null){
                    return super.loadClass(name);
                }
                try {
                    byte[] b = new byte[is.available()];
                    is.read(b);
                    return defineClass(name, b, 0, b.length);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return super.loadClass(name);
            }
        };

        try {
            Class c = myLoader.loadClass("gc.ClassLoaderTest");
            Object o = null;
            try {
                o = c.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            System.out.println(o.getClass());     // class gc.ClassLoaderTest
            System.out.println(o instanceof ClassLoaderTest);        // false
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            Class c = Class.forName("gc.ClassLoaderTest");
            Object o = null;
            try {
                o = c.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            System.out.println(o.getClass());  // class gc.ClassLoaderTest
            System.out.println(o instanceof ClassLoaderTest);    // true
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

}
