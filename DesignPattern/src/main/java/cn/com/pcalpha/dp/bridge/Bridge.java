package cn.com.pcalpha.dp.bridge;

import decorator.*;

/**
 * Created by caiyida on 2016/10/1.
 */
public class Bridge {
    private Sourceable source;

    public Bridge(Sourceable source) {
        this.source = source;
    }

    public void method(){
        source.method();
    }
}
