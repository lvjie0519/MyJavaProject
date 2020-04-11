package design.patterns.observer;

public class ObserverStudent implements Observer {

    private String stuName;

    public ObserverStudent(String stuName) {
        this.stuName = stuName;
    }

    @Override
    public void update(String info) {
        System.out.println(stuName+":  "+info);
    }


}
