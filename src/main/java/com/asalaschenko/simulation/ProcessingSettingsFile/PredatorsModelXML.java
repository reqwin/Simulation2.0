package com.asalaschenko.simulation.ProcessingSettingsFile;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "EAGLE", "WOLF", "LION" })
public class PredatorsModelXML {

    boolean LION;
    boolean WOLF;
    boolean EAGLE;

    public PredatorsModelXML(){

    }

    public PredatorsModelXML(boolean lion, boolean wolf, boolean eagle) {
        this.EAGLE = eagle;
        this.WOLF = wolf;
        this.LION = lion;
    }

    public boolean getLION() {
        return LION;
    }

    public boolean getEAGLE() {
        return EAGLE;
    }

    public boolean getWOLF() {
        return WOLF;
    }

    public void setWOLF(boolean WOLF) {
        this.WOLF = WOLF;
    }

    public void setLION(boolean LION) {
        this.LION = LION;
    }

    public void setEAGLE(boolean EAGLE) {
        this.EAGLE = EAGLE;
    }
}
