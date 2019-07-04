package com.company;

import com.company.AircraftCarriers;
import com.company.Cruisers;
import com.company.Destroyers;
import com.company.Submarine;

import java.lang.reflect.Array;
import java.util.*;

import static com.company.Direction.LEFT;

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
        for (Boat boat : boats) {
            this.boatPlacer(boat);
        }
        for (int row = 0; row < 10; row++) {
            
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

        int nbSpot = boat.getNbSpots();
        int row = (int) Math.floor(Math.random() * 10);
        int column = (int) Math.floor(Math.random() * 10);
        Spot randomSpot = gridSpots[row][column];
        List<Spot> boatSpots = new ArrayList<>();
        BoatSpot boatSpot;

        Collections.shuffle(this.directions);

            switch (this.directions.get(0)) {
                case LEFT :
                    if (row-nbSpot >=0) {
                        // loop sur le nombre de spot du bateau
                        for (int spotNum = 0; spotNum < nbSpot; spotNum++) {

                            boatSpots.add();
                        }
                        boat.setBoatSpots(boatSpots);
                        break;
                    }
            }




    }
}
