package com.hspedu.extend_.exercise;

public class Computer {
    private String cpu;
    private int memory;
    private int disk;

    public Computer (String cpu, int memory, int disk) {
        this.setCpu(cpu);
        this.setMemory(memory);
        this.setDisk(disk);
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public int getMemory() {
        return memory;
    }

    public void setMemory(int memory) {
        this.memory = memory;
    }

    public int getDisk() {
        return disk;
    }

    public void setDisk(int disk) {
        this.disk = disk;
    }

    public String getDetails () {
        return "CPU：" + cpu + "，内存：" + memory + "，硬盘：" + disk;
    }

}
