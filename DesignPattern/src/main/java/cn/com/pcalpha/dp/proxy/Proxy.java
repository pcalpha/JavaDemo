package cn.com.pcalpha.dp.proxy;

/**
 * Created by caiyida on 2016/10/1.
 */
public class Proxy implements Sourceable {
    private Source source;

    public Proxy(Source source) {
        this.source = source;
    }


    @Override
    public void method() {
        beforeMethod();
        source.method();
        afterMethod();
    }

    public void beforeMethod(){
        System.out.println("before");
    }

    public void afterMethod(){
        System.out.println("after");
    }
}
