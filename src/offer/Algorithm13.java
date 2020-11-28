package offer;

/**
 * 两个大数相加， 用字符串来解决
 */
public class Algorithm13 {

    public static void main(String args[]) {

        String numb1 = "123123";
        String numb2 = "9999";
        System.out.println(addBigNumber(numb1, numb2));
    }

    public static String addBigNumber(String numb1, String numb2){

        if(numb1 == null || "".equals(numb1)){
            return numb2;
        }
        if(numb2 == null || "".equals(numb2)){
            return numb1;
        }

        String result = "";
        int numb1Len = numb1.length();
        int numb2Len = numb2.length();
        int i = numb1Len-1, j = numb2Len-1;
        int tenFlag = 0;
        while (i>=0 && j>=0){
            int temp1 = numb1.charAt(i)-'0';
            int temp2 = numb2.charAt(j)-'0';
            int add = temp1+temp2+tenFlag;
            if(add>=10){
                tenFlag = 1;
                add -=10;
            }else{
                tenFlag = 0;
            }
            result = add+result;
            i--;
            j--;
        }

        String subStr = "";
        if(numb1Len>numb2Len){
            subStr = numb1.substring(0, numb1Len-numb2Len);
        }else if(numb1Len<numb2Len){
            subStr = numb2.substring(0, numb2Len-numb1Len);
        }
        result = stringNumbAddValue(subStr, tenFlag)+result;

        return result;
    }

    private static String stringNumbAddValue(String numb, int value){
        if(value <= 0){
            return numb;
        }

        String result = "";
        int len = numb.length();
        int i=len-1;
        int tenFlag = 0;
        boolean isAddValue = false;
        while (i>=0){
            int temp = numb.charAt(i)-'0';
            int add = 0;
            if(!isAddValue){
                add = temp+value+tenFlag;
                isAddValue = true;
            }else{
                add = temp+tenFlag;
            }

            if(add>=10){
                add -=10;
                tenFlag = 1;
            }else{
                tenFlag = 0;
            }
            result = add+result;
            i--;
        }
        if(tenFlag == 1){
            result = tenFlag+result;
        }
        return result;
    }

}
