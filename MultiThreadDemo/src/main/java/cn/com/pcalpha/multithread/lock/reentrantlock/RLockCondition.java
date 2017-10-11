package cn.com.pcalpha.multithread.lock.reentrantlock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class RLockCondition implements Runnable{
    public static ReentrantLock lock = new ReentrantLock();
    public static Condition condition = lock.newCondition();
    @Override
    public void run() {

        try{
            lock.lock();
            condition.await();
            System.out.println("continue do");

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public static void main(String[] args) throws InterruptedException {
        RLockCondition i1 = new RLockCondition();
        Thread t1 = new Thread(i1);

        t1.start();

        Thread.sleep(2000);
        lock.lock();
        condition.signal();
        lock.unlock();

    }
}
