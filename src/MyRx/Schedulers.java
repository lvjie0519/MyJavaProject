package MyRx;

import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2017/12/20 0020.
 */
public class Schedulers {

    private static final Scheduler ioScheduler = new Scheduler(Executors.newSingleThreadExecutor());

    public static Scheduler io(){
        return  ioScheduler;
    }

}
