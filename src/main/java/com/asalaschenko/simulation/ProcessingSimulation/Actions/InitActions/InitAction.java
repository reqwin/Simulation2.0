package com.asalaschenko.simulation.ProcessingSimulation.Actions.InitActions;

import com.asalaschenko.simulation.ProcessingSimulation.Actions.Action;

import java.awt.*;
import java.util.Random;

public abstract class InitAction implements Action {

        public Point getRandomPoint(Point size) {
                int x = new Random().nextInt(size.x);
                int y = new Random().nextInt(size.y);
                return new Point(x, y);
        }

}
