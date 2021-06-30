package com.combat;

public abstract class SotfCharacters {

    // variables that will apply to both Jemad(The main character) and his Enemies(Will be different types)
    // Variables for max and current HP
    public String name;
    public int characterMaxHP, characterCurrentHp;

    // Constructor for Jemad and enemies
    public SotfCharacters(String name, int maxHP, int currentHp){
        this.name = name;
        this.characterMaxHP = maxHP;
        this.characterCurrentHp = currentHp;
    }

    // abstract methods for attacks
    public abstract int attack();

    // abstract methods for defense
    public abstract int defense();


}