import AST.*;
import AST.Token.TokenTypes;

import java.util.HashMap;
import java.util.LinkedList;

public class Lexer {
    private final TextManager textManager;
    private int lineNumber = 1;
    private int columnNumber = 1;
    private final HashMap<String, TokenTypes> keywords;
    private final LinkedList<Integer> indentLevels;

    public Lexer(String input) {
        this.textManager = new TextManager(input);
        this.keywords = new HashMap<>();
        this.indentLevels = new LinkedList<>();
        indentLevels.add(0);

        keywords.put("unique", TokenTypes.UNIQUE);
        keywords.put("var", TokenTypes.VAR);
    }

    public LinkedList<Token> Lex() throws SyntaxErrorException {
        LinkedList<Token> tokens = new LinkedList<>();
        while (!textManager.isAtEnd()) {
            // if indentation level > 0 at beginning, we need to handle DEDENTs first.
            if (columnNumber == 1 && !textManager.isAtEnd() && !Character.isWhitespace(textManager.PeekCharacter())) {
                while (indentLevels.getLast() > 0) {
                    indentLevels.removeLast();
                    tokens.add(new Token(Token.TokenTypes.DEDENT, lineNumber, columnNumber));
                }
            }
            // Handle indentation only at the beginning of a line.
            if (columnNumber == 1 && Character.isWhitespace(textManager.PeekCharacter())) {
                int indent = 0;
                while (!textManager.isAtEnd() && Character.isWhitespace(textManager.PeekCharacter())) {
                    char currentChar = textManager.GetCharacter();
                    if (currentChar == '\t') {
                        indent += 4;
                    } else if (currentChar == ' ') {
                        indent++;
                    } else if (currentChar == '\n') {
                        tokens.add(new Token(Token.TokenTypes.NEWLINE, lineNumber, columnNumber));
                        lineNumber++;
                        columnNumber = 1;
                        indent = 0; // Reset for the new line
                    }
                }

                if (indent % 4 != 0) {
                    throw new SyntaxErrorException("Indentation Error", lineNumber, columnNumber);
                }

                if (indent > indentLevels.getLast()) {
                    indentLevels.add(indent);
                    tokens.add(new Token(Token.TokenTypes.INDENT, lineNumber, columnNumber));
                } else {
                    while (indent < indentLevels.getLast()) {
                        indentLevels.removeLast();
                        tokens.add(new Token(Token.TokenTypes.DEDENT, lineNumber, columnNumber));
                    }
                }
                 columnNumber += indent;
            }

            if (textManager.isAtEnd()) break;

            char currentChar = textManager.PeekCharacter();
            if (Character.isLetter(currentChar)) {
                tokens.add(readWord());
            } else if (Character.isDigit(currentChar)) {
                tokens.add(readNumber());
            } else if (currentChar == '\n') {
                textManager.GetCharacter();
                tokens.add(new Token(Token.TokenTypes.NEWLINE, lineNumber, columnNumber));
                lineNumber++;
                columnNumber = 1;
            } else if (Character.isWhitespace(currentChar)){
                textManager.GetCharacter(); // Consume other whitespace
                columnNumber++;
            }
            else {
                tokens.add(readPunctuation());
            }
        }

        // Handle DEDENT for any remaining indentation at the end of the file.
        while (indentLevels.getLast() > 0) {
            indentLevels.removeLast();
            tokens.add(new Token(Token.TokenTypes.DEDENT, lineNumber, columnNumber));
        }

        tokens.add(new Token(Token.TokenTypes.NEWLINE, lineNumber, columnNumber));
        return tokens;
    }

    private Token readWord() {
        StringBuilder stringBuilder = new StringBuilder();
        int startColumn = columnNumber;
        while (!textManager.isAtEnd() && Character.isLetterOrDigit(textManager.PeekCharacter())) {
            stringBuilder.append(textManager.GetCharacter());
            columnNumber++;
        }
        String word = stringBuilder.toString();
        if (keywords.containsKey(word)) {
            return new Token(keywords.get(word), lineNumber, startColumn);
        } else {
            return new Token(Token.TokenTypes.IDENTIFIER, lineNumber, startColumn, word);
        }
    }

    private Token readNumber() {
        StringBuilder stringBuilder = new StringBuilder();
        int startColumn = columnNumber;
        while (!textManager.isAtEnd() && Character.isDigit(textManager.PeekCharacter())) {
            stringBuilder.append(textManager.GetCharacter());
            columnNumber++;
        }
        return new Token(Token.TokenTypes.NUMBER, lineNumber, startColumn, stringBuilder.toString());
    }

    private Token readPunctuation() throws SyntaxErrorException {
        char currentChar = textManager.GetCharacter();
        int startColumn = columnNumber;
        columnNumber++;
        switch (currentChar) {
            case '{':
                return new Token(Token.TokenTypes.LEFTCURLY, lineNumber, startColumn);
            case '}':
                return new Token(Token.TokenTypes.RIGHTCURLY, lineNumber, startColumn);
            case '[':
                return new Token(Token.TokenTypes.LEFTBRACE, lineNumber, startColumn);
            case ']':
                return new Token(Token.TokenTypes.RIGHTBRACE, lineNumber, startColumn);
            case ',':
                return new Token(Token.TokenTypes.COMMA, lineNumber, startColumn);
            case '.':
                return new Token(Token.TokenTypes.DOT, lineNumber, startColumn);
            case ':':
                return new Token(Token.TokenTypes.COLON, lineNumber, startColumn);
            case '=':
                if (!textManager.isAtEnd() && textManager.PeekCharacter() == '>') {
                    textManager.GetCharacter();
                    columnNumber++;
                    return new Token(Token.TokenTypes.YIELDS, lineNumber, startColumn);
                }
                return new Token(Token.TokenTypes.EQUAL, lineNumber, startColumn);
            case '!':
                if (!textManager.isAtEnd() && textManager.PeekCharacter() == '=') {
                    textManager.GetCharacter();
                    columnNumber++;
                    return new Token(Token.TokenTypes.NOTEQUAL, lineNumber, startColumn);
                }
                throw new SyntaxErrorException("Error: '!' without '='", lineNumber, startColumn);
            default:
                throw new SyntaxErrorException("Unknown character '" + currentChar + "'", lineNumber, startColumn);
        }
    }
}