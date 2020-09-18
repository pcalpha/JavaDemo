package cn.com.pcalpha.nd.netty.heart;

import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by caiyida on 2016/12/1.
 */
public class ServerHeartBeatHandler extends ChannelInboundHandlerAdapter {
    private static HashMap<String, String> AUTH_IP_MAP = new HashMap<String, String>();
    private static final String SUCCESS_KEY = "auth_success_key";

    static{
        AUTH_IP_MAP.put("192.168.1.102","12345");
    }

    private boolean auth(ChannelHandlerContext ctx,Object msg){
        String[] ret = ((String)msg).split(",");
        String auth = AUTH_IP_MAP.get(ret[0]);
        if(auth!=null&&auth.equals(ret[1])){
            ctx.writeAndFlush(SUCCESS_KEY);
            return true;
        }else{
            ctx.writeAndFlush("auth failed").addListener(ChannelFutureListener.CLOSE);
            return false;
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);
        if(msg instanceof String){
            auth(ctx,msg);
        }else if(msg instanceof RequestInfo){
            RequestInfo req = (RequestInfo) msg;
            System.out.println("---------------------");
            System.out.println("当前主机IP地址为:"+ req.getIP());
            Map<String,String> cpuinfo = req.getCpuinfo();
            System.out.println("cpu使用率为:"+cpuinfo.get("combined"));
            System.out.println("用户使用率:"+cpuinfo.get("user"));
            System.out.println("系统使用率:"+cpuinfo.get("sys"));
            System.out.println("等待率:"+cpuinfo.get("wait"));
            System.out.println("空闲率"+cpuinfo.get("idle"));


            ctx.writeAndFlush("info received");
        }else{
            ctx.writeAndFlush("connect failed").addListener(ChannelFutureListener.CLOSE);
        }
    }
}
