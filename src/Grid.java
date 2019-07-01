import java.util.List;

public class Grid {
    private Player player;
    private Spot[][] spots = new Spot[10][10];
    private boolean hasPlayed;
    private Boat[]  boats = new Boat[5];

    public boolean isHasPlayed() {
        return hasPlayed;
    }

    public void setHasPlayed(boolean hasPlayed) {
        this.hasPlayed = hasPlayed;
    }

    private boolean checkBoats() {
        return false;
    }
}
