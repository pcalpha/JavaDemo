package cn.com.pcalpha.dp.singleton;

/**
 * Created by caiyida on 2016/10/1.
 */
public class Singleton4 {
    private static Singleton4 singleton;

    private Singleton4() {

    }

    public static synchronized void syncInit(){
        if(singleton==null){
            singleton = new Singleton4();
        }
    }

    public static Singleton4 getInstance(){
        if(singleton==null){
            syncInit();
        }
        return singleton;
    }
}
