import java.util.*;

public class Simulation {
    private volatile boolean isStartSimulation;
    private int counter;
    private MapSimulation mapSimulation;
    private MapRenderer renderer;
    private Actions actions;
    private Scanner scanner = new Scanner(System.in);

    public Simulation() {

    }

    public void start() throws InterruptedException {
        MessagePrinter.welcome();

        while (true) {
            MessagePrinter.inputSizeMap();
            int choiceSizeMap = scanner.nextInt();

            if (choiceSizeMap == 1) {
                mapSimulation = new MapSimulation();
                break;
            } else if (choiceSizeMap == 2) {
                while (true) {
                    System.out.println("Input width and height (no less than 10 and no more than 50): ");
                    int width = scanner.nextInt();
                    int height = scanner.nextInt();

                    if (width > 50 || width < 10 || height > 50 || height < 10) {
                        MessagePrinter.incorrectValue();
                        continue;
                    }

                    mapSimulation = new MapSimulation(width, height);
                    break;
                }
                break;
            } else {
                MessagePrinter.incorrectValue();
            }
        }

        renderer = new MapRenderer(mapSimulation);
        actions = new Actions(mapSimulation);
        actions.initActions();
        BreadthFirstSearch.setMapSimulation(mapSimulation);

        while (true) {
            MessagePrinter.choiceAction();

            int choiceAction = scanner.nextInt();

            if (choiceAction == 1) {
                renderer.render(mapSimulation.getMap());
                MessagePrinter.outputCounterTurn(counter);
                nextTurn();
            } else if (choiceAction == 2) {
                isStartSimulation = true;
                startSimulation();
            } else if (choiceAction == 3) {
                return;
            } else {
                MessagePrinter.incorrectValue();
            }
        }

    }

    private void startSimulation() throws InterruptedException {
        Thread endlessSimulation = new Thread(() -> {
            while (isStartSimulation) {
                renderer.render(mapSimulation.getMap());
                MessagePrinter.outputCounterTurn(counter);
                MessagePrinter.stopSimulation();
                nextTurn();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        endlessSimulation.start();

        scanner.nextLine();
        scanner.nextLine();
        pauseSimulation();

        endlessSimulation.join();
    }

    private void nextTurn() {
        counter++;

        actions.addGrass(mapSimulation.getMap());

        List<Creature> creatures = new ArrayList<>();
        for (Entity entity : mapSimulation.getMap().values()) {
            if (entity instanceof Herbivore || entity instanceof Predator) {
                creatures.add((Creature) entity);
            }
        }

        for (Creature creature : creatures) {
            creature.makeMove(mapSimulation.getMap());
        }
    }

    private void pauseSimulation() {
        isStartSimulation = false;
    }

}
