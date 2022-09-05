package com.longday.otherTools.designPatterns.createDesignMode;

import lombok.Data;
import org.junit.jupiter.api.Test;

/**
 * @author 君
 * @version 1.0
 * @date 2022/9/5
 */
public class SingletonTest {
// 饿汉式推荐使用 枚举方式
//懒汉式 推荐使用 静态内部类方式，当然双重检查锁方式也可。
    @Test
    public void hungryModel(){
        SingletonHungry clazz1 = SingletonHungry.getHungrySingleton();
        SingletonHungry clazz2 = SingletonHungry.getHungrySingleton();
        System.out.println(clazz1 == clazz2);//true
        System.out.println(clazz1.equals(clazz2));//true
    }

    @Test
    public void hungryModelEnum(){
        SingletonHungryByEnum clazz1 = SingletonHungryByEnum.INSTANCE;
        SingletonHungryByEnum clazz2 = SingletonHungryByEnum.INSTANCE;
        System.out.println(clazz1 == clazz2);//true
        System.out.println(clazz1.equals(clazz2));//true
    }
    @Test
    public void lazyModelOne(){
        //这种方式线程不安全
        SingletonLazyOne lazyModelSingleton1 = SingletonLazyOne.getLazyModelSingleton();
        SingletonLazyOne lazyModelSingleton2 = SingletonLazyOne.getLazyModelSingleton();
        System.out.println(lazyModelSingleton1 == lazyModelSingleton2);
        System.out.println(lazyModelSingleton1.equals(lazyModelSingleton2));
        /*控制台输出：  第一次取值,进行赋值
                      true
                      true
         */
    }

    @Test
    public void lazyModelTwo(){
        //为了实现线程安全,加锁处理 ==> 双重检查锁模式  因为获取单例对象是读操作次数大于写操作次数的行为
        SingletonLazyTwo lazyModelSingleton1 = SingletonLazyTwo.getLazyModelSingleton();
        SingletonLazyTwo lazyModelSingleton2 = SingletonLazyTwo.getLazyModelSingleton();
        System.out.println(lazyModelSingleton1 == lazyModelSingleton2);
        System.out.println(lazyModelSingleton1.equals(lazyModelSingleton2));
        /*控制台输出：  锁代码块中:第一次取值,进行赋值
                      true
                      true
         */
    }

    @Test
    public void lazyModelThree(){
        //为了实现线程安全,加锁处理 ==> 双重检查锁模式  因为获取单例对象是读操作次数大于写操作次数的行为
        SingletonLazyThree lazyModelSingleton1 = SingletonLazyThree.getLazyModelSingleton();
        SingletonLazyThree lazyModelSingleton2 = SingletonLazyThree.getLazyModelSingleton();
        System.out.println(lazyModelSingleton1 == lazyModelSingleton2);
        System.out.println(lazyModelSingleton1.equals(lazyModelSingleton2));
        /*控制台输出：  锁代码块中:第一次取值,进行赋值
                      true
                      true
         */
    }
}

/**
 * 饿汉式
 */
@Data
class SingletonHungry{
    private static SingletonHungry singletonHungry = new SingletonHungry();
    private String info;
    private String title;
    /**
     * 单例模式不对外提供构造方法
     */
    private SingletonHungry(){
    }

    public static SingletonHungry getHungrySingleton(){
        return singletonHungry;
    }
}

enum SingletonHungryByEnum{
    INSTANCE;
}

/**
 * 懒汉式(线程不安全)
 */
@Data
class SingletonLazyOne{
    //只提供声明，不提供真正的对象
    private static SingletonLazyOne singletonLazyOne;
    private String info;
    private String title;
    /**
     * 单例模式不对外提供构造方法
     */
    private SingletonLazyOne(){
    }

    public static SingletonLazyOne getLazyModelSingleton(){
        if(singletonLazyOne == null){
            singletonLazyOne = new SingletonLazyOne();
            System.out.println("第一次取值,进行赋值");
            return singletonLazyOne;
        }
        return singletonLazyOne;
    }
}

/**
 * 懒汉式:双重检查锁模式
 */
@Data
class SingletonLazyTwo{
    //只提供声明，不提供真正的对象  volatile 关键字，保证多线程模式下的线程安全
    private static volatile SingletonLazyTwo singletonLazyTwo;
    private String info;
    private String title;
    /**
     * 单例模式不对外提供构造方法
     */
    private SingletonLazyTwo(){
    }

    public static SingletonLazyTwo getLazyModelSingleton(){
        if(singletonLazyTwo == null){
            synchronized (SingletonLazyTwo.class){
                if(singletonLazyTwo == null){
                    singletonLazyTwo = new SingletonLazyTwo();
                    System.out.println("锁代码块中:第一次取值,进行赋值");
                }
            }
        }
        return singletonLazyTwo;
    }
}

/**
 * 懒汉式:静态内部类
 */
@Data
class SingletonLazyThree{
    /*      静态内部类。JVM加载机制：加载外部类时不进行静态内部类的加载。只有内部类的属性或方法被调用时才会加载，并
        初始化内部类的静态属性。
    */
    private static class SingletonLazyHolder{
        private static final SingletonLazyThree INSTANCE = new SingletonLazyThree();
    }
    private String info;
    private String title;
    /**
     * 单例模式不对外提供构造方法
     */
    private SingletonLazyThree(){
    }

    public static SingletonLazyThree getLazyModelSingleton(){
        return SingletonLazyHolder.INSTANCE;
    }
}