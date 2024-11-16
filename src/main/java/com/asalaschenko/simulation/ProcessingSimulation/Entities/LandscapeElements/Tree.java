package com.asalaschenko.simulation.ProcessingSimulation.Entities.LandscapeElements;

import com.asalaschenko.simulation.ProcessingSimulation.Entities.PropertyLandscape;

public class Tree extends Plant {

    public Tree(){
        super(PropertyLandscape.TREE.getHP(), PropertyLandscape.TREE.getCode());
    }

}
