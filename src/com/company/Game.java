package com.company;

import java.util.Random;

public class Game {
  private Grid grid1;
  private Grid grid2;
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
    } else if (whoPlays == 1){
      currentGrid = grid2;
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
  }
  public void play(){
    Human player1 = new Human();
    player1.setName(Constants.SCAN.nextLine());
    grid1.setPlayer(player1);
    Computer player2 = new Computer();
    grid2.setPlayer(player2);
    firstPlayer();
    System.out.println("La bataille commence, voici votre grille");
    System.out.println(grid1);
    if (player1.getName().equals("cheater")) {
      System.out.println("Vu que tu es un petit rageux, voici la grille du CPU");
      System.out.println(grid2);
    } else if (player1.getName().toUpperCase().equals("QUENTIN")
            || player1.getName().toUpperCase().equals("PROTOXS")
            || player1.getName().toUpperCase().equals("PROTOXSAZARUL")){
      System.out.println("Ok Quentin... Ne rage pas si tu perds !");
    }
    while (!isLooser(currentGrid)) {
      Spot currentSpot = null; // Le spot choisi par le joueur
      String currentChoice = null;
      do {
        currentChoice = currentGrid.getPlayer().getChoice();

        if (currentSpot.isTouched()){
          System.out.println("T'as déjà tiré ici gros débile");
        }
        //Tir sur la case
        System.out.println(currentGrid + " a envoyé la sauce sur l'emplacement " + currentChoice);
      } while (currentSpot.isTouched());
       currentSpot.setTouched(true);
       if (currentSpot instanceof SeaSpot){
         System.out.println("Raté sale noob");
         changePlayer();
       } else if (currentSpot instanceof BoatSpot){
         System.out.println("Touché");
        if(((BoatSpot) currentSpot).getBoat().isSinked()){
          System.out.println("Coulé");
          if (isLooser(adverseGrid)){
            System.out.println(  currentGrid+" a gagné !" );
          } else {
            changePlayer();
          }
        } else {
          changePlayer();
        }
       }
    }
    System.out.println("Partie terminée, bravo Colonel.");
  }

}
