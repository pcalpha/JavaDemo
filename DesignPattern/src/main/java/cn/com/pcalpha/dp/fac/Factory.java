package cn.com.pcalpha.dp.fac;

/**
 * Created by caiyida on 2016/10/1.
 */
public class Factory {

    public static Product produce(String str) {
        Product product = null;
        switch (str) {
            case "product1":
                product = new Product1();
                break;
            case "product2":
                product = new Product2();
                break;
        }
        return product;
    }
}
