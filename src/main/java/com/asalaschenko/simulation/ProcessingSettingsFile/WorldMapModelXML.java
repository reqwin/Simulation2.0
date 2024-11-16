package com.asalaschenko.simulation.ProcessingSettingsFile;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "WorldMap")
@XmlAccessorType(XmlAccessType.FIELD)
public class WorldMapModelXML {

        @XmlElement(name="Size")
        SizeModelXML sizeModelXML;

        @XmlElement(name="SpeedRendering")
        private int SpeedRendering;

        @XmlElement(name="GroundSprite")
        private String GroundSprite;

        @XmlElement(name="Creatures")
        CreaturesModelXML creaturesModelXML;

        public WorldMapModelXML(){

        }

        public SizeModelXML getSize() {
                return sizeModelXML;
        }

        public int getSpeedRendering() {
                return SpeedRendering;
        }

        public String getGroundSprite() {
                return GroundSprite;
        }

        public CreaturesModelXML getCreatures() {
                return creaturesModelXML;
        }

        public void setSizeModelXML(SizeModelXML sizeModelXML) {
                this.sizeModelXML = sizeModelXML;
        }

        public void setGroundSprite(String groundSprite) {
                this.GroundSprite = groundSprite;
        }

        public void setCreaturesModelXML(CreaturesModelXML creaturesModelXML) {
                this.creaturesModelXML = creaturesModelXML;
        }

        public void setSpeedRendering(int speedRendering) {
                SpeedRendering = speedRendering;
        }
}
