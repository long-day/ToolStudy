package com.longday.otherTools.designPatterns.structuralMode;

import org.junit.jupiter.api.Test;

/**
 * @author 君
 * @version 1.0
 * @date 2022/9/8
 */
public class FacadeTest {
    @Test
    public void facade() {
        SmartHomeApp smartHomeApp = new SmartHomeApp();
        smartHomeApp.voiceControl("打开");
        System.out.println("========");
        smartHomeApp.voiceControl("关闭");

    }
}

/**
 * 灯
 */
class Light {

    /**
     * 开灯
     */
    public void on() {
        System.out.println("打开灯");
    }

    /**
     * 关灯
     */
    public void off() {
        System.out.println("关闭灯");
    }
}

/**
 * 电视
 */
class Tv {

    /**
     * 开电视
     */
    public void on() {
        System.out.println("打开电视");
    }

    /**
     * 关电视
     */
    public void off() {
        System.out.println("关闭电视");
    }
}

/**
 * 空调
 */
class AirConditioning {

    /**
     * 开空调
     */
    public void on() {
        System.out.println("打开空调");
    }

    /**
     * 关空调
     */
    public void off() {
        System.out.println("关闭空调");
    }
}

class SmartHomeApp {
    private final Light light;
    private final Tv tv;
    private final AirConditioning airConditioning;

    SmartHomeApp() {
        this.light = new Light();
        this.tv = new Tv();
        this.airConditioning = new AirConditioning();
    }

    public void voiceControl(String speakSentence) {
        if (speakSentence.contains("打开")) {
            on();
        } else if (speakSentence.contains("关闭")) {
            off();
        }
    }

    private void on() {
        this.light.on();
        this.tv.on();
        this.airConditioning.on();
    }

    private void off() {
        this.light.off();
        this.tv.off();
        this.airConditioning.off();
    }
}