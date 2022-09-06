package com.longday.otherTools.designPatterns.createDesignMode;

import lombok.Data;
import org.junit.jupiter.api.Test;

/**
 * @author 君
 * @version 1.0
 * @date 2022/9/5
 */
public class FactoryTest {

    /**
     * 简单工厂模式：<br/>
     * 优点：解耦了{@link CoffeeStore}和具体的{@link Coffee}的耦合<br/>
     * 缺点：产生了新的耦合，即{@link SimpleCoffeeFactory}和{@link Coffee}之间的耦合<br/>
     */
    @Test
    public void simpleFactory() {
        CoffeeStore coffeeStore = new CoffeeStore();
        Coffee coffee = coffeeStore.orderCoffee(CoffeeType.LATTE_COFFEE);
        System.out.println(coffee.getCoffeeName() + "生产完成");
    }

    /**
     * 工厂模式：<br/>
     * 优点：满足开闭原则。仅需设置特定工厂即可获取对应的Coffee<br/>
     * 缺点：增加一个新Coffee实现类是需要添加一个具体产品和一个对应的具体产品工厂，增加了系统的复杂度<br/>
     */
    @Test
    public void factoryModel() {
        LatteCoffeeFactory latteCoffeeFactory = new LatteCoffeeFactory();
        CoffeeStorePro coffeeStorePro = new CoffeeStorePro();
        coffeeStorePro.setCoffeeFactory(latteCoffeeFactory);
        Coffee latteCoffee = coffeeStorePro.orderCoffee();
        System.out.println(latteCoffee.getCoffeeName() + "生产完成");
        CappuccinoCoffeeFactory cappuccinoCoffeeFactory = new CappuccinoCoffeeFactory();
        coffeeStorePro.setCoffeeFactory(cappuccinoCoffeeFactory);
        Coffee cappuccinoCoffee = coffeeStorePro.orderCoffee();
        System.out.println(cappuccinoCoffee.getCoffeeName() + "生产完成");

    }

    /**
     * 抽象工厂模式：<br/>
     * 优点：<br/>
     * 缺点：<br/>
     */
    @Test
    public void abstractFactoryModel() {
        ItalySweetsFactory italySweetsFactory = new ItalySweetsFactory();
        Coffee latteCoffee = italySweetsFactory.makeCoffee();
        System.out.println(latteCoffee.getCoffeeName() + "生产完成");
        Dessert tiramisu = italySweetsFactory.makeDessert();
        System.out.println(tiramisu.getDessertName()+ "生产完成");

        System.out.println("------");

        AmericanSweetsFactory americanSweetsFactory = new AmericanSweetsFactory();
        Coffee cappuccinoCoffee = americanSweetsFactory.makeCoffee();
        System.out.println(cappuccinoCoffee.getCoffeeName() + "生产完成");
        Dessert matchaMousse = americanSweetsFactory.makeDessert();
        System.out.println(matchaMousse.getDessertName()+ "生产完成");
    }
    @Test
    public void abstractFactoryModelPro() {
        HaganDasSweetsStore haganDasSweetsStore = new HaganDasSweetsStore();
        AmericanSweetsFactory americanSweetsFactory = new AmericanSweetsFactory();
        haganDasSweetsStore.setSweetsFactory(americanSweetsFactory);
        Sweet sweet = haganDasSweetsStore.orderSweet(SweetType.COFFEE);
        Coffee coffee = (Coffee) sweet;
        System.out.println(coffee.getCoffeeName() + "生产完成");

        Sweet sweet2 = haganDasSweetsStore.orderSweet(SweetType.DESSERT);
        Dessert dessert = (Dessert) sweet2;
        System.out.println(dessert.getDessertName() + "生产完成");

    }


}

/**
 * 咖啡店 创建Coffee的类
 */
class CoffeeStore {
    private final SimpleCoffeeFactory simpleCoffeeFactory = new SimpleCoffeeFactory();

    public Coffee orderCoffee(CoffeeType coffeeType) {
        Coffee coffee = simpleCoffeeFactory.makeCoffee(coffeeType);
        coffee.addSugar();
        coffee.addMilk();
        return coffee;
    }
}

/**
 * 咖啡店 创建Coffee的类
 */
@Data
class CoffeeStorePro {
    private CoffeeFactory coffeeFactory;

    public Coffee orderCoffee() {
        Coffee coffee = coffeeFactory.makeCoffee();
        coffee.addMilk();
        coffee.addSugar();
        return coffee;
    }
}

