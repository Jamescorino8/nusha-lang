import AST.Token;

import java.lang.classfile.ClassFile.Option;
import java.util.LinkedList;
import java.util.Optional;

public class TokenManager {
    LinkedList<Token> tokens;

    public TokenManager(LinkedList<Token> tokens) {
        this.tokens = tokens;
    }

    public int getLine() {
        return tokens.getFirst().LineNumber;
    }

    public int getColumn() {
        return tokens.getFirst().ColumnNumber;
    }

    public boolean Done() {
        return tokens.isEmpty();
    }

    public Optional<Token> MatchAndRemove(Token.TokenTypes t) {
        Optional<Token> match = Optional.empty();
        if (!tokens.isEmpty() && tokens.getFirst().Type == t) {
            match = Optional.of(tokens.getFirst());
            tokens.removeFirst();
        }
        return match;
    }

    public Optional<Token> Peek (int i) {
        if (i < tokens.size()) {
            return Optional.of(tokens.get(i));
        }
        return Optional.empty();
    }

    void RequireNewLine() throws SyntaxErrorException {
        // Require at least one NEWLINE token; then collapse subsequent NEWLINEs.
        if (MatchAndRemove(Token.TokenTypes.NEWLINE).isEmpty()) {
            throw new SyntaxErrorException("Expected newline.", getLine(), getColumn());
        }
        while (MatchAndRemove(Token.TokenTypes.NEWLINE).isPresent()) {
            // consume extra blank lines

        }
    }
}
