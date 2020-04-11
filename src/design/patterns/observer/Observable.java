package design.patterns.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 被观察者
 */
public abstract class Observable {

    protected List<Observer> observers = new ArrayList<>();
    public abstract void notifyAllObserver();

    public void addObserver(Observer observer){
        observers.add(observer);
    }
}
