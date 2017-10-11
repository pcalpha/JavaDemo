package cn.com.pcalpha.dp.observer;

/**
 * Created by caiyida on 2016/10/1.
 */
public class Main {
    public static void main(String[] args) {
        Subject s = new MySubject();
        s.add(new MyObserver1());
        s.add(new MyObserver2());

        s.notifyObservers();
    }
}
