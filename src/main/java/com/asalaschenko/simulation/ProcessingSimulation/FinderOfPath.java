package com.asalaschenko.simulation.ProcessingSimulation;

import com.asalaschenko.simulation.ProcessingSimulation.Entities.Entity;
import java.awt.*;
import java.util.*;
import java.util.stream.IntStream;

public class FinderOfPath {

        WorldMap map;

        public FinderOfPath(WorldMap map) {
                this.map = map;
        }

        private ArrayList<FindPathsPoint> listOfReachablePoint = new ArrayList<>();
        private ArrayList<FindPathsPoint> listOfExploredPoint = new ArrayList<>();

        public ArrayList<Point> findOfPath(Point point,  Class<? extends Entity> object){

            ArrayList<Point> listOfPathsPoint = new ArrayList<>();
            FindPathsPoint fppStart = new FindPathsPoint(point, 0, null);

            Point goal = findNearestGoal(point, object);

            if(shortestDistanceBetweenDots(goal, point) == 1){
                    listOfPathsPoint.add(goal);
                    return listOfPathsPoint;
            }

            findNewReachablePoint(fppStart);
            listOfExploredPoint.add(fppStart);

            while(true){
                    int key = getNearestToGoalReachablePoint(goal);
                    if(key == -1){
                            key = getNearestToGoalExploredPoint(goal);
                            listOfExploredPoint.removeFirst();
                            createPath(listOfPathsPoint, listOfExploredPoint.get(key));
                            Collections.reverse(listOfPathsPoint);
                            return listOfPathsPoint;
                    }
                    if(shortestDistanceBetweenDots(goal, listOfReachablePoint.get(key).getPoint()) == 1){
                            listOfPathsPoint.add(goal);
                            createPath(listOfPathsPoint, listOfReachablePoint.get(key));
                            Collections.reverse(listOfPathsPoint);
                            return listOfPathsPoint;
                    }
                    findNewReachablePoint(listOfReachablePoint.get(key));
                    listOfExploredPoint.add(listOfReachablePoint.get(key));
                    listOfReachablePoint.remove(key);
            }
        }

        private void findNewReachablePoint(FindPathsPoint findPathsPoint){

            ArrayList<Point> listOfAroundPoint = getAroundPoint(findPathsPoint);

            for(Point point : listOfAroundPoint){
                if(!isExploredPoint(point)) {
                    if (isReachablePoint(point)) {
                        FindPathsPoint fpp = listOfReachablePoint.get(getReachablePointIndex(point));
                        if (findPathsPoint.getCost() + 1 < fpp.getCost()) {
                            fpp.setPreviousLink(findPathsPoint);
                            fpp.setCost(findPathsPoint.getCost() + 1);
                        }
                    } else if (map.isEmptyPlace(point)) {
                        listOfReachablePoint.add(new FindPathsPoint(point, findPathsPoint.getCost() + 1, findPathsPoint));
                    }
                }
            }
        }

        private Point findNearestGoal(Point point, Class<? extends Entity> object){

             ArrayList<Point> listOfPoint = map.getObjects(object);
             int[] array = new int[listOfPoint.size()];
             for(int i = 0; i < listOfPoint.size(); i++){
                 array[i] = shortestDistanceBetweenDots(listOfPoint.get(i), point);
             }
             return listOfPoint.get(findMinIdx(array));

        }

        private int getNearestToGoalReachablePoint(Point goal){
            int distance = Integer.MAX_VALUE;
            int key = -1;
            for(int i = 0; i < listOfReachablePoint.size(); i++){
                int var = shortestDistanceBetweenDots(goal, listOfReachablePoint.get(i).getPoint());
                if(var < distance){
                    key = i;
                    distance = var;
                }
            }
            return key;
        }

        private int getNearestToGoalExploredPoint(Point goal){
            int distance = Integer.MAX_VALUE;
            int key = -1;
            for(int i = 0; i < listOfExploredPoint.size(); i++){
                int var = shortestDistanceBetweenDots(goal, listOfExploredPoint.get(i).getPoint());
                if(var < distance){
                    key = i;
                    distance = var;
                }
            }
            return key;
        }

