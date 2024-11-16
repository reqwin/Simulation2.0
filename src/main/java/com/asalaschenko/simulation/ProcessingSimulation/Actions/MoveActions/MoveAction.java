package com.asalaschenko.simulation.ProcessingSimulation.Actions.MoveActions;

import com.asalaschenko.simulation.ProcessingSimulation.Actions.Action;
import com.asalaschenko.simulation.ProcessingSimulation.Entities.CreatureElements.Creature;
import com.asalaschenko.simulation.ProcessingSimulation.Entities.LandscapeElements.Plant;

public abstract class MoveAction implements Action {

    protected enum TypeOfLiveChange{
        INCREASE,
        DECREASE
    }

    protected void plantDecreaseHP(Plant plant, int decreaseSizeOfHP){
        int currentHPOfAttackedPlant = plant.getHp();
        currentHPOfAttackedPlant -= decreaseSizeOfHP;
        plant.setHp(currentHPOfAttackedPlant);
    }

    protected void creatureChangeSizeHP(Creature creature, int sizeHP, TypeOfLiveChange tlc){
        int currentHPOfCreature = creature.getHp();
        if(tlc.equals(TypeOfLiveChange.DECREASE)){
            currentHPOfCreature -= sizeHP;
            creature.setHp(currentHPOfCreature);
        }else if(tlc.equals(TypeOfLiveChange.INCREASE)){
            currentHPOfCreature += sizeHP;
            creature.setHp(Math.min(currentHPOfCreature, 100));
        }
    }

}
