package cn.com.pcalpha.dp.decorator;

/**
 * Created by caiyida on 2016/10/1.
 */
public class Decorator implements Sourceable {
    private Sourceable source;

    public Decorator(Source source) {
        this.source = source;
    }

    @Override
    public void method() {
        System.out.println("start");
        source.method();
        System.out.println("end");
    }
}
