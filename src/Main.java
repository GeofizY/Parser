import Nodes.Node;

import java.util.Map;

public class Main {
    public static void main(String[] args) {

        System.out.println("Point 1");
        Parser parser = new Parser();
        Node expr = parser.parseExpression("(x + 1) * (x + 1)");
        Map<String, Double> variables1 = Map.of("x", 3.0);
        System.out.println("Expression: " + expr); // Вывод математического выражения
        System.out.println("Variables: " + variables1); // Вывод списка переменных и их значений
        System.out.println("Result: " + expr.evaluate(variables1)); // Вывод результата

        System.out.println("\nPoint 2");
        Parser parser2 = new Parser();
        Node expr2 = parser2.parseExpression("(x + 1) +(x + 1)*(x+3)+(x+3)");
        Map<String, Double> variables2 = Map.of("x", 5.0);
        Simplifier simplifier = new Simplifier();
        Node simplifiedExpr = simplifier.simplifyExpression(expr2);
        System.out.println("Simplified Expression: " + simplifiedExpr); // Вывод математического выражения
        System.out.println("Variables: " + variables2); // Вывод списка переменных и их значений
        System.out.println("Result: " + simplifiedExpr.evaluate(variables2)); // Вывод результата

        System.out.println("\nPoint 3");
        ExprToDot converter = new ExprToDot();
        String dotRepresentation = converter.convertToDot(simplifiedExpr); // Конвертация выражения в текст на языке dot
        System.out.println("Copy to GraphvizOnline:\n" + dotRepresentation); // Вывод результата

    }
}