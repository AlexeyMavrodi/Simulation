import java.util.Map;
import java.util.Random;

public class Actions {
    private final static Random RANDOM = new Random();
    private MapSimulation mapSimulation;

    public Actions(MapSimulation mapSimulation) {
        this.mapSimulation = mapSimulation;
    }

    public void initActions() {
        Map<Coordinates, Entity> map = mapSimulation.getMap();

        addTree(map);
        addRock(map);

        for (int i = 0; i < 10; i++) {
            addGrass(map);
            addGrass(map);
            addHerbivore(map);
        }

        addPredator(map);
    }

    private void addTree(Map<Coordinates, Entity> map) {
        for (int i = 0; i < 15; i++) {
            Coordinates coordinates = generateRandomCoordinates();

            if (map.containsKey(coordinates)) {
                i--;
                continue;
            }

            map.put(coordinates, new Tree(coordinates));
        }
    }

    private void addRock(Map<Coordinates, Entity> map) {
        for (int i = 0; i < 15; i++) {
            Coordinates coordinates = generateRandomCoordinates();

            if (map.containsKey(coordinates)) {
                i--;
                continue;
            }

            map.put(coordinates, new Rock(coordinates));
        }
    }

    public void addGrass(Map<Coordinates, Entity> map) {
        while (true) {
            Coordinates coordinates = generateRandomCoordinates();

            if (map.containsKey(coordinates)) {;
                continue;
            }

            map.put(coordinates, new Grass(coordinates));
            break;
        }
    }

    private void addHerbivore(Map<Coordinates, Entity> map) {
        while (true) {
            Coordinates coordinates = generateRandomCoordinates();

            if (map.containsKey(coordinates)) {
                continue;
            }

            map.put(coordinates, new Herbivore(coordinates, 3, 100));
            break;
        }
    }

    private void addPredator(Map<Coordinates, Entity> map) {
        for (int i = 0; i < 5; i++) {
            Coordinates coordinates = generateRandomCoordinates();

            if (map.containsKey(coordinates)) {
                i--;
                continue;
            }

            map.put(coordinates, new Predator(coordinates, 2, 100, 25));
        }
    }

    private Coordinates generateRandomCoordinates() {
        return new Coordinates(RANDOM.nextInt(mapSimulation.getWIDTH()), RANDOM.nextInt(mapSimulation.getHEIGHT()));
    }

}
