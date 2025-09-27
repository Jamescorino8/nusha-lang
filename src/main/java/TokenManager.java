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
        if (tokens.getFirst().Type == t) {
            match = Optional.of(tokens.getFirst());
            tokens.removeFirst();
        }
        return match;
    }

    public Optional<Token> Peek (int i) {
        return Optional.of(tokens.get(i));
    }
}
