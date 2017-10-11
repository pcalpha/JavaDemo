package cn.com.pcalpha.nd.netty.heart;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by caiyida on 2016/12/1.
 */
public class ClientHeartBeatHandler extends ChannelHandlerAdapter {

    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private java.util.concurrent.ScheduledFuture<?> heartBeat;
    private InetAddress address;
    private static final String SUCCESS_KEY = "auth_success_key";

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        address = InetAddress.getLocalHost();
        String ip = address.getHostAddress();
        String key = "12345";
        String auth = ip+","+key;
        ctx.writeAndFlush(auth);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        if(msg instanceof String){
            String ret = (String) msg;
            if(SUCCESS_KEY.equals(ret)){
                this.heartBeat = this.scheduler.scheduleWithFixedDelay(new HeartBeatTask(ctx), 0, 2, TimeUnit.SECONDS);
                System.out.println(msg);
            }else{
                System.out.println(msg);
            }
        }
    }

    private class HeartBeatTask implements Runnable {
        private final ChannelHandlerContext ctx;

        public HeartBeatTask(ChannelHandlerContext ctx) {
            this.ctx = ctx;
        }

        public void run() {
            RequestInfo requestInfo = new RequestInfo();

            requestInfo.setIP(address.getHostAddress());


            Map<String,String> cpuInfo = new HashMap<String, String>();
            cpuInfo.put("combined","1");
            cpuInfo.put("user","2");
            cpuInfo.put("sys","3");
            cpuInfo.put("wait","4");
            cpuInfo.put("idle","5");
        }
    }


}
