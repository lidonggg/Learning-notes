package com.lidong.java.base.initwithnew;

/**
 * Java new 父子关系初始化流程
 *
 * @author Ls J
 * @date 2020/6/6 11:14 PM
 */
public class InitTest {
    public static void main(String[] args) {
        // 1  4  2
        new Man("11");
        // 1  3
        new Man();
    }
}

class Person {

    String name;

    Person() {
        System.out.println(1);
    }

    Person(String name) {
        System.out.println(2);
        this.name = name;
    }
}

class Man extends Person {

    Person person;

    Man() {
        System.out.println(3);
    }

    Man(String name) {
        System.out.println(4);
        this.name = name;
        person = new Person(name);
    }
}

