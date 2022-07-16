package com.hsren.designPattern;

public class SingletonTest {
    public static void main(String[] args) {
        Singleton singleton = Singleton.getUniqueInstance("张三");
        System.out.println(singleton.getName());
        singleton.setName("李四");
        Singleton singleton1 = Singleton.getUniqueInstance();
        System.out.println(singleton1.getName());
    }
}