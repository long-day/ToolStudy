package com.longday.toolstudy.otherTools.designPatterns.principles;

/**
 * @author 君
 * @version 1.0
 * @date 2022/9/4
 */
public class LawOfDemeterTest {
    // 迪米特法则(Law of Demeter)
    /*      也叫最少知识原则。迪米特法则的定义是只与你的直接朋友交谈，不与"陌生人"说话。如果两个软件实体无须
        直接通信，那么就不应当发生直接的相互调用，可以通过第三方转发该应用。其目的是降低类之间的耦合度，提高模
        块的相对独立性
    */
    //    示例
        //所有的*Factory类几乎都符合此原则。例如: ThreadFactory 和 BeanFactory等
    //    小结
    /*
       1、在类的划分上，应该创建弱耦合的类。类与类之间的耦合越弱，就越有利于实现可复用的目标。
    　　2、在类的结构设计上，尽量降低类成员的访问权限。
    　　3、在类的设计上，优先考虑将一个类设置成不变类。
    　　4、在对其他类的引用上，将引用其他对象的次数降到最低。
    　　5、不暴露类的属性成员，而应该提供相应的访问器（set 和 get 方法）。
    　　6、谨慎使用序列化（Serializable）功能。
    */
}