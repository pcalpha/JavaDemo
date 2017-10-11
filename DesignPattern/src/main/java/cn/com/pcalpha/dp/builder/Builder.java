package cn.com.pcalpha.dp.builder;

import java.util.List;

/**
 * Created by caiyida on 2016/10/1.
 */
public class Builder {

    private List<Product> list;

    public List<Product> buildProduct1(int count){
        for(int i=0;i<count;i++){
            list.add(new Product1());
        }
        return list;
    }

    public List<Product> buildProduct2(int count){
        for(int i=0;i<count;i++){
            list.add(new Product2());
        }
        return list;

    }

}
