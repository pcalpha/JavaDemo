package cn.com.pcalpha.dd.disruptor3;

import com.lmax.disruptor.EventHandler;

import java.util.UUID;

/**
 * Created by caiyida on 2016/10/7.
 */
public class Handler1 implements EventHandler<Trade> {

    public void onEvent(Trade trade, long l, boolean b) throws Exception {
        trade.setId(UUID.randomUUID().toString());
        trade.setName(trade.getName()+"trade1;");
        System.out.println("handler1=" + trade.getId()+";name="+trade.getName());
    }
}
