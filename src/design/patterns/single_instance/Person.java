package design.patterns.single_instance;

/**
 * Created by Administrator on 2017/11/1 0001.
 */
public class Person {

    private String name;
    private int age;

    private static Person mPerson = null;

    private Person(){}

//    public static Person getInstance(){
//        if(mPerson == null){
//            synchronized (Person.class){
//                if(mPerson == null){
//                    mPerson = new Person();
//                }
//            }
//        }
//        return mPerson;
//    }

    public static Person getInstance(){
        return PersonHolder.mPerson;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private static class PersonHolder{
        private static final Person mPerson = new Person();
    }

}
