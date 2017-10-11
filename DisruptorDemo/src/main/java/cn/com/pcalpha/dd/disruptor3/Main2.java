package cn.com.pcalpha.dd.disruptor3;

import com.lmax.disruptor.BusySpinWaitStrategy;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by caiyida on 2016/10/8.
 */
public class Main2 {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executors = Executors.newFixedThreadPool(8);
        int BUFFER_SIZE = 1024;

        Disruptor<Trade> disruptor = new Disruptor<Trade>(new EventFactory<Trade>() {
            public Trade newInstance() {
                return new Trade();
            }
        }, BUFFER_SIZE, executors, ProducerType.SINGLE, new BusySpinWaitStrategy());


        Handler1 h1 = new Handler1();
        Handler2 h2 = new Handler2();
        Handler3 h3 = new Handler3();
        Handler4 h4 = new Handler4();
        Handler5 h5 = new Handler5();

        disruptor.handleEventsWith(h1, h2);
        disruptor.after(h1).handleEventsWith(h4);
        disruptor.after(h2).handleEventsWith(h5);
        disruptor.after(h4, h5).handleEventsWith(h3);

        disruptor.start();

        CountDownLatch latch = new CountDownLatch(1);
        executors.submit(new TradePublisher(latch, disruptor));

        latch.await();

        disruptor.shutdown();
        executors.shutdown();


    }
}
