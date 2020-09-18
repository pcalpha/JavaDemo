package cn.com.pcalpha.nd.netty.heart;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by caiyida on 2016/12/1.
 */
public class RequestInfo {
    private String IP;
    private Map<String,String> cpuinfo = new HashMap<String, String>();

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public Map<String, String> getCpuinfo() {
        return cpuinfo;
    }

    public void setCpuinfo(Map<String, String> cpuinfo) {
        this.cpuinfo = cpuinfo;
    }
}
