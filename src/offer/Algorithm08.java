package offer;

import java.util.Scanner;

/**
 * Created by lvjie on 2019/10/26.
 * 给出一个无序的数列，找出其中缺失的第一个正数，
 * 要求复杂度为 O(n) 如：[1,2,0]，第一个缺失为3。 如：[3,4,-1,1]，第一个缺失为2。
 */
public class Algorithm08 {

    public static void main(String args[]) {
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

            System.out.println(getNeedData(array));
            // System.out.println("answer");
        }
    }

    private static int getNeedData(int []array){
        int result = -1;
        if(array.length == 0){
            return  1;
        }
        int len = array.length;
        int []tags = new int[len];
        for(int i=0; i<len; i++){
            if(array[i]>0 && array[i]<=len){
                tags[array[i]-1] = 1;
            }
        }
        for(int i=0; i<len; i++){
            if(tags[i] != 1){
                result = i+1;
                break;
            }
        }

        if(result == -1){
            result = array.length+1;
        }

        return result;
    }

}
