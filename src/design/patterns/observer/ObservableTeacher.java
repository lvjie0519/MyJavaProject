package design.patterns.observer;

public class ObservableTeacher extends Observable {

    private String notifyInfo;

    @Override
    public void notifyAllObserver() {
        for (Observer o: observers) {
            o.update(this.notifyInfo);
        }
    }

    public void setNotifyInfo(String notifyInfo) {
        this.notifyInfo = notifyInfo;
    }
}
