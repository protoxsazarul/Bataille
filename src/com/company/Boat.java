package com.company;

import java.util.ArrayList;
import java.util.List;

public abstract class Boat {
    private boolean isSinked;
    private List<Spot> boatSpots = new ArrayList<>();
    private int nbSpots;

    public int getNbSpots() {
        return nbSpots;
    }

    public void setNbSpots(int nbSpots) {
        this.nbSpots = nbSpots;
    }

    public boolean isSinked() {
        return isSinked;
    }

    public void setIsSinked(boolean sinked) {
        isSinked = sinked;
    }

    public List<Spot> getBoatSpots() {
        return boatSpots;
    }

    public void setBoatSpots(List<Spot> boatSpots) {
        this.boatSpots = boatSpots;
    }
}
