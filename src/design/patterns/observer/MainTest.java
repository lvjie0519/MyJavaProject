package design.patterns.observer;

public class MainTest {

    public static void main(String[] args){
        ObservableTeacher teacher = new ObservableTeacher();

        ObserverStudent student1 = new ObserverStudent("张三");
        ObserverStudent student2 = new ObserverStudent("李四");
        ObserverStudent student3 = new ObserverStudent("王五");

        teacher.addObserver(student1);
        teacher.addObserver(student2);
        teacher.addObserver(student3);

        teacher.setNotifyInfo("站起来");
        teacher.notifyAllObserver();
    }

}
