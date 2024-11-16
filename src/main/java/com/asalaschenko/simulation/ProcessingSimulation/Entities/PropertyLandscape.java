package com.asalaschenko.simulation.ProcessingSimulation.Entities;

import com.asalaschenko.simulation.ProcessingSimulation.Entities.LandscapeElements.*;

public enum PropertyLandscape {

    ROCK("⛰️", 0.075 , Rock.class),
    GRASS("\uD83C\uDF40", 5, 0.05, Grass.class),
    FLOWER("\uD83C\uDF3A", 10, 0.05 , Flower.class),
    TREE("☘️", 20, 0.025, Tree.class);

    private double spawnRate;
    private String code;
    private int HP;
    private Class<? extends Landscape>  landscapeClass;

    PropertyLandscape(String code, int HP,double spawnRate, Class<? extends Landscape> landscapeClass) {
        this.spawnRate = spawnRate;
        this.landscapeClass = landscapeClass;
        this.code = code;
        this.HP = HP;
    }

    PropertyLandscape(String code, double spawnRate, Class<? extends Landscape> landscapeClass){
        this.spawnRate = spawnRate;
        this.landscapeClass = landscapeClass;
        this.code = code;
    }

    public double getSpawnRate() {
        return spawnRate;
    }

    public int getHP(){
        return HP;
    }

    public String getCode(){
        return code;
    }

    public Class<? extends Landscape> getLandscapeClass() {
        return landscapeClass;
    }

}
