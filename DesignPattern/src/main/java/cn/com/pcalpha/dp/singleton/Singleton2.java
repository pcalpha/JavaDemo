package cn.com.pcalpha.dp.singleton;

/**
 * Created by caiyida on 2016/10/1.
 */
public class Singleton2 {
    private static Singleton2 singleton;

    private Singleton2() {

    }

    public synchronized static Singleton2 getInstance(){
        if(singleton==null){
            synchronized (singleton){
                if(singleton==null){
                    singleton = new Singleton2();
                }
            }

        }
        return singleton;
    }


}
