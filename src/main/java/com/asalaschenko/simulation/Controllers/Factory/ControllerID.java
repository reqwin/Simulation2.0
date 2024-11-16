package com.asalaschenko.simulation.Controllers.Factory;

import com.asalaschenko.simulation.Controllers.SpeedRenderingController;

public enum ControllerID {

        MainMenu(0x01),
        BasicSettings(0x02),
        Simulation(0x03),
        CreatureSettings(0x04),
        SizeSettings(0x05),
        GroundSprite(0x06),
        RestoreSettings(0x07),
        ReadmeMessage(0x08),
        SpeedRendering(0x09);

        Integer code;

        ControllerID(Integer code){
            this.code = code;
        }

        public Integer getCode(){
        return code;
    }
}
