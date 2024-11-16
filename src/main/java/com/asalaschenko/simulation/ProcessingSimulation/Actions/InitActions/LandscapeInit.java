package com.asalaschenko.simulation.ProcessingSimulation.Actions.InitActions;

import com.asalaschenko.simulation.ProcessingSimulation.Entities.Entity;
import com.asalaschenko.simulation.ProcessingSimulation.Entities.LandscapeElements.Landscape;
import com.asalaschenko.simulation.ProcessingSimulation.Entities.PropertyLandscape;
import com.asalaschenko.simulation.ProcessingSimulation.WorldMap;

import java.awt.*;

public class LandscapeInit extends InitAction {

    public LandscapeInit() {

    }

    public void doAction(WorldMap map) throws InstantiationException, IllegalAccessException {
        for(PropertyLandscape elementOfLandscape : PropertyLandscape.values()){
            Entity entity = elementOfLandscape.getLandscapeClass().newInstance();
            Class<? extends Landscape> entityClass = elementOfLandscape.getLandscapeClass();
            int size = map.getSize().x * map.getSize().y;
            boolean loopManage = true;
            while(loopManage) {
                if((double) map.getObjects(entityClass).size()/size >= elementOfLandscape.getSpawnRate()){
                    loopManage = false;
                }
                Point point = getRandomPoint(map.getSize());
                if (map.isEmptyPlace(point)) {
                    map.setEntity(point, entity);
                }
            }
        }
    }

}
