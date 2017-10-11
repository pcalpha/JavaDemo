package cn.com.pcalpha.dd.disruptor3;

import com.lmax.disruptor.BusySpinWaitStrategy;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.EventHandlerGroup;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by caiyida on 2016/10/7.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        int BUFFER_SIZE = 1024;
        ExecutorService executor = Executors.newFixedThreadPool(8);

        Disruptor<Trade> disruptor = new Disruptor<Trade>(new EventFactory<Trade>() {
            public Trade newInstance() {
                return new Trade();
            }
        }, BUFFER_SIZE, executor, ProducerType.SINGLE, new BusySpinWaitStrategy());

        EventHandlerGroup<Trade> handlerGroup = disruptor.handleEventsWith(new Handler1(), new Handler2());
        handlerGroup.then(new Handler3());

        disruptor.start();

        CountDownLatch latch = new CountDownLatch(1);
        executor.submit(new TradePublisher(latch, disruptor));

        latch.await();
        disruptor.shutdown();
        executor.shutdown();
    }
}
