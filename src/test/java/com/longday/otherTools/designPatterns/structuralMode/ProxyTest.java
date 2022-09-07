package com.longday.otherTools.designPatterns.structuralMode;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author 君
 * @version 1.0
 * @date 2022/9/7
 */
public class ProxyTest {

    /**
     * 静态代理
     */
    @Test
    public void staticProxy(){
        ProxyPoint proxyPoint = new ProxyPoint();
        proxyPoint.sell();
    }
    /**
     * Jdk动态代理
     */
    @Test
    public void dynamicProxyJdk(){
        JdkProxyFactory proxyFactory = new JdkProxyFactory();
        SellTickets proxyObject = proxyFactory.getProxyObject();
        proxyObject.sell();

    }

    /**
     * CGLib动态代理
     */
    @Test
    public void dynamicProxyCGLib(){
        CGLibProxyFactory cgLibProxyFactory = new CGLibProxyFactory();
        TrainStation cgLibProxyObject = cgLibProxyFactory.getCGLibProxyObject();
        cgLibProxyObject.sell();
    }

    @Test
    public void otherTest(){
        //关于静态内部类和内部类的创建问题
        OuterClass.StaticInnerClass staticInnerClass = new OuterClass.StaticInnerClass();
        staticInnerClass.StaticInnerClassMethod();

        OuterClass.CommonInnerClass commonInnerClass = new OuterClass().new CommonInnerClass();
        commonInnerClass.CommonInnerClassMethod();

    }

}

interface SellTickets{
    void sell();
}

class TrainStation implements SellTickets{

    /**
     * 火车站卖票
     */
    @Override
    public void sell() {
        System.out.println("火车站卖票！");
    }


}

/**
 * 静态代理   </br>  ------------分割------------
 */
class ProxyPoint implements SellTickets{

    private TrainStation trainStation = new TrainStation();
    /**
     * 代理点卖票
     */
    @Override
    public void sell() {
        System.out.println("代售点收取代理费2元");
        trainStation.sell();
    }
}

/**
 * Jdk动态代理   </br>  ------------分割------------
 */
class JdkProxyFactory{
    /**
     * 目标对象
     */
    private TrainStation trainStation = new TrainStation();

    /**
     * 获取代理对象
     * @return 代理对象集合
     */
    public SellTickets getProxyObject(){
        SellTickets proxyObject = (SellTickets)Proxy.newProxyInstance(
                trainStation.getClass().getClassLoader(),//类加载器，用于加载代理类，可通过目标对象获取
                trainStation.getClass().getInterfaces(), //代理类实现接口的字节码对象数组
                (proxy, method, args) -> {  //Lambda 形式的 InvocationHandler实现类的匿名对象
                    // proxy 代理对象 和proxyObject是同一个对象
//                    System.out.println("proxy: "+proxy.getClass());
                    System.out.println("代理方法执行,收取代理费2元(JDK)");
                    return method.invoke(trainStation, args);
                });
//        System.out.println("proxyObject: "+proxyObject);
        return proxyObject;

    }
}


/**
 * CGLib动态代理   </br>  ------------分割------------
 */
class CGLibProxyFactory implements MethodInterceptor {
    private TrainStation station = new TrainStation();
    public TrainStation getCGLibProxyObject(){
        //Enhancer类 相当于Jdk的Proxy类
        Enhancer enhancer = new Enhancer();
        //设置父类的字节码对象
        enhancer.setSuperclass(TrainStation.class);

        //设置回调函数
        enhancer.setCallback(this);

        //创建代理对象
        TrainStation proxyObject = (TrainStation) enhancer.create();
        return proxyObject;
    }

    /**
     * @param o 代理对象
     * @param method 方法反射
     * @param objects 方法参数
     * @param methodProxy 方法代理？？
     * @return 所代理的方法的返回值
     * @throws Throwable 异常
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("代售点收取2元(CGLib)");
        Object invoke = method.invoke(station, objects);
        return invoke;
    }
}

/**
 * 静态内部类和内部类  </br>  ------------分割------------
 */
class OuterClass{
    public static class StaticInnerClass{
        public void StaticInnerClassMethod(){
            System.out.println("StaticInnerClassMethod()");
        }
    }
    public class CommonInnerClass{
        public void CommonInnerClassMethod(){
            System.out.println("CommonInnerClassMethod()");
        }
    }
}