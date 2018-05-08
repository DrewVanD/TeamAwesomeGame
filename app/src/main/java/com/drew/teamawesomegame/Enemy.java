package com.drew.teamawesomegame;

public class Enemy {

    String enemyName;
    int enemyNumber;
    public static int health;
    int damage;
    int coinReward;
    int expReward;
    int timeBetweenSwings;
    public static int hurt;
    public static int facenum;



<<<<<<< HEAD
<<<<<<< HEAD
    public Enemy(String enemyName, int health, int damage, int coinReward, int expReward, int timeBetweenSwings, int hurt, int facenum) {
=======
=======
>>>>>>> 8f6237d776fd3325ed10e397297719bc42ca05ca

    public Enemy(String enemyName, int health, int damage, int coinReward, int expReward, int timeBetweenSwings, int hurt) {
>>>>>>> 8f6237d776fd3325ed10e397297719bc42ca05ca
        this.enemyName = enemyName;
        this.health = health;
        this.damage = damage;
        this.coinReward = coinReward;
        this.expReward = expReward;
        this.timeBetweenSwings = timeBetweenSwings;
        this.hurt = hurt;
        this.facenum = facenum;
    }
}
