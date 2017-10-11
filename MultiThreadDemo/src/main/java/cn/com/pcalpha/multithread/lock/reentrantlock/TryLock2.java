package cn.com.pcalpha.multithread.lock.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

public class TryLock2 implements Runnable {
    private static ReentrantLock lock1 = new ReentrantLock();
    private static ReentrantLock lock2 = new ReentrantLock();
    int lock;

    public TryLock2(int i) {
        this.lock = i;
    }

    @Override
    public void run() {


        try {
            if (lock == 1) {
                lock1.tryLock();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock2.tryLock();
            }else{
                lock2.tryLock();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock1.tryLock();
            }
        }finally {
            if(lock1.isHeldByCurrentThread()){
                lock1.unlock();
            }
            if(lock2.isHeldByCurrentThread()){
                lock2.unlock();
            }
            System.out.println(Thread.currentThread().getId()+"退出");

        }
    }

    public static void main(String[] args) {
        TryLock2 i1 = new TryLock2(1);
        TryLock2 i2 = new TryLock2(2);

        Thread t1 = new Thread(i1);
        Thread t2 = new Thread(i2);

        t1.start();
        t2.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
