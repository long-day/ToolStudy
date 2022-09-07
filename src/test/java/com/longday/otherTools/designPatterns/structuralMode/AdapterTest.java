package com.longday.otherTools.designPatterns.structuralMode;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;

/**
 * @author 君
 * @version 1.0
 * @date 2022/9/7
 */
public class AdapterTest {
    @Test
    public void ClassAdapter() {
        //读取SD卡中的数据
        Computer computer = new Computer();
        String s = computer.readSD(new SDCardImpl());

        System.out.println(s);
        System.out.println("================");
        //读取TF卡中的数据
        String s1 = computer.readSD(new SDAdapterTF());
        System.out.println(s1);
    }

    @Test
    public void ObjectAdapter() {
        //读取SD卡中的数据
        Computer computer = new Computer();
        String s = computer.readSD(new SDCardImpl());

        System.out.println(s);
        System.out.println("================");
        String s1 = computer.readSD(new SDAdapterTFPro(new TFCardImpl()));
        System.out.println(s1);
    }
}

/**
 * 适配者类
 */
interface TFCard {
    /**
     * 读取数据
     */
    String readTF();

    /**
     * 写数据
     */
    void writeTF(String msg);
}

class TFCardImpl implements TFCard {

    /**
     * 读取数据
     *
     * @return 写的数据
     */
    @Override
    public String readTF() {
        return "TFCardImpl读取数据并返回";
    }

    /**
     * 写数据
     *
     * @param msg 写的数据
     */
    @Override
    public void writeTF(String msg) {
        System.out.println("TFCardImpl 写数据: " + msg);
    }
}

/**
 * 目标接口
 */
interface SDCard {
    /**
     * 读取数据
     */
    String readSD();

    /**
     * 写数据
     */
    void writeSD(String msg);
}

class SDCardImpl implements SDCard {

    /** #
     * @return #
     */
    @Override
    public String readSD() {
        return "readSD 读取数据并返回";
    }

    /** #
     * @param msg 写数据的内容
     */
    @Override
    public void writeSD(String msg) {
        System.out.println("readSD 写数据: " + msg);
    }
}

class Computer {
    public String readSD(SDCard sdCard) {
        if (sdCard == null) {
            throw new RuntimeException("sdCard 不能为空");
        }
        return sdCard.readSD();
    }
}

class SDAdapterTF extends TFCardImpl implements SDCard{

    /** #
     * @return #
     */
    @Override
    public String readSD() {
        System.out.println("SD Adapter TF");
        return readTF();
    }

    /** #
     * @param msg #
     */
    @Override
    public void writeSD(String msg) {
        System.out.println("SD Adapter TF");
        writeTF(msg);
    }
}

@AllArgsConstructor
class SDAdapterTFPro implements SDCard{
    private TFCard tfCard;

    /** #
     * @return #
     */
    @Override
    public String readSD() {
        System.out.println("SDAdapterTFPro: 对象适配器模式");
        return tfCard.readTF();
    }

    /** #
     * @param msg  #
     */
    @Override
    public void writeSD(String msg) {
        System.out.println("SDAdapterTFPro: 对象适配器模式"+msg);
    }
}