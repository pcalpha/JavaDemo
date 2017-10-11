package cn.com.pcalpha.dp.singleton;

/**
 * Created by caiyida on 2016/10/1.
 */
public class Singleton3 {
    static class SingletonFactory{
        static Singleton3 singleton = new Singleton3();
    }

    private Singleton3() {
    }

    public static Singleton3 getInstance(){
        return SingletonFactory.singleton;
    }
}
