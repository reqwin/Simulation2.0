package com.asalaschenko.simulation.Controllers;

import com.asalaschenko.simulation.ProcessingGraphic.ColorPrinter;
import com.asalaschenko.simulation.ProcessingSettingsFile.HandlerInputOutputXML;
import com.asalaschenko.simulation.ProcessingSettingsFile.WorldMapModelXML;
import jakarta.xml.bind.JAXBException;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class SpeedRenderingController extends BasicSettingsController {

    WorldMapModelXML map;

    public SpeedRenderingController() throws JAXBException, FileNotFoundException {
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
    protected void printSettings(){
        int var = this.map.getSpeedRendering();
        ColorPrinter.printlnRedText("Скорость задержки кадра: ");
        System.out.println(var);
        System.out.println();
    }

    @Override
    protected void changeSettings() throws JAXBException, FileNotFoundException {

        boolean loopManage = true;
        boolean isNeedSavingSettings = false;
        Scanner sc = new Scanner (System.in);

        while(loopManage) {
            System.out.println("Введите параметр задержки кадра от 100 до 2000, либо EXIT, если хотите выйти в предыдущее меню: ");
            String str = sc.nextLine();
            if(str.equalsIgnoreCase("EXIT")){
                loopManage = false;
            }else if(isValidInput(str)){
                writeNewSettings(str);
                loopManage = false;
                isNeedSavingSettings = true;
            }else{
                ColorPrinter.printlnYellowText("Неверно !");
                System.out.println();
            }
        }

        if(isNeedSavingSettings) {
            saveChanges();
        }

    }


    @Override
    protected void writeNewSettings(String str) {
        int speed = Integer.parseInt(str);
        map.setSpeedRendering(speed);
    }


    protected boolean isValidInput(String str){
        if(isNumber(str)){
            int speed = Integer.parseInt(str);
            return isValidNumber(speed);
        }else{
            return false;
        }
    }

    private boolean isValidNumber(int num){
        return num >= 100 && num <= 2000;  //здесь задаётся минимальное и максимальное значение рендеринга карты
    }

    private boolean isNumber(String str) {
        try {
            Integer.parseInt(str);
        } catch (Exception e) {
            return false;
        }
        return true;
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
    protected void printMenuSettingController(){
        ColorPrinter.printlnYellowBackgroundText("СКОРОСТЬ РЕНДЕРИНГА");
        System.out.println("""
                Введите: \
                
                "1" - показать настройки ;\
                
                "2" - изменить настройки ;\
                
                "0" - выход в предыдущее меню.""");

    }

    @Override
    protected void printUncorrectedInputMessage(){
        ColorPrinter.printlnYellowText("Неверно.");
        System.out.println("Введите одно из значений 1/2/0");
        System.out.println();
    }

}
