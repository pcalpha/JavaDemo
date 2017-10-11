package cn.com.pcalpha.dp.observer;

/**
 * Created by caiyida on 2016/10/1.
 */
public class MyObserver1 implements Observer {
    @Override
    public void update() {
        System.out.println("MyObserver1 update");
    }
}
