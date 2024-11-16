package com.asalaschenko.simulation.ProcessingSimulation.Actions.MoveActions;

import com.asalaschenko.simulation.ProcessingSimulation.Entities.CreatureElements.Herbivore.Herbivore;
import com.asalaschenko.simulation.ProcessingSimulation.Entities.CreatureElements.Predator.Predator;
import com.asalaschenko.simulation.ProcessingSimulation.WorldMap;
import java.awt.*;
import java.util.ArrayList;


public class PredatorMove extends MoveAction {

    public PredatorMove() {

    }

    @Override
    public void doAction(WorldMap map) {
        ArrayList<Point> list = map.getObjects(Predator.class);
        for(Point point : list){
            Predator predator = (Predator)map.getEntity(point);
            MessageResultOfMove messageResultOfMove = new MessageResultOfMove();
            predator.makeMove(point, map, messageResultOfMove);
            int decreaseHP = predator.getFatigue() * messageResultOfMove.getCountOfSteps();
            creatureChangeSizeHP(predator, decreaseHP, TypeOfLiveChange.DECREASE);
            if(messageResultOfMove.isAttack()){
                Herbivore herbivore = (Herbivore)map.getEntity(messageResultOfMove.getCoordinatesOfAttackedCreature());
                int currentHPOfAttackedCreature = herbivore.getHp();
                if(currentHPOfAttackedCreature <= predator.getAttackPower()){
                    map.removeEntity(messageResultOfMove.getCoordinatesOfAttackedCreature());
                    creatureChangeSizeHP(predator, currentHPOfAttackedCreature, TypeOfLiveChange.INCREASE);
                }else{
                    creatureChangeSizeHP(herbivore, predator.getAttackPower(), TypeOfLiveChange.DECREASE);
                    creatureChangeSizeHP(predator, predator.getAttackPower(), TypeOfLiveChange.INCREASE);
                }
            }
            int currentHPOfPredator = predator.getHp();
            if(currentHPOfPredator <= 0){
                map.removeEntity(messageResultOfMove.getNewPoint());
            }
            if(map.isPredatorOrHerbivorePopulationDying()){
                break;
            }
        }
    }

    public void doAction(WorldMap map, Class<? extends Predator> predatorClass) {     //перегруженный метод, если необходимо сделать ход
                                                                                      // каким-либо конкретным существом (травоядным)
        ArrayList<Point> list = map.getObjects(predatorClass);
        for(Point point : list){
            Predator predator = (Predator)map.getEntity(point);
            MessageResultOfMove messageResultOfMove = new MessageResultOfMove();
            predator.makeMove(point, map, messageResultOfMove);
            int decreaseHP = predator.getFatigue() * messageResultOfMove.getCountOfSteps();
            creatureChangeSizeHP(predator, decreaseHP, TypeOfLiveChange.DECREASE);
            if(messageResultOfMove.isAttack()){
                Herbivore herbivore = (Herbivore)map.getEntity(messageResultOfMove.getCoordinatesOfAttackedCreature());
                int currentHPOfAttackedCreature = herbivore.getHp();
                if(currentHPOfAttackedCreature <= predator.getAttackPower()){
                    map.removeEntity(messageResultOfMove.getCoordinatesOfAttackedCreature());
                    creatureChangeSizeHP(predator, currentHPOfAttackedCreature, TypeOfLiveChange.INCREASE);
                }else{
                    creatureChangeSizeHP(herbivore, predator.getAttackPower(), TypeOfLiveChange.DECREASE);
                    creatureChangeSizeHP(predator, predator.getAttackPower(), TypeOfLiveChange.INCREASE);
                }
            }
            int currentHPOfPredator = predator.getHp();
            if(currentHPOfPredator <= 0){
                map.removeEntity(messageResultOfMove.getNewPoint());
            }
            if(map.isPredatorOrHerbivorePopulationDying()){
                break;
            }
        }
    }
}
