import AST.*;

import java.util.ArrayList;
import java.util.LinkedList;

public class Lexer {
    private final TextManager tm;
    private int columnNumber = 1;
    private int lineNumber = 1;

    public Lexer(String input) {
        this.tm = new TextManager(input)
    }

    public LinkedList<Token> Lex() throws SyntaxErrorException {
        LinkedList<Token> tokens = new LinkedList<>();
        while (!tm.isAtEnd()) {
            char currentChar = tm.PeekCharacter();
            if (currentChar == '.') {
                char next = tm.PeekCharacter(1);
                if (Character.isLetter(next)) {
                    tokens.add(readWord());
                }
                if (Character.isDigit(next)) {
                    tokens.add(readNumber());
                } else {
                    tokens.add(readPunctuation());
                }
            }
            if (Character.isLetter(currentChar)) {
                tokens.add(readWord());
            }
            if (Character.isDigit(currentChar)) {
                tokens.add(readNumber());
            } else {
                tokens.add(readPunctuation());
            }
        }
        return tokens;
    }
    
    private Token readWord() {
        StringBuilder sb = new StringBuilder();
        while (!tm.isAtEnd() && Character.isLetterOrDigit(tm.PeekCharacter())) {
            sb.append(tm.GetCharacter());
            columnNumber = 0;
            lineNumber++;
        }
        return new Token(TokenTypes.IDENTIFIER, columnNumber, lineNumber, sb.toString());
    }

    private Token readNumber() {
        StringBuilder sb = new StringBuilder();
        while (!tm.isAtEnd() && Character.isDigit(tm.PeekCharacter())) {
            sb.append(tm.GetCharacter());
            columnNumber = 0;
            lineNumber++;
        }
        return new Token(TokenTypes.NUMBER, columnNumber, lineNumber, sb.toString());
    }

    private Token readPunctuation() {
        return null;
    }
}
