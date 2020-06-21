import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Created by Administrator on 2017/9/3 0003.
 */
public class TestMain {

    public static void main(String []args){
        String s1 = "aaaa";
        String s2 = "aaa";
        String s3 = s2.intern();
        String s4 = s1+s2;
        System.out.println(s1.compareTo(s2));
        System.out.println(s2.compareTo(s1));
        System.out.println(s3 == s2);
        System.out.println(s4);


        int h;
        int result = s2 == null ? 0 : (h = s2.hashCode()) ^ h >>> 16;
    }
}