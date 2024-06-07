package Nodes;

import java.util.Map;

public class MathFunction implements Node {
    private final String functionName;
    private final Node[] arguments;

    public MathFunction(String functionName, Node... arguments) {
        this.functionName = functionName;
        this.arguments = arguments;
    }

    @Override
    public double evaluate(Map<String, Double> variables) {
        switch (functionName) {
            case "cos": return Math.cos(arguments[0].evaluate(variables));
            case "pow": return Math.pow(arguments[0].evaluate(variables), arguments[1].evaluate(variables));
            default: throw new IllegalArgumentException("Unknown function: " + functionName);
        }
    }
}
