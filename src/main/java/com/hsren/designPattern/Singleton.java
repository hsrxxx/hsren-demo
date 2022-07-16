package com.hsren.designPattern;

/**
 * 双重校验锁实现对象单例（线程安全）
 */
public class Singleton {

    private String name;

    private volatile static Singleton uniqueInstance;

    private Singleton() {}

    private Singleton(String name) {
        this.name = name;
    }

    public  static Singleton getUniqueInstance() {
        //先判断对象是否已经实例过，没有实例化过才进入加锁代码
        if (uniqueInstance == null) {
            //类对象加锁
            synchronized (Singleton.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new Singleton();
                }
            }
        }
        return uniqueInstance;
    }

    public  static Singleton getUniqueInstance(String name) {
       //先判断对象是否已经实例过，没有实例化过才进入加锁代码
        if (uniqueInstance == null) {
            //类对象加锁
            synchronized (Singleton.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new Singleton(name);
                }
            }
        }
        return uniqueInstance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}