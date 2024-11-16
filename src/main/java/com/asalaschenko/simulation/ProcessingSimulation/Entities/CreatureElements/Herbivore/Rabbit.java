package com.asalaschenko.simulation.ProcessingSimulation.Entities.CreatureElements.Herbivore;

import com.asalaschenko.simulation.ProcessingSimulation.Entities.PropertyCreatures;

public class Rabbit extends Herbivore {

    public Rabbit() {
        super(PropertyCreatures.RABBIT.getSpeed(), PropertyCreatures.RABBIT.getAttackPower(), PropertyCreatures.RABBIT.getHP(), PropertyCreatures.RABBIT.getCode());
    }
}
