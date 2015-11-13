package edu.tsu.ge.salesman.core.model;

public class ResultMetaModel {
    private int initialDistance;
    private int finalDistance;
    private Tour fittest;

    public int getInitialDistance() {
        return initialDistance;
    }

    public void setInitialDistance(int initialDistance) {
        this.initialDistance = initialDistance;
    }

    public int getFinalDistance() {
        return finalDistance;
    }

    public void setFinalDistance(int finalDistance) {
        this.finalDistance = finalDistance;
    }

    public Tour getFittest() {
        return fittest;
    }

    public void setFittest(Tour fittest) {
        this.fittest = fittest;
    }
}
