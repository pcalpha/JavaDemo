package cn.com.pcalpha.dp.strategy;

/**
 * Created by caiyida on 2016/10/1.
 */
public abstract class AbstractCalculate {
    public int[] split(String exp,String opt){
        String arr[] = exp.split(opt);
        int ret[] = new int[2];
        ret[0] = Integer.parseInt(arr[0]);
        ret[1] = Integer.parseInt(arr[1]);
        return ret;
    }

}
