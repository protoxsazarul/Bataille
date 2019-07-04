package com.company;

import java.util.Random;

public class Computer extends Player {

    public String getChoice() {
        Random random = new Random();
        int coordNum = random.nextInt(10);
        int randLetter = random.nextInt(10);
        String coordLetter = Constants.REVERSE_MAPPING.get(randLetter);
        String coord = coordLetter+coordNum;
        return coord;
    }
    public String toString(String choice){
        return "L'autre abruti de CPU a lancé une attaque sur "+ choice;
    }
}
