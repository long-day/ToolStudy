package com.longday.toolstudy.reflect;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 君
 * @version 1.0
 * @date 2022/9/2
 */
public class ReflectTest {

    private static final Person onePerson = new Person();
    private final Class<?> personClass = onePerson.getClass();

    @Test
    public void getClassDemo1(){
        Class<?> personClass = Person.class;
        System.out.println(personClass);
    }

    @Test
    public void getClassDemo2(){
        Person person = new Person();
        Class<? extends Person> aClass = person.getClass();
        System.out.println(aClass);
    }

    @Test
    public void getClassDemo3(){
        try {
            Class<?> aClass = Class.forName("com.longday.toolstudy.reflect.Person");
            System.out.println(aClass);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void getClassName(){
        System.out.println(personClass);

        System.out.println(personClass.getSimpleName());

        System.out.println(personClass.getName());
    }

    @Test
    public void getClassPublicConstructor(){

        try {
            System.out.println(personClass.getConstructor(String.class));
            // 只能拿到public限定的构造器
            System.out.println(Arrays.toString(personClass.getConstructors()));
            // 报错
            // System.out.println(personClass.getConstructor(String.class,String.class,String.class));

        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void getClassAllConstructor(){

        try {
            // 拿到了上一步未拿到的构造器
            System.out.println(personClass.getDeclaredConstructor(String.class,String.class,String.class));
            // 拿到了所有的构造器
            System.out.println(Arrays.toString(personClass.getDeclaredConstructors()));

        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void useConstructorsToCreatObject(){

        try {
            // 拿到构造器
            Constructor<?> constructor = personClass.getDeclaredConstructor(String.class, String.class, String.class);

            //打开构造器的权限 (一次性的)
            constructor.setAccessible(true);
            // 创建对象
            Object o = constructor.newInstance("root", "中国", "498765432110111213");
            System.out.println(o);

            System.out.println(constructor.newInstance("宝宝","地球","999999999999999999"));

        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void getClassFields(){
        Arrays.stream(personClass.getDeclaredFields()).forEach(System.out::println);
    }

    @Test
    public void getClassFieldsToGetValueAndSetValue(){
        try {
            Constructor<?> constructor = personClass.getConstructor();
            Person bb = (Person) constructor.newInstance();
            Field personNameField = personClass.getDeclaredField("name");
            personNameField.setAccessible(true);
            personNameField.set(bb,"宝宝");
//            System.out.println(bb);
            System.out.println(personNameField.get(bb));
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void getClassMethods(){
        Method[] personClassDeclaredMethods = personClass.getDeclaredMethods();
        for (Method method : personClassDeclaredMethods) {
            System.out.println(method.getName()+" 参数个数: "+method.getParameterCount()+" 方法返回值: " +method.getReturnType());
        }
    }

    @Test
    public void getClassMethodToRun(){
        Person p = new Person();

        try {
            Method setName = personClass.getMethod("setName", String.class);
            Object invoke = setName.invoke(p, "洛玉衡");
            System.out.println("调用方法的返回值: "+invoke);
            System.out.println("对象："+p);

        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void reflectDestruction(){
        //这个就比较有意思了,穿透泛型限制
        List<Person> personArrayList = new ArrayList<>();
        personArrayList.add(new Person("n1"));
        personArrayList.add(new Person("n2"));
        personArrayList.add(new Person("n3"));
        personArrayList.add(new Person("n4"));

        Class<?> personArrayListClass = personArrayList.getClass();
        try {
            Method add = personArrayListClass.getMethod("add", Object.class);
            add.invoke(personArrayList,"我穿透你了,笨比");
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
        for (Object o : personArrayList) {
            System.out.println(o);
        }
    }

}
