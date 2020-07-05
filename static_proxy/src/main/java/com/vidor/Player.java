package com.vidor;

public class Player implements Play {
    private String name;

    public Player(String name) {
        System.out.println(name + "上线");
        this.name = name;
    }

    public void play() {
        System.out.println(name + "操作");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
