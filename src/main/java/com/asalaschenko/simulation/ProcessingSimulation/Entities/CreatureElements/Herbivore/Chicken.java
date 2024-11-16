package com.asalaschenko.simulation.ProcessingSimulation.Entities.CreatureElements.Herbivore;

import com.asalaschenko.simulation.ProcessingSimulation.Entities.PropertyCreatures;

public class Chicken extends Herbivore {

    public Chicken() {

        super(PropertyCreatures.CHICKEN.getSpeed(), PropertyCreatures.CHICKEN.getAttackPower(), PropertyCreatures.CHICKEN.getHP(), PropertyCreatures.CHICKEN.getCode());
    }

}
