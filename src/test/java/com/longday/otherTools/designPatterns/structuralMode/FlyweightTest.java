package com.longday.otherTools.designPatterns.structuralMode;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

/**
 * @author 君
 * @version 1.0
 * @desc 享元模式
 * @date 2022/9/8
 */
public class FlyweightTest {

    /**
     * 优点
     *      极大减少内存中相似或相同对象数量，节约系统资源，提供系统性能
     *      享元模式中的外部状态相对独立，且不影响内部状态
     * 缺点:
     *      为了使对象可以共享，需要将享元对象的部分状态外部化，分离内部状态和外部状态，使程序逻辑复杂
     * 使用场景:
     *      一个系统有大量相同或者相似的对象，造成内存的大量耗费。
     *      对象的大部分状态都可以外部化，可以将这些外部状态传入对象中。
     *      在使用享元模式时需要维护一个存储享元对象的享元池，而这需要耗费一定的系统资源，因此，应当在需要多次重复使用
     *      享元对象时才值得使用享元模式。
     */

    @Test
    public void flyweight(){
        Box l = BoxFactory.getInstance().getShapeByName("L");
        l.display("red");

        Box o = BoxFactory.getInstance().getShapeByName("O");
        o.display("yellow");

        Box v = BoxFactory.getInstance().getShapeByName("V");
        v.display("blue");

        Box e = BoxFactory.getInstance().getShapeByName("E");
        e.display("green");

        Box e2 = BoxFactory.getInstance().getShapeByName("E");
        e2.display("black");

        System.out.println("是否同一个对象: "+(e == e2));

    }
}

/**
 * 抽象享元角色
 */
abstract class Box{
    /**
     * 获取图形编码
     * @return 图形编码
     */
    public abstract String getShape();

    public void display(String color){
        System.out.println("方块编码: "+getShape()+"颜色: "+color);
    }

}

/**
 * 具体享元角色
 */
class LBox extends Box{

    /**
     * @return #
     */
    @Override
    public String getShape() {
        return "L";
    }
}

/**
 * 具体享元角色
 */
class OBox extends Box{

    /**
     * @return #
     */
    @Override
    public String getShape() {
        return "O";
    }
}

/**
 * 具体享元角色
 */
class VBox extends Box{

    /**
     * @return #
     */
    @Override
    public String getShape() {
        return "V";
    }
}

/**
 * 具体享元角色
 */
class EBox extends Box{

    /**
     * @return #
     */
    @Override
    public String getShape() {
        return "E";
    }
}

class BoxFactory{
    private static final BoxFactory factory = new BoxFactory();
    private final HashMap<String,Box> boxHashMap;

    private BoxFactory(){
        boxHashMap = new HashMap<>(9);
        boxHashMap.put("L",new LBox());
        boxHashMap.put("O",new OBox());
        boxHashMap.put("V",new VBox());
        boxHashMap.put("E",new EBox());
    }

    public static BoxFactory getInstance(){
        return factory;
    }

    public Box getShapeByName(String name){
        return boxHashMap.get(name);
    }
}