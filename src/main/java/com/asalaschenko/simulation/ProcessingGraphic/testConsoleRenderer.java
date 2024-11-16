package com.asalaschenko.simulation.ProcessingGraphic;


import com.asalaschenko.simulation.ProcessingSimulation.WorldMap;


import java.awt.*;
import java.util.ArrayList;

public class testConsoleRenderer {

    private final String ANSI_THIN_SPACE = "\u2009";
    private String GROUND;
    private String pathPoint;

    public testConsoleRenderer(String GROUND){
        this.GROUND = GROUND;
        this.pathPoint = "\uD83D\uDFE5";
    }

    public boolean isPointInList(Point point, ArrayList<Point> list){
        return list.stream().anyMatch(x -> x.equals(point));
    }

    public void render(WorldMap map, int counter, ArrayList<Point> list){
        Point size = map.getSize();
        for(int row = size.x-1; row >= 0; row--){
            for(int column = 0; column < size.y; column++){
                if(map.isEmptyPlace(new Point(row, column))){
                    if(list != null && isPointInList(new Point(row, column), list)){
                        System.out.print(pathPoint + ANSI_THIN_SPACE);
                    }else{
                        System.out.print(GROUND + ANSI_THIN_SPACE);
                    }
                }else{
                    System.out.print(map.getEntity(new Point(row, column)).getSprite() + ANSI_THIN_SPACE);
                }
            }
            System.out.println();
        }
        System.out.println();
    }



}
