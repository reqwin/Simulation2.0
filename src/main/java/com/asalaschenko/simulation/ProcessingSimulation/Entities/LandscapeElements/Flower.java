package com.asalaschenko.simulation.ProcessingSimulation.Entities.LandscapeElements;

import com.asalaschenko.simulation.ProcessingSimulation.Entities.PropertyLandscape;

public class Flower extends Plant {
        public Flower() {
            super(PropertyLandscape.FLOWER.getHP(), PropertyLandscape.FLOWER.getCode());
        }
}
