package cn.com.pcalpha.multithread.lock.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

public class RLock implements Runnable {

    public static ReentrantLock lock = new ReentrantLock();
    public static int i=0;
    @Override
    public void run() {
        for(int j=0;j<10000000;j++){
            lock.lock();
            try {
                i++;
            }finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        RLock r = new RLock();

        Thread t = new Thread(r);
        Thread t2 = new Thread(r);
        t.start();
        t2.start();
        t.join();
        t2.join();
        System.out.println(i);
    }
}
