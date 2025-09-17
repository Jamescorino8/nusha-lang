package AST;

import java.util.Optional;

public class VRModifier {

    public boolean dot;
    public Optional<String> part;
    public Optional<VRModifier> vrmodifier;
    public String size;

    @Override
    public String toString() {
        if (dot)
            return "." + part.get() + (vrmodifier.isPresent() ? vrmodifier.toString() : "");
        else
            return "[" + size + "]" + (vrmodifier.isPresent() ? vrmodifier.toString() : "");
    }
}
