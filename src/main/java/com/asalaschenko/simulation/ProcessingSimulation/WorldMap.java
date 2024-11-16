package com.asalaschenko.simulation.ProcessingSimulation;
import com.asalaschenko.simulation.ProcessingSimulation.Entities.CreatureElements.Herbivore.Herbivore;
import com.asalaschenko.simulation.ProcessingSimulation.Entities.CreatureElements.Predator.Predator;
import com.asalaschenko.simulation.ProcessingSimulation.Entities.Entity;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WorldMap {

        Map<Point, Entity> area = new HashMap<>();
        Point size;

        public WorldMap(Point size){
            this.size = size;
        }

        public Point getSize(){
                return size;
        }

        public void setEntity(Point point, Entity entity){
                area.put(point, entity);
        }

        public Entity getEntity(Point point){
                return area.get(point);
        }

        public void moveEntity(Point start, Point end){
                Entity e = area.get(start);
                area.remove(start);
                area.put(end, e);
        }

        public void removeEntity(Point point) {
                area.remove(point);
        }

        public boolean isEmptyPlace(Point point){
                return !area.containsKey(point);
        }

        public ArrayList<Point> getObjects(Class<? extends Entity> obj){
                ArrayList<Point> listOfPoint = new ArrayList<>();
                for(Map.Entry<Point, Entity> entry : area.entrySet()){
                        if(obj.isInstance(entry.getValue())){
                                listOfPoint.add(entry.getKey());
                        }
                }
                return listOfPoint;
        }

        public boolean isPredatorOrHerbivorePopulationDying(){
                ArrayList<Point> listPredator = getObjects(Predator.class);
                ArrayList<Point> listHerbivore = getObjects(Herbivore.class);
            return listPredator.isEmpty() || listHerbivore.isEmpty();
        }
}
