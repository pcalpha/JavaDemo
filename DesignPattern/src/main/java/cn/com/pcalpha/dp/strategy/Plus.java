package cn.com.pcalpha.dp.strategy;

/**
 * Created by caiyida on 2016/10/1.
 */
public class Plus extends AbstractCalculate implements ICalculate {

    @Override
    public int calculate(String exp) {
        int arr[] = split(exp,"\\+");
        return arr[0]+arr[1];
    }
}
