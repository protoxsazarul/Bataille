import java.util.List;

public abstract class Boat {
    private boolean isSinked;
    private List<Spot> boatSpots;
    private int nbSpots;

    public int getNbSpots() {
        return nbSpots;
    }

    public void setNbSpots(int nbSpots) {
        this.nbSpots = nbSpots;
    }

    public boolean getSinked() {
        return isSinked;
    }

    public void setSinked(boolean sinked) {
        isSinked = sinked;
    }
}
