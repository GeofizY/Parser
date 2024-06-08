import Nodes.*;

import java.util.List;
import java.util.ArrayList;

public class Simplifier {
    private final List<String> cacheKeys = new ArrayList<>();
    private final List<Node> cacheValues = new ArrayList<>();

    public Node simplifyExpression(Node expr) {
        if (expr instanceof Operation) {
            Operation binOp = (Operation) expr;
            Node left = simplifyExpression(binOp.getLeft());
            Node right = simplifyExpression(binOp.getRight());
            String key = left.toString() + binOp.getOperator() + right.toString();

            int cacheIndex = cacheKeys.indexOf(key);
            if (cacheIndex != -1) {
                return cacheValues.get(cacheIndex);
            } else {
                Node simplified = new Operation(left, right, binOp.getOperator());
                cacheKeys.add(key);
                cacheValues.add(simplified);
                return simplified;
            }
        } else if (expr instanceof MathFunction) {
            MathFunction funcCall = (MathFunction) expr;
            Node[] args = new Node[funcCall.getArguments().length];
            for (int i = 0; i < funcCall.getArguments().length; i++) {
                args[i] = simplifyExpression(funcCall.getArguments()[i]);
            }
            StringBuilder keyBuilder = new StringBuilder(funcCall.getFunctionName() + "(");
            for (int i = 0; i < args.length; i++) {
                keyBuilder.append(args[i].toString());
                if (i < args.length - 1) {
                    keyBuilder.append(", ");
                }
            }
            keyBuilder.append(")");
            String key = keyBuilder.toString();

            int cacheIndex = cacheKeys.indexOf(key);
            if (cacheIndex != -1) {
                return cacheValues.get(cacheIndex);
            } else {
                Node simplified = new MathFunction(funcCall.getFunctionName(), args);
                cacheKeys.add(key);
                cacheValues.add(simplified);
                return simplified;
            }
        } else {
            return expr;
        }
    }
}