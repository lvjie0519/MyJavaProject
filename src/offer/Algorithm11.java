package offer;

/**
 * Created by lvjie on 2020/6/6
 * 斐波那契数列   f(n) = f(n-1) + f(n-2)
 * f(1) = 0; f(1) = 1;
 */
public class Algorithm11 {

    public static void main(String args[]) {

        System.out.println(fibonacci1(1));
        System.out.println(fibonacci1(2));
        System.out.println(fibonacci1(3));
        System.out.println(fibonacci1(4));
        System.out.println(fibonacci2(4));
        System.out.println(fibonacci2(5));
    }

    /**
     * 可以使用递归的方式
     * @param n
     * @return
     */
    public static int fibonacci1(int n){
        if(n <= 1){
            return n;
        }
        return fibonacci1(n-1) + fibonacci1(n-2);
    }

    /**
     * 非递归方式
     * @param n
     * @return
     */
    public static int fibonacci2(int n){
        if(n <= 1){
            return n;
        }

        int f_n_1 = 1;
        int f_n_2 = 0;
        int f_n = 0;
        for(int i=2; i<=n; i++){
            f_n = f_n_1+f_n_2;
            f_n_2 = f_n_1;
            f_n_1 = f_n;
        }

        return f_n;

    }
}
