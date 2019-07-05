package com.company;


public abstract class Player {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
    public boolean isWinner() {
        return false;
    }

    protected abstract String getChoice();
}
