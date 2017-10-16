package cn.com.pcalpha;

public class GcTest {

    /**
     *
     * -Xms5m
     * -Xmx20m
     * -XX:+PrintGC
     * -XX:+PrintGCDetails
     * -XX:SurvivorRatio  设置eden跟from/to的比例   -XX:survivorRatio =eden/from=eden/to
     * -XX:NewRatio 新生代老年代的比率 -XX:newRatio = 老年代/新生代
     * -XX:UseSerialGC
     * @param args
     */
    public static void main(String[] args) {

        System.out.println("max memery :"+Runtime.getRuntime().maxMemory());
        System.out.println("free memery :"+Runtime.getRuntime().freeMemory());
        System.out.println("total memery :"+Runtime.getRuntime().totalMemory());

        byte[] i = new byte[1*1024*1024];
        System.out.println("分配了1M");
        System.out.println("max memery :"+Runtime.getRuntime().maxMemory());
        System.out.println("free memery :"+Runtime.getRuntime().freeMemory());
        System.out.println("total memery :"+Runtime.getRuntime().totalMemory());


        byte[] j = new byte[4*1024*1024];
        System.out.println("分配了4M");
        System.out.println("max memery :"+Runtime.getRuntime().maxMemory());
        System.out.println("free memery :"+Runtime.getRuntime().freeMemory());
        System.out.println("total memery :"+Runtime.getRuntime().totalMemory());


    }
}
