package reflect.demo;

/**
 * Created by Administrator on 2017/9/25 0025.
 */
public class Person {

    private String name;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    public void printStr(String string){
        System.out.println(string);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "name: "+this.name;
    }
}
