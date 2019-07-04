package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Grid {
    private Player player;
    private Spot[][] gridSpots = new Spot[10][10];
    private Boat[] boats = new Boat[]{ new AircraftCarriers(), new Cruisers(), new Destroyers(), new Submarine(), new Submarine() };
    private Map<String, Integer> mappingCoord;
    private List<Direction> directions = new ArrayList<>();

    public Grid () {
        directions.add(Direction.LEFT);
        directions.add(Direction.RIGHT);
        directions.add(Direction.UP);
        directions.add(Direction.DOWN);
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                gridSpots[row][col] = new SeaSpot();
            }
        }
        for (Boat boat : boats) {
            this.boatPlacer(boat);
        }
    }

    public Player getPlayer() {
        return player;
    }

    public Boat[] getBoats() {
        return boats;
    }

    public void setBoats(Boat[] boats) {
        this.boats = boats;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public boolean spotAlreadyPlayed(Spot spot) {
        return spot.isTouched();
    }

    /**
     *
     * @return vrai quand tous les bateaux sont coulés
     */
    public boolean checkBoats() {

        boolean result = true;
        for (Boat boat : boats) {
            result &= !(boat.isSinked()) ;
        }
        return result;
    }

    /**
     * définit les spots sur lesquels un bateau est placé
     *
     * @param boat le bateau à placer
     */
    private void boatPlacer(Boat boat) {

        /*
        Regarder le nombre de spot à définir
        Selection aléatoire d'une case de la grid
        selection aléatoire de l'orientation du bateau
        boucler si une des cases est deja prise ou si il n'y pas la place pour le boat
        si True alors créer la liste des spots (boatSpots) :
            - Boucler par rapport au nombre Boat.nbSpots
            - Aujouter les coordonnées à la liste de spot (boatSpots)
            - lancer le setter de Boat.boatSpots (boatSpots)
        */

        boolean isPossibleBoatPlacement = false;

        do {
            int nbSpot = boat.getNbSpots();
            int row = (int) Math.floor(Math.random() * 10);
            int column = (int) Math.floor(Math.random() * 10);
            Spot randomSpot = gridSpots[row][column];
            List<Spot> boatSpots = new ArrayList<>();

            Collections.shuffle(this.directions);

            switch (this.directions.get(0)) {
                case LEFT :
                    System.out.println("left");
                    if (row - nbSpot >=0) {
                        for (int x = 0; x < nbSpot; x++) {
                            Spot currentSpot = gridSpots[row - x][column];
                            isPossibleBoatPlacement = true;
                            boolean testSeaSpot = currentSpot instanceof SeaSpot;
                            isPossibleBoatPlacement &= testSeaSpot;
                        }
                        // loop sur le nombre de spot du bateau
                        for (int x = 0; x < nbSpot; x++) {
                            BoatSpot boatSpot = new BoatSpot(boat);
                            gridSpots[row - x][column] = boatSpot;
                            boatSpots.add(boatSpot);
                        }
                        boat.setBoatSpots(boatSpots);
                    }
                    break;
                case RIGHT:
                    System.out.println("right");
                    if (row + nbSpot <= 9) {
                        for (int x = 0; x < nbSpot; x++) {
                            Spot currentSpot = gridSpots[row + x][column];
                            isPossibleBoatPlacement = true;
                            boolean testSeaSpot = currentSpot instanceof SeaSpot;
                            isPossibleBoatPlacement &= testSeaSpot;
                        }                        // loop sur le nombre de spot du bateau
                        for (int x = 0; x < nbSpot; x++) {
                            BoatSpot boatSpot = new BoatSpot(boat);
                            gridSpots[row + x][column] = boatSpot;
                            boatSpots.add(boatSpot);
                        }
                        boat.setBoatSpots(boatSpots);
                    }
                    break;
                case UP:
                    System.out.println("up");
                    if (column - nbSpot >= 0) {
                        for (int y = 0; y < nbSpot; y++) {
                            Spot currentSpot = gridSpots[row][column - y];
                            isPossibleBoatPlacement = true;
                            boolean testSeaSpot = currentSpot instanceof SeaSpot;
                            isPossibleBoatPlacement &= testSeaSpot;
                        }
                        // loop sur le nombre de spot du bateau
                        for (int y = 0; y < nbSpot; y++) {
                            BoatSpot boatSpot = new BoatSpot(boat);
                            gridSpots[row][column - y] = boatSpot;
                            boatSpots.add(boatSpot);
                        }
                        boat.setBoatSpots(boatSpots);
                    }
                    break;
                case DOWN:
                    System.out.println("down");
                    if (column + nbSpot <= 9) {
                        for (int y = 0; y < nbSpot; y++) {
                            Spot currentSpot = gridSpots[row][column + y];
                            isPossibleBoatPlacement = true;
                            boolean testSeaSpot = currentSpot instanceof SeaSpot;
                            isPossibleBoatPlacement &= testSeaSpot;
                        }
                        // loop sur le nombre de spot du bateau
                        for (int y = 0; y < nbSpot; y++) {
                            BoatSpot boatSpot = new BoatSpot(boat);
                            gridSpots[row][column + y] = boatSpot;
                            boatSpots.add(boatSpot);
                        }
                        boat.setBoatSpots(boatSpots);
                    }
                    break;
            }
        } while (!isPossibleBoatPlacement);
    }

    public String toString () {
        String result = "";
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                result += gridSpots[row][col];
            }
            result += "\n";
        }
        return result;
    }
}