        private int shortestDistanceBetweenDots(Point p1, Point p2){
            int x1 = p1.x;
            int x2 = p2.x;
            int y1 = p1.y;
            int y2 = p2.y;
            return Math.abs(x1 - x2) + Math.abs(y1 - y2);
        }


        private int findMinIdx(int[] numbers) {
            OptionalInt min = IntStream.of(numbers).min();
            return IntStream.of(numbers).boxed().toList().indexOf(min.getAsInt());
        }

        private boolean isExploredPoint(Point point){
            return listOfExploredPoint.stream().anyMatch(x -> x.getPoint().equals(point));
        }

        private boolean isReachablePoint(Point point){
            return listOfReachablePoint.stream().anyMatch(x -> x.getPoint().equals(point));
        }

        private int getReachablePointIndex(Point point){
            for(int i = 0; i<listOfReachablePoint.size(); i++){
                    if(listOfReachablePoint.get(i).getPoint().equals(point)){
                        return i;
                    }
            }
            return -1;
        }

        private void createPath(ArrayList<Point> listOfPathsPoint, FindPathsPoint fpp){
            if(fpp.getPreviousLink()!=null){
                listOfPathsPoint.add(fpp.getPoint());
                createPath(listOfPathsPoint, fpp.getPreviousLink());
            }
        }

        private ArrayList<Point> getAroundPoint(FindPathsPoint findPathsPoint){
            ArrayList<Point> listOfAroundPoint = new ArrayList<>();
            Point point1 = new Point(findPathsPoint.getPoint().x+1, findPathsPoint.getPoint().y);
            Point point2 = new Point(findPathsPoint.getPoint().x, findPathsPoint.getPoint().y+1);
            Point point3 = new Point(findPathsPoint.getPoint().x-1, findPathsPoint.getPoint().y);
            Point point4 = new Point(findPathsPoint.getPoint().x, findPathsPoint.getPoint().y-1);
            Point[] array = new Point[]{point1, point2, point3, point4};
            for(Point point : array){
                if(point.getX() >= 0 && point.getX() < map.getSize().getX() && point.getY() >= 0 && point.getY() < map.getSize().getY()){
                    listOfAroundPoint.add(point);
                }
            }
            return listOfAroundPoint;
        }



        public ArrayList<Point> findOfPath(Point pointStart,  Point pontEnd){   //перегруженный метод
                                                                                //на случай если потребуется найти кратчайший путь
                                                                                //между двумя точками
            ArrayList<Point> listOfPathsPoint = new ArrayList<>();
            FindPathsPoint fppStart = new FindPathsPoint(pointStart, 0, null);

            Point goal = pontEnd;

            if(shortestDistanceBetweenDots(goal, pointStart) == 1){
                listOfPathsPoint.add(goal);
                return listOfPathsPoint;
            }

            findNewReachablePoint(fppStart);
            listOfExploredPoint.add(fppStart);

            while(true){
                int key = getNearestToGoalReachablePoint(goal);
                if(key == -1){
                    listOfExploredPoint.removeFirst();
                    key = getNearestToGoalExploredPoint(goal);
                    if(key != -1) {
                        createPath(listOfPathsPoint, listOfExploredPoint.get(key));
                        Collections.reverse(listOfPathsPoint);
                    }
                    return listOfPathsPoint;
                }
                if(shortestDistanceBetweenDots(goal, listOfReachablePoint.get(key).getPoint()) == 1){
                    listOfPathsPoint.add(goal);
                    createPath(listOfPathsPoint, listOfReachablePoint.get(key));
                    Collections.reverse(listOfPathsPoint);
                    return listOfPathsPoint;
                }
                findNewReachablePoint(listOfReachablePoint.get(key));
                listOfExploredPoint.add(listOfReachablePoint.get(key));
                listOfReachablePoint.remove(key);
            }
        }

}
