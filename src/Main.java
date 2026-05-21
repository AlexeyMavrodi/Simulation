
public class Main {

    public static void main(String[] args) {
        Simulation simulation = new Simulation();

        try {
            simulation.start();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

    }
}
