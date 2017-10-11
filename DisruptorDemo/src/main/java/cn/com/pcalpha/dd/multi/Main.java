package cn.com.pcalpha.dd.multi;


import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;

/**
 * Created by caiyida on 2016/10/8.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        RingBuffer<Order> ringBuffer = RingBuffer.create(ProducerType.MULTI, new EventFactory<Order>() {
            public Order newInstance() {
                return new Order();
            }
        }, 1024 * 1024, new YieldingWaitStrategy());

        SequenceBarrier barrier = ringBuffer.newBarrier();

        Customer[] customers = new Customer[3];
        for (int i = 0; i < customers.length; i++) {
            customers[i] = new Customer("c" + i);
        }

        WorkerPool<Order> workerPool = new WorkerPool<Order>(ringBuffer, barrier, new IgnoreExceptionHandler(), customers);
        ringBuffer.addGatingSequences(workerPool.getWorkerSequences());
        workerPool.start(Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()));
        final CountDownLatch latch = new CountDownLatch(1);

        for (int i = 0; i < 10; i++) {
            final Producer producer = new Producer(ringBuffer);
            new Thread(new Runnable() {
                public void run() {
                    try {
                        latch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    for (int x = 0; x < 10; x++) {
                        producer.onData(UUID.randomUUID().toString());
                    }
                }

            }).start();
        }

        Thread.sleep(2000);
        System.out.println("---------------");
        latch.countDown();
        Thread.sleep(5000);
        System.out.println("总数:" + customers[0].getCount());
        workerPool.halt();
    }
}
