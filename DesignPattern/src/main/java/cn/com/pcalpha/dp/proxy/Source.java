package cn.com.pcalpha.dp.proxy;

/**
 * Created by caiyida on 2016/10/1.
 */
public class Source implements Sourceable {
    @Override
    public void method() {
        System.out.println("source method");
    }
}
