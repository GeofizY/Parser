package Nodes;

import java.util.Map;

public class Constant implements Node { // Для констант (E, PI, PHI, просто числа)
    private final double value;

    public Constant(double value) {
        this.value = value;
    }

    @Override
    public double evaluate(Map<String, Double> variables) {
        return value;
    }

    @Override
    public String toString() {
        return Double.toString(value);
    }
}
