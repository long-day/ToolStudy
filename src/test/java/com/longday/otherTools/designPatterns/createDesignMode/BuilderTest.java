package com.longday.otherTools.designPatterns.createDesignMode;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;

/**
 * @author 君
 * @version 1.0
 * @date 2022/9/7
 */
public class BuilderTest {
    /**
     *  当一个类的构造函数参数个数超过4个，而且这些参数有些是可选的参数，考虑使用构造者模式。
     *  下面这个是在Java中简化的建造者模式，特点就是简单。
     */
    @Test
    public void builderJavaVersion(){
        Computer computer=new Computer.Builder("因特尔","三星")
                .setDisplay("三星24寸")
                .setKeyboard("罗技")
                .setUsbCount(2)
                .build();
        System.out.println(computer);
    }

    /**
     *  这个是正常的标准建造者模式。
     */
    @Test
    public void builderCommon(){
        ComputerComDirector computerComDirector = new ComputerComDirector();
        ComputerCom macCom = computerComDirector.makeComputerCom(new MacComputerBuilder("I9处理器", "Samsung 125"));
        System.out.println(macCom);
        ComputerCom lenovoCom = computerComDirector.makeComputerCom(new LenovoComputerBuilder("锐龙9 5950X", "KVR48S40BD8K2-64"));
        System.out.println(lenovoCom);

    }
}

@Data
class Computer {
    private String cpu;//必须
    private String ram;//必须
    private int usbCount;//可选
    private String keyboard;//可选
    private String display;//可选

    private Computer(Builder builder){
        this.cpu=builder.cpu;
        this.ram=builder.ram;
        this.usbCount=builder.usbCount;
        this.keyboard=builder.keyboard;
        this.display=builder.display;
    }
    public static class Builder{
        private String cpu;//必须
        private String ram;//必须
        private int usbCount;//可选
        private String keyboard;//可选
        private String display;//可选

        public Builder(String cup,String ram){
            this.cpu=cup;
            this.ram=ram;
        }

        public Builder setUsbCount(int usbCount) {
            this.usbCount = usbCount;
            return this;
        }
        public Builder setKeyboard(String keyboard) {
            this.keyboard = keyboard;
            return this;
        }
        public Builder setDisplay(String display) {
            this.display = display;
            return this;
        }
        public Computer build(){
            return new Computer(this);
        }
    }
}

/**
 * 这个对应建造者中的产品
 */
@Data
@NoArgsConstructor
class ComputerCom {
    private String cpu;//必须
    private String ram;//必须
    private int usbCount;//可选
    private String keyboard;//可选
    private String display;//可选
    public ComputerCom(String cpu, String ram) {
        this.cpu = cpu;
        this.ram = ram;
    }
}

/**
 * 这个是抽象建造者 建造者，通知组件的实现
 */
abstract class ComputerComBuilder {
    public abstract void setUsbCount();
    public abstract void setKeyboard();
    public abstract void setDisplay();

    public abstract ComputerCom getComputerCom();
}

/**
 * 这个是具体建造者 Mac
 */
class MacComputerBuilder extends ComputerComBuilder {
    private ComputerCom computerCom;
    public MacComputerBuilder(String cpu, String ram) {
        computerCom = new ComputerCom(cpu, ram);
    }
    @Override
    public void setUsbCount() {
        computerCom.setUsbCount(2);
    }
    @Override
    public void setKeyboard() {
        computerCom.setKeyboard("苹果键盘");
    }
    @Override
    public void setDisplay() {
        computerCom.setDisplay("苹果显示器");
    }
    @Override
    public ComputerCom getComputerCom() {
        return computerCom;
    }
}

/**
 * 这个是具体建造者 Lenovo
 */
class LenovoComputerBuilder extends ComputerComBuilder {
    private ComputerCom computerCom;
    public LenovoComputerBuilder(String cpu, String ram) {
        computerCom=new ComputerCom(cpu,ram);
    }
    @Override
    public void setUsbCount() {
        computerCom.setUsbCount(4);
    }
    @Override
    public void setKeyboard() {
        computerCom.setKeyboard("联想键盘");
    }
    @Override
    public void setDisplay() {
        computerCom.setDisplay("联想显示器");
    }
    @Override
    public ComputerCom getComputerCom() {
        return computerCom;
    }
}

/**
 * 指挥者 控制构建流程
 */
class ComputerComDirector {
    public ComputerCom makeComputerCom(ComputerComBuilder builder){
        builder.setUsbCount();
        builder.setDisplay();
        builder.setKeyboard();
        return builder.getComputerCom();
    }
}