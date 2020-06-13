package thread.book1;

public class ThreadUtils {

    public static void sleep(long time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static String getName(){
        return Thread.currentThread().getName();
    }

}
