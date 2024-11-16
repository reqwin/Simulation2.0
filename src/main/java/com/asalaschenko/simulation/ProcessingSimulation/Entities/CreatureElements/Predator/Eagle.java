package com.asalaschenko.simulation.ProcessingSimulation.Entities.CreatureElements.Predator;

import com.asalaschenko.simulation.ProcessingSimulation.Entities.PropertyCreatures;

public class Eagle extends Predator {
    public Eagle() {super(PropertyCreatures.EAGLE.getSpeed(), PropertyCreatures.EAGLE.getAttackPower(), PropertyCreatures.EAGLE.getFatigue(), PropertyCreatures.EAGLE.getHP(), PropertyCreatures.EAGLE.getCode());}
}
