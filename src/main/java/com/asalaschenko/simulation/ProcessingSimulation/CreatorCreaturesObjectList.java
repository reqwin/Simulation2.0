package com.asalaschenko.simulation.ProcessingSimulation;

import com.asalaschenko.simulation.ProcessingSettingsFile.CreaturesModelXML;
import com.asalaschenko.simulation.ProcessingSimulation.Entities.CreatureElements.Creature;
import com.asalaschenko.simulation.ProcessingSimulation.Entities.CreatureElements.Herbivore.Chicken;
import com.asalaschenko.simulation.ProcessingSimulation.Entities.CreatureElements.Herbivore.Rabbit;
import com.asalaschenko.simulation.ProcessingSimulation.Entities.CreatureElements.Herbivore.Rodent;
import com.asalaschenko.simulation.ProcessingSimulation.Entities.CreatureElements.Predator.Eagle;
import com.asalaschenko.simulation.ProcessingSimulation.Entities.CreatureElements.Predator.Lion;
import com.asalaschenko.simulation.ProcessingSimulation.Entities.CreatureElements.Predator.Wolf;

import java.util.ArrayList;
import java.util.List;


public class CreatorCreaturesObjectList {

    public CreatorCreaturesObjectList(){

    }

    public static List<Creature> getCreaturesList(CreaturesModelXML model){
        List<Creature> creaturesList = new ArrayList<>();
        if(model.getHerbivores().getCHICKEN()){
            creaturesList.add(new Chicken());
        }
        if(model.getHerbivores().getRABBIT()){
            creaturesList.add(new Rabbit());
        }
        if(model.getHerbivores().getRODENT()){
            creaturesList.add(new Rodent());
        }
        if(model.getPredators().getLION()){
            creaturesList.add(new Lion());
        }
        if(model.getPredators().getWOLF()){
            creaturesList.add(new Wolf());
        }
        if(model.getPredators().getEAGLE()){
            creaturesList.add(new Eagle());
        }
        return creaturesList;
    }
}
