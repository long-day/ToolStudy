package com.longday.toolstudy.otherTools.designPatterns.principles;

import org.junit.jupiter.api.Test;

/**
 * @author 君
 * @version 1.0
 * @date 2022/9/4
 */
public class OpenClosedTest {
    // 开闭原则: 软件对象(类、模块、方法等)应该对于扩展是开放的，对修改是关闭的

    // 开闭原则简单来说就是类的抽象。将某些功能抽象称为一个抽象类或接口中的方法
    // 当有相关实现类时可以做到“只添加不修改的目的”
    // 最熟悉的例子莫过于List接口和ArrayList实现类和LinkedList实现类的关系了
    // 示例
    @Test
    public void requirementOne(/*Cat tom*/){
        //假设这个对象是方法传进来的，Test方法无法传参进来，使用此法代替
        Cat tom = new Cat();
        tom.speak();
        tom.walk();
    }
    /* 初始状态  只有一个Cat,如果我们直接写Cat的speak()和walk()方法,这时我们需求突然改变(增加),不仅仅有Cat了
    还需要有Dog,这时怎么办？直接写一个Dog类出来？当然，这样也可以，但是这存在一个问题，之前代码中直接调用Cat类的
    实例化对象的地方无法直接使用Dog类,因为这不是同一种类.这时候我们想到了Java面向对象的特点: 多态.对是应该用多态，
    但是怎么用合适呢？这就需要根据实际需求考虑，比如这里的，我们是需要一个动物，让他speak()和walk()[根据上面代码来判断]
    那么我们可以向宏观上想:
        类一: 猫 是 猫科动物，是一种小动物，是一种可爱的动物；
        类而: 狗 是 犬科动物，是一种较大的动物，是一种...可能可爱的动物
        怎么抽象？动物，显然意见。在拓展想想，以后大概率来的需求还是和动物有关，抽象为动物合理.
     */
    //改进示例
    @Test
    public void requirementTwo(/*Animal oneAnimal*/){
        //假设这个对象是方法传进来的，Test方法无法传参进来，使用此法代替
        Animal tom = new Cat();
        tom.speak();
        tom.walk();

        //当方法需要一个Dog对象来处理相关需求时，直接将Dog对象传入到此方法即可复用方法了。

        //假设这个对象是方法传进来的，Test方法无法传参进来，使用此法代替
        Animal bigYellow = new Dog();
        bigYellow.speak();
        bigYellow.walk();
    }
}
// 示例类

interface Animal{
    void speak();
    void walk();
}

class Cat implements Animal{

    @Override
    public void speak() {
        System.out.println("猫speak(): 喵喵喵");
    }

    @Override
    public void walk() {
        System.out.println("猫walk: 实时四驱,弯道快才是真的快！");
    }
}

class Dog implements Animal{

    @Override
    public void speak() {
        System.out.println("狗speak(): 汪汪汪");
    }

    @Override
    public void walk() {
        System.out.println("狗walk: 憨批狗爬着走！");
    }
}