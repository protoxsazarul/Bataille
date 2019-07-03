package com.company;

import java.util.List;

public abstract class Spot {
    private boolean isTouched;

    public boolean isTouched() {
        return isTouched;
    }

    public void setTouched(boolean isTouched) {
        this.isTouched = isTouched;
    }

    public List<Spot> getSpots() {
        return null;
    }


}
