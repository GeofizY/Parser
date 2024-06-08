package Nodes;

import java.util.Map;

public class Operation implements Node {
    private final Node left;
    private final Node right;
    private final char operator;

    public Operation(Node left, Node right, char operator) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    @Override
    public double evaluate(Map<String, Double> variables) {
    double leftValue = left.evaluate(variables);
    double rightValue = right.evaluate(variables);

        switch (operator) {
            case '+': return leftValue + rightValue;
            case '-': return leftValue - rightValue;
            case '*': return leftValue * rightValue;
            case '/': return leftValue / rightValue;
            default: throw new IllegalArgumentException("Unknown operator: " + operator);
        }
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public char getOperator() {
        return operator;
    }
    @Override
    public String toString() {
        return "(" + left.toString() + " " + operator + " " + right.toString() + ")";
    }

}
