package com.asalaschenko.simulation.Controllers;

import com.asalaschenko.simulation.Controllers.Factory.ControllerID;
import com.asalaschenko.simulation.Controllers.Factory.SettingsControllersFactory;
import com.asalaschenko.simulation.ProcessingGraphic.ColorPrinter;

import java.util.Scanner;

public class BasicSettingsController implements Controller {


    public BasicSettingsController() {

    }

    public void start() throws Exception {
        boolean loopManage = true;
        Scanner sc = new Scanner (System.in);
        while(loopManage){
            printMenuSettingController();
            switch (sc.nextLine()){
                case "1" -> runController(SettingsControllersFactory.create(ControllerID.SizeSettings));
                case "2" -> runController(SettingsControllersFactory.create(ControllerID.SpeedRendering));
                case "3" -> runController(SettingsControllersFactory.create(ControllerID.CreatureSettings));
                case "4" -> runController(SettingsControllersFactory.create(ControllerID.GroundSprite));
                case "0" -> loopManage = false;
                default  -> printUncorrectedInputMessage();
            }
        }
    }

    protected <T extends BasicSettingsController> void runController(T controller) throws Exception{
        controller.start();
    }

    protected boolean isSaveChanges() {

        Scanner sc = new Scanner (System.in);
        ColorPrinter.printlnRedText("Сохранить настройки ? (ДА/НЕТ)");

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

    protected void writeNewSettings(String str){

    }

    protected void printSettings(){

    }

    protected void changeSettings() throws Exception {

    }

    protected void saveChanges() throws Exception {

    }

    protected boolean isValidInput(String str){
        return true;
    }

    protected void printMenuSettingController(){
        ColorPrinter.printlnYellowBackgroundText("МЕНЮ НАСТРОЕК");
        System.out.println("""
                Введите: \
                
                "1" - размер карты  ;\
                
                "2" - скорость рендеринга  ;\
                
                "3" - существа в симуляции ;\
                
                "4" - цвет карты ;\
                
                "0" - выход в главное меню.""");
    }

    protected void printUncorrectedInputMessage(){
        ColorPrinter.printlnYellowText("Неверно.");
        System.out.println("Введите одно из значений 1/2/3/4/0");
        System.out.println();
    }
}
