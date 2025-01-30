package com.workintech.library.model;

public abstract class Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public Person() {

    }

    public String getName() {
        return name;
    }

    public abstract String whoYouAre();
}
