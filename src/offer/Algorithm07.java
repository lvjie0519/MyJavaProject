package offer;


import java.util.Scanner;

/**
 * Created by Administrator on 2017/10/15 0015.
 * 给出一个有序数列随机旋转之后的数列，如原有序数列为：[0,1,2,4,5,6,7] ，
 * 旋转之后为[4,5,6,7,0,1,2]。 假定数列中无重复元素，且数列长度为奇数。
 * 求出旋转数列的中间值。如数列[4,5,6,7,0,1,2]的中间值为4。
 *
 * 思路： 首先找到最小值在数组中的位置minPosition，那么最小值移动的距离为d=minPosition-1
 * 最初始的时候中间值的位置centerPosition = len/2+1;由于是旋转移动，则中间值的当前位置为（centerPosition+d）%len
 */
public class Algorithm07 {

    public static void main(String []args){

        Scanner scan = new Scanner(System.in);
        String line;
        while (scan.hasNextLine()) {
            line = scan.nextLine().trim();
            // please write your code here
            String datas[] = line.split(",");
            int array[] = new int[datas.length];
            for(int i=0; i<datas.length; i++){
                array[i] = Integer.valueOf(datas[i]);
            }

            System.out.println(getCenterValue(array));
            // System.out.println("answer");
        }

    }

    private static int getCenterValue(int [] array){

        if(array.length == 1){
            return array[0];
        }

        int result = -1;

        int len = array.length;
        int minPosition = 0;
        int minValue = array[0];
        for(int i=1; i<len; i++){
            if(minValue>array[i]){
                minValue = array[i];
                minPosition = i;
            }
        }
        int centerPosition = minPosition+len/2+1;
        centerPosition = centerPosition%len;
        if(centerPosition == 0){
            centerPosition = len;
        }
        result = array[centerPosition-1];

        return result;
    }

}
