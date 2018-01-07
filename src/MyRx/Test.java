package MyRx;

/**
 * Created by Administrator on 2017/12/20 0020.
 * https://mp.weixin.qq.com/s?__biz=MzA5MzI3NjE2MA==&mid=2650240146&idx=1&sn=2db65955c805cc4c2eadcdc40b2b27be&scene=19#wechat_redirect
 */
public class Test {
    public static void main(String []args){

        Observable.create(new Observable.Onsubscribe<Integer>(){

            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                subscriber.onNext(1);
            }

        }).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }

            @Override
            public void onError(Throwable t) {
                System.out.println("onError");
            }

            @Override
            public void onNext(Integer var) {
                System.out.println("onNext   "+var);
            }
        });

    }
}
