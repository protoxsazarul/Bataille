package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.company.Direction.DOWN;

public class Grid {
    private Player player;
    private int nbRows = 10;
    private int nbColumns = 10;
    private Spot[][] gridSpots = new Spot[this.nbRows][this.nbColumns];
    private Boat[] boats = new Boat[]{ new AircraftCarriers(), new Cruisers(), new Destroyers(), new Submarine(), new Submarine() };
    private Map<String, Integer> mappingCoord;
    private List<Direction> directions = new ArrayList<>();

    public Spot[][] getGridSpots() {
        return gridSpots;
    }

    public void setGridSpots(Spot[][] gridSpots) {
        this.gridSpots = gridSpots;
    }

    public Grid () {
        directions.add(Direction.LEFT);
        directions.add(Direction.RIGHT);
        directions.add(Direction.UP);
        directions.add(Direction.DOWN);
        for (int row = 0; row < gridSpots.length; row++) {
            for (int col = 0; col < gridSpots[row].length; col++) {
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
            int row = (int) Math.floor(Math.random() * this.nbRows);
            int column = (int) Math.floor(Math.random() * this.nbColumns);
            Spot randomSpot = gridSpots[row][column];
            List<Spot>boatSpots = new ArrayList<>();

            Collections.shuffle(this.directions);

            switch (this.directions.get(0)) {
                case LEFT:
                    if (row - nbSpot >= 0) { // si le bateau entier rentre dans la grille
                        isPossibleBoatPlacement = true;

                        for (int x = 0; x < nbSpot; x++) {
                            Spot currentSpot = gridSpots[row - x][column];
                            boolean testSeaSpot = currentSpot instanceof SeaSpot;
                            isPossibleBoatPlacement &= testSeaSpot;
                        }
                    }

                    if(isPossibleBoatPlacement) {
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
                    if (row + nbSpot <= this.nbRows) {
                        isPossibleBoatPlacement = true;

                        for (int x = 0; x < nbSpot; x++) {
                            Spot currentSpot = gridSpots[row + x][column];
                            boolean testSeaSpot = currentSpot instanceof SeaSpot;
                            isPossibleBoatPlacement &= testSeaSpot;
                        }
                    }
                    if(isPossibleBoatPlacement) {// loop sur le nombre de spot du bateau
                        for (int x = 0; x < nbSpot; x++) {
                            BoatSpot boatSpot = new BoatSpot(boat);
                            gridSpots[row + x][column] = boatSpot;
                            boatSpots.add(boatSpot);
                        }
                        boat.setBoatSpots(boatSpots);
                    }
                    break;
                case UP:
                    if (column - nbSpot >= 0) {
                        isPossibleBoatPlacement = true;

                        for (int y = 0; y < nbSpot; y++) {
                            Spot currentSpot = gridSpots[row][column - y];
                            boolean testSeaSpot = currentSpot instanceof SeaSpot;
                            isPossibleBoatPlacement &= testSeaSpot;
                        }
                    }
                    if(isPossibleBoatPlacement) {
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
                    if (column + nbSpot <= this.nbColumns) {
                        isPossibleBoatPlacement = true;

                        for (int y = 0; y < nbSpot; y++) {
                            Spot currentSpot = gridSpots[row][column + y];
                            boolean testSeaSpot = currentSpot instanceof SeaSpot;
                            isPossibleBoatPlacement &= testSeaSpot;
                        }
                    }
                    if(isPossibleBoatPlacement) {
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
        result += "   ";
        for (int coordRow = 0; coordRow < this.nbRows; coordRow++){
            result += Constants.REVERSE_MAPPING.get(coordRow) + " ";
        }
        result += "\n";
        for (int row = 0; row < this.nbRows; row++) {
            result += row + " |";
            for (int col = 0; col < this.nbColumns; col++) {
                result += gridSpots[row][col] + "|";
            }
            result += "\n";
        }
        return result;
    }

    public String displayPlayerTouchedGrid () {
        String result = "";
        result += "   ";
        for (int coordRow = 0; coordRow < this.nbRows; coordRow++){
            result += Constants.REVERSE_MAPPING.get(coordRow) + " ";
        }
        result += "\n";
        for (int row = 0; row < this.nbRows; row++) {
            result += row + " |";
            for (int col = 0; col < this.nbColumns; col++) {
                if (gridSpots[row][col].isTouched()) {
                    if (gridSpots[row][col] instanceof BoatSpot) {
                        result += "O|";
                    } else {
                        result += "X|";
                    }
                } else {
                    result += gridSpots[row][col] + "|";
                }
            }
            result += "\n";
        }
        return result;
    }

    public String displayTouchedSpot(){
        String result = "";
        result += "   ";
        for (int coordRow = 0; coordRow < this.nbRows; coordRow++)
        {
            result += Constants.REVERSE_MAPPING.get(coordRow) + " ";
        }
        result += "\n";
        for (int row = 0; row < this.nbRows; row++)
        {
            result += row + " |";
            for (int col = 0; col < this.nbColumns; col++)
            {
                if (!gridSpots[row][col].isTouched())
                {
                    result += "_|";
                }
                else
                {
                    if (gridSpots[row][col] instanceof BoatSpot)
                    {
                        result += "O|";
                    }
                    else
                    {
                        result += "X|";
                    }
                }

            }
            result += "\n";
        }
        return result;
    }

    public int getNbRows() {
        return nbRows;
    }

    public void setNbRows(int nbRows) {
        this.nbRows = nbRows;
    }

    public int getNbColumns() {
        return nbColumns;
    }

    public void setNbColumns(int nbColumns) {
        this.nbColumns = nbColumns;
    }
}
