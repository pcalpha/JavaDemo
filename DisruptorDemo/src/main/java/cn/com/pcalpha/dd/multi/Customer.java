package cn.com.pcalpha.dd.multi;

import com.lmax.disruptor.WorkHandler;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by caiyida on 2016/10/8.
 */
public class Customer implements WorkHandler<Order> {
    private String id;
    private static AtomicInteger count = new AtomicInteger(0);

    public Customer(String id) {
        this.id = id;
    }


    public void onEvent(Order order) throws Exception {
        System.out.println("当前消费者" + id + "消费" + order.getId());
        count.incrementAndGet();
    }

    public int getCount() {
        return count.get();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
