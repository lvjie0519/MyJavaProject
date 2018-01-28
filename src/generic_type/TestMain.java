package generic_type;

/**
 * Created by Administrator on 2018/1/15 0015.
 * https://www.zhihu.com/question/20400700
 */
public class TestMain {

    public static void main(String []args){


//        Plate<Fruit> p=new Plate<Apple>(new Apple()); // error

        // ok
        Plate<? extends Fruit> p=new Plate<Apple>(new Apple());
        // 不能忘里面设置内容
//        p.setItem(new Fruit());       // error
//        p.setItem(new Apple());       // error
//        p.setItem(new Food());        // error

        // 可以获取内容  如果是子类需要强转
        Apple apple = (Apple) p.getItem();
        Fruit fruit =  p.getItem();
        Food food =  p.getItem();


        Plate<? super Fruit> plate = new Plate<>(new Fruit());
        // 可以设置内容  但是上线是 Fruit， 只能是Fruit以及子类
//        plate.setItem(new Food());    // error
        plate.setItem(new Fruit());
        plate.setItem(new Apple());
        plate.setItem(new RedApple());

        // 可以取，但都需要强转
        RedApple redApple1 = (RedApple) plate.getItem();
        Apple apple1 = (Apple) plate.getItem();
        Fruit fruit1 = (Fruit) plate.getItem();
        Food food1 = (Food) plate.getItem();


    }

}
