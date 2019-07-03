public class Game {
  private Grid grid1;
  private Grid grid2;
  private Grid currentGrid;


  public void isLooser(Grid grid) {
    if (grid.checkBoats()) {
      System.out.println(currentGrid + "a perdu");
    }
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
   * L'autre Grid isWinner
   * checkVictory true
   * GG !
   */
  public void play() {

  }
}
