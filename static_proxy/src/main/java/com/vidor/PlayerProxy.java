package com.vidor;

public class PlayerProxy implements Play {

    private Player player;

    public PlayerProxy(Player player) {
        this.player = player;
    }

    public void play() {
        System.out.println("代理：" + player.getName() + "操作");
    }
}
