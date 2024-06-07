import Nodes.*;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    private String expression; // Принимаемое математическое выражение
    private int pos; // Текущая позиция

    public Node parseExpression(String expression) {
        this.expression = expression.replaceAll("\\s+", ""); // Удаление пробелов
        this.pos = 0; // Изначальная позиция
        return parsePlusMinus(); // Запуск рекурсивного спуска
    }

    private Node parsePlusMinus() {
        Node result = parseMultDiv();
        while (pos < expression.length()) {
            char operator = expression.charAt(pos);
            if (operator == '+' || operator == '-') {
                pos++;
                result = new Operation(result, parseMultDiv(), operator);
            } else {
                break;
            }
        }
        return result;
    }

    private Node parseMultDiv() {
        Node result = parseFactor();
        while (pos < expression.length()) {
            char op = expression.charAt(pos);
            if (op == '*' || op == '/') {
                pos++;
                result = new Operation(result, parseFactor(), op);
            } else {
                break;
            }
        }
        return result;
    }

    private Node parseFactor() {
        if (pos < expression.length() && expression.charAt(pos) == '(') {
            pos++;
            Node result = parsePlusMinus();
            pos++; // Пропустить ')'
            return result;
        }

        if (pos < expression.length() && (Character.isDigit(expression.charAt(pos)) || expression.charAt(pos) == '.')) {
            return parseNumber();
        }

        if (pos < expression.length() && Character.isLetter(expression.charAt(pos))) {
            if (Character.isLowerCase(expression.charAt(pos))) {
                return parseVariableOrFunction();
            } else {
                return parseConstant();
            }
        }

        throw new IllegalArgumentException("Unexpected character at position " + pos);
    }

    private Node parseNumber() {
        int start = pos;
        while (pos < expression.length() && (Character.isDigit(expression.charAt(pos)) || expression.charAt(pos) == '.')) {
            pos++;
        }
        return new Constant(Double.parseDouble(expression.substring(start, pos)));
    }

    private Node parseConstant() {
        int start = pos;
        while (pos < expression.length() && Character.isLetter(expression.charAt(pos))) {
            pos++;
        }
        String name = expression.substring(start, pos);

        switch (name) {
            case "PI" -> {
                return new Constant(Math.PI);
            }
            case "E" -> {
                return new Constant(Math.E);
            }
            case "PHI" -> {
                return new Constant(1.618);
            }
            default -> throw new IllegalArgumentException("Incorrect constant " + name);
        }
    }

    private Node parseVariableOrFunction() {
        int start = pos;
        while (pos < expression.length() && Character.isLetter(expression.charAt(pos))) {
            pos++;
        }
        String name = expression.substring(start, pos);

        if (pos < expression.length() && expression.charAt(pos) == '(') {
            pos++;
            List<Node> arguments = new ArrayList<>();
            while (pos < expression.length() && expression.charAt(pos) != ')') {
                arguments.add(parsePlusMinus());
                if (pos < expression.length() && expression.charAt(pos) == ',') {
                    pos++;
                }
            }
            return new MathFunction(name, arguments.toArray(new Node[0]));
        }

        return new Variable(name);
    }
}
