package MyRx;

/**
 * Created by Administrator on 2017/12/20 0020.
 * Observable（订阅源）
 */
public class Observable<T> {

    final Onsubscribe<T> onSubscribe;

    private Observable(Onsubscribe<T> onSubscribe){
        this.onSubscribe = onSubscribe;
    }

    public static <T> Observable<T> create(Onsubscribe<T> onSubscribe){
        return new Observable<T>(onSubscribe);
    }

    public void subscribe(Subscriber<? super T> subscriber){
        subscriber.onStart();
        onSubscribe.call(subscriber);
    }

    public <R> Observable<R> map(Transformer<? super T, ? extends R> transformer){
        return create(new Onsubscribe<R>() {
            @Override
            public void call(Subscriber<? super R> subscriber) {
                Observable.this.subscribe(new Subscriber<T>() {
                    @Override
                    public void onCompleted() {
                        subscriber.onCompleted();
                    }

                    @Override
                    public void onError(Throwable t) {
                        subscriber.onError(t);
                    }

                    @Override
                    public void onNext(T var) {
                        subscriber.onNext(transformer.call(var));
                    }
                });
            }
        });
    }

    // TODO subscribeOn


    public interface Transformer<T, R>{
        R call(T from);
    }

    public interface Onsubscribe<T>{
        void call(Subscriber<?super T> subscriber);
    }


}
