package cn.com.pcalpha.dp.singleton;

/**
 * Created by caiyida on 2016/10/1.
 */
public class Singleton {
    private static Singleton singleton;
    private Singleton() {
    }

    public static Singleton getInstance(){
        if(singleton==null){
            singleton = new Singleton();
        }
        return singleton;
    }
}
