package cn.com.pcalpha.multithread.lock.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

public class FairLock implements Runnable {
    private static ReentrantLock fairLock = new ReentrantLock(true);

    @Override
    public void run() {
        while(true){
            try {
                fairLock.lock();
                System.out.println(Thread.currentThread().getName() + "获得锁");
            }finally {
                fairLock.unlock();
            }

        }
    }

    public static void main(String[] args) {
        FairLock i1 = new FairLock();
        Thread t1 = new Thread(i1);
        Thread t2 = new Thread(i1);

        t1.start();
        t2.start();

    }
}
