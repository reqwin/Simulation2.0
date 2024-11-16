package com.asalaschenko.simulation.Controllers.Factory;

import com.asalaschenko.simulation.Controllers.*;

import java.lang.invoke.MethodHandles;

public class SettingsControllersFactory extends ControllersFactory {

    public static BasicSettingsController create(ControllerID controller) throws Exception{
        return switch(controller.getCode()){
            case 0x04 -> new CreatureSettingsController();
            case 0x05 -> new SizeSettingsController();
            case 0x06 -> new GroundSpriteSettingsController();
            case 0x09 -> new SpeedRenderingController();
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
