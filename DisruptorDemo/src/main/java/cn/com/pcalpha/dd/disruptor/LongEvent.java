package cn.com.pcalpha.dd.disruptor;

/**
 * Created by caiyida on 2016/10/7.
 */
public class LongEvent {
    private long value;

    public void setValue(long value) {
        this.value = value;
    }

    public long getValue() {
        return value;
    }
}
