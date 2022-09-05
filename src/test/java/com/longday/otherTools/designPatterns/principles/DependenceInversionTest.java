package com.longday.otherTools.designPatterns.principles;

import org.junit.jupiter.api.Test;

/**
 * @author 君
 * @version 1.0
 * @date 2022/9/4
 */
public class DependenceInversionTest {
    //依赖倒置原则(Dependence Inversion Principle)
/*
    1、高层模块不应该依赖底层模块,二者都应该依赖抽象.
　　2、抽象不应该依赖细节,细节应该依赖抽象.
　　3、依赖倒置的中心思想是面向接口编程.
　　4、依赖倒置原则是基于这样的设计理念：相对于细节的多变性,抽象的东西要稳定的多.以抽象为基础搭建的架构比以细节为基础
      搭建的架构要稳定的多.
　　5、使用接口或抽象类的目的是指定好规范,而不涉及任何具体的操作,把展现细节的任务交给他们的实现类来完成.
*/

// 示例
    @Test
    public void assemblingComputer(){
        /*Computer类在设计的时候假设，直接将CPU,HardDisk,Memory这些依赖设置为实例类如：IntelCPU，
          XiJieHardDisk,KingstonMemory等具体的实体类，那么Computer实例对象在创建时就不可以再更改使用
          其他的组成如：AmdCPU，WesternDataDisk,GSkillMemory等,具有极大的耦合性，但是如果我们将这些
          组成替换为一个抽象的接口:CPU，HardDisk，Memory,后，这些具体的类只需实现相关接口即可达到同样的
          效果，使代码复用性提升，类与类之间的耦合降低。
        */
        IntelCPU intelCPU = new IntelCPU("酷睿i9-12900KS");
        KingstonMemory kingstonMemory = new KingstonMemory("金士顿（Kingston）DDR5");
        XiJieHardDisk xiJieHardDisk = new XiJieHardDisk("希捷(SEAGATE)Seagate希捷酷玩530");
        Computer computer = new Computer(intelCPU,xiJieHardDisk,kingstonMemory);
        computer.run();

    }
    
// 总结一下
/*
    1、每个类尽量都有接口或者抽象类,或者抽象类和接口两都具备.
　　2、变量的表面类型尽量是接口或者抽象类.
　　3、任何类都不应该从具体类派生.
　　4、尽量不要覆写基类的方法.
　　5、如果基类是一个抽象类,而这个方法已经实现了,子类尽量不要覆写.类间依赖的是抽象,覆写了抽象方法,
      对依赖的稳定性会有一定的影响.
　　6、结合里氏替换原则使用.
*/    
}

//示例类
class Computer{
    private CPU cpu;
    private  HardDisk hardDisk;
    private  Memory memory;

    public Computer(){

    }
    public Computer(CPU cpu, HardDisk hardDisk, Memory memory) {
        this.cpu = cpu;
        this.hardDisk = hardDisk;
        this.memory = memory;
    }

    public void run(){
        System.out.println("带有"+cpu+"CPU和"+hardDisk+"硬盘和"+memory+"内存的电脑正在运行！");
    }
}
interface CPU{

}

interface HardDisk{

}

interface Memory{

}


class IntelCPU implements CPU{
    private String name;

    public IntelCPU(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CPU{" +
                "name='" + name + '\'' +
                '}';
    }
}

class XiJieHardDisk implements HardDisk{
    private String name;

    public XiJieHardDisk(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "HardDisk{" +
                "name='" + name + '\'' +
                '}';
    }
}

class KingstonMemory implements Memory{
    private String name;

    public KingstonMemory(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Memory{" +
                "name='" + name + '\'' +
                '}';
    }
}