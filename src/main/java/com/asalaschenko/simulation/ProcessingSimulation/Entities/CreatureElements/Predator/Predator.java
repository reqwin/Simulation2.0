package com.asalaschenko.simulation.ProcessingSimulation.Entities.CreatureElements.Predator;

import com.asalaschenko.simulation.ProcessingSimulation.Entities.CreatureElements.Creature;
import com.asalaschenko.simulation.ProcessingSimulation.Entities.CreatureElements.Herbivore.Herbivore;
import com.asalaschenko.simulation.ProcessingSimulation.FinderOfPath;
import com.asalaschenko.simulation.ProcessingSimulation.Actions.MoveActions.MessageResultOfMove;
import com.asalaschenko.simulation.ProcessingSimulation.WorldMap;

import java.awt.*;
import java.util.ArrayList;


public class Predator extends Creature {

    private int fatigue;

    public Predator(int speed, int attackPower, int fatigue, int hp, String sprite) {
        super(speed, attackPower, hp, sprite);
        this.fatigue = fatigue;
    }

    public int getFatigue() {
        return fatigue;
    }

    public void makeMove(Point point, WorldMap map, MessageResultOfMove messageResult) {
        FinderOfPath finderOfPath = new FinderOfPath(map);
        ArrayList<Point> listPoint = finderOfPath.findOfPath(point, Herbivore.class);
        int numberOfStepsToCreature = listPoint.size()-1;
        if(map.getEntity(listPoint.get(listPoint.size() - 1)) instanceof Herbivore) {
            if (numberOfStepsToCreature == 0) {
                writeMessageResult(messageResult, true, 0, null, listPoint.get(0));
            } else if (numberOfStepsToCreature > 0 && numberOfStepsToCreature <= this.getSpeed()) {
                writeMessageResult(messageResult, true, numberOfStepsToCreature, listPoint.get(numberOfStepsToCreature - 1), listPoint.get(numberOfStepsToCreature));
                map.moveEntity(point, listPoint.get(numberOfStepsToCreature - 1));
            } else {
                writeMessageResult(messageResult, false, this.getSpeed(), listPoint.get(this.getSpeed() - 1), null);
                map.moveEntity(point, listPoint.get(this.getSpeed() - 1));
            }
        }else {
            writeMessageResult(messageResult, false, listPoint.get(listPoint.size() - 1), null);
            map.moveEntity(point, listPoint.get(listPoint.size() - 1));
        }
    }
}
