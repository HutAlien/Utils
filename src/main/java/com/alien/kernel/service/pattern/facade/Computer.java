package com.alien.kernel.service.pattern.facade;

/**
 * @Auther: FYJ
 * @Date: 2019/10/30 11:17
 * @Description: 外观模式就是将他们的关系（cpu memory disk）放在一个Facade类中，降低了类类之间的耦合度,不会形成严重依赖
 */
public class Computer {

    private Cpu cpu;
    private Memory memory;
    private Disk disk;

    public Computer() {
        cpu=new Cpu();
        memory=new Memory();
        disk=new Disk();
    }

    public void startUp(){
        System.out.println("computer start");
        cpu.startUp();
        memory.startUp();
        disk.startUp();
        System.out.println("computer start finish");
    }

    public void shutdown(){
        System.out.println("computer close");
        cpu.shutdown();
        memory.shutdown();
        disk.shutdown();
        System.out.println("computer close finish");
    }

    public static void main(String[] args) {
        Computer computer=new Computer();
        computer.startUp();
        computer.shutdown();
    }
}
