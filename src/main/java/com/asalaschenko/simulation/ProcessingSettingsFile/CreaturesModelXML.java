package com.asalaschenko.simulation.ProcessingSettingsFile;


import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)

public class CreaturesModelXML {
    @XmlElement(name="Herbivores")
    HerbivoresModelXML herbivoresModelXML;
    @XmlElement(name="Predators")
    PredatorsModelXML predatorsModelXML;

    public CreaturesModelXML(){

    }

    public CreaturesModelXML(HerbivoresModelXML herbivoresModelXML, PredatorsModelXML predatorsModelXML){
        this.herbivoresModelXML = herbivoresModelXML;
        this.predatorsModelXML = predatorsModelXML;
    }

    public PredatorsModelXML getPredators() {
        return predatorsModelXML;
    }

    public void setPredators(PredatorsModelXML predatorsModelXML) {
        this.predatorsModelXML = predatorsModelXML;
    }

    public HerbivoresModelXML getHerbivores() {
        return herbivoresModelXML;
    }

    public void setHerbivores(HerbivoresModelXML herbivoresModelXML) {
        this.herbivoresModelXML = herbivoresModelXML;
    }
}
