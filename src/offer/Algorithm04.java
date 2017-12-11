package offer;

import java.util.Stack;

/**
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
 * 输入一个非递减排序的数组的一个旋转，输出旋转数组的最小元素。
 * 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。
 * NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
 */
public class Algorithm04 {



    public static void main(String []args){

        int [] array = {3,4,5,1,2};
        System.out.println(minNumberInRotateArray(array));
    }

    public static int minNumberInRotateArray(int [] array) {
        if(array == null || array.length == 0){
            return 0;
        }

        int result = 0;

        int size = array.length;
        int left = 0;
        int right = size-1;
        int mid = 0;
        while (array[left] >= array[right]){

            if(right - left<=1){
                break;
            }

            mid = left+(right-left)/2;


            if(array[mid]>array[right]){
                // 最小数在右边
                left = mid;
            }else{
                // 最小数在左边
                right = mid;
            }

            if(left == right || left== right-1){
                break;
            }

        }
        result = array[left]>array[right]?array[right]:array[left];

        return result;

    }

    public static int orderFind(int [] array){

        int result = array[0];

        for (int k: array) {
            if(k<result){
                result = k;
            }
        }

        return result;
    }

}
