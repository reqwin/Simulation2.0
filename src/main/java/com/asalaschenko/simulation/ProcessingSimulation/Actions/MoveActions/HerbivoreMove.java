package com.asalaschenko.simulation.ProcessingSimulation.Actions.MoveActions;

import com.asalaschenko.simulation.ProcessingSimulation.Actions.Action;
import com.asalaschenko.simulation.ProcessingSimulation.Actions.InitActions.LandscapeInit;
import com.asalaschenko.simulation.ProcessingSimulation.Entities.CreatureElements.Herbivore.Herbivore;
import com.asalaschenko.simulation.ProcessingSimulation.Entities.LandscapeElements.Plant;
import com.asalaschenko.simulation.ProcessingSimulation.WorldMap;

import java.awt.*;
import java.util.ArrayList;

public class HerbivoreMove extends MoveAction {

    public HerbivoreMove() {

    }

    public void doAction(WorldMap map) throws Exception {

        ArrayList<Point> pointsOfPlants = map.getObjects(Plant.class);
        if(pointsOfPlants.size()==0){
            Action initLand = new LandscapeInit();
            initLand.doAction(map);
        }

        ArrayList<Point> list = map.getObjects(Herbivore.class);
        for (Point point : list) {
            Herbivore creature = (Herbivore) map.getEntity(point);
            MessageResultOfMove messageResultOfMove = new MessageResultOfMove();
            creature.makeMove(point, map, messageResultOfMove);
            if (messageResultOfMove.isAttack()) {
                Plant plant = (Plant) map.getEntity(messageResultOfMove.getCoordinatesOfAttackedCreature());
                int currentHPOfAttackedPlant = plant.getHp();
                if (currentHPOfAttackedPlant <= creature.getAttackPower()) {
                    map.removeEntity(messageResultOfMove.getCoordinatesOfAttackedCreature());
                    creatureChangeSizeHP(creature, currentHPOfAttackedPlant, TypeOfLiveChange.INCREASE);
                } else {
                    plantDecreaseHP(plant, creature.getAttackPower());
                    creatureChangeSizeHP(creature, creature.getAttackPower(), TypeOfLiveChange.INCREASE);
                }
            }
        }
    }

    public void doAction(WorldMap map, Class<? extends Herbivore> creatureClass) throws Exception {   //перегруженный метод, если необходимо сделать ход
                                                                                                     //каким-либо конкретным существом (травоядным)
        ArrayList<Point> pointsOfPlants = map.getObjects(Plant.class);
        if(pointsOfPlants.size()==0){
            Action initLand = new LandscapeInit();
            initLand.doAction(map);
        }

        ArrayList<Point> list = map.getObjects(creatureClass);
        for (Point point : list) {
            Herbivore creature = (Herbivore) map.getEntity(point);
            MessageResultOfMove messageResultOfMove = new MessageResultOfMove();
            creature.makeMove(point, map, messageResultOfMove);
            if (messageResultOfMove.isAttack()) {
                Plant plant = (Plant) map.getEntity(messageResultOfMove.getCoordinatesOfAttackedCreature());
                int currentHPOfAttackedPlant = plant.getHp();
                if (currentHPOfAttackedPlant <= creature.getAttackPower()) {
                    map.removeEntity(messageResultOfMove.getCoordinatesOfAttackedCreature());
                    creatureChangeSizeHP(creature, currentHPOfAttackedPlant, TypeOfLiveChange.INCREASE);
                } else {
                    plantDecreaseHP(plant, creature.getAttackPower());
                    creatureChangeSizeHP(creature, creature.getAttackPower(), TypeOfLiveChange.INCREASE);
                }
            }
        }
    }

}
