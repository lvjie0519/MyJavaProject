package generic_type;

/**
 * Created by Administrator on 2018/1/15 0015.
 * 盘子 类
 */
public class Plate<T> {

    private T item;

    public Plate(T item) {
        this.item = item;
    }

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }
}
