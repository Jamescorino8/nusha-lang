package AST;

import java.util.LinkedList;

public class Rule {
    public Expression expression;
    public LinkedList<Expression> thens = new LinkedList<>();

    @Override
    public String toString() {
        if (thens.isEmpty()) return expression.toString();

        return expression.toString() +
                String.join("\n",thens.stream().map(t -> "   " + t.toString()).toList());
    }
}
