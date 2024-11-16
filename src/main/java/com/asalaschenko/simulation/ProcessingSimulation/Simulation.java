package com.asalaschenko.simulation.ProcessingSimulation;

import com.asalaschenko.simulation.ProcessingGraphic.ColorPrinter;
import com.asalaschenko.simulation.ProcessingGraphic.Renderer;
import com.asalaschenko.simulation.ProcessingSimulation.Actions.Action;
import com.asalaschenko.simulation.ProcessingSimulation.Actions.InitActions.CreatureInit;
import com.asalaschenko.simulation.ProcessingSimulation.Actions.InitActions.LandscapeInit;
import com.asalaschenko.simulation.ProcessingSimulation.Actions.MoveActions.HerbivoreMove;
import com.asalaschenko.simulation.ProcessingSimulation.Actions.MoveActions.PredatorMove;
import com.asalaschenko.simulation.ProcessingSimulation.Entities.CreatureElements.Creature;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class Simulation {

    WorldMap map;
    Renderer renderer;
    List<Creature> creaturesList;
    Action initCreature, initLandscape, predatorMove, herbivoreMove;
    boolean isPause;
    int count;
    int speed;

    public Simulation(WorldMap map, List<Creature> creaturesList, Renderer renderer, int speed){
        this.map = map;
        this.renderer = renderer;
        this.creaturesList = creaturesList;
        initCreature = new CreatureInit(creaturesList);
        initLandscape = new LandscapeInit();
        predatorMove = new PredatorMove();
        herbivoreMove = new HerbivoreMove();
        this.speed = speed;
        isPause = false;
        count = 0;
    }

    public void start() throws Exception{
        initCreature.doAction(this.map);
        initLandscape.doAction(this.map);
        renderer.render(this.map, count, true);

        runSimulation();

        ColorPrinter.printlnYellowBackgroundText("СИМУЛЯЦИЯ ОКОНЧЕНА !");
        System.out.println();
    }

    private void runSimulation() throws Exception {
        boolean loopManage = true;
        boolean stepOrder = true; //true - шаг делает Predator; false - Herbivore
        while(loopManage){
            Thread.sleep(speed);
            while(isPause){
                TimeUnit.SECONDS.sleep(1);
            }
            count++;
            if(stepOrder){
                predatorMove.doAction(map);
                if(map.isPredatorOrHerbivorePopulationDying()){
                    loopManage = false;
                }
            }else{
                herbivoreMove.doAction(map);
            }
            renderer.render(this.map, count, stepOrder);
            stepOrder = !stepOrder;
        }
    }

    public boolean getPause() {
        return isPause;
    }

    public void setPause() {
        isPause = !isPause;
    }
}
