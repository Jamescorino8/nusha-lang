package AST;

public class Op {

    public enum OpTypes {
        Equal,
        NotEqual
    }

    public OpTypes type;

    @Override
    public String toString() {
        return type == OpTypes.Equal ? " == " : " != ";
    }
}
