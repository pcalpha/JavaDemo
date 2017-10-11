package cn.com.pcalpha.dp.adapter2;

/**
 * Created by caiyida on 2016/10/1.
 */
public class Adapter implements Targetable {
    private Source source;

    public Adapter(Source source) {
        this.source = source;
    }

    @Override
    public void method1(){
        source.method1();
    }

    @Override
    public void method2() {
        System.out.println("Adapter method2");
    }
}
