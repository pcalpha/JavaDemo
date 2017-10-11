package cn.com.pcalpha.multithread.executor;

/**
 * Created by caiyida on 2016/10/5.
 */
public class A {
    public static void main(String args[]) {
        String str1 = "hello";
        String str2 = str1;
        System.err.println(str1 == str2);
    }
}
