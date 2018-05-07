package com.drew.teamawesomegame;

public class Enemy {

    String enemyName;
    int health;
    int damage;
    int coinReward;
    int expReward;
    int timeBetweenSwings;



    public Enemy(String enemyName, int health, int damage, int coinReward, int expReward, int timeBetweenSwings) {
        this.enemyName = enemyName;
        this.health = health;
        this.damage = damage;
        this.coinReward = coinReward;
        this.expReward = expReward;
        this.timeBetweenSwings = timeBetweenSwings;
    }
}
