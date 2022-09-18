package com.longday.otherTools.designPatterns.BehavioralMode;

import lombok.AllArgsConstructor;
import lombok.Setter;
import org.junit.jupiter.api.Test;

/**
 * @author 君
 * @version 1.0
 * @desc 中介者模式
 * @date 2022/9/16
 */
public class MediatorTest {
    /**
     * 优点:
     *  1.松散耦合
     *      中介者模式通过把多个同事对象之间的交互封装到中介者对象里面，从而使得同事对象之间松散耦合,基本上可以做到
     *      互补依赖。这样一来,同事对象就可以独立地变化和复用，而不再像以前那样“牵一处而动全身”了。
     *  2.集中控制交互
     *  多个同事对象的交互，被封装在中介者对象里面集中管理，使得这些交互行为发生变化的时候，只需要修改中介者对象就可以了,
     *  当然如果是已经做好的系统，那么就扩展中介者对象,而各个同事类不需要做修改。
     *  3.一对多关联转变为一对一的关联
     *      没有使用中介者模式的时候，同事对象之间的关系通常是一对多的, 引入中介者对象以后,中介者对象和同事对象的关系
     *      通常变成双向的一对一，这会让对象的关系更容易理解和实现。
     * 缺点:
     *  当同事类太多时，中介者的职责将很大，它会变得复杂而庞大,以至于系统难以维护。
     *
     *  应用场景:
     *      系统中对象之间存在复杂的引用爱心，系统结构混乱且难以理解
     *      当想创建一个运行于多个类之间的对象，又不想生成新的子类时。
     */
    @Test
    public void mediator(){
        MediatorStructure mediatorStructure = new MediatorStructure();
        HouseOwner houseOwner = new HouseOwner("张三", mediatorStructure);
        Tenant tenant = new Tenant("打工人", mediatorStructure);
        mediatorStructure.setHouseOwner(houseOwner);
        mediatorStructure.setTenant(tenant);

        tenant.connect("我要租房子");
        houseOwner.connect("我这里只有三室一厅了,你要吗？");
    }
}

@AllArgsConstructor
abstract class Person{
    protected String name;
    protected Mediator mediator;
}

class Tenant extends Person{


    public Tenant(String name, Mediator mediator) {
        super(name, mediator);
    }

    public void connect(String msg){
        mediator.connect(msg,this);
    }

    public void getMessage(String message){
        System.out.println("租房者: "+name+" 获取到的信息是:"+ message);
    }
}

class HouseOwner extends Person{

    public HouseOwner(String name, Mediator mediator) {
        super(name, mediator);
    }
    public void connect(String msg){
        mediator.connect(msg,this);
    }

    public void getMessage(String message){
        System.out.println("房主: "+name+" 获取到的信息是:"+ message);
    }
}

abstract class Mediator{

    public abstract void connect(String message,Person person);
}

@Setter
class MediatorStructure extends Mediator{

    private HouseOwner houseOwner;
    private Tenant tenant;

    @Override
    public void connect(String message, Person person) {
        if(person == houseOwner){
            tenant.getMessage(message);
        }else {
            houseOwner.getMessage(message);
        }
    }
}