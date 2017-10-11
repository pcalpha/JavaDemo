package cn.com.pcalpha.dp.fac.abs;

/**
 * Created by caiyida on 2016/10/1.
 */
public class Factory1 implements Factory {
    @Override
    public Product produce() {
        return new Procuct1();
    }
}
