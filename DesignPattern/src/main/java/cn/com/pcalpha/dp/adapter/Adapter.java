package cn.com.pcalpha.dp.adapter;

/**
 * Created by caiyida on 2016/10/1.
 */
public class Adapter extends Source implements Targetable {

    @Override
    public void method1(){
        super.method1();
    }

    @Override
    public void mehtod2() {
        System.out.println("Adapter method2");
    }
}
