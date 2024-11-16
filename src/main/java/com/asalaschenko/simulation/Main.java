package com.asalaschenko.simulation;

import com.asalaschenko.simulation.Controllers.Controller;
import com.asalaschenko.simulation.Controllers.Factory.BasicControllersFactory;
import com.asalaschenko.simulation.Controllers.Factory.ControllerID;


public class Main {

    public static void main(String[] args) throws Exception {

        Controller controller = BasicControllersFactory.create(ControllerID.MainMenu);
        controller.start();

    }
}
