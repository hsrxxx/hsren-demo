package com.hsren.demo;

public class Test {
    public static void main(String[] args) {
        String hashTarget = new String(new char[5]).replace('\0', '0');
        System.out.println(hashTarget);
    }
}
