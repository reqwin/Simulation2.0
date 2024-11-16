package com.asalaschenko.simulation.Controllers;

import com.asalaschenko.simulation.Controllers.Factory.BasicControllersFactory;
import com.asalaschenko.simulation.Controllers.Factory.ControllerID;
import com.asalaschenko.simulation.ProcessingGraphic.ColorPrinter;

import java.util.Scanner;

public class MainMenuController implements Controller {

    public MainMenuController() {
            
    }

    public void start() throws Exception{
        printWelcomeMessage();
        boolean loopManage = true;
        Scanner sc = new Scanner (System.in);
        while(loopManage){
            printStartMessage();
            switch (sc.nextLine()) {
                case "1" -> runController(BasicControllersFactory.create(ControllerID.Simulation));
                case "2" -> runController(BasicControllersFactory.create(ControllerID.BasicSettings));
                case "3" -> runController(BasicControllersFactory.create(ControllerID.ReadmeMessage));
                case "4" -> runController(BasicControllersFactory.create(ControllerID.RestoreSettings));
                case "0" -> {
                    loopManage = false;
                    printGoodByeMessage();
                }
                default -> printUncorrectedInputMessage();
            }
        }
    }

    protected void runController(Controller controller) throws Exception{
        controller.start();
    }

    private void printStartMessage(){
        ColorPrinter.printlnYellowBackgroundText("ГЛАВНОЕ МЕНЮ");
        ColorPrinter.printlnYellowText("""
                Введите: \
                
                "1" - начать симуляцию ;\
                
                "2" - посмотреть или изменить настройки ;\
                
                "3" - описание программы ;\
                
                "4" - восстановление настроек ;\
                
                "0" - выход из программы.""");
    }

    private void printWelcomeMessage(){
        ColorPrinter.printlnYellowText("Приветствую тебя в программе \"Симуляция\" !");
        System.out.println();
    }

    private void printGoodByeMessage(){
        ColorPrinter.printlnYellowText("GOOD BYE !");
        System.out.println();
    }

    private void printUncorrectedInputMessage(){
        ColorPrinter.printlnYellowText("Неверно.");
        System.out.println("Введите одно из значений 1/2/3/4/0");
        System.out.println();
    }
}
