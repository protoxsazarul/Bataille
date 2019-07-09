package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Computer extends Player {
    private Grid adverseGrid;
    private List<Direction>directions = new ArrayList<>();

    public Computer(Grid adverseGrid){
        this.setName("CPU");
        this.adverseGrid = adverseGrid;
        for (Direction direction : directions) {
            this.directions.add(direction);
        }
    }

    public String getChoice() {
        boolean isSmartToPlay = this.isSmartToPlay(this.spotIfIsBoatSpotTouched());

        if (isSmartToPlay) {
            return this.smartChoice();
        } else {
            return this.randomChoice();
        }
    }

    private String randomChoice() {
        Random random = new Random();
        int coordNum = random.nextInt(10);
        int randLetter = random.nextInt(10);
        String coordLetter = Constants.REVERSE_MAPPING.get(randLetter);
        String coord = coordLetter + coordNum;
        return coord;
    }

    private String smartChoice() {
        return  "";
    }

    private Spot spotIfIsBoatSpotTouched() {
        Spot[][] adverseGridSpots = this.adverseGrid.getGridSpots();
        Spot spotPlayed;

        for (int row = 0; row < adverseGridSpots.length; row++) {
            for (int col = 0; col < adverseGridSpots[row].length; col++) {
                spotPlayed = adverseGridSpots[row][col];
                if (spotPlayed.isTouched() && spotPlayed instanceof BoatSpot) {
                    return spotPlayed;
                }
            }
        }
        return null;
    }

    private boolean isSmartToPlay(Spot spot) {
        if (spot == null) {
            return false;
        } else if (((BoatSpot) spot).getBoat().isSinked()) {
            return false;
        } else {
            return true;
        }
    }
}
