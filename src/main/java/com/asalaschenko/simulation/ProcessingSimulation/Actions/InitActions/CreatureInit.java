package com.asalaschenko.simulation.ProcessingSimulation.Actions.InitActions;

import com.asalaschenko.simulation.ProcessingSimulation.Entities.CreatureElements.Creature;
import com.asalaschenko.simulation.ProcessingSimulation.WorldMap;

import java.awt.*;
import java.util.List;

public class CreatureInit extends InitAction {

    List<Creature> listCreatures;

    public CreatureInit(List<Creature> listCreatures) {
        this.listCreatures = listCreatures;
    }

    public void doAction(WorldMap map) {
        for(Creature creature : listCreatures){
            boolean loopManage = true;
            while(loopManage){
                Point point = getRandomPoint(map.getSize());
                if(map.isEmptyPlace(point)){
                    map.setEntity(point, creature);
                    loopManage = false;
                }
            }
        }
    }

}
