import Nodes.Node;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Parser parser = new Parser();
        Node expr = parser.parseExpression("4 + 5 * (3+x)");
        Map<String, Double> variables = Map.of("x", 1.0, "y", 4.0);
        System.out.println("Temp: " + expr);
        System.out.println("Result: " + expr.evaluate(variables));
    }
}