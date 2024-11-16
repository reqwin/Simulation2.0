package com.asalaschenko.simulation.ProcessingSimulation.Entities.CreatureElements;

import com.asalaschenko.simulation.ProcessingSimulation.Entities.Entity;
import com.asalaschenko.simulation.ProcessingSimulation.Actions.MoveActions.MessageResultOfMove;

import java.awt.*;


public abstract class Creature extends Entity {

    private int speed;
    private int hp;
    private int attackPower;

    public Creature(int speed, int attackPower, int hp, String sprite) {
        super(sprite);
        this.speed = speed;
        this.hp = hp;
        this.attackPower = attackPower;
    }

    protected void writeMessageResult(MessageResultOfMove messageResult, boolean Attack, int countOfSteps, Point newPoint, Point coordinatesOfAttackedCreature){   //for Predators
        messageResult.setAttack(Attack);
        messageResult.setCountOfSteps(countOfSteps);
        messageResult.setNewPoint(newPoint);
        messageResult.setCoordinatesOfAttackedCreature(coordinatesOfAttackedCreature);
    }

    protected void writeMessageResult(MessageResultOfMove messageResult, boolean Attack, Point newPoint, Point coordinatesOfAttackedCreature){   //for Herbivores
        messageResult.setAttack(Attack);
        messageResult.setNewPoint(newPoint);
        messageResult.setCoordinatesOfAttackedCreature(coordinatesOfAttackedCreature);
    }

    public int getSpeed() {
        return speed;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

}
