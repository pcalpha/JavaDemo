package cn.com.pcalpha.dp.flyweight;

import java.util.List;

/**
 * Created by caiyida on 2016/10/1.
 */
public class FlyWeightFactory {
    private List<FlyWeight> list;
    private int poolSize;

    public FlyWeightFactory(int poolSize) {
        for(int i=0;i<poolSize;i++){
            list.add(new FlyWeight1());
        }
    }

    public FlyWeight get(){
        if(list.size()>0){
            return list.remove(0);
        }
        return null;
    }

    public void release(FlyWeight flyWeight){
        list.add(flyWeight);
    }


}
