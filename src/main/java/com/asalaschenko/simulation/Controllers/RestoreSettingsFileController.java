package com.asalaschenko.simulation.Controllers;

import com.asalaschenko.simulation.ProcessingGraphic.ColorPrinter;
import com.asalaschenko.simulation.ProcessingSettingsFile.*;
import jakarta.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RestoreSettingsFileController implements Controller {
        public void start() throws JAXBException, FileNotFoundException {
            if(isRestoreFile()) {
                WorldMapModelXML map = new WorldMapModelXML();
                map.setSizeModelXML(new SizeModelXML(10, 10));
                map.setSpeedRendering(1000);
                map.setCreaturesModelXML(new CreaturesModelXML(new HerbivoresModelXML(true, true, true), new PredatorsModelXML(true, true, true)));
                map.setGroundSprite("⬜");
                new HandlerInputOutputXML().convertObjectToXml(map);
            }
                WorldMapModelXML map = new HandlerInputOutputXML().fromXmlToObject();
                printSuccessMessage();
        }

    private static void printSuccessMessage() {
        ColorPrinter.printlnGreenBackgroundText("Файл восстановлен !");
        System.out.println();
    }

    private boolean isRestoreFile() {

        Scanner sc = new Scanner (System.in);
        ColorPrinter.printlnRedBackgroundText("Восстановить настройки ? (ДА/НЕТ)");

        while (true) {
            switch (sc.nextLine().toUpperCase()) {
                case "ДА" -> {
                    return true;
                }
                case "НЕТ" -> {
                    return false;
                }
                default -> printDefaultIsSaveChanges();
            }
        }
    }

    private void printDefaultIsSaveChanges() {
        ColorPrinter.printlnYellowText("Неверно.");
        ColorPrinter.printlnYellowText("Введите одно из значений ДА/НЕТ.");
    }
}
