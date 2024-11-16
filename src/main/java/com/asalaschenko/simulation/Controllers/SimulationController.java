package com.asalaschenko.simulation.Controllers;

import com.asalaschenko.simulation.ProcessingGraphic.*;
import com.asalaschenko.simulation.ProcessingGraphic.Renderer;
import com.asalaschenko.simulation.ProcessingSettingsFile.*;
import com.asalaschenko.simulation.ProcessingSimulation.*;
import com.asalaschenko.simulation.ProcessingSimulation.Entities.CreatureElements.Creature;
import jakarta.xml.bind.JAXBException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimulationController implements Controller {

        WorldMapModelXML mapSettings;
        Simulation simulation;
        private ExecutorService executorService;

    public SimulationController() throws JAXBException, FileNotFoundException {
                mapSettings = new HandlerInputOutputXML().fromXmlToObject();
                executorService = Executors.newCachedThreadPool();
        }

        public void start()throws Exception{
                int columnSize = mapSettings.getSize().getColumn();
                int rowSize = mapSettings.getSize().getRow();
                int speed = mapSettings.getSpeedRendering();

                Point sizeOfMap = new Point(columnSize, rowSize);
                WorldMap map = new WorldMap(sizeOfMap);

                Renderer renderer = new ConsoleRenderer(mapSettings.getGroundSprite());

                List<Creature> creaturesList = CreatorCreaturesObjectList.getCreaturesList(mapSettings.getCreatures());

                simulation = new Simulation(map, creaturesList, renderer, speed);
                printMenuSettingController();
                progressLoading();

                executorService.submit(() -> {
                    try {
                        simulation.start();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });

                JFrame frame = new JFrame();
                controllerGraphicService(executorService ,simulation, frame);
                executorService.shutdown();
                while(!executorService.isTerminated()){} //ждем когда executorService выполнит все задачи
                frame.dispose();
        }

        private void controllerGraphicService(ExecutorService executorService, Simulation simulation, JFrame frame) throws InterruptedException {
            KeyListener listener = new KeyListener() {
                @Override
                public void keyPressed(KeyEvent keyEvent) {
                    char key = keyEvent.getKeyChar();
                    switch (String.valueOf(key)){
                        case "1" -> {
                            simulation.setPause();
                            if(simulation.getPause()){
                                ColorPrinter.printlnYellowBackgroundText("ПАУЗА");
                            }
                            System.out.println();
                        }
                        case "2" -> {
                            executorService.shutdownNow();
                            ColorPrinter.printlnRedBackgroundText("СИМУЛЯЦИЯ ОСТАНОВЛЕНА !");
                            System.out.println();
                        }
                        default  -> printUncorrectedInputMessage();
                    }
                }
                @Override
                public void keyReleased(KeyEvent e) {
                }
                @Override
                public void keyTyped(KeyEvent e) {
                }
            };

            JTextField textField = new JTextField(20);
            textField.addKeyListener(listener);

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(textField, BorderLayout.CENTER);
            frame.pack();
            frame.setLocation(475, 525);
            frame.setVisible(true);
        }

        protected void printMenuSettingController() {
            ColorPrinter.printlnYellowBackgroundText("СТАРТ");
            System.out.println("""
                    Управление симуляцией: \
                    
                    "1" - пауза/продолжить ;\
                    
                    "2" - завершение.""");
            ColorPrinter.printlnRedBackgroundText("ВО ВРЕМЯ СИМУЛЯЦИИ НИЧЕГО НЕ ВВОДИТЬ В КОНСОЛЬ !");
        }

        protected void printUncorrectedInputMessage(){
            ColorPrinter.printlnYellowText("Неверно.");
            System.out.println("Введите одно из значений 1/2");
            System.out.println();
        }

        private void progressLoading() throws InterruptedException {
            ColorPrinter.printYellowText("Waiting start ");
            for(int i = 0; i < 30; i++){
                ColorPrinter.printYellowText(".");
                Thread.sleep(100);
            }
            System.out.println();
        }
}
