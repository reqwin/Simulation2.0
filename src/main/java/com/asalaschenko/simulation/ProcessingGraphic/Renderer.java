package com.asalaschenko.simulation.ProcessingGraphic;

import com.asalaschenko.simulation.ProcessingSimulation.WorldMap;

import java.awt.*;
import java.util.ArrayList;

public interface Renderer {

        void render(WorldMap map, int turnCounter, boolean stepOrder);


}
