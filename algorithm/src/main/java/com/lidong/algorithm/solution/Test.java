package com.lidong.algorithm.solution;

/**
 * @author Ls J
 * @date 2020/6/6 11:14 PM
 */
public class Test {
    public static void main(String[] args) {
        new Man("11");
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

class Man  extends Person{

    Person person;

    Man(){
        System.out.println(3);
    }

    Man(String name) {
        System.out.println(4);
        this.name = name;
        person = new Person();
    }
}

