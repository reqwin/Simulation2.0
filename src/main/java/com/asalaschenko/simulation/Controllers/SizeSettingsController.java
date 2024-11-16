package com.asalaschenko.simulation.Controllers;

import com.asalaschenko.simulation.ProcessingGraphic.ColorPrinter;
import com.asalaschenko.simulation.ProcessingSettingsFile.HandlerInputOutputXML;
import com.asalaschenko.simulation.ProcessingSettingsFile.SizeModelXML;
import com.asalaschenko.simulation.ProcessingSettingsFile.WorldMapModelXML;
import jakarta.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SizeSettingsController extends BasicSettingsController {

    WorldMapModelXML map;
    final String separator = " ";

    public SizeSettingsController() throws JAXBException, FileNotFoundException {
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
    protected void changeSettings() throws JAXBException, FileNotFoundException {

        boolean loopManage = true;
        boolean isNeedSavingSettings = false;
        Scanner sc = new Scanner (System.in);

        while(loopManage) {
            System.out.println("Введите через пробел количество столбцов и строк от 6 до 20, либо EXIT, если хотите выйти в предыдущее меню: ");
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
    protected void saveChanges() throws JAXBException, FileNotFoundException {
        if(isSaveChanges()){
            new HandlerInputOutputXML().convertObjectToXml(map); //запись новых настроек
        }else{
            map = new HandlerInputOutputXML().fromXmlToObject(); //возвращаем прежние настройки
        }
    }

    @Override
    protected void writeNewSettings(String str) {

        String[] parts = str.split(separator);

        int column = Integer.parseInt(parts[0]);
        int row    = Integer.parseInt(parts[1]);

        map.getSize().setColumn(column);
        map.getSize().setRow(row);

    }

    @Override
    protected boolean isValidInput(String str){

        String[] parts = str.split(separator);

        if(parts.length == 2 && isNumber(parts[0]) && isNumber(parts[1])){
            int column = Integer.parseInt(parts[0]);
            int row    = Integer.parseInt(parts[1]);
            return isValidNumber(column) && isValidNumber(row);
        }else{
            return false;
        }

    }

    private boolean isNumber(String str) {

        try {
            Integer.parseInt(str);
        } catch (Exception e) {
            return false;
        }
        return true;

    }

    private boolean isValidNumber(int num){
        return num > 5 && num < 21;  //здесь задаётся минимальное и максимальное значение размера карты
    }

    @Override
    protected void printMenuSettingController(){
        ColorPrinter.printlnYellowBackgroundText("РАЗМЕР КАРТЫ");
        System.out.println("""
                Введите: \
                
                "1" - показать настройки ;\
                
                "2" - изменить настройки ;\
                
                "0" - выход в предыдущее меню.""");

    }


    @Override
    protected void printSettings(){

        SizeModelXML sizeModelXML = this.map.getSize();
        ColorPrinter.printlnRedText("Количество столбцов (ширина): ");
        System.out.println(sizeModelXML.getColumn());
        ColorPrinter.printlnRedText("Количество строк (длина): ");
        System.out.println(sizeModelXML.getRow());
        System.out.println();

    }

    @Override
    protected void printUncorrectedInputMessage(){
        ColorPrinter.printlnYellowText("Неверно.");
        System.out.println("Введите одно из значений 1/2/0");
        System.out.println();
    }
}
