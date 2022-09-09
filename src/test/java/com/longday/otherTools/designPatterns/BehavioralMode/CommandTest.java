package com.longday.otherTools.designPatterns.BehavioralMode;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * @author 君
 * @version 1.0
 * @desc 命令模式
 * @date 2022/9/9
 */
public class CommandTest {
    /**
     *优点:
     *      降低系统的耦合度。命令模式能将调用操作的对象与实现该操作的对象解耦。
            增加或删除命令非常方便。采用命令模式增加与删除命令不会影响其他类，它满足“开闭原则”，对扩展比较灵活。
     *      可以实现宏命令。命令模式可以与组合模式结合,将多个命令装配成-一个组合命令，即宏命令。
     *      方便实现Undo和Redo 操作。命令模式可以与后面介绍的备忘录模式结合,实现命令的撤销与恢复。
     * 缺点:
     *      使用命令模式可能会导致某些系统有过多的具体命令类。
     *      系统结构更加复杂。
     * 使用场景
     *      系统需要将请求调用者和请求接收者解耦,使得调用者和接收者不直接交互。
     *      系统需要在不同的时间指定请求、将请求排队和执行请求。
     *      系统需要支持命令的撤销(Undo)操作和恢复(Redo)操作。
     */
    @Test
    public void command() {
        Order order = new Order();
        order.setDiningTableId(1);
        order.orderFood("番茄鸡蛋",2);

        Order order2 = new Order();
        order2.setDiningTableId(2);
        order2.orderFood("澳洲龙虾",1);

        Waiter waiter = new Waiter();
        Chef chef = new Chef();

        waiter.addCommand(new OrderCommand(chef,order));
        waiter.addCommand(new OrderCommand(chef,order2));
        waiter.activeCommit();
    }
}

interface Command {
    /**
     * 命令触发
     */
    void activeCommand();
}

@Data
class Order {
    private Integer diningTableId;

    private HashMap<String, Integer> map = new HashMap<>(8);

    public void orderFood(String foodName, Integer quota) {
        map.put(foodName, quota);
    }
}

class Chef {
    public void cookingFood(String foodName, Integer quota) {
        System.out.println("厨师: 制作" + foodName + ":" + quota + "份");
    }
}

@AllArgsConstructor
class OrderCommand implements Command {
    /**
     * 命令接受者
     */
    private Chef chefReceiver;
    /**
     * 订单
     */
    private Order order;

    /**
     * 具体命令类
     */
    @Override
    public void activeCommand() {
        System.out.println(order.getDiningTableId() + "号桌的订单:");
        order.getMap().forEach((k, v) -> chefReceiver.cookingFood(k, v));
        System.out.println(order.getDiningTableId() + "号桌的订单准备完毕！");
    }
}

@Data
class Waiter {
    private List<Command> commandList = new ArrayList<>();

    public void addCommand(Command command){
        commandList.add(command);
    }

    public void activeCommit(){
        System.out.println("服务员发起订单:");
        for (Command command : commandList) {
            if (!Objects.isNull(command)){
                command.activeCommand();
            }
        }
    }
}