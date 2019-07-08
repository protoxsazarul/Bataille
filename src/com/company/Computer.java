package com.company;

import java.util.Random;

public class Computer extends Player {

    public Computer(){
        setName("CPU");
    }

    public String getChoice() {
        return this.randomChoice();
    }

    public String toString(String choice){
        return "L'autre abruti de CPU a lancé une attaque sur "+ choice;
    }

    public String randomChoice() {
        Random random = new Random();
        int coordNum = random.nextInt(10);
        int randLetter = random.nextInt(10);
        String coordLetter = Constants.REVERSE_MAPPING.get(randLetter);
        String coord = coordLetter+coordNum;
        return coord;
    }
}
