package com.company;

public class BoatSpot extends Spot {
    private Boat boat;

    public BoatSpot (Boat boat) {
        this.boat = boat;
    }
    public String toString () {
        return getBoat().toString();
    }

    public Boat getBoat() {
        return boat;
    }

    public void setBoat(Boat boat) {
        this.boat = boat;
    }
}
