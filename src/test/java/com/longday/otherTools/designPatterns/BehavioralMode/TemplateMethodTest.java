package com.longday.otherTools.designPatterns.BehavioralMode;

import org.junit.jupiter.api.Test;

/**
 * @author 君
 * @version 1.0
 * @desc 模板方法模式
 * @date 2022/9/8
 */
public class TemplateMethodTest {
    /**
     * 优点:
     *      提高代码复用性
     *      将相同部分的代码放在抽象的父类中,而将不同的代码放入不同的子类中.
     *      实现了反向控制
     *      通过一个父类调用其子类的操作,通过对子类的具体实现扩展不同的行为,实现了反向控制,并符合“开闭原则”.
     * 缺点:
     *      对每个不同的实现都需要定义一-个子类,这会导致类的个数增加,系统更加庞大,设计也更加抽象.
     *      父类中的抽象方法由子类实现,子类执行的结果会影响父类的结果,这导致一种反向的控制结构,它提高了代码阅读的难度.
     * 适用场景
     *      算法的整体步骤很固定,但其中个别部分易变时,这时候可以使用模板方法模式,将容易变的部分抽象出来,供子类实现.
     *      需要通过子类来决定父类算法中某个步骤是否执行,实现子类对父类的反向控制.
     */
    @Test
    public void templateMethod(){
        CookingCarrot cookingCarrot = new CookingCarrot();
        cookingCarrot.cookProcessing();

        System.out.println("---------");
        CookingChicken cookingChicken = new CookingChicken();
        cookingChicken.cookProcessing();
    }
}

abstract class Cooking{

    public final void cookProcessing(){
        pourOil();
        heatingOil();
        pourVegetable();
        pourSauce();
        cokingVegetable();
        System.out.println("做好装盘！");
    }

    public void pourOil(){
        System.out.println("倒入花生油...");
    }
    public void heatingOil(){
        System.out.println("将油加热到5成热...");
    }
    public abstract void pourVegetable();
    public abstract void pourSauce();

    public void cokingVegetable(){
        System.out.println("翻炒熬制...");
    }

}

class CookingCarrot extends Cooking{

    /**
     * 重写添加蔬菜方法
     */
    @Override
    public void pourVegetable() {
        System.out.println("添加胡萝卜");
    }

    /**
     * 重写添加佐料方法
     */
    @Override
    public void pourSauce() {
        System.out.println("添加: 鸡精,味精,盐,蚝油,老抽,少许白糖");
    }
}


class CookingChicken extends Cooking{

    /**
     * 重写添加蔬菜方法
     */
    @Override
    public void pourVegetable() {
        System.out.println("鸡肉块");
    }

    /**
     * 重写添加佐料方法
     */
    @Override
    public void pourSauce() {
        System.out.println("添加: 料酒、啤酒、白芷、八角、桂皮、生姜、花椒等");
    }
}