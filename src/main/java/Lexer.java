import AST.*;
import AST.Token.TokenTypes;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

public class Lexer {
    private final TextManager textManager;
    private final HashMap<String, TokenTypes> keywords;
    private final HashMap<Character, Token.TokenTypes> punctuation;
    private Stack<Integer> indentLevel; // stack of indentation levels (in spaces)

    public Lexer(String input) {
        this.textManager = new TextManager(input);
        this.keywords = new HashMap<String, TokenTypes>() {
            {
                put("unique", TokenTypes.UNIQUE);
                put("var", TokenTypes.VAR);
            }
        };
        this.punctuation = new HashMap<Character, Token.TokenTypes>() {
            {
                put('{', Token.TokenTypes.LEFTCURLY);
                put('}', Token.TokenTypes.RIGHTCURLY);
                put('[', Token.TokenTypes.LEFTBRACE);
                put(']', Token.TokenTypes.RIGHTBRACE);
                put(',', Token.TokenTypes.COMMA);
                put(':', Token.TokenTypes.COLON);
            }
        };
        this.indentLevel = new Stack<Integer>() {
            {
                push(0);
            }
        };
    }

    public LinkedList<Token> Lex() throws SyntaxErrorException {
        LinkedList<Token> tokens = new LinkedList<>();
        while (!textManager.isAtEnd()) {
            char currentChar = textManager.PeekCharacter();
            if (currentChar == '\n') {
                tokens.add(new Token(Token.TokenTypes.NEWLINE, textManager.getLineNumber(), textManager.getPosition()));
                textManager.GetCharacter();
                // Count indentation of next line
                int spaces = 0;
                while (!textManager.isAtEnd()) {
                    char c = textManager.PeekCharacter();
                    if (c == ' ') { 
                        spaces += 1; 
                        textManager.GetCharacter(); 
                    } else if (c == '\t') { 
                        spaces += 4; 
                        textManager.GetCharacter(); 
                    }
                    else break;
                }
                int currentIndent = indentLevel.peek();
                if (spaces > currentIndent) {
                    if (spaces % 4 != 0) {
                        throw new SyntaxErrorException("Indentation Error: indent must be multiple of 4", textManager.getLineNumber(), spaces + 1);
                    }
                    indentLevel.push(spaces);
                    tokens.add(new Token(Token.TokenTypes.INDENT, textManager.getLineNumber(), 1));
                } else if (spaces < currentIndent) {
                    while (indentLevel.size() > 1 && spaces < indentLevel.peek()) {
                        indentLevel.pop();
                        tokens.add(new Token(Token.TokenTypes.DEDENT, textManager.getLineNumber(), 1));
                    }
                    if (spaces != indentLevel.peek()) {
                        throw new SyntaxErrorException("Indentation Error: unmatched dedent", textManager.getLineNumber(), spaces + 1);
                    }
                }
                continue;
            }

            // Skip mid line whitespace
            if (Character.isWhitespace(currentChar)) {
                textManager.GetCharacter(); 
                continue; 
            }

            if (Character.isLetter(currentChar)) tokens.add(readWord());
            else if (Character.isDigit(currentChar)) tokens.add(readNumber());
            else tokens.add(readPunctuation());
        }

        // Handle EOF DEDENT
        while (indentLevel.size() > 1) {
            indentLevel.pop();
            tokens.add(new Token(Token.TokenTypes.DEDENT, textManager.getLineNumber(), 1));
        }
        tokens.add(new Token(Token.TokenTypes.NEWLINE, textManager.getLineNumber(), textManager.getPosition()));
        return tokens;
    }

    private Token readWord() {
        StringBuilder stringBuilder = new StringBuilder();
        int startColumn = textManager.getPosition();
        while (!textManager.isAtEnd() && Character.isLetterOrDigit(textManager.PeekCharacter())) {
            stringBuilder.append(textManager.GetCharacter());
        }
        String word = stringBuilder.toString();
        if (keywords.containsKey(word)) {
            return new Token(keywords.get(word), textManager.getLineNumber(), startColumn);
        } else {
            return new Token(Token.TokenTypes.IDENTIFIER, textManager.getLineNumber(), startColumn, word);
        }
    }

    private Token readNumber() {
        StringBuilder stringBuilder = new StringBuilder();
        int startColumn = textManager.getPosition();
        while (!textManager.isAtEnd() && Character.isDigit(textManager.PeekCharacter())) {
            stringBuilder.append(textManager.GetCharacter());
        }
        return new Token(Token.TokenTypes.NUMBER, textManager.getLineNumber(), startColumn, stringBuilder.toString());
    }

    private Token readPunctuation() throws SyntaxErrorException {
        char currentChar = textManager.GetCharacter();
        int startColumn = textManager.getPosition();
        if (currentChar == '.') {
            if (!textManager.isAtEnd() && Character.isDigit(textManager.PeekCharacter(1))) {
            return readNumber();
            }
            return new Token(Token.TokenTypes.DOT, textManager.getLineNumber(), startColumn);
        }
        if (currentChar == '=') {
            if (!textManager.isAtEnd() && textManager.PeekCharacter() == '>') {
            textManager.GetCharacter();
            return new Token(Token.TokenTypes.YIELDS, textManager.getLineNumber(), startColumn);
            }
            return new Token(Token.TokenTypes.EQUAL, textManager.getLineNumber(), startColumn);
        }
        if (currentChar == '!') {
            if (!textManager.isAtEnd() && textManager.PeekCharacter() == '=') {
            textManager.GetCharacter();
            return new Token(Token.TokenTypes.NOTEQUAL, textManager.getLineNumber(), startColumn);
            }
            throw new SyntaxErrorException("Error: '!' without '='", textManager.getLineNumber(), startColumn);
        }
        if (punctuation.containsKey(currentChar)) {
            return new Token(punctuation.get(currentChar), textManager.getLineNumber(), startColumn);
        }
        throw new SyntaxErrorException("Unknown character '" + currentChar + "'", textManager.getLineNumber(), startColumn);
    }
}
