package thread.book1;

public class ThreadUtils {

    public static void sleep(long time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            System.out.println("ThreadUtils -> sleep error...");
            e.printStackTrace();
        }
    }

    public static String getName(){
        return Thread.currentThread().getName();
    }

}
