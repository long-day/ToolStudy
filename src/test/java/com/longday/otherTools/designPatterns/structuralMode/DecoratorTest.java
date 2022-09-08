package com.longday.otherTools.designPatterns.structuralMode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;

/**
 * @author 君
 * @version 1.0
 * @date 2022/9/7
 */
public class DecoratorTest {
    /**
     * 装饰者模式:
     *  优势：
     *  装饰者模式可以带来比继承更加灵活性的扩展功能,使用更加方便,可以通过不同组合的装饰者对象来获取具有不同行为状态
     *  的多结果.装饰者模式比继承更具良好的扩展性,完美遵循开闭原则,继承是静态的附加责任,装饰者则是动态的附加责任.
     *  装饰类 和 被装饰类 可以独立发展,不会相互耦合,装饰模式是继承的一个替代模式,装饰模式可以动态扩展一个实现类的功能.
     *
     *  应用场景:
     *  当不能采用继承的方式对系统进行扩充或者采用继承不利于系统扩展和维护时.
     *  不能采用继承的情况主要有两类:
     *      .第一类是系统中存在大量独立的扩展,为支持每一种组合将产生大量的子类,使得子类数目呈爆炸性增长;
     *      .第二类是因为类定义不能继承(如final类)
     *  在不影响其他对象的情况下,以动态、透明的方式给单个对象添加职责.
     *  当对象的功能要求可以动态地添加,也可以再动态地撤销时.
     *
     */
    @Test
    public void decorator(){
        FastFood firedRice = new FiredRice();
        System.out.println(firedRice.getDesc()+": "+ firedRice.pay());
        System.out.println("===========");
        FastFood eggFiredRice = new Egg(firedRice);
        System.out.println(eggFiredRice.getDesc() + ":" + eggFiredRice.pay());
        System.out.println("===========");
        FastFood eggFiredRice2 = new Egg(eggFiredRice);
        System.out.println(eggFiredRice2.getDesc() + ":" + eggFiredRice2.pay());
        System.out.println("===========");
        FastFood firedNoodle = new FiredNoodle();
        System.out.println(firedRice.getDesc()+": "+ firedRice.pay());
        System.out.println("===========");
        FastFood eggFiredNoodle = new Egg(firedNoodle);
        System.out.println(eggFiredNoodle.getDesc()+": "+ eggFiredNoodle.pay());
    }

    /**
     * 静态代理和装饰者模式的区别:
     * 相同点:
     *      。都要实现与目标类相同的业务接口
     *      。在两个类中都要声明目标对象
     *      。都可以在不修改目标类的前提下增强目标方法
     * 不同点:
     *      。目的不同
     *          装饰者是为了增强目标对象
     *          静态代理是为了保护和隐藏目标对象
     *      。获取目标对象构建的地方不同
     *          装饰者是由外界传递进来，可以通过构造方法传递
     *          静态代理是在代理类内部创建，以此来隐藏目标对象
     */
    @Test
    public void TheDifferenceBetweenProxyAndDecoration(){}
}

/**
 * 抽象构件角色
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
abstract class FastFood{
    private Double price;
    private String desc;
    public abstract Double pay();
}
/**
 * 具体构件角色
 */
class FiredRice extends FastFood{

    FiredRice(){
        super(8.0,"炒饭");
    }
    /**
     * 付款
     */
    @Override
    public Double pay() {
        return getPrice();
    }
}
/**
 * 具体构件角色
 */
class FiredNoodle extends FastFood{

    FiredNoodle(){
        super(7.0,"炒面");
    }
    /**
     * 付款
     */
    @Override
    public Double pay() {
       return getPrice();
    }
}

/**
 * 抽象装饰者角色
 */
abstract class Garnish extends FastFood{
    private FastFood fastFood;

    public Garnish(Double price, String desc, FastFood fastFood) {
        super(price, desc);
        this.fastFood = fastFood;
    }

    public FastFood getFastFood() {
        return fastFood;
    }
}

class Egg extends Garnish{

    public Egg(FastFood fastFood) {
        super(1.0, "鸡蛋", fastFood);
    }

    /**
     *
     */
    @Override
    public Double pay() {
        return getFastFood().pay()+getPrice();
    }

    /**
     * @return #
     */
    @Override
    public String getDesc() {
        return getFastFood().getDesc() +"+"+  super.getDesc();
    }
}