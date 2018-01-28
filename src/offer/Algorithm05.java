package offer;


/**
 * Created by Administrator on 2017/10/15 0015.
 * 大家都知道斐波那契数列，现在要求输入一个整数n，请你输出斐波那契数列的第n项。
 */
public class Algorithm05 {

    public static void main(String []args){

        System.out.println(Fibonacci(39));
        System.out.println(Fibonacci1(39));

    }

    public static int Fibonacci(int n) {

        if(n < 0){
            return 0;
        }

        if(n == 0){
            return 0;
        }else if(n == 1){
            return 1;
        }else return Fibonacci(n-1)+ Fibonacci(n-2);

    }

    public static int Fibonacci1(int n) {

        if(n<0){
            return 0;
        }

        if(n == 0 || n==1){
            return n;
        }

        int num1 = 0, num2 = 1;
        for(int i=2; i<=n; i++){
            num2 = num1+num2;
            num1 = num2-num1;
        }


        return num2;
    }

}
