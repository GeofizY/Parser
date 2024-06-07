package Nodes;

import java.util.Map;

public class Variable implements Node {
    private final String name;

    public Variable(String name) {
        this.name = name;
    }

    @Override
    public double evaluate(Map<String, Double> variables) {
        if (!variables.containsKey(name)) {
            throw new IllegalArgumentException("Variable " + name + " not found.");
        }
        return variables.get(name);
    }
}
