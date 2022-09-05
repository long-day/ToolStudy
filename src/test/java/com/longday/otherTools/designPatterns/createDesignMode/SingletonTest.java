package com.longday.otherTools.designPatterns.createDesignMode;

import lombok.Data;
import org.junit.jupiter.api.Test;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author 君
 * @version 1.0
 * @date 2022/9/5
 */
public class SingletonTest {
// 饿汉式中枚举方式会浪费一定的内存空间但是由于其实JVM底层的实现，所以可以防止单例模式被破坏(天然自带)
// 懒汉式 推荐使用 静态内部类方式，当然双重检查锁方式也可。
// 破坏单例模式的方法: 序列化和反射
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
    @Test
    public void destructionSingletonModelByReflect() {
        //运行此方法时请将SingletonHungry类上的@Data注解注释掉，要不然不会打印出地址值
        Class<?> singleton = SingletonHungry.class;
        try {
            Constructor<?> noArgsConstruction = singleton.getDeclaredConstructor();
            noArgsConstruction.setAccessible(true);
            Object o = noArgsConstruction.newInstance();
            Object o1 = noArgsConstruction.newInstance();
            System.out.println(o);//SingletonHungry@27c6e487
            System.out.println(o1);//SingletonHungry@49070868
            System.out.println(o == o1);//false
        }catch (Exception e){
            e.printStackTrace();
        }
        //解决方案
        /*走构造方法这样写,直接不让他创建:
         private SingletonHungry(){
            throw new RuntimeException("请不要创建多个对象！(小比崽子,用反射是吧？不要脸 -.-)");
         }
        */
    }

    @Test
    public void destructionSingletonModelByDeserialize() throws Exception {
        //运行此方法时请将SingletonHungry类上的@Data注解注释掉，要不然不会打印出地址值
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(Files.newOutputStream(Paths.get("src/test/java/com/longday/otherTools/designPatterns/createDesignMode/files/temp.txt")));
        objectOutputStream.writeObject(SingletonHungry.getHungrySingleton());
        objectOutputStream.close();

        ObjectInputStream inputStream = new ObjectInputStream(Files.newInputStream(Paths.get("src/test/java/com/longday/otherTools/designPatterns/createDesignMode/files/temp.txt")));
        SingletonHungry o = (SingletonHungry) inputStream.readObject();
        inputStream.close();

        inputStream = new ObjectInputStream(Files.newInputStream(Paths.get("src/test/java/com/longday/otherTools/designPatterns/createDesignMode/files/temp.txt")));
        SingletonHungry o1 = (SingletonHungry) inputStream.readObject();
        inputStream.close();
        System.out.println(o);//SingletonHungry@dd3b207
        System.out.println(o1);//SingletonHungry@551bdc27
        System.out.println(o == o1); //false

        //解决方案
        //在被序列化类中添加readResolve方法后，返回为true
    }
}

/**
 * 饿汉式
 */
//@Data
class SingletonHungry implements Serializable {
    private static SingletonHungry singletonHungry = new SingletonHungry();
    private String info;
    private String title;
    /**
     * 单例模式不对外提供构造方法
     */
    private SingletonHungry(){
      throw new RuntimeException("请不要创建多个对象！(小比崽子,用反射是吧？不要脸 -.-)");
    }

    public static SingletonHungry getHungrySingleton(){
        return singletonHungry;
    }

    /**
     * 反序列化规则，设置反序列化时对象的获取来源。没有此方法默认为new一个新对象。
     * @return 单例对象
     */
    public Object readResolve(){
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