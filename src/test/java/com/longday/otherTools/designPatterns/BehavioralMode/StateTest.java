package com.longday.otherTools.designPatterns.BehavioralMode;

import org.junit.jupiter.api.Test;

/**
 * @author 君
 * @version 1.0
 * @desc 状态模式
 * @date 2022/9/16
 */
public class StateTest {

    /**
     * 优点:
     * 1.将所有 与某个状态有关的行为放到一一个类中，并且可以方便地增加新的状态， 只需要改变对象状态即可改变对象的行为。
     * 2.允许状态转换逻辑与状态对象合成一-体， 而不是某一一个巨大的条件语句块。
     *
     * 缺点:
     * 1.状态模式的使用必然会增加系统类和对象的个数。
     * 2.状态模式的结构与实现都较为复杂，如果使用不当将导致程序结构和代码的混乱。
     * 3.状态模式对"开闭原则"的支持并不太好。
     *
     * 使用场景:
     * 1.当一个对象的行为取决于它的状态,并且它必须在运行时根据状态改变它的行为时，就可以考虑使用状态模式。
     * 2.一个操作中含有庞大的分支结构,并且这些分支决定于对象的状态时。
     */

    @Test
    public void common(){
        ILift lift = new Lift();
        lift.setState(ILift.RUNNING_STATE);
        System.out.println("************");
        lift.open();
        System.out.println("************");
        lift.close();
        System.out.println("************");
        lift.run();
        System.out.println("************");
        lift.stop();
    }

    @Test
    public void state(){
        Content content = new Content();
        content.setLiftState(Content.RUNNING_STATE);
        System.out.println("----------");
        content.open();
        System.out.println("----------");
        content.close();
        System.out.println("----------");
        content.run();
        System.out.println("----------");
        content.stop();
    }
}

/**
 * 一般情况，具有很多的判断，不符合软件设计思想
 */

interface ILift{
    byte OPENING_STATE = 1;
    byte CLOSING_STATE = 2;
    byte RUNNING_STATE = 3;
    byte STOPPING_STATE = 4;

    void setState(byte state);

    void open();
    void close();
    void run();
    void stop();
}

class Lift implements ILift{

    private byte state;

    /**
     * @param state 状态
     */
    @Override
    public void setState(byte state) {
        this.state = state;
    }

    /**
     *
     */
    @Override
    public void open() {
        switch (state){
            case OPENING_STATE:
                System.out.println("(open)什么也没有发生...");
                break;
            case CLOSING_STATE:
            case STOPPING_STATE:
                System.out.println("电梯打开了");
                this.setState(OPENING_STATE);
                break;
            case RUNNING_STATE:
                System.out.println("电梯正在运行，无法打开");
                break;
            default:
                System.out.println("请输入正确的状态！");
                break;
        }
    }

    /**
     *
     */
    @Override
    public void close() {
        switch (state){
            case CLOSING_STATE:
                System.out.println("(close)什么也没有发生...");
                break;
            case OPENING_STATE:
            case STOPPING_STATE:
                System.out.println("电梯关闭了");
                setState(CLOSING_STATE);
                break;
            case RUNNING_STATE:
                System.out.println("电梯正在运行，无法进行此操作");
                break;
            default:
                System.out.println("请输入正确的状态！");
                break;
        }
    }

    /**
     *
     */
    @Override
    public void run() {
        switch (state){
            case OPENING_STATE:
                System.out.println("电梯打开过程中，无法run");
                break;
            case CLOSING_STATE:
            case STOPPING_STATE:
                System.out.println("电梯即将启动");
                setState(RUNNING_STATE);
                break;
            case RUNNING_STATE:
                System.out.println("电梯已在运行");
                break;
            default:
                System.out.println("请输入正确的状态！");
                break;
        }
    }

    /**
     *
     */
    @Override
    public void stop() {
        switch (state){
            case OPENING_STATE:
            case STOPPING_STATE:
                break;
            case CLOSING_STATE:
            case RUNNING_STATE:
                System.out.println("电梯停止了");
                break;
            default:
                System.out.println("请输入正确的状态！");
                break;
        }
    }
}


/**
 * 状态模式
 */
abstract class LiftState{
    /**
     * 环境
     */
    protected Content context;

    /**
     * 设置当前环境
     * @param content #
     */
    public void setContext(Content content) {
        this.context = content;
    }

    /**
     * 电梯开门
     */
    public abstract void open();

    /**
     * 电梯关门
     */
    public abstract void close();

    /**
     * 电梯启动
     */
    public abstract void run();

    /**
     * 电梯停止
     */
    public abstract void stop();

}

class OpeningState extends LiftState{

    /**
     * 电梯开门
     */
    @Override
    public void open() {
        System.out.println("电梯开启...");
    }

    /**
     * 电梯关门
     */
    @Override
    public void close() {
        // 修改状态
        super.context.setLiftState(Content.CLOSING_STATE);
        // 调用当前状态中的Context中的close方法
        super.context.close();
    }

    /**
     * 电梯启动
     */
    @Override
    public void run() {
        //do nothing
        System.out.println("电梯门开着呢,跑啥跑");
    }

    /**
     * 电梯停止
     */
    @Override
    public void stop() {
        //do nothing
        System.out.println("电梯门开着呢,我还能动咋滴？");
    }
}

class ClosingState extends LiftState{

    /**
     * 电梯开门
     */
    @Override
    public void open() {
        System.out.println("切换到开启状态");
        super.context.setLiftState(Content.OPENING_STATE);
        super.context.open();
    }

    /**
     * 电梯关门
     */
    @Override
    public void close() {
        System.out.println("电梯关闭");
    }

    /**
     * 电梯启动
     */
    @Override
    public void run() {
        System.out.println("切换到运行状态");
        super.context.setLiftState(Content.RUNNING_STATE);
        super.context.run();
    }

    /**
     * 电梯停止
     */
    @Override
    public void stop() {
        System.out.println("电梯切换到停止状态");
        super.context.setLiftState(Content.STOPPING_STATE);
        super.context.stop();
    }
}

class RunningState extends LiftState{

    /**
     * 电梯开门
     */
    @Override
    public void open() {
        //do nothing
        System.out.println("电梯正在运行，无法打开");
    }

    /**
     * 电梯关门
     */
    @Override
    public void close() {
        //do nothing
        System.out.println("电梯正在运行，无法关门");
    }

    /**
     * 电梯启动
     */
    @Override
    public void run() {
        System.out.println("电梯正在运行");
    }

    /**
     * 电梯停止
     */
    @Override
    public void stop() {
        System.out.println("电梯切换到停止状态");
        super.context.setLiftState(Content.STOPPING_STATE);
        super.context.stop();
    }
}

class StoppingState extends LiftState{

    /**
     * 电梯开门
     */
    @Override
    public void open() {
        System.out.println("切换到开启状态");
        super.context.setLiftState(Content.OPENING_STATE);
        super.context.open();
    }

    /**
     * 电梯关门
     */
    @Override
    public void close() {
        System.out.println("切换到关门");
        super.context.setLiftState(Content.CLOSING_STATE);
        super.context.close();
    }

    /**
     * 电梯启动
     */
    @Override
    public void run() {
        System.out.println("切换到运行状态");
        super.context.setLiftState(Content.RUNNING_STATE);
        super.context.run();
    }

    /**
     * 电梯停止
     */
    @Override
    public void stop() {
        System.out.println("电梯停止了");
    }
}

class Content{
    public static final OpeningState OPENING_STATE = new OpeningState();
    public static final ClosingState CLOSING_STATE = new ClosingState();
    public static final RunningState RUNNING_STATE = new RunningState();
    public static final StoppingState STOPPING_STATE = new StoppingState();

    private LiftState liftState;

    public void setLiftState(LiftState liftState) {
        this.liftState = liftState;
        this.liftState.setContext(this);
    }

    public void open(){
        liftState.open();
    }

    public void close(){
        liftState.close();
    }

    public void run(){
        liftState.run();
    }

    public void stop(){
        liftState.stop();
    }



}