package com.asalaschenko.simulation.Controllers;

import com.asalaschenko.simulation.ProcessingGraphic.ColorPrinter;
import com.asalaschenko.simulation.ProcessingSettingsFile.CreaturesModelXML;
import com.asalaschenko.simulation.ProcessingSettingsFile.HandlerInputOutputXML;
import com.asalaschenko.simulation.ProcessingSettingsFile.WorldMapModelXML;
import com.asalaschenko.simulation.ProcessingSimulation.Entities.PropertyCreatures;
import jakarta.xml.bind.JAXBException;

import java.io.FileNotFoundException;
import java.lang.invoke.MethodHandles;
import java.util.Scanner;

public class CreatureSettingsController extends BasicSettingsController {

    WorldMapModelXML map;

    public CreatureSettingsController() throws JAXBException, FileNotFoundException {
        this.map = new HandlerInputOutputXML().fromXmlToObject();
    }

    @Override
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
    public void changeSettings() throws JAXBException, FileNotFoundException {

        boolean loopManage = true;
        boolean isNeedSavingSettings = false;
        Scanner sc = new Scanner (System.in);

        while(loopManage) {

                printMenuChangeSettingsOfCreatures();
                String str = sc.nextLine().toUpperCase();

                if(isValidInput(str)){
                    if(checkOtherCreatures(str)) {
                        writeNewSettings(str);
                        printSettings();
                        isNeedSavingSettings = true;
                    }else{
                        printChangeWarningMessage(str);
                    }
                }else if(str.equals("SAVE")){
                    saveChangesWithoutWriteOldSettings();
                    isNeedSavingSettings = false;
                }else if(str.equals("EXIT")){
                    loopManage = false;
                }else{
                    printUncorrectedInputMessage();
                }
        }

        if(isNeedSavingSettings) {
            saveChanges();
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

    protected void saveChangesWithoutWriteOldSettings() throws JAXBException { //только сохранение новых настроек

        if(isSaveChanges()){
            new HandlerInputOutputXML().convertObjectToXml(map);
        }
    }

    private boolean checkOtherCreatures(String str){

        switch (str){

            case "EAGLE"   -> {
                    return map.getCreatures().getPredators().getWOLF() || map.getCreatures().getPredators().getLION();}
            case "WOLF"    -> {
                    return map.getCreatures().getPredators().getEAGLE() || map.getCreatures().getPredators().getLION();}
            case "LION"    -> {
                    return map.getCreatures().getPredators().getEAGLE() || map.getCreatures().getPredators().getWOLF();}
            case "RODENT"  -> {
                    return map.getCreatures().getHerbivores().getCHICKEN() || map.getCreatures().getHerbivores().getRABBIT();}
            case "RABBIT"  -> {
                    return map.getCreatures().getHerbivores().getCHICKEN() || map.getCreatures().getHerbivores().getRODENT();}
            case "CHICKEN" -> {
                    return map.getCreatures().getHerbivores().getRABBIT() || map.getCreatures().getHerbivores().getRODENT();}

            default -> {
                return false;
            }

        }
    }

    @Override
    protected boolean isValidInput(String str){

        switch (str){
            case "EAGLE", "WOLF", "LION", "RODENT", "RABBIT", "CHICKEN" -> {
                return true;
            }
            default -> {
                return false;
            }
        }

    }

    @Override
    protected void writeNewSettings(String str){

            switch (str){
                case "EAGLE"   -> map.getCreatures().getPredators().setEAGLE(!map.getCreatures().getPredators().getEAGLE());
                case "WOLF"    -> map.getCreatures().getPredators().setWOLF(!map.getCreatures().getPredators().getWOLF());
                case "LION"    -> map.getCreatures().getPredators().setLION(!map.getCreatures().getPredators().getLION());
                case "RODENT"  -> map.getCreatures().getHerbivores().setRODENT(!map.getCreatures().getHerbivores().getRODENT());
                case "RABBIT"  -> map.getCreatures().getHerbivores().setRABBIT(!map.getCreatures().getHerbivores().getRABBIT());
                case "CHICKEN" -> map.getCreatures().getHerbivores().setCHICKEN(!map.getCreatures().getHerbivores().getCHICKEN());
                default -> throw new WriteNewSettingsException("Call "
                        + Thread.currentThread().getStackTrace()[1].getMethodName()
                        + "() of "
                        + MethodHandles.lookup().lookupClass()
                        + "."
                        + " Please, check value of argument: str="
                        + str);
            }

    }

    private void printChangeWarningMessage(String str) {
        ColorPrinter.printlnRedText("Нельзя убрать " + str + " ! На карте должны присутствовать минимум один хищник и одно травоядное !");
        System.out.println();

    }

    @Override
    protected void printSettings(){
        CreaturesModelXML creaturesModelXML = map.getCreatures();
        ColorPrinter.printlnRedText("Хищники: ");
        System.out.print("EAGLE     "
                + PropertyCreatures.EAGLE.getCode()
                + "      ");
        printTickOrCross(creaturesModelXML.getPredators().getEAGLE());
        System.out.print("WOLF      "
                + PropertyCreatures.WOLF.getCode()
                + "      ");
        printTickOrCross(creaturesModelXML.getPredators().getWOLF());
        System.out.print("LION      "
                + PropertyCreatures.LION.getCode()
                + "      ");
        printTickOrCross(creaturesModelXML.getPredators().getLION());
        ColorPrinter.printlnRedText("Травоядные: ");
        System.out.print("RODENT    "
                + PropertyCreatures.RODENT.getCode()
                + "      ");
        printTickOrCross(creaturesModelXML.getHerbivores().getRODENT());
        System.out.print("RABBIT    "
                + PropertyCreatures.RABBIT.getCode()
                + "      ");
        printTickOrCross(creaturesModelXML.getHerbivores().getRABBIT());
        System.out.print("CHICKEN   "
                + PropertyCreatures.CHICKEN.getCode()
                + "      ");
        printTickOrCross(creaturesModelXML.getHerbivores().getCHICKEN());
        System.out.println();
    }

    @Override
    protected void printMenuSettingController(){
        ColorPrinter.printlnYellowBackgroundText("СУЩЕСТВА В СИМУЛЯЦИИ");
        System.out.println("""
                Введите: \
                
                "1" - показать настройки ;\
                
                "2" - изменить настройки ;\
                
                "0" - выход в предыдущее меню.""");
    }


    private void printTickOrCross(boolean flag){
        if(flag){
            ColorPrinter.printlnGreenText("✔");
        }else{
            ColorPrinter.printlnRedText("⛌");
        }
    }

    private void printMenuChangeSettingsOfCreatures() {
        ColorPrinter.printlnYellowBackgroundText("ИЗМЕНИТЬ НАСТРОЙКИ");
        System.out.println("""
                Введите:\s
                Имя существа (EAGLE/WOLF/LION/RODENT/CHICKEN/RABBIT),\s
                SAVE, чтобы сохранить настройки.\s
                EXIT, чтобы выйти в предыдущее меню,\s
                """);
    }

    @Override
    protected void printUncorrectedInputMessage(){
        ColorPrinter.printlnYellowText("Неверно.");
        System.out.println("Введите одно из значений 1/2/0");
        System.out.println();
    }
}
