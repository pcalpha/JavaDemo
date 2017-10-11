package cn.com.pcalpha.dd.disruptor2;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;

/**
 * Created by caiyida on 2016/10/7.
 */
public class TradeHandler implements EventHandler<Trade>, WorkHandler<Trade> {
    public void onEvent(Trade trade, long l, boolean b) throws Exception {
        this.onEvent(trade);
    }

    public void onEvent(Trade trade) throws Exception {
        System.out.println("cc:id="+trade.getId()+",name="+trade.getName()+",price:"+trade.getPrice()+",t"+trade.getProducer());
    }
}
