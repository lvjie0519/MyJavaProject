package MyRx;


import java.util.concurrent.Executor;

/**
 * Created by Administrator on 2017/12/20 0020.
 */
public class Scheduler {

    final Executor executor;
    public Scheduler(Executor executor){
        this.executor = executor;
    }

    public Worker createWorker(){
        return new Worker(executor);
    }

    public static class Worker{
        final Executor executor;
        public Worker(Executor executor){
            this.executor = executor;
        }

        public void schedule(Runnable runnable){
            executor.execute(runnable);
        }
    }

}
