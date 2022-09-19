package com.longday.otherTools.designPatterns.BehavioralMode;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 君
 * @version 1.0
 * @desc 访问者模式
 * @date 2022/9/16
 */
public class VisitorTest {
    /**
     * 优点:
     *  1.扩展性好
     *      在不修改对象结构中的元素的情况下，为对象结构中的元素添加新的功能。
     *  2.复用性好
     *      通过访问者来定义整个对象结构通用的功能，从而提高复用程度。
     *  3.分离无关行为
     *      通过访问者来分离无关的行为，把相关的行为封装在- -起， 构成-个访问者, 这样每一个访问者的功能都比较单一。
     *
     * 缺点:
     *  1.对象结构变化很困难
     *      在访问者模式中，每增加一个新的元素类,都要在每一个具体访问者类中增加相应的具体操作,这违背了“开闭原则”。
     *  2.违反了依赖倒置原则
     *      访问者模式依赖了具体类,而没有依赖抽象类。
     *
     * 应用场景:
     *  1.对象结构相对稳定，但其操作算法经常变化的程序。
     *  2.对象结构中的对象需要提供多种不同且不相关的操作，而且要避免让这些操作的变化影响对象的结构。
     */
    @Test
    public void visitor(){
        Home home = new Home();
        home.add(new GoldenLayerCat());
        home.add(new GoldenLayerCat());
        home.add(new LabradorDog());
        home.add(new LabradorDog());
        home.action(new Owner());
        System.out.println("----------");
        home.action(new Someone());
    }
}

interface Animal{
    void accept(People people);
}

class GoldenLayerCat implements Animal{

    @Override
    public void accept(People people) {
        people.feed(this);
        System.out.println("喵喵喵");
    }
}

class LabradorDog implements Animal{

    @Override
    public void accept(People people) {
        people.feed(this);
        System.out.println("汪汪汪");
    }
}



interface People{
    void feed(GoldenLayerCat cat);
    void feed(LabradorDog dog);
}

class Owner implements People{

    @Override
    public void feed(GoldenLayerCat cat) {
        System.out.println("主人喂猫");
    }

    @Override
    public void feed(LabradorDog dog) {
        System.out.println("主人喂狗");
    }
}

class Someone implements People{

    @Override
    public void feed(GoldenLayerCat cat) {
        System.out.println("爱猫人士喂猫");
    }

    @Override
    public void feed(LabradorDog dog) {
        System.out.println("爱狗人士喂狗");
    }
}

class Home{
    private final List<Animal> nodeList = new ArrayList<>(2);

    public void add(Animal animal){
        nodeList.add(animal);
    }

    public void action(People people){
        for (Animal animal : nodeList) {
            animal.accept(people);
        }
    }
}
