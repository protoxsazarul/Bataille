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

    public boolean getIsSinked() {
        return isSinked;
    }

    public void setIsSinked(boolean sinked) {
        isSinked = sinked;
    }

    public List<Spot> getBoatSpots() {
        return boatSpots;
    }

    public void setBoatSpots(List<Spot> boatSpots) {
        this.boatSpots = boatSpots;
    }
    
    public void setUpdateSinked(List<Boat> boat){
        for (int i=0; i<boat.length; i++)
        {
            type var = boat[i];
            statements using var;
        }
            
        }
    }
}
