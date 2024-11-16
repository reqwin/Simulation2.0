package com.asalaschenko.simulation.Controllers.Factory;

import com.asalaschenko.simulation.Controllers.*;
import java.lang.invoke.MethodHandles;

public class BasicControllersFactory extends ControllersFactory {
        public static Controller create(ControllerID controller) throws Exception{
                return switch(controller.getCode()){
                        case 0x01 -> new MainMenuController();
                        case 0x02 -> new BasicSettingsController();
                        case 0x03 -> new SimulationController();
                        case 0x07 -> new RestoreSettingsFileController();
                        case 0x08 -> new ReadmeTextController();
                        default -> throw new ControllersFactoryCreateException ("Call "
                                + Thread.currentThread().getStackTrace()[1].getMethodName()
                                + "() of "
                                + MethodHandles.lookup().lookupClass()
                                + "."
                                + " Code "
                                + controller.getCode()
                                + " not found. Please check ControllerID.code of "
                                + controller
                                + " !");
                };
        }
}
