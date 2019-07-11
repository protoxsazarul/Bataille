package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Computer extends Player {
    private Grid adverseGrid;
    private List<Direction>directions = new ArrayList<>();
    private int directionIndex = 0;
    private int[]spotTouchedPlayedCoords = null;

    public Computer(Grid adverseGrid){
        this.setName("CPU");
        this.adverseGrid = adverseGrid;
        for (Direction direction : Direction.values()) {
            this.directions.add(direction);
        }
    }

    public String getChoice() {
        boolean isSmartToPlay = this.isSmartToPlay(this.spotIfIsBoatSpotTouched());

        if (isSmartToPlay) {
            return this.smartChoice();
        } else if (this.spotTouchedPlayedCoords != null) {
            this.directionIndex++;
            return this.smartChoice();
        } else {
            return this.randomChoice();
        }
    }

    private String randomChoice() {

        Collections.shuffle(this.directions);
        this.directionIndex = 0;

        Random random = new Random();
        int coordNum = random.nextInt(this.adverseGrid.getNbRows());
        int randLetter = random.nextInt(this.adverseGrid.getNbColumns());
        String coordLetter = Constants.REVERSE_MAPPING.get(randLetter);
        String coord = coordLetter + coordNum;
        return coord;
    }

    private String smartChoice() {
            if (this.directionIndex > 3) {
                return this.randomChoice();
            } else {
                System.out.println("row : " + this.spotTouchedPlayedCoords[0] + " col : " + this.spotTouchedPlayedCoords[1]);
                /*switch (this.directions.get(this.directionIndex)) {
                    case LEFT:
                        break;
                }*/
                String coordLetter = Constants.REVERSE_MAPPING.get(this.spotTouchedPlayedCoords[1]);
                String coord = coordLetter + this.spotTouchedPlayedCoords[0];
                return coord;
            }
    }

    private Spot spotIfIsBoatSpotTouched() {
        Spot[][] adverseGridSpots = this.adverseGrid.getGridSpots();
        Spot spotPlayed;

        for (int row = 0; row < adverseGridSpots.length; row++) {
            for (int col = 0; col < adverseGridSpots[row].length; col++) {
                spotPlayed = adverseGridSpots[row][col];
                if (spotPlayed.isTouched() && spotPlayed instanceof BoatSpot) {
                    if (this.directionIndex == 0) {
                        this.spotTouchedPlayedCoords = new int[]{col+1, row+1};
                    }
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
            this.spotTouchedPlayedCoords = null;
            return false;
        } else {
            return true;
        }
    }
}
