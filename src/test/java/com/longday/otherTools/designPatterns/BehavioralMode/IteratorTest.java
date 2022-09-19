package com.longday.otherTools.designPatterns.BehavioralMode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 君
 * @version 1.0
 * @desc 迭代器模式
 * @date 2022/9/16
 */
public class IteratorTest {
    /**
     * 优点:
     *      1.它支持以不同的方式遍历一个聚合对象，在同一一个聚合对象上可以定义多种遍历方式。在迭代器模式中只需要用一个
     *      不同的迭代器来替换原有迭代器即可改变遍历算法，我们也可以自己定义迭代器的子类以支持新的遍历方式。
     *      2.迭代器简化了聚合类。由于引入了迭代器，在原有的聚合对象中不需要再自行提供数据遍历等方法,
     *          这样可以简化聚合类的设计。
 *          3.在迭代器模式中，由于引入了抽象层，增加新的聚合类和迭代器类都很方便,无须修改原有代码，满足“开闭原则”的要求
     * 缺点:
     *      一定程度上增加了系统的复杂性
     */
    @Test
    public void iterator(){
        StudentAggregateImpl studentAggregate = new StudentAggregateImpl();
        Student yunYun = new Student("云韵", 1);
        Student yanLingJI = new Student("焰灵姬", 2);
        Student luoYuHeng = new Student("洛玉衡", 3);
        Student zLi = new Student("钟离", 1);
        studentAggregate.addStudent(yunYun);
        studentAggregate.addStudent(yanLingJI);
        studentAggregate.addStudent(luoYuHeng);
        studentAggregate.addStudent(zLi);

        StudentIterator iterator = studentAggregate.getStudentIterator();

        while (iterator.hasNext()){
            System.out.println(iterator.getNext());
        }
    }
}

@NoArgsConstructor
@AllArgsConstructor
@Data
class Student{
    private String name;
    private Integer stuID;
}

interface StudentIterator{
    boolean hasNext();

    Student getNext();
}


class StudentIteratorImpl implements StudentIterator{
    private List<Student> studentList;
    private int curIndex;
    public StudentIteratorImpl(List<Student> list){
        this.studentList = list;
    }
    @Override
    public boolean hasNext() {
        return curIndex < studentList.size();
    }

    @Override
    public Student getNext() {
        Student student = studentList.get(curIndex);
        curIndex++;
        return student;
    }
}

interface StudentAggregate{
    void addStudent(Student student);
    void removeStudent(Student student);
    StudentIterator getStudentIterator();
}

class StudentAggregateImpl implements StudentAggregate{

    private List<Student> list = new ArrayList<>(10);

    @Override
    public void addStudent(Student student) {
        list.add(student);
    }

    @Override
    public void removeStudent(Student student) {
        list.remove(student);
    }

    @Override
    public StudentIterator getStudentIterator() {
        return new StudentIteratorImpl(this.list);
    }
}