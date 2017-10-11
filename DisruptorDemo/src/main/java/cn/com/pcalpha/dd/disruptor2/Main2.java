package cn.com.pcalpha.dd.disruptor2;

import com.lmax.disruptor.*;

import java.util.concurrent.*;

/**
 * Created by caiyida on 2016/10/7.
 */
public class Main2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int BUFFER_SIZE = 1024;
        int TRADE_NUMBERS = 4;

        final RingBuffer<Trade> ringBuffer = RingBuffer.createSingleProducer(new EventFactory<Trade>() {
            public Trade newInstance() {
                return new Trade();
            }
        }, BUFFER_SIZE, new YieldingWaitStrategy());

        //生产者
        //创建线程池
        ExecutorService executors = Executors.newFixedThreadPool(TRADE_NUMBERS);

        //消费者
        WorkHandler<Trade> workHandler = new TradeHandler();
        //创建Barrier
        SequenceBarrier barrier = ringBuffer.newBarrier();
        WorkerPool<Trade> workerPool = new WorkerPool<Trade>(ringBuffer, barrier, new IgnoreExceptionHandler(), workHandler);
        workerPool.start(executors);


        for (int i = 0; i < 10; i++) {
            long seq = ringBuffer.next();
            Trade t = ringBuffer.get(seq);
            t.setId(i+"");
            t.setName("name"+i);
            t.setPrice(Math.random() * 9999);

            ringBuffer.publish(seq);
        }

        Thread.sleep(1000);

        workerPool.halt();
        executors.shutdown();

    }
}
