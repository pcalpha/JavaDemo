package cn.com.pcalpha.dd.disruptor3;

import com.lmax.disruptor.EventHandler;

import java.util.UUID;

/**
 * Created by caiyida on 2016/10/7.
 */
public class Handler2 implements EventHandler<Trade> {

    public void onEvent(Trade trade, long l, boolean b) throws Exception {
        trade.setId(UUID.randomUUID().toString());
        trade.setName(trade.getName()+"trade2;");
        System.out.println("handler2=" + trade.getId()+";name="+trade.getName());
    }
}
