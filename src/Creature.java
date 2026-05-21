import java.util.Map;

public abstract class Creature extends Entity {
    protected int speed;
    protected int health;

    public Creature(Coordinates coordinates, int speed, int health) {
        super(coordinates);
        this.speed = speed;
        this.health = health;
    }

    public Creature() {};

    public abstract void makeMove(Map<Coordinates, Entity> map);

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
