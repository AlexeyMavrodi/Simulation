import java.util.List;
import java.util.Map;

public class Herbivore extends Creature {

    public Herbivore(Coordinates coordinates, int speed, int health) {
        super(coordinates, speed, health);
    }

    public Herbivore() {};

    @Override
    public void makeMove(Map<Coordinates, Entity> map) {
        List<Coordinates> path = BreadthFirstSearch.bfs(this.coordinates, new Grass(), map);

        if (!path.isEmpty()) {
            path.removeFirst();
        }

        Coordinates coordinates = this.coordinates;

        int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        for (int[] xy : directions) {
            int nx = coordinates.x() + xy[0];
            int ny = coordinates.y() + xy[1];
            Coordinates temp = new Coordinates(nx, ny);

            if (map.containsKey(temp) && map.get(temp) instanceof Grass) {
                Entity entity = map.get(temp);
                Grass grass = (Grass) entity;
                this.health += grass.getCountHealing();

                if (this.health > 100) {
                    this.health = 100;
                }

                map.remove(temp);
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
