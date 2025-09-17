package AST;

import java.util.Optional;

public class Token {
    public enum TokenTypes {
        IDENTIFIER,
        NUMBER,
        INDENT,
        DEDENT,
        NEWLINE,
        UNIQUE,
        VAR,
        EQUAL,
        LEFTCURLY,
        COMMA,
        RIGHTCURLY,
        LEFTBRACE,
        RIGHTBRACE,
        COLON,
        YIELDS,
        NOTEQUAL,
        DOT,
    }

    public Optional<String> Value;
    public TokenTypes Type;
    public int ColumnNumber;
    public int LineNumber;

    public Token(TokenTypes type, int line, int column) {
        this(type, line, column, Optional.empty());
    }

    public Token (TokenTypes type, int line, int column, String val) {
        this(type, line, column, Optional.of(val));
    }

    public Token (TokenTypes type, int line, int column, Optional<String> val) {
        this.Type = type;
        this.LineNumber = line;
        this.ColumnNumber = column;
        this.Value = val;
    }

    @Override
    public String toString() {
        return Type + " " + Value.orElse("") + " @ " + LineNumber + "," + ColumnNumber;
    }
}
