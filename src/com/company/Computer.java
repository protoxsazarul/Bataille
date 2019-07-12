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

    public Computer(Grid adverseGrid) {
        this.setName("CPU");
        this.adverseGrid = adverseGrid;
        for (Direction direction : Direction.values()) {
            this.directions.add(direction);
        }
    }

    public String getChoice() {
        boolean isSmartToPlay = false;
        Spot lastSpot;
        BoatSpot lastSpotAsBoatSpot;
        try {
            lastSpot = adverseGrid.getGridSpots()[this.lastSpotTouchedPlayedCoords[1]][this.lastSpotTouchedPlayedCoords[0]];
            lastSpotAsBoatSpot = (BoatSpot) lastSpot;
        } catch (Exception error) {
            lastSpot = null;
            lastSpotAsBoatSpot = null;
        }
            if (firstSpotTouchedPlayedCoords == null) {
                isSmartToPlay = this.isSmartToPlay(this.spotIfIsBoatSpotTouched());
            }

        if (isSmartToPlay) {
            System.out.println("Smart");
            return this.smartChoice();
        } else {
            if (this.firstSpotTouchedPlayedCoords != null
                    && lastSpot instanceof SeaSpot) {
                System.out.println("Smart++");
                this.directionIndex++;
                System.out.println("this.directionIndex à vérif : " + this.directionIndex);
                return this.smartChoice();
            } else if (this.firstSpotTouchedPlayedCoords != null
                    && lastSpot instanceof BoatSpot
                    && !lastSpotAsBoatSpot.getBoat().isSinked()) {
                return this.smartChoice();
            }
            System.out.println("notSmart");
            return this.randomChoice();
        }
    }

    private String randomChoice() {

        Collections.shuffle(this.directions);
        this.directionIndex = 0;
        this.firstSpotTouchedPlayedCoords = null;
        this.lastSpotTouchedPlayedCoords = null;

        Random random = new Random();
        int coordNum = random.nextInt(this.adverseGrid.getNbRows());
        int randLetter = random.nextInt(this.adverseGrid.getNbColumns());
        String coordLetter = Constants.REVERSE_MAPPING.get(randLetter);
        String coord = coordLetter + coordNum;
        return coord;
    }

    private String smartChoice() {
            /*if (this.directionIndex > 3) { // à tester si on enlève la condition
                return this.randomChoice();
            } else {*/
                System.out.println("direction : " + this.directions.get(this.directionIndex));
                System.out.println("first coord shot : " + Constants.REVERSE_MAPPING.get(this.firstSpotTouchedPlayedCoords[0]) + this.firstSpotTouchedPlayedCoords[1]);
                System.out.println("last coord shot : " + Constants.REVERSE_MAPPING.get(this.lastSpotTouchedPlayedCoords[0]) + this.lastSpotTouchedPlayedCoords[1]);
                int row;
                int col;
                if (adverseGrid.getGridSpots()[this.lastSpotTouchedPlayedCoords[1]][this.lastSpotTouchedPlayedCoords[0]] instanceof BoatSpot) {
                    row = this.lastSpotTouchedPlayedCoords[1];
                    col = this.lastSpotTouchedPlayedCoords[0];
                } else {
                    row = this.firstSpotTouchedPlayedCoords[1];
                    col = this.firstSpotTouchedPlayedCoords[0];
                }
                do {
                    switch (this.directions.get(this.directionIndex)) {
                        case LEFT:
                            System.out.println("Case LEFT");
                            col--;
                            break;
                        case RIGHT:
                            System.out.println("Case RIGHT");
                            col++;
                            break;
                        case UP:
                            System.out.println("Case UP");
                            row--;
                            break;
                        case DOWN:
                            System.out.println("Case DOWN");
                            row++;
                            break;
                    }
                    if (row < 0 || row > adverseGrid.getNbRows() || col < 0 || col > adverseGrid.getNbColumns()) {
                        this.directionIndex++;
                    }
                } while (row < 0 || row > adverseGrid.getNbRows() || col < 0 || col > adverseGrid.getNbColumns());

                this.lastSpotTouchedPlayedCoords = new int[]{col, row};
                String coordLetter = Constants.REVERSE_MAPPING.get(col);
                String coord = coordLetter + row;
                System.out.println(coord);
                return coord;
            //}
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
            this.lastSpotTouchedPlayedCoords = null;
            return false;
        } else {
            return true;
        }
    }
}
