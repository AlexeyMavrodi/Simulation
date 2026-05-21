public class MessagePrinter {

    public static void welcome() {
        System.out.println("Welcome to simulation!\n");
    }

    public static void choiceAction() {
        System.out.println("Input value:\n1. Simulate one move\n2. Run endless simulation\n3. Exit");
    }

    public static void inputSizeMap() {
        System.out.println("Input size map:\n1. Default (25x25)\n2. Specify yours");
    }

    public static void outputCounterTurn(int counter) {
        System.out.println("Number of moves: " + counter);
    }

    public static void stopSimulation() {
        System.out.println("Input any symbols if want stop.");
    }

    public static void incorrectValue() {
        System.out.println("Incorrect value! Try again.");
    }
}
