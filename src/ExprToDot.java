import Nodes.*;

import java.util.HashMap;
import java.util.Map;

public class ExprToDot {
    private int nodeId = 0; // Идентификатор узла
    private final StringBuilder dotStr = new StringBuilder(); // Строка для конвертации
    private final Map<Node, Integer> nodeIds = new HashMap<>(); // Словарь уникальных узлов

    public String convertToDot(Node expression) {
        dotStr.append("digraph Expression {\n");
        generateDot(expression);
        dotStr.append("}");
        return dotStr.toString();
    }

    private int generateDot(Node node) {
        if (nodeIds.containsKey(node)) {
            return nodeIds.get(node);
        }

        int currentId = nodeId++;
        nodeIds.put(node, currentId);

        if (node instanceof Variable variable) {
            dotStr.append(String.format("  node%d [label=\"%s\"];\n", currentId, variable));
        } else if (node instanceof Constant constant) {
            dotStr.append(String.format("  node%d [label=\"%s\"];\n", currentId, constant));
        } else if (node instanceof Operation operation) {
            dotStr.append(String.format("  node%d [label=\"%s\"];\n", currentId, operation.getOperator()));
            int leftId = generateDot(operation.getLeft());
            int rightId = generateDot(operation.getRight());
            dotStr.append(String.format("  node%d -> node%d;\n", currentId, leftId));
            dotStr.append(String.format("  node%d -> node%d;\n", currentId, rightId));
        } else if (node instanceof MathFunction mathFunction) {
            dotStr.append(String.format("  node%d [label=\"%s\"];\n", currentId, mathFunction.getFunctionName()));
            for (Node arg : mathFunction.getArguments()) {
                int argId = generateDot(arg);
                dotStr.append(String.format("  node%d -> node%d;\n", currentId, argId));
            }
        }
        return currentId;
    }
}
