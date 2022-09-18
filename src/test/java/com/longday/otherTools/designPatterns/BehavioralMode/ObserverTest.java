package com.longday.otherTools.designPatterns.BehavioralMode;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 君
 * @version 1.0
 * @desc 观察者模式
 * @date 2022/9/16
 */
public class ObserverTest {
    /**
     * 优点:
     *      降低了目标与观察者之间的耦合关系，两者之间是抽象耦合关系。
     *      被观察者发送通知，所有注册的观察者都会收到信息[可以实现广播机制]
     * 缺点:
     *      如果观察者非常多的话，会有较大耗时
     *      如果被观察者有循环依赖的话，那么 “被观察者” 发送通知时会使观察者被循环调用,会导致系统崩溃
     * 使用场景
     *      对象间存在一对多关系, 一个对象的状态发生改变会影响其他对象。
     *      当一个抽象模型有两个方面，其中一个方面依赖于另一方面时。
     */
    @Test
    public void observer(){
        WeiXinUser user1 = new WeiXinUser("洛玉衡");
        WeiXinUser user2 = new WeiXinUser("焰灵姬");
        WeiXinUser user3 = new WeiXinUser("云韵");

        SubscriptionSubject subject = new SubscriptionSubject();
        subject.attach(user1).attach(user2).attach(user3);
        subject.notify("花园宝宝更新了");
    }
}

/**
 * 抽象主题类
 */
interface Subject{
    /**
     *  添加订阅者
     * @param observer 观察者对象，表示相应的对象
     */
    Subject attach(Observer observer);

    /**
     *  删除订阅者
     * @param observer 观察者对象，表示相应的对象
     */
    Subject detach(Observer observer);

    /**
     *  通知订阅者
     * @param msg 观察者对象，表示相应的对象
     */
    void notify(String msg);
}

/**
 * 具体主题类
 */
class SubscriptionSubject implements Subject{

    private final List<Observer> weiXinUserList = new ArrayList<>();

    /**
     * 添加订阅者
     *
     * @param observer 观察者对象，表示相应的对象
     */
    @Override
    public SubscriptionSubject attach(Observer observer) {
        weiXinUserList.add(observer);
        return this;
    }

    /**
     * 删除订阅者
     *
     * @param observer 观察者对象，表示相应的对象
     */
    @Override
    public SubscriptionSubject detach(Observer observer) {
        weiXinUserList.remove(observer);
        return this;
    }

    /**
     * 通知订阅者
     *
     * @param msg 观察者对象，表示相应的对象
     */
    @Override
    public void notify(String msg) {
        for (Observer observer : weiXinUserList) {
            observer.update("通知你，你要更新了: "+msg);
        }
    }
}


/**
 * 抽象观察者类
 */
interface Observer{
    /**
     * 更新
     */
    void update(String msg);
}

@AllArgsConstructor
class WeiXinUser implements Observer{

    private String name;
    /**
     * 更新
     * @param msg 信息
     */
    @Override
    public void update(String msg) {
        System.out.println(this.name+" : "+msg);
    }
}