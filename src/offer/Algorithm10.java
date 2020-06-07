package offer;

/**
 * Created by lvjie on 2020/6/6
 * 找数组中是否有重复的数字， 数组里面的值大小是 0 - n-1
 */
public class Algorithm10 {

    public static void main(String args[]) {
        int []array = {0,1,3,4,2,5};
        System.out.println(findRepeatData(array));
    }

    public static int findRepeatData(int [] array){
        int result = -1;
        if(array == null || array.length<2){
            return result;
        }

        int len = array.length;
        int i=0;
        while (i<len){
            int value = array[i];
            if(i !=value && value==array[value]){
                result = value;
                break;
            }
            if(i == value){
                i++;
            }else{
                int temp = array[value];
                array[value] = value;
                array[i] = temp;
            }
        }
        return result;
    }
}
