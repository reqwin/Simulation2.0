package com.asalaschenko.simulation.ProcessingSettingsFile;


import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "RODENT", "RABBIT", "CHICKEN" })
public class HerbivoresModelXML {

    boolean CHICKEN;
    boolean RABBIT;
    boolean RODENT;

    public HerbivoresModelXML(){

    }

    public HerbivoresModelXML(boolean chicken, boolean rodent, boolean rabbit) {
        this.RODENT = rodent;
        this.CHICKEN = chicken;
        this.RABBIT = rabbit;
    }

    public boolean getRABBIT() {
        return RABBIT;
    }

    public boolean getCHICKEN() {
        return CHICKEN;
    }

    public boolean getRODENT() {
        return RODENT;
    }

    public void setRODENT(boolean RODENT) {
        this.RODENT = RODENT;
    }

    public void setRABBIT(boolean RABBIT) {
        this.RABBIT = RABBIT;
    }

    public void setCHICKEN(boolean CHICKEN) {
        this.CHICKEN = CHICKEN;
    }
}
