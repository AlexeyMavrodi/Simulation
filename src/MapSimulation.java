import java.util.HashMap;
import java.util.Map;

public class MapSimulation {
    private final int WIDTH;
    private final int HEIGHT;

    private final Map<Coordinates, Entity> map = new HashMap<>();

    public MapSimulation(int width, int height) {
        this.WIDTH = width;
        this.HEIGHT = height;
    }

    public MapSimulation() {
        this(25, 25);
    }

    public Map<Coordinates, Entity> getMap() {
        return map;
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }
}
