package com.asalaschenko.simulation.Controllers;

import com.asalaschenko.simulation.ProcessingGraphic.ColorPrinter;
import com.asalaschenko.simulation.ProcessingSettingsFile.HandlerInputOutputXML;
import com.asalaschenko.simulation.ProcessingSettingsFile.WorldMapModelXML;
import jakarta.xml.bind.JAXBException;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class GroundSpriteSettingsController extends BasicSettingsController {

    WorldMapModelXML map;
    public GroundSpriteSettingsController() throws JAXBException, FileNotFoundException {
        this.map = new HandlerInputOutputXML().fromXmlToObject();
    }

    public void start() throws JAXBException, FileNotFoundException {
        boolean loopManage = true;
        Scanner sc = new Scanner (System.in);
        while(loopManage){
            printMenuSettingController();
            switch (sc.nextLine()){
                case "1" -> printSettings();
                case "2" -> changeSettings();
                case "0" -> loopManage = false;
                default  -> printUncorrectedInputMessage();
            }
        }
    }

    @Override
    protected void changeSettings() throws JAXBException, FileNotFoundException {

        boolean loopManage = true;
        boolean isNeedSavingSettings;
        Scanner sc = new Scanner (System.in);
        while(loopManage) {

            isNeedSavingSettings = true;
            loopManage = false;
            printMenuChangeGroundSprite();

            switch (sc.nextLine().toUpperCase()) {
                case "1" -> writeNewSettings("🟨");
                case "2" -> writeNewSettings("⬜");
                case "3" -> writeNewSettings("⬛");
                case "0" -> isNeedSavingSettings = false;
                default -> {
                    printUncorrectedInputMessage();
                    loopManage = true;
                }
            }

            if(isNeedSavingSettings) {
                saveChanges();
            }

        }
    }

    @Override
    protected void saveChanges() throws JAXBException, FileNotFoundException {
        if(isSaveChanges()){
            new HandlerInputOutputXML().convertObjectToXml(map); //запись новых настроек
        }else{
            map = new HandlerInputOutputXML().fromXmlToObject(); //возвращаем прежние настройки
        }
    }

    @Override
    protected void writeNewSettings(String str) {
        map.setGroundSprite(str);
    }

    @Override
    protected void printSettings(){
        String var = this.map.getGroundSprite();
        ColorPrinter.printlnRedText("Цвет карты: ");
        System.out.println(var);
        System.out.println();
    }

    @Override
    protected void printMenuSettingController(){
        ColorPrinter.printlnYellowBackgroundText("НАСТРОЙКА ЦВЕТА КАРТЫ");
        System.out.println("""
                Введите: \
                
                "1" - показать настройки ;\
                
                "2" - изменить настройки ;\
                
                "0" - выход в предыдущее меню.""");
    }

    private void printMenuChangeGroundSprite() {

        ColorPrinter.printlnYellowBackgroundText("ИЗМЕНИТЬ НАСТРОЙКИ");
        System.out.println("""
                Введите:\s
                "1" - 🟨 \s
                "2" - ⬜ \s
                "3" - ⬛ \s
                "0" - выход в предыдущее меню\s
                """);

    }

    @Override
    protected void printUncorrectedInputMessage(){
        ColorPrinter.printlnYellowText("Неверно.");
        System.out.println("Введите одно из значений 1/2/0");
        System.out.println();
    }
}
