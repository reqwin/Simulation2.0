package com.asalaschenko.simulation.ProcessingGraphic;

import com.asalaschenko.simulation.ProcessingSimulation.WorldMap;


public interface Renderer {

        void render(WorldMap map, int turnCounter, boolean stepOrder);


}
