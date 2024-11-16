package com.asalaschenko.simulation.ProcessingSimulation.Entities;

import com.asalaschenko.simulation.ProcessingSimulation.Entities.CreatureElements.Herbivore.Chicken;
import com.asalaschenko.simulation.ProcessingSimulation.Entities.CreatureElements.Herbivore.Herbivore;
import com.asalaschenko.simulation.ProcessingSimulation.Entities.CreatureElements.Herbivore.Rabbit;
import com.asalaschenko.simulation.ProcessingSimulation.Entities.CreatureElements.Herbivore.Rodent;
import com.asalaschenko.simulation.ProcessingSimulation.Entities.CreatureElements.Predator.Eagle;
import com.asalaschenko.simulation.ProcessingSimulation.Entities.CreatureElements.Predator.Lion;
import com.asalaschenko.simulation.ProcessingSimulation.Entities.CreatureElements.Predator.Predator;
import com.asalaschenko.simulation.ProcessingSimulation.Entities.CreatureElements.Predator.Wolf;

public enum PropertyCreatures {

    EAGLE("\uD83E\uDD85", 3, 2, 2, 30, Eagle.class),
    LION("\uD83E\uDD81", 2, 6, 1, 30, Lion.class),
    WOLF("\uD83D\uDC3A", 1, 4, 3, 30, Wolf.class),
    RODENT("\uD83D\uDC39", 2, 6, 30, Rodent.class),
    RABBIT("\uD83D\uDC07", 3, 2, 30, Rabbit.class),
    CHICKEN("\uD83D\uDC24", 1, 4, 30, Chicken.class);

    private String code;

    private int HP;

    private int attackPower;

    private int speed;

    private int fatigue;

    private Class<? extends Predator>  predatorClass;

    private Class<? extends Herbivore>  herbivoreClass;


    PropertyCreatures(String code, int speed, int attackPower , int hp, Class<? extends Herbivore>  herbivoreClass){
        this.code = code;
        this.HP = hp;
        this.attackPower = attackPower;
        this.speed = speed;
        this.herbivoreClass = herbivoreClass;
    }

    PropertyCreatures(String code, int speed, int attackPower, int fatigue, int hp, Class<? extends Predator>  predatorClass){
        this.code = code;
        this.HP = hp;
        this.attackPower = attackPower;
        this.speed = speed;
        this.fatigue = fatigue;
        this.predatorClass = predatorClass;
    }

    public String getCode(){
        return code;
    }

    public int getSpeed() {
        return speed;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public int getFatigue() {
        return fatigue;
    }

    public int getHP(){
        return HP;
    }

    public Class<? extends Predator> getPredatorClass() {
        return predatorClass;
    }

    public Class<? extends Herbivore> getHerbivoreClass() {
        return herbivoreClass;
    }

}
