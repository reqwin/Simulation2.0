package com.asalaschenko.simulation.ProcessingSimulation.Entities.LandscapeElements;

public class Plant extends Landscape {

    private int hp;

    public Plant(int hp, String sprite) {
        super(sprite);
        this.hp = hp;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }


}
