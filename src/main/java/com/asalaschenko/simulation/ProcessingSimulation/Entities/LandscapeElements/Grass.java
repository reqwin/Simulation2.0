package com.asalaschenko.simulation.ProcessingSimulation.Entities.LandscapeElements;

import com.asalaschenko.simulation.ProcessingSimulation.Entities.PropertyLandscape;

public class Grass extends Plant {

        public Grass(){
            super(PropertyLandscape.GRASS.getHP(), PropertyLandscape.GRASS.getCode());
        }

}
