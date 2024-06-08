import Nodes.*;

import java.util.List;
import java.util.ArrayList;

public class Simplifier {
    private final List<Node> subexpressions = new ArrayList<>();

    public Node simplifyExpression(Node expression) {
        for (Node node : subexpressions) {
            if (expression.toString().equals(node.toString())) {
                return node;
            }
        }

        if (expression instanceof Operation) {
            Operation binaryNode = (Operation) expression;
            Node left = simplifyExpression(binaryNode.getLeft());
            Node right = simplifyExpression(binaryNode.getRight());
            Node simplifiedNode = new Operation(left, right, binaryNode.getOperator());
            subexpressions.add(simplifiedNode);
            return simplifiedNode;
        } else if (expression instanceof MathFunction) {
            MathFunction MathFunction = (MathFunction) expression;
            Node[] arguments = MathFunction.getArguments();
            Node[] simplifiedArguments = new Node[arguments.length];
            for (int i = 0; i < arguments.length; i++) {
                simplifiedArguments[i] = simplifyExpression(arguments[i]);
            }
            Node simplifiedNode = new MathFunction(MathFunction.getFunctionName(), simplifiedArguments);
            subexpressions.add(simplifiedNode);
            return simplifiedNode;
        } else {
            subexpressions.add(expression);
            return expression;
        }
    }
}