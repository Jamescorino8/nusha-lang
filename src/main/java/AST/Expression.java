package AST;

public class Expression {
    public VariableReference left;
    public Op op;
    public VariableReference right;

    @Override
    public String toString() {
        return left + " " + op + " " + right;
    }
}
