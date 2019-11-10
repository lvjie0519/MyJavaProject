package offer;

import java.util.Scanner;

/**
 * Created by lvjie on 2019/10/26.
 * 在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，
 * 每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，
 * 判断数组中是否含有该整数。
 */
public class Algorithm09 {

    public static void main(String args[]) {
        int [][] array={
                {1,2,3,4},
                {2,5,6,8},
                {3,7,9,10}
        };
        System.out.println(find(5, array));
        System.out.println(find(11, array));
    }

    public static boolean find(int target, int [][] array) {
        if(array == null || array.length == 0){
            return false;
        }
        int lenR = array.length;
        int lenC = array[0].length;

        boolean result = false;
        for(int r=0, c=lenC-1; r<lenR&&c>=0;){
            if(target == array[r][c]){
                result = true;
                break;
            }
            if(target<array[r][c]){
                c--;
            }else if(target>array[r][c]){
                r++;
            }
        }
        return result;
    }

}
