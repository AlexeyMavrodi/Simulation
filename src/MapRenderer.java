import java.util.Map;

public class MapRenderer {
    private static final String TREE = "\uD83C\uDF33 ";
    private static final String ROCK = "\uD83D\uDDFF ";
    private static final String GRASS = "\uD83C\uDF3F ";
    private static final String HERBIVORE = "\uD83E\uDD93 ";
    private static final String PREDATOR = "\uD83E\uDD81 ";
    private static final String EMPTY = " _ ";

    private final MapSimulation mapSimulation;

    public MapRenderer(MapSimulation mapSimulation) {
        this.mapSimulation = mapSimulation;
    }

    public void render(Map<Coordinates, Entity> map) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < mapSimulation.getHEIGHT(); i++) {
            for (int j = 0; j < mapSimulation.getWIDTH(); j++) {
                Coordinates coordinates = new Coordinates(j, i);

                if (map.containsKey(coordinates)) {
                    result.append(getSpriteEntity(map.get(coordinates)));
                } else {
                    result.append(EMPTY);
                }

            }
            result.append("\n");
        }

        System.out.println(result);
    }

    private String getSpriteEntity(Entity entity) {
        return switch (entity.getClass().getSimpleName()) {
            case "Tree" -> TREE;
            case "Rock" -> ROCK;
            case "Grass" -> GRASS;
            case "Herbivore" -> HERBIVORE;
            case "Predator" -> PREDATOR;
            default -> throw new RuntimeException();
        };
    }




}
