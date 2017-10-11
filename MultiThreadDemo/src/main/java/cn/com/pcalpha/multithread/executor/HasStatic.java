package cn.com.pcalpha.multithread.executor;

/**
 * Created by caiyida on 2016/10/5.
 */

public class  HasStatic{
    private static int x=100;
    public static void main(String  args[  ]){
        Double i = Double.NaN;
        Double j = Double.NaN;
        System.out.println(i);
        System.out.println(i > j || i <= j);
    }
}