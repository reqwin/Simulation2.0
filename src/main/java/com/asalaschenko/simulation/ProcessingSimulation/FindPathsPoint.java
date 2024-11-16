package com.asalaschenko.simulation.ProcessingSimulation;

import java.awt.*;

public class FindPathsPoint {

        private int cost;
        private Point point;
        private FindPathsPoint previousLink;

        public FindPathsPoint(Point point, int cost, FindPathsPoint previousLink) {
                this.cost = cost;
                this.point = point;
                this.previousLink = previousLink;
        }

        public Point getPoint() {
                return point;
        }

        public void setPoint(Point point) {
                this.point = point;
        }

        public int getCost() {
                return cost;
        }

        public void setCost(int cost) {
                this.cost = cost;
        }

        public FindPathsPoint getPreviousLink() {
                return previousLink;
        }

        public void setPreviousLink(FindPathsPoint previousLink) {
                this.previousLink = previousLink;
        }

}
