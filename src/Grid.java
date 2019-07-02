import java.util.Map;

public class Grid {
    private Player player;
    private Spot[][] spots = new Spot[10][10];
    private Boat[] boats = new Boat[5];
    private Map<String, Integer> mappingCoord;

    public boolean spotAlreadyPlayed(Spot spot) {
        return spot.isTouched();
    }

    /**
     *
     * @return vrai tant qu'il reste au moins un bateau non coulé
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

    }
}
