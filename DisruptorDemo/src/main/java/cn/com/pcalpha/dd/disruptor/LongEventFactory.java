package cn.com.pcalpha.dd.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * Created by caiyida on 2016/10/7.
 */
public class LongEventFactory implements EventFactory<LongEvent> {
    public LongEvent newInstance() {
        return new LongEvent();
    }
}
