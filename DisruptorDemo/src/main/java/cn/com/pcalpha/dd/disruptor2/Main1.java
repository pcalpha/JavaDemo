package cn.com.pcalpha.dd.disruptor2;

import com.lmax.disruptor.*;
import org.junit.Test;

import java.util.concurrent.*;

/**
 * Created by caiyida on 2016/10/7.
 */
public class Main1 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int BUFFER_SIZE = 1024;
        int TRADE_NUMBERS = 4;

        final RingBuffer<Trade> ringBuffer = RingBuffer.createSingleProducer(new EventFactory<Trade>() {
            public Trade newInstance() {
                return new Trade();
            }
        }, BUFFER_SIZE, new YieldingWaitStrategy());



        //创建Barrier
        SequenceBarrier barrier = ringBuffer.newBarrier();

        //消费者
        //创建消费者消息处理器
        BatchEventProcessor<Trade> processor = new BatchEventProcessor<Trade>(ringBuffer, barrier, new TradeHandler());
        //把消费者的位置信息引入给生产者
        ringBuffer.addGatingSequences(processor.getSequence());

        //生产者
        //创建线程池
        ExecutorService executors = Executors.newFixedThreadPool(TRADE_NUMBERS);
        //消息处理器交给线程池
        executors.submit(processor);
        Future<?> future = executors.submit(new Callable<Void>() {

            public Void call() throws Exception {
                long seq = 0;
                for (int i = 0; i < 1000; i++) {
                    seq = ringBuffer.next();
                    ringBuffer.get(seq).setId(i+"");
                    ringBuffer.get(seq).setProducer(Thread.currentThread().getName());
                    ringBuffer.get(seq).setName("cyd"+i);
                    ringBuffer.get(seq).setPrice(Math.random() * 9999);
                    ringBuffer.publish(seq);
                }
                return null;
            }
        });

        future.get();
        Thread.sleep(1000);

        processor.halt();
        executors.shutdown();



    }
}
