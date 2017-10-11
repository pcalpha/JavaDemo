package cn.com.pcalpha.dp.facade;

/**
 * Created by caiyida on 2016/10/1.
 */
public class Computer {

    private CPU cpu = new CPU();
    private Memory memory = new Memory();
    private Disk disk = new Disk();

    public void startup(){
        cpu.startup();
        memory.startup();
        disk.startup();
    }

    public void shutdown(){
        cpu.shutdown();
        memory.shutdown();
        disk.shutdown();
    }
}
