package com.asalaschenko.simulation.ProcessingSimulation.Actions;

import com.asalaschenko.simulation.ProcessingSimulation.Entities.CreatureElements.Creature;
import com.asalaschenko.simulation.ProcessingSimulation.WorldMap;

import java.util.List;

public interface Action {

        void doAction(WorldMap map) throws Exception ;

}
