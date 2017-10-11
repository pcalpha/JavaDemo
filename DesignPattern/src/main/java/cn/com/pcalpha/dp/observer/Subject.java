package cn.com.pcalpha.dp.observer;

/**
 * Created by caiyida on 2016/10/1.
 */
public interface Subject {
    public void add(Observer observer);
    public void del(Observer observer);
    public void notifyObservers();
}
