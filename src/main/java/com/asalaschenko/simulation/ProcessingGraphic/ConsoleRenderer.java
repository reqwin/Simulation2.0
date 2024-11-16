package com.asalaschenko.simulation.ProcessingGraphic;


import com.asalaschenko.simulation.ProcessingSimulation.WorldMap;
import java.awt.*;


public class ConsoleRenderer implements Renderer {

    private final String ANSI_THIN_SPACE = "\u2009";
    private String GROUND;

    public ConsoleRenderer(String GROUND){
        this.GROUND = GROUND;
    }

    public void render(WorldMap map, int counter, boolean stepOrder){
        Point size = map.getSize();
        for(int row = size.x-1; row >= 0; row--){
            for(int column = 0; column < size.y; column++){
                if(map.isEmptyPlace(new Point(row, column))){
                        System.out.print(GROUND + ANSI_THIN_SPACE);
                }else{
                    System.out.print(map.getEntity(new Point(row, column)).getSprite() + ANSI_THIN_SPACE);
                }
            }
            System.out.println();
        }

        monitorStateOfSimulation(counter, stepOrder);
    }

    private void monitorStateOfSimulation(int counter, boolean stepOrder){
        if (counter == 0) {
            ColorPrinter.printlnGreenText("Initialization");
        } else {
            System.out.print(ColorPrinter.Color.GREEN.getCode()
                    + "Шаг "
                    + counter
                    + ". "
                    + ColorPrinter.Color.RESET.getCode());
            if (stepOrder) {
                ColorPrinter.printlnYellowText("Шаг делает Хищник.");
            } else {
                ColorPrinter.printlnYellowText("Шаг делает Травоядное.");
            }
        }
        System.out.println();
    }
}
