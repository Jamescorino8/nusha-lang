import AST.*;
import AST.Token;
import AST.Token.TokenTypes;

import java.util.HashMap;
import java.util.LinkedList;

public class Lexer {
    private final TextManager textManager;
    private int lineNumber = 0;
    private int columnNumber = 0;
    private final HashMap<String, TokenTypes> keywords;

    public Lexer(String input) {
        this.textManager = new TextManager(input);
        this.keywords = new HashMap<>();
        
        // insert all the used keywords into keywords map
        keywords.put("unique", TokenTypes.UNIQUE);
        keywords.put("var", TokenTypes.VAR);
    }

    public LinkedList<Token> Lex() throws SyntaxErrorException {
        LinkedList<Token> tokens = new LinkedList<>();
        while (!textManager.isAtEnd()) {
            char currentChar = textManager.PeekCharacter();
            if (Character.isLetter(currentChar)) {
                tokens.add(readWord());
            } else if (currentChar == '\n') {
                tokens.add(new Token(Token.TokenTypes.NEWLINE, lineNumber, columnNumber));
                textManager.GetCharacter();
            } else if (Character.isWhitespace(currentChar)) { 
                textManager.GetCharacter(); // skip whitespace chars
            }
        }
        return tokens;
    }

    private Token readWord() {
        StringBuilder stringBuilder = new StringBuilder(); // StringBuilder bc cant add char to String
        while (!textManager.isAtEnd() && Character.isLetterOrDigit(textManager.PeekCharacter())) {
            stringBuilder.append(textManager.GetCharacter());
        }
        String word = stringBuilder.toString();
        if (keywords.containsKey(word)) {
            return new Token(keywords.get(word), lineNumber, columnNumber);
        } else {
            return new Token(Token.TokenTypes.IDENTIFIER, lineNumber, columnNumber, word);
        }
    }
}
