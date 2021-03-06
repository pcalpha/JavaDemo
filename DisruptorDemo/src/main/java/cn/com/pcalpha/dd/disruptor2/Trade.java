package cn.com.pcalpha.dd.disruptor2;

import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by caiyida on 2016/10/7.
 */
public class Trade {
    private String id;
    private String name;
    private double price;
    private String producer;
    private AtomicInteger count = new AtomicInteger(0);

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public AtomicInteger getCount() {
        return count;
    }

    public void setCount(AtomicInteger count) {
        this.count = count;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }
}
