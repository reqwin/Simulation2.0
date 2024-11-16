package com.asalaschenko.simulation.ProcessingSimulation.Actions.MoveActions;

import java.awt.*;

public class MessageResultOfMove {

    private boolean isAttack = false;
    private Point coordinatesOfAttackedCreature;
    private Point newPoint;
    private int countOfSteps;

    public MessageResultOfMove() {

    }

    public Point getNewPoint() {
        return newPoint;
    }

    public void setNewPoint(Point newPoint) {
        this.newPoint = newPoint;
    }

    public boolean isAttack() {
        return isAttack;
    }

    public void setAttack(boolean attack) {
        isAttack = attack;
    }

    public void setCountOfSteps(int countOfSteps) {
        this.countOfSteps = countOfSteps;
    }

    public int getCountOfSteps() {
        return countOfSteps;
    }

    public Point getCoordinatesOfAttackedCreature() {
        return coordinatesOfAttackedCreature;
    }

    public void setCoordinatesOfAttackedCreature(Point coordinatesOfAttackedCreature) {
        this.coordinatesOfAttackedCreature = coordinatesOfAttackedCreature;
    }
}
