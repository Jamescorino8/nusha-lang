import java.util.Optional;

public class Token {
    private final Optional<String> value;
    private final TokenTypes type;
    private final int columnNumber;
    private final int lineNumber;

    public Token(TokenTypes type, int columnNumber, int lineNumber) {
        this.value = Optional.empty();
        this.type = type;
        this.columnNumber = columnNumber;
        this.lineNumber = lineNumber;
    }

    public Token(TokenTypes type, int columnNumber, int lineNumber, String value) {
        this.value = Optional.of(value);
        this.type = type;
        this.columnNumber = columnNumber;
        this.lineNumber = lineNumber;
    }

    public TokenTypes getType() {
        return this.type;
    }

    public Optional<String> getValue() {
        return this.value;
    }
    
    public String toString() {
        return String.format("Token of Type: %s Text: %s Column: %d Line: %d", 
                        this.type, this.value.get(), this.columnNumber, this.lineNumber);
    }
}

