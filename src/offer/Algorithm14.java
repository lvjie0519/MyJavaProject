package offer;

/**
 * 一个数组， 基数位于数组前半部分，偶数位于后半部分
 */
public class Algorithm14 {

    public static void main(String args[]) {

        int []array = {1,2,3,4,5,6,7,8, 9};
        swapArray(array);
        for (int data: array) {
            System.out.print(data+" ");
        }
        System.out.println();
    }

    public static void swapArray(int []array){
        if(array == null || array.length <=1){
            return;
        }

        int len = array.length;
        int i=0;
        int j=len-1;
        while (i<j){
            if(array[i] % 2 ==1){
                i++;
                continue;
            }

            if(array[j] % 2 == 0){
                j--;
                continue;
            }

            int temp = array[j];
            array[j] = array[i];
            array[i] = temp;
        }

    }

}