/**
 * Coffee抽象类
 */
abstract class Coffee implements Sweet {
    public abstract String getCoffeeName();

    public void addSugar() {
        System.out.println(getCoffeeName() + "-加糖！");
    }

    public void addMilk() {
        System.out.println(getCoffeeName() + "-加奶！");
    }
}

/**
 * LatteCoffee 具体的咖啡
 */
class LatteCoffee extends Coffee {

    @Override
    public String getCoffeeName() {
        return "拿铁咖啡";
    }
}

/**
 * CappuccinoCoffee 具体的咖啡
 */
class CappuccinoCoffee extends Coffee {

    @Override
    public String getCoffeeName() {
        return "卡布奇诺咖啡";
    }
}

/**
 * 枚举 咖啡的类型
 */
enum CoffeeType {
    /**
     * 拿铁
     */
    LATTE_COFFEE,
    /**
     * 卡布奇诺
     */
    CAPPUCCINO_COFFEE
}

/**
 * 简单工厂模式的工厂<br/>
 * -------------------- 分割线 --------------------
 */
class SimpleCoffeeFactory {
    /**
     * 简单工厂获取对象的方法。注意，这个也可以设置为静态方法。这样就成为了静态工厂<br/>
     *
     * @param coffeeType 咖啡的类型<br/>
     * @return 对应类型的咖啡对象<br />
     */
    public Coffee makeCoffee(CoffeeType coffeeType) {

        switch (coffeeType) {
            case LATTE_COFFEE:
                return new LatteCoffee();
            case CAPPUCCINO_COFFEE:
                return new CappuccinoCoffee();
            default:
                return null;
        }
    }
}


/**
 * 工厂模式的工厂<br/>
 * -------------------- 分割线 --------------------
 */
interface CoffeeFactory {
    /**
     * 制作咖啡
     *
     * @return 具体的咖啡
     */
    Coffee makeCoffee();
}

/**
 * 拿铁咖啡工厂
 */
class LatteCoffeeFactory implements CoffeeFactory {

    @Override
    public Coffee makeCoffee() {
        return new LatteCoffee();
    }
}

/**
 * 卡布奇诺咖啡工厂
 */
class CappuccinoCoffeeFactory implements CoffeeFactory {

    @Override
    public Coffee makeCoffee() {
        return new CappuccinoCoffee();
    }
}

/**
 * 抽象工厂模式的工厂<br/>
 * -------------------- 分割线 --------------------
 */
@Data
class HaganDasSweetsStore{
    SweetsFactory sweetsFactory;
    public Sweet orderSweet(SweetType sweetType){
        switch (sweetType){
            case COFFEE:
                return sweetsFactory.makeCoffee();
            case DESSERT:
                return sweetsFactory.makeDessert();
            default:
                return null;

        }
    }
}

/**
 * 甜点接口
 */
interface Sweet{

}

interface SweetsFactory{
    /**
     * 生产咖啡
     * @return 咖啡
     */
    Coffee makeCoffee();
    /**
     * 生产甜点
     * @return 甜点
     */
    Dessert makeDessert();
}

/**
 * 美国风味的甜品
 */
class AmericanSweetsFactory implements SweetsFactory{

    @Override
    public Coffee makeCoffee() {
        return new CappuccinoCoffee();
    }

    @Override
    public Dessert makeDessert() {
        return new MatchaMousse();
    }
}

/**
 * 意大利风味的甜品
 */
class ItalySweetsFactory implements SweetsFactory{

    /**
     * 制作咖啡
     * @return 拿铁咖啡
     */
    @Override
    public Coffee makeCoffee() {
        return new LatteCoffee();
    }

    @Override
    public Dessert makeDessert() {
        return new Tiramisu();
    }
}


/**
 * 甜品抽象类
 */
abstract class Dessert implements Sweet {
    public abstract String getDessertName();
}

/**
 * 提拉米苏甜品
 */
class Tiramisu extends Dessert {

    @Override
    public String getDessertName() {
        return "提拉米苏甜品";
    }
}

/**
 * 抹茶慕斯 (WTF？Matcha单词报错认真的吗？)
 */
class MatchaMousse extends Dessert {

    @Override
    public String getDessertName() {
        return "抹茶慕斯甜品";
    }
}

enum SweetType{
    /**
     * 咖啡
     */
    COFFEE,
    /**
     * 甜点
     */
    DESSERT
}