package com.asalaschenko.simulation.ProcessingSimulation.Entities;

public abstract class Entity {

    private String sprite;

    public Entity(String sprite) {
        this.sprite = sprite;
    }

    public String getSprite() {
        return sprite;
    }

    public void setSprite(String sprite) {
        this.sprite = sprite;
    }


}

