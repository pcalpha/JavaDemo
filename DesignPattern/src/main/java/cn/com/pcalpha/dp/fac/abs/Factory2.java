package cn.com.pcalpha.dp.fac.abs;

/**
 * Created by caiyida on 2016/10/1.
 */
public class Factory2 implements Factory{

    @Override
    public Product produce() {
        return new Product2();
    }
}


