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

    public void play() {
    }
}
