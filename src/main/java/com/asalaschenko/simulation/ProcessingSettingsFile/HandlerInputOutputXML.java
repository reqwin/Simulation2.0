package com.asalaschenko.simulation.ProcessingSettingsFile;

import com.asalaschenko.simulation.ProcessingGraphic.ColorPrinter;
import jakarta.xml.bind.*;

import java.io.*;
import java.lang.invoke.MethodHandles;
import java.util.stream.Collectors;

public class HandlerInputOutputXML {

    private final String fileName = "src\\main\\resources\\settings.xml";
    private final String filePath = System.getProperty("user.dir") + "\\" + fileName;

    public HandlerInputOutputXML() {

    }

    public WorldMapModelXML fromXmlToObject() throws JAXBException, FileNotFoundException {
        FileReader filereader = new FileReader(filePath);
        BufferedReader br = new BufferedReader(filereader);
        String body = br.lines().collect(Collectors.joining());
        StringReader reader = new StringReader(body);
        JAXBContext context = JAXBContext.newInstance(WorldMapModelXML.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        WorldMapModelXML map;
        try {
            map = (WorldMapModelXML) unmarshaller.unmarshal(reader);
        }catch(UnmarshalException e){
            ColorPrinter.printlnRedBackgroundText(e.toString());
            ColorPrinter.printlnRedBackgroundText("Файл настроек содержит некорректную информацию ! Необходимо перезапустить программу и выполнить восстановление настроек !");
            throw new checkParametersInSettingsFileException("Call "
                    + Thread.currentThread().getStackTrace()[1].getMethodName()
                    + "() of "
                    + MethodHandles.lookup().lookupClass()
                    + ".\n");
        }
        checkParametersInSettingsFile(map);
        return map;
    }

    public void convertObjectToXml(WorldMapModelXML map) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(WorldMapModelXML.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(map, new File(filePath));
    }

    private void checkParametersInSettingsFile(WorldMapModelXML map) {

        int row;
        int column;
        int speed;

        boolean eagle;
        boolean lion;
        boolean wolf;
        boolean chicken;
        boolean rabbit;
        boolean rodent;

        try {
            row = map.getSize().getRow();
            column = map.getSize().getColumn();
            speed = map.getSpeedRendering();

            eagle = map.getCreatures().getPredators().getEAGLE();
            lion = map.getCreatures().getPredators().getLION();
            wolf = map.getCreatures().getPredators().getWOLF();
            chicken = map.getCreatures().getHerbivores().getCHICKEN();
            rabbit = map.getCreatures().getHerbivores().getRABBIT();
            rodent = map.getCreatures().getHerbivores().getRODENT();

        }catch (NullPointerException e){
            ColorPrinter.printlnRedBackgroundText(e.toString());
            ColorPrinter.printlnRedBackgroundText("Файл настроек содержит некорректную информацию ! Необходимо перезапустить программу и выполнить восстановление настроек !");
            throw new checkParametersInSettingsFileException("Call "
                    + Thread.currentThread().getStackTrace()[1].getMethodName()
                    + "() of "
                    + MethodHandles.lookup().lookupClass()
                    + ".\n");
        }

        boolean creaturesParametersError = !(eagle || lion || wolf) || !(chicken || rabbit || rodent);
        boolean sizeParametersError = row<6 || row>20 || column<6 || column>20;  //проверяем допустимость размера
        boolean speedParametersError = speed < 100 || speed > 2000;

        if(creaturesParametersError || sizeParametersError || speedParametersError) {
            throw new checkParametersInSettingsFileException("Call "
                    + Thread.currentThread().getStackTrace()[1].getMethodName()
                    + "() of "
                    + MethodHandles.lookup().lookupClass()
                    + ".\n"
                    + "Недопустимые настройки файла \"src/main/resources/settings.xml\".\n"
                    + generateMessageForException(creaturesParametersError, sizeParametersError, speedParametersError));
        }

    }

    private String generateMessageForException(boolean creaturesParametersError, boolean sizeParametersError, boolean speedParametersError) {

        String errorMessage = "";

        if(sizeParametersError){
            errorMessage += "Проверьте блок <Size>. Количество столбцов и строк должно быть в пределах от 6 до 20 !\n";
        }
        if(creaturesParametersError){
            errorMessage += "Проверьте блок <Creatures>. Должны быть активированы минимум одно травоядное и один хищник !\n";
        }
        if(speedParametersError){
            errorMessage += "Проверьте блок <SpeedRendering>. Значение должно быть в пределах от 100 до 2000 !\n";
        }

        errorMessage += "Либо выполните восстановление настроек после запуска программы через меню.";

        return errorMessage;
    }
}
