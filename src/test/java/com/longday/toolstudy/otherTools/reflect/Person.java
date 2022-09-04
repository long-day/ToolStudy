package com.longday.toolstudy.reflect;

/**
 * @author 君
 * @version 1.0
 * @date 2022/9/2
 */
public class Person {
    private String name;
    private String address;
    private String IDCard;

    public Person() {
    }

    private Person(String name, String address, String IDCard) {
        this.name = name;
        this.address = address;
        this.IDCard = IDCard;
    }

    public Person(String name) {
        this(name,null,null);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIDCard() {
        return IDCard;
    }

    public void setIDCard(String IDCard) {
        this.IDCard = IDCard;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", IDCard='" + IDCard + '\'' +
                '}';
    }
}
