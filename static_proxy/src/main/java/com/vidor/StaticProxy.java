package com.vidor;

public class StaticProxy {
    public static void main(String[] args) {
        Player player = new Player("初级玩家");
        player.play();

        Play play = new PlayerProxy(new Player("初级玩家"));
        play.play();
    }
}
