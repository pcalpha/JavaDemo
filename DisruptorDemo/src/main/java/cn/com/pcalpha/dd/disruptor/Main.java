package cn.com.pcalpha.dd.disruptor;

import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by caiyida on 2016/10/7.
 */
public class Main {
    private static final EventTranslatorOneArg<LongEvent, ByteBuffer> TRANSLATOR =
            new EventTranslatorOneArg<LongEvent, ByteBuffer>() {
                public void translateTo(LongEvent event, long l, ByteBuffer byteBuffer) {
                    event.setValue(byteBuffer.getLong(0));
                }
            };

    public static void main(String[] args) {
        ExecutorService executors = Executors.newCachedThreadPool();
        WaitStrategy YIELDING_WAIT = new YieldingWaitStrategy();
        EventFactory<LongEvent> eventFactory = new LongEventFactory();
        //ExecutorService executor = Executors.newSingleThreadExecutor();
        int ringBufferSize = 1024 * 1024; // RingBuffer 大小，必须是 2 的 N 次方；


        //第一个参数为工厂对象
        //第二个参数为缓冲区大小
        //第三个参数为线程池Disruptor内部的数据接受处理调度
        //第四个参数Producer的类型
        //第五个参数为策略
        Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(eventFactory,
                ringBufferSize, executors, ProducerType.SINGLE,
                new YieldingWaitStrategy());

        EventHandler<LongEvent> eventHandler = new LongEventHandler();
        disruptor.handleEventsWith(eventHandler);

        //启动
        disruptor.start();

        //生产者获取ringBuffer
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
        //LongEventProducer producer = new LongEventProducer(ringBuffer);
        LongEventProducerWithTranslator producer = new LongEventProducerWithTranslator(ringBuffer);
        ByteBuffer byteBuffer = ByteBuffer.allocate(8);
        for (long a = 0; a < 100; a++) {
            byteBuffer.putLong(0, a);
            ringBuffer.publishEvent(TRANSLATOR,byteBuffer);
            //producer.onData(byteBuffer);
        }


        disruptor.shutdown();
        executors.shutdown();
    }
}
