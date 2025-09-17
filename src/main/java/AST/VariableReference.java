package AST;

import java.util.Optional;

public class VariableReference {

    public String variableName;
    public Optional<VRModifier> vrmodifier;

    @Override
    public String toString() {
        return variableName + (vrmodifier.isPresent() ? vrmodifier.toString() : "");
    }
}
