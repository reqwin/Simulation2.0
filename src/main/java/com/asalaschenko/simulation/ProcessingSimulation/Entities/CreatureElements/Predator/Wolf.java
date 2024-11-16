package com.asalaschenko.simulation.ProcessingSimulation.Entities.CreatureElements.Predator;

import com.asalaschenko.simulation.ProcessingSimulation.Entities.PropertyCreatures;

public class Wolf extends Predator {
    public Wolf() {super(PropertyCreatures.WOLF.getSpeed(), PropertyCreatures.WOLF.getAttackPower(), PropertyCreatures.WOLF.getFatigue(), PropertyCreatures.WOLF.getHP(), PropertyCreatures.WOLF.getCode());}
}
