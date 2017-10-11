package cn.com.pcalpha.dd.disruptor3;

import com.lmax.disruptor.EventTranslator;
import com.lmax.disruptor.dsl.Disruptor;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * Created by caiyida on 2016/10/7.
 */
public class TradePublisher implements Runnable {

    private Disruptor<Trade> disruptor;
    private CountDownLatch latch;
    private TradeEventTranslator translator;

    private static int LOOP = 10;

    public TradePublisher(CountDownLatch latch, Disruptor<Trade> disruptor) {
        this.disruptor = disruptor;
        this.latch = latch;
    }

    public void run() {
        translator = new TradeEventTranslator();
        for (int i = 0; i < LOOP; i++) {

            disruptor.publishEvent(translator);
        }
        latch.countDown();
    }
}

class TradeEventTranslator implements EventTranslator<Trade> {
    private Random r = new Random();

    public void translateTo(Trade trade, long l) {
        this.generateTrade(trade);
    }

    public Trade generateTrade(Trade trade) {
        trade.setPrice(r.nextDouble() * 9999);
        return trade;
    }
}
