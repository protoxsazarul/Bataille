package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Computer extends Player {
    private Grid adverseGrid;
    private List<Direction>directions = new ArrayList<>();
    private int directionIndex = 0;
    private int[] firstSpotTouchedPlayedCoords = null;
    private int[] lastSpotTouchedPlayedCoords = null;

    public Computer(Grid adverseGrid){
        this.setName("CPU");
        this.adverseGrid = adverseGrid;
        for (Direction direction : Direction.values()) {
            this.directions.add(direction);
        }
    }

    public String getChoice() {
        boolean isSmartToPlay = false;
            if (firstSpotTouchedPlayedCoords == null) {
                isSmartToPlay = this.isSmartToPlay(this.spotIfIsBoatSpotTouched());
            }


        if (isSmartToPlay) {
            return this.smartChoice();
        } else {
            if (this.firstSpotTouchedPlayedCoords != null) {
                this.directionIndex++;
                System.out.println("this.directionIndex à vérif : " + this.directionIndex);
                return this.smartChoice();
            }
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
            if (this.directionIndex > 3) { // à tester si on enlève la condition
                return this.randomChoice();
            } else {
                System.out.println("last coord shot : " + Constants.REVERSE_MAPPING.get(this.lastSpotTouchedPlayedCoords[0]) + this.lastSpotTouchedPlayedCoords[1]);
                int row = this.lastSpotTouchedPlayedCoords[1];
                int col = this.lastSpotTouchedPlayedCoords[0];
                switch (this.directions.get(this.directionIndex)) {
                    case LEFT:
                        col--;
                        break;
                    case RIGHT:
                        col++;
                        break;
                    case UP:
                        row--;
                        break;
                    case DOWN:
                        row++;
                        break;
                }
                this.lastSpotTouchedPlayedCoords = new int[]{row, col};
                String coordLetter = Constants.REVERSE_MAPPING.get(col);
                String coord = coordLetter + row;
                System.out.println(coord);
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
                        this.firstSpotTouchedPlayedCoords = new int[]{col, row};
                        if (this.lastSpotTouchedPlayedCoords == null) {
                            this.lastSpotTouchedPlayedCoords = this.firstSpotTouchedPlayedCoords;
                        }
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
            this.firstSpotTouchedPlayedCoords = null;
            this.lastSpotTouchedPlayedCoords = null;
            return false;
        } else {
            return true;
        }
    }
}
