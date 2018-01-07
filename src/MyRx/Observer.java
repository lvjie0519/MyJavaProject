package MyRx;

/**
 * Created by Administrator on 2017/12/20 0020.
 */
public interface Observer<T> {
    void onCompleted();
    void onError(Throwable t);
    void onNext(T var);
}
