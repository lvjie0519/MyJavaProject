package offer;


/**
 * Created by Administrator on 2017/10/15 0015.
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法。
 */
public class Algorithm06 {

    public static void main(String []args){

        System.out.println(JumpFloor(4));

    }

    public static int JumpFloor(int target) {

        if(target <=2 ){
            return target;
        }

        int lastOne = 2;
        int lastTwo = 1;
        int result = 0;
        for(int i=3; i<=target; i++){
            result = lastOne+lastTwo;
            lastTwo = lastOne;
            lastOne = result;
        }

        return result;

    }


}
