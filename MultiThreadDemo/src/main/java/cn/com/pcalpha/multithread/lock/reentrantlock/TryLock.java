package cn.com.pcalpha.multithread.lock.reentrantlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class TryLock implements Runnable {
    private static ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        try {
            if (lock.tryLock(5, TimeUnit.SECONDS)) {
                Thread.sleep(6000);
            } else {
                System.out.println("lock failed");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
            System.out.println(Thread.currentThread().getId() + "退出");

        }
    }

    public static void main(String[] args) {
        TryLock i1 = new TryLock();
        Thread t1 = new Thread(i1);
        Thread t2 = new Thread(i1);

        t1.start();
        t2.start();

    }
}
