package offer;

/**
 * Created by Administrator on 2017/10/15 0015.
 * 请实现一个函数，将一个字符串中的空格替换成“%20”。
 * 例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
 */
public class Algorithm02 {

    public static void main(String []args){



    }


    public static String replaceSpace(StringBuffer str) {
        String result = "";
        if(str == null || "".equals(str.toString())){
            return result;
        }

        result = str.toString().replaceAll(" ", "%20");

        return result;
    }
}
