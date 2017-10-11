package cn.com.pcalpha.multithread.executor;

/**
 * Created by caiyida on 2016/10/5.
 */
public interface Er {

        double PI=3.14;
        void tongdian();
        void duandian();
}
class Dfqc implements Er
{
    public void tongdian()
    {
        System.out.println("通电函数"+PI);
    }
    public void duandian()
    {
    }
    public void duan()
    {
    }
}
class Test
{
    public static void main(String[] args)
    {
        Dfqc t=new Dfqc();
        t.tongdian();
    }
}

