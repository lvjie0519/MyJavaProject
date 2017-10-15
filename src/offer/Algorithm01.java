package offer;

/**
 * Created by Administrator on 2017/10/15 0015.
 * 在一个二维数组中，每一行都按照从左到右递增的顺序排序，
 * 每一列都按照从上到下递增的顺序排序。
 * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 */
public class Algorithm01 {

    public static void main(String []args){

        int [][]array = {
                {11,13,15,17,19},
                {21,23,25,27,29},
                {31,33,35,37,39},
                {41,43,45,47,49},
        };

        System.out.println(find(35, array));

    }


    public static boolean find(int target, int [][] array) {
        boolean isFind = false;

        if(array == null || array.length == 0 || array[0].length == 0){
            return isFind;
        }

        int lenI = array.length;        // 二维数组的行数
        int lenJ = array[0].length;     // 二维数组的列数

        int i=lenI-1, j=0;
        do{

            if(target == array[i][j]){
                isFind = true;
            }else if(target < array[i][j]){
                i--;
            }else{
                j++;
            }

        }while (i>=0 && j<lenJ && !isFind);

        return  isFind;
    }
}
