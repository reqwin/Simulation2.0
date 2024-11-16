package com.asalaschenko.simulation.ProcessingSimulation.Entities.CreatureElements.Herbivore;

import com.asalaschenko.simulation.ProcessingSimulation.Entities.PropertyCreatures;

public class Rodent extends Herbivore {

    public Rodent() {
        super(PropertyCreatures.RODENT.getSpeed(), PropertyCreatures.RODENT.getAttackPower(), PropertyCreatures.RODENT.getHP(), PropertyCreatures.RODENT.getCode());
    }
}
