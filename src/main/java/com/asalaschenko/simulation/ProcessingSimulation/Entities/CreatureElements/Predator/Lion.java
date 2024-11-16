package com.asalaschenko.simulation.ProcessingSimulation.Entities.CreatureElements.Predator;

import com.asalaschenko.simulation.ProcessingSimulation.Entities.PropertyCreatures;

public class Lion extends Predator {
        public Lion() {
            super(PropertyCreatures.LION.getSpeed(), PropertyCreatures.LION.getAttackPower(), PropertyCreatures.LION.getFatigue(), PropertyCreatures.LION.getHP(), PropertyCreatures.LION.getCode());
        }
}
