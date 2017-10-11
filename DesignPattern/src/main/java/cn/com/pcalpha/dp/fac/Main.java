package cn.com.pcalpha.dp.fac;

/**
 * Created by caiyida on 2016/10/1.
 */
public class Main {

    public static void main(String[] args) {
        Product p1 = Factory.produce("product1");
        Product p2 = Factory.produce("product2");

        p1.execute();
        p2.execute();
    }
}
