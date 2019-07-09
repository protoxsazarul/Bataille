package com.company;

import sun.plugin2.gluegen.runtime.CPU;

import java.util.Random;

public class Game {
    private Grid grid1 = new Grid();
    private Grid grid2 = new Grid();
    private Grid currentGrid;
    private Grid adverseGrid;


    public boolean isLooser(Grid grid) {
        for (Boat boat : grid.getBoats())
        {
            if (!boat.isSinked()){
                return false;
            }
        }
        return true;
    }

    // faire la toString de currentGrid

    /**
     * Donner hasPlayed a une grid
     * Lancement du getChoice de la currentGrid
     * Recuperation du getChoice et envoi vers la map
     * verifier si le spot isTouched
     * Si le spot isTouched relancer getChoice
     * Sinon le spot isTouched
     * Si le spot est seaSpot, currentGrid = hasPlayed, return "Raté"
     * Si le spot est boatSpot, return "Touché", verifier si boat isSinked
     * Si le boat isSinked, return "Coulé", verifier si les autres bateaux isSinked
     * Si au moins un bateau n'est pas isSinked basculer la grid en hasPlayed
     * Changement de la currentGrid
     * relance Play
     * Si tous les bateaux sont coulés, currentGrid isLooser
     * L'autre com.company.Grid isWinner
     * checkVictory true
     * GG !
     */
    public void firstPlayer(){
        Random random = new Random();
        int whoPlays = random.nextInt(2);
        if (whoPlays == 0){
            currentGrid = grid1;
            adverseGrid = grid2;
        } else if (whoPlays == 1){
            currentGrid = grid2;
            adverseGrid = grid1;

        }
    }
    public void changePlayer() {
        if (currentGrid == grid1) {
            currentGrid = grid2;
            adverseGrid = grid1;

        } else {
            currentGrid = grid1;
            adverseGrid = grid2;
        }
        System.out.println(adverseGrid.getPlayer().getName() + " a fini son tour \n");
        System.out.println("==================================================================================\n");
        String input = "";
        do {
            System.out.println("Appuyez sur entrée pour continuer");
            input = Constants.SCAN.nextLine();
        } while (!input.equals(""));
        System.out.println("\n");
        System.out.println("______________________________________________________ \n");
        System.out.println("Au tour de " + currentGrid.getPlayer().getName());
        System.out.println("\n");
    }
    public void play(){

        Human player1 = new Human();
        System.out.println("Entre ton nom Ô Grand Maître absolu");
        player1.setName(Constants.SCAN.nextLine());
        grid1.setPlayer(player1);
        Computer player2 = new Computer(grid2);
        grid2.setPlayer(player2);
        firstPlayer();
        System.out.println("La bataille commence, voici votre grille");
        System.out.println("Grille de " + grid1.getPlayer().getName());
        System.out.println(grid1);
        if (player1.getName().equals("cheater")) {
            System.out.println("Vu que tu es un petit rageux, voici la grille du CPU");
            System.out.println("Grille de " + grid2.getPlayer().getName());
            System.out.println(grid2);
        }
        while (!isLooser(currentGrid)) {
            Spot currentSpot = null; // Le spot choisi par le joueur
            String currentChoice = null;
            String coords = "";
            do {
                currentChoice = currentGrid.getPlayer().getChoice();
                String currentCol = currentChoice.charAt(0) + "";
                int coordCol = Constants.MAPPING.get(currentCol);
                int coordRow = Integer.parseInt(currentChoice.charAt(1) + "");
                currentSpot = adverseGrid.getGridSpots()[coordRow][coordCol];
                if (currentSpot.isTouched()){
                    System.out.println("T'as déjà tiré ici gros débile");
                } else {
                    coords = currentCol + coordRow;
                }
                //Tir sur la case
            } while (currentSpot.isTouched());
            System.out.println(currentGrid.getPlayer().getName() + " a tiré sur " + coords + "\n");
            currentSpot.setTouched(true);

            if (currentSpot instanceof SeaSpot){
                System.out.println("========= RATÉ =========");
                changePlayer();
            } else if (currentSpot instanceof BoatSpot){
                System.out.println("========= TOUCHÉ =========");
                ((BoatSpot) currentSpot).getBoat().setUpdateSinked();
                if(((BoatSpot) currentSpot).getBoat().isSinked()){
                    System.out.println("========= COULÉ =========");
                    if (isLooser(adverseGrid)){
                        System.out.println("========= " + currentGrid.getPlayer().getName()+" a gagné ! =========" );
                        changePlayer();
                    } else {
                        changePlayer();
                    }
                } else {
                    changePlayer();
                }
            }
            if (currentGrid.getPlayer() instanceof Human) {
                System.out.println("grille de l'adversaire \n");
                System.out.println(adverseGrid.displayTouchedSpot() + "\n");
                System.out.println("_______________________________________\n");
                System.out.println("grille de " + currentGrid.getPlayer().getName()+ "\n");
                System.out.println(currentGrid.displayPlayerTouchedGrid() + "\n");
            }
        }
        System.out.println("Partie terminée, bravo Colonel.");
    }

    public Grid getCurrentGrid() {
        return currentGrid;
    }

    public void setCurrentGrid(Grid currentGrid) {
        this.currentGrid = currentGrid;
    }

    public Grid getAdverseGrid() {
        return adverseGrid;
    }

    public void setAdverseGrid(Grid adverseGrid) {
        this.adverseGrid = adverseGrid;
    }
}
