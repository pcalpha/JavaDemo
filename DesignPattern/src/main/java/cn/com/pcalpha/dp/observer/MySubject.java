package cn.com.pcalpha.dp.observer;

import java.util.Vector;

/**
 * Created by caiyida on 2016/10/1.
 */
public class MySubject implements Subject {
    private Vector<Observer> observers = new Vector<Observer>();


    @Override
    public void add(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void del(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer:observers){
            observer.update();
        }
    }
}
