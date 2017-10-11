package cn.com.pcalpha.dd.disruptor;


import com.lmax.disruptor.EventHandler;

/**
 * Created by caiyida on 2016/10/7.
 */
public class LongEventHandler implements EventHandler<LongEvent> {

    public void onEvent(LongEvent longEvent, long l, boolean b) throws Exception {
        System.out.println("event:" + longEvent.getValue());
    }
}
