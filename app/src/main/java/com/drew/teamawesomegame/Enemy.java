package com.drew.teamawesomegame;

public class Enemy {

    String enemyName;
    //int enemyNumber;
    public static int health;
    public static int maxHealth;
    int damage;
    int coinReward;
    int expReward;
    int timeBetweenSwings;
    public static int hurt;
    public static int facenum;


    public Enemy(String enemyName, int health, int maxHealth, int damage, int coinReward, int expReward, int timeBetweenSwings, int hurt, int facenum) {

        this.enemyName = enemyName;
        this.health = health;
        this.maxHealth = maxHealth;
        this.damage = damage;
        this.coinReward = coinReward;
        this.expReward = expReward;
        this.timeBetweenSwings = timeBetweenSwings;
        this.hurt = hurt;
        this.facenum = facenum;
    }
}
