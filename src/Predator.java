import java.util.List;
import java.util.Map;

public class Predator extends Creature {
    private int attackPower;

    public Predator(Coordinates coordinates, int speed, int health, int attackPower) {
        super(coordinates, speed, health);
        this.attackPower = attackPower;
    }

    @Override
    public void makeMove(Map<Coordinates, Entity> map) {
        List<Coordinates> path = BreadthFirstSearch.bfs(this.coordinates, new Herbivore(), map);

        if (!path.isEmpty()) {
            path.removeFirst();
        }

        Coordinates coordinates = this.coordinates;

        int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        for (int[] xy : directions) {
            int nx = coordinates.x() + xy[0];
            int ny = coordinates.y() + xy[1];
            Coordinates temp = new Coordinates(nx, ny);

            if (map.containsKey(temp) && map.get(temp) instanceof Herbivore) {
                Entity entity = map.get(temp);
                Herbivore herbivore = (Herbivore) entity;
                herbivore.setHealth(herbivore.getHealth() - this.attackPower);

                if (herbivore.getHealth() <= 0) {
                    map.remove(temp);
                }

                return;
            }
        }

        if (!path.isEmpty()) {
            int speed = this.speed;
            for (int i = path.size() - 2; i >= 0; i--) {
                map.remove(coordinates);
                coordinates = path.get(i);
                map.put(path.get(i), this);
                this.coordinates = path.get(i);

                speed--;

                if (speed == 0) {
                    break;
                }
            }
        }
    }
}
