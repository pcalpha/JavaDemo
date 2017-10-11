package cn.com.pcalpha.multithread.executor;

/**
 * Created by caiyida on 2016/10/5.
 */
public class It2 {
    private static final String d = "hello";

    public static void main(String[] args) {
        String a = "hello";
        String b = new String("hello");
        String c = "he";
        String d = c+"llo";
        System.out.println(a==b);
        System.out.println(a==d);
        System.out.println(b==d);
        System.out.println(a==d.intern());
    }
}

