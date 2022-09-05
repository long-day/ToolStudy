package com.longday.toolstudy.otherTools.designPatterns.principles;

/**
 * @author 君
 * @version 1.0
 * @date 2022/9/4
 */
public class InterfaceSegregationTest {
    //接口隔离原则(Interface Segregation Principle)
    /*
        客户端不应依赖它不需要的接口  且  类间的依赖关系应该建立在最小的接口上
        也就是说，实体类应在需要一个接口中全部方法时才去实现该接口，如果某个方法时期不需要的那么要么此接口设计
        不合理(拆分力度不足,有功能差距较大的方法),要么该类不应当依赖此接口(应当依赖一个新接口)。
    */
//  此原则较为容易理解，不再进行举例。

//    小结: 不要在一个接口中放置多个方法,这样会显得臃肿,不易维护.
}
