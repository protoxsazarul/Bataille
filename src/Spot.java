import java.util.List;

public abstract class Spot {
    private static boolean isTouched;

    public static boolean getIsTouched() {
        return isTouched;
    }

    public static void setIsTouched(boolean isTouched) {
        Spot.isTouched = isTouched;
    }

    public List<Spot> getSpots() {
        return null;
    }


}
