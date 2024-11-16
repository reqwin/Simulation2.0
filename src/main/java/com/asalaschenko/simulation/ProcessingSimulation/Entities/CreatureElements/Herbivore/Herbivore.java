package com.asalaschenko.simulation.ProcessingSimulation.Entities.CreatureElements.Herbivore;

import com.asalaschenko.simulation.ProcessingSimulation.Entities.CreatureElements.Creature;
import com.asalaschenko.simulation.ProcessingSimulation.Entities.CreatureElements.Predator.Predator;
import com.asalaschenko.simulation.ProcessingSimulation.Entities.LandscapeElements.Plant;
import com.asalaschenko.simulation.ProcessingSimulation.FinderOfPath;
import com.asalaschenko.simulation.ProcessingSimulation.Actions.MoveActions.MessageResultOfMove;
import com.asalaschenko.simulation.ProcessingSimulation.WorldMap;
import java.awt.*;
import java.util.*;
import java.util.stream.Collectors;

public abstract class Herbivore extends Creature {

    public Herbivore(int speed, int attackPower, int hp, String sprite) {
        super(speed, attackPower, hp, sprite);
    }

    public void makeMove(Point point, WorldMap map, MessageResultOfMove messageResult) {
        FinderOfPath fop = new FinderOfPath(map);
        ArrayList<Point> listPoint = fop.findOfPath(point, Predator.class);
        if (map.getEntity(listPoint.getLast()) instanceof Predator && (listPoint.size() - 1) <= 5) {
                runAway(point, map, messageResult);
        }else {
            fop = new FinderOfPath(map);
            listPoint = fop.findOfPath(point, Plant.class);
            if (map.getEntity(listPoint.getLast()) instanceof Plant) {
                int numberOfStepsToPlant = listPoint.size() - 1;
                if (numberOfStepsToPlant == 0) {
                    writeMessageResult(messageResult, true, null, listPoint.getFirst());
                } else if (numberOfStepsToPlant > 0 && numberOfStepsToPlant <= this.getSpeed()) {
                    writeMessageResult(messageResult, true, listPoint.get(numberOfStepsToPlant - 1), listPoint.get(numberOfStepsToPlant));
                    map.moveEntity(point, listPoint.get(numberOfStepsToPlant - 1));
                } else {
                    writeMessageResult(messageResult, false, listPoint.get(getSpeed() - 1), null);
                    map.moveEntity(point, listPoint.get(getSpeed() - 1));
                }
            } else {
                writeMessageResult(messageResult, false, listPoint.getLast(), null);
                map.moveEntity(point, listPoint.getLast());
            }
        }
    }

    private void runAway(Point point, WorldMap map, MessageResultOfMove messageResult){

        ArrayList<Point> listOfAroundPoint = getAroundPoint(point, map);
        ArrayList<Point> listOfEmptyPlace = listOfAroundPoint.stream().filter(map::isEmptyPlace).collect(Collectors.toCollection(ArrayList::new));

        ArrayList<Point> listOfReachable = new ArrayList<>();
        for (Point valuePoint : listOfEmptyPlace) {
            FinderOfPath finderOfPathObject = new FinderOfPath(map);
            ArrayList<Point> listPoint = finderOfPathObject.findOfPath(point, valuePoint);
            if (!listPoint.isEmpty() && listPoint.size() <= this.getSpeed()) {
                listOfReachable.add(valuePoint);
            }
        }

        Map<Point, Integer> tablePointDistance = new HashMap<>();
        ArrayList<Point> listOfMaxDistancePoint = new ArrayList<>();
        Point pointToMove = null;

        if(!listOfReachable.isEmpty()) {
            fillTablePointDistance(map, listOfReachable, tablePointDistance);
            fillListOfMaxDistPoint(tablePointDistance, listOfMaxDistancePoint);
            if(listOfMaxDistancePoint.size()>1){
                pointToMove = getRandomPoint(listOfMaxDistancePoint);
            }else if(listOfMaxDistancePoint.size() == 1){
                pointToMove = listOfMaxDistancePoint.getFirst();
            }
        }

        if(pointToMove != null){
            map.moveEntity(point, pointToMove);
            messageResult.setNewPoint(pointToMove);
        }
    }

    private void fillListOfMaxDistPoint(Map<Point, Integer> tablePointDistance, ArrayList<Point> listOfMaxDistancePoint) {
        int maxValue;
        Map.Entry<Point, Integer> maxEntry = null;
        for (Map.Entry<Point, Integer> entry : tablePointDistance.entrySet()) {
            if (maxEntry == null || entry.getValue() > maxEntry.getValue()) {
                maxEntry = entry;
            }
        }

        maxValue = maxEntry.getValue();
        for (Map.Entry<Point, Integer> entry : tablePointDistance.entrySet()) {
            if (entry.getValue() == maxValue) {
                listOfMaxDistancePoint.add(entry.getKey());
            }
        }
    }

    private static void fillTablePointDistance(WorldMap map, ArrayList<Point> listOfReachableValid, Map<Point, Integer> tablePointDistance) {
        ArrayList<Point> listPoint;
        for(Point pointOfListRV : listOfReachableValid){
            ArrayList<Point> list = map.getObjects(Predator.class);
            int distance = 0;
            for(Point pointOfPredator : list){
                FinderOfPath finderOfPathObject = new FinderOfPath(map);
                listPoint = finderOfPathObject.findOfPath(pointOfListRV, pointOfPredator);
                distance += listPoint.size();  //суммируем пути до каждого оставшегося в живых хищника
            }
            tablePointDistance.put(pointOfListRV, distance);
        }
    }

    public ArrayList<Point> getAroundPoint(Point point, WorldMap map){

        ArrayList<Point> listOfAroundPoint = new ArrayList<>();

        for(int i = 1; i<=this.getSpeed(); i++){
                    listOfAroundPoint.add(new Point(point.x+i, point.y));
                    listOfAroundPoint.add(new Point(point.x-i, point.y));
                    listOfAroundPoint.add(new Point(point.x, point.y+i));
                    listOfAroundPoint.add(new Point(point.x, point.y-i));
        }

        for(int i=1; i<=this.getSpeed()-1; i++){
            for(int j = 1; j<=this.getSpeed()-i; j++){
                listOfAroundPoint.add(new Point(point.x+i, point.y+j));
                listOfAroundPoint.add(new Point(point.x+i, point.y-j));
                listOfAroundPoint.add(new Point(point.x-i, point.y+j));
                listOfAroundPoint.add(new Point(point.x-i, point.y-j));
            }
        }

        ArrayList<Point> listOfAroundPointValid = new ArrayList<>();
        for (Point valuePoint : listOfAroundPoint) {
            if (valuePoint.getX() >= 0 && valuePoint.getX() < map.getSize().getX() && valuePoint.getY() >= 0 && valuePoint.getY() < map.getSize().getY()) {
                listOfAroundPointValid.add(valuePoint);
            }
        }

        return listOfAroundPointValid;
    }

    private Point getRandomPoint(ArrayList<Point> listPoint){
        int index = new Random().nextInt(listPoint.size());
        return listPoint.get(index);
    }

}
