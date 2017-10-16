package cn.com.pcalpha.nd.test;

/**
 * Created by caiyida on 2017/2/25.
 */

/**
 *总结
 * 全局  静态语句块>静态属性>构造方法
 * 局部  构造语句块>构造方法
 *
 * 静态语句块跟静态属性方面  子类实例化优先初始化父类的静态语句块，静态属性，子类的静态语句块，静态属性，然后才是构造方法
 *
 */

public class Father {
    private static String str = getString();

    static {
        System.out.println("父类静态语句块初始化");
    }

    public Father(){
        System.out.println("父类无参构造方法初始化");
        show();

    }

    {
        System.out.println("父类构造语句块初始化");
    }



    public static String getString(){
        System.out.println("父类静态成员初始化");
        return "父类静态成员属性";
    }



    public void show(){
        System.out.println("父类show()方法");
    }
}
