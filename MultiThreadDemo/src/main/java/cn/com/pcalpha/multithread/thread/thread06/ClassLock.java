package cn.com.pcalpha.multithread.thread.thread06;

/**
 * Created by caiyida on 2016/10/5.
 */
public class ClassLock {

    public static void method1(){
        //***����
        synchronized (ClassLock.class){
            System.out.println("method1 execing");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static synchronized void method2(){
        System.out.println("method2 execing");
    }

    public static void main(String[] args) {
        final ClassLock cl = new ClassLock();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                cl.method1();
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                cl.method2();
            }
        });

        t1.start();
        t2.start();

    }
}
