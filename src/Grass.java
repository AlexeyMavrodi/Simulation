public class Grass extends Entity {

    private int countHealing = 20;

    public Grass(Coordinates coordinates) {
        super(coordinates);
    }

    public Grass() {};

    public int getCountHealing() {
        return countHealing;
    }
}
