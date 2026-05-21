import java.util.*;

public class BreadthFirstSearch {

    private static MapSimulation mapSimulation;

    public static void setMapSimulation(MapSimulation mapSimulation) {
        BreadthFirstSearch.mapSimulation = mapSimulation;
    }

    public static boolean isInBound(Coordinates coordinates) {
        int x = coordinates.x();
        int y = coordinates.y();

        return x >= 0 && x < mapSimulation.getWIDTH() && y >= 0 && y < mapSimulation.getHEIGHT();
    }

    private static List<Coordinates> restorePath(Map<String, Coordinates> prev, Coordinates end) {
        List<Coordinates> path = new ArrayList<>();
        Coordinates cur = end;

        while(!(cur.y() == -1 && cur.x() == -1)) {
            int x = cur.x(), y = cur.y();
            path.add(cur);
            cur = prev.get(key(y, x));
        }

        return path;
    }

    public static List<Coordinates> bfs(Coordinates coordinates, Entity entity, Map<Coordinates, Entity> map) {
        boolean[][] usedCoordinates = new boolean[mapSimulation.getHEIGHT()][mapSimulation.getWIDTH()];
        Queue<Coordinates> queueCoordinates = new LinkedList<>();
        Map<String, Coordinates> prev = new HashMap<>();

        prev.put(key(coordinates.y(), coordinates.x()), new Coordinates(-1, -1));

        usedCoordinates[coordinates.y()][coordinates.x()] = true;
        queueCoordinates.add(coordinates);

        addDirections(coordinates, usedCoordinates, queueCoordinates, prev);

        while (!queueCoordinates.isEmpty()) {
            coordinates = queueCoordinates.poll();

            if (map.containsKey(coordinates)) {
                if (map.get(coordinates).getClass().getSimpleName().equals(entity.getClass().getSimpleName())) {
                    return restorePath(prev, coordinates);
                }
                continue;
            }

            addDirections(coordinates, usedCoordinates, queueCoordinates, prev);

        }

        return new ArrayList<>();
    }

    private static void addDirections(Coordinates coordinates, boolean[][] usedCoordinates, Queue<Coordinates> queueCoordinates, Map<String, Coordinates> prev) {
        int[][] directions = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};
        int x = coordinates.x(), y = coordinates.y();

        for (int[] d : directions) {
            int nx = x + d[0], ny = y + d[1];

            Coordinates temp = new Coordinates(nx, ny);

            if (isInBound(temp) && !usedCoordinates[ny][nx]) {
                usedCoordinates[ny][nx] = true;
                prev.put(key(ny, nx), coordinates);
                queueCoordinates.add(temp);
            }
        }
    }

    private static String key(int y, int x) {
        return y + "," + x;
    }

}
