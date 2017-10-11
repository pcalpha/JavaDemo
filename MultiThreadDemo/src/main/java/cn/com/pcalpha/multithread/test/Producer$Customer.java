package cn.com.pcalpha.multithread.test;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by caiyida on 2016/10/6.
 */
public class Producer$Customer {


    public static void main(String[] args) {
        BlockingQueue<String> products = new ArrayBlockingQueue<String>(10);
        Producer p1 = new Producer(products);
        Producer p2 = new Producer(products);
        Producer p3 = new Producer(products);

        Customer c1 = new Customer(products);
        Customer c2 = new Customer(products);
        Customer c3 = new Customer(products);

        ExecutorService cachePool = Executors.newCachedThreadPool();
        cachePool.execute(p1);
        cachePool.execute(p2);
        cachePool.execute(p3);

        cachePool.execute(c1);
        cachePool.execute(c2);
        cachePool.execute(c3);
    }
}

class Producer implements Runnable {
    private BlockingQueue<String> procucts;
    private Random r = new Random();

    public Producer(BlockingQueue procucts) {
        this.procucts = procucts;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {

            Integer j = r.nextInt(10);
            procucts.offer(j.toString());
            System.out.println(Thread.currentThread().getName() + " PUT " + j);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}

class Customer implements Runnable {
    private BlockingQueue<String> procucts;

    public Customer(BlockingQueue<String> procucts) {
        this.procucts = procucts;
    }

    @Override
    public void run() {
        while (true) {
            try {
                String i = procucts.take();
                System.out.println(Thread.currentThread().getName() + " GET " + i);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
