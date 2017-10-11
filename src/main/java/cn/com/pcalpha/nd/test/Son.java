package cn.com.pcalpha.nd.test;

/**
 * Created by caiyida on 2017/2/25.
 */
public class Son extends Father {

    static{
        System.out.println("子类静态语句块初始化");
    }

    {
        System.out.println("子类构造语句块初始化");
    }

    private static String str = getString2();

    private static String getString2(){
        System.out.println("子类静态成员初始化");
        return "子类静态成员";
    }

    public void show(){
        System.out.println("子类show()方法"+str);
    }

    public Son(){
        System.out.println("子类构造方法初始化");
        show();
    }

    public static void main(String[] args) {
        new Son();
    }


}
