import AST.Token;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Lexer1Test {

    @Test
    public void onlyWords() throws SyntaxErrorException {
        var line = "Author Alice BOB carol dAVID";
        var tokens = new Lexer(line).Lex();

        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(0).Type);
        Assertions.assertEquals("Author", tokens.get(0).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(1).Type);
        Assertions.assertEquals("Alice", tokens.get(1).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(2).Type);
        Assertions.assertEquals("BOB", tokens.get(2).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(3).Type);
        Assertions.assertEquals("carol", tokens.get(3).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(4).Type);
        Assertions.assertEquals("dAVID", tokens.get(4).Value.orElseThrow());
    }

    @Test
    public void withKeywords() throws SyntaxErrorException {
        var line = "story unique Author Var Unique pet var";
        var tokens = new Lexer(line).Lex();

        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(0).Type);
        Assertions.assertEquals("story", tokens.get(0).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.UNIQUE, tokens.get(1).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(2).Type);
        Assertions.assertEquals("Author", tokens.get(2).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(3).Type);
        Assertions.assertEquals("Var", tokens.get(3).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(4).Type);
        Assertions.assertEquals("Unique", tokens.get(4).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(5).Type);
        Assertions.assertEquals("pet", tokens.get(5).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.VAR, tokens.get(6).Type);
    }

    @Test
    public void wordsWithNumbers() throws SyntaxErrorException {
        var line = "var Animal1 Dog";
        var tokens = new Lexer(line).Lex();

        Assertions.assertEquals(Token.TokenTypes.VAR, tokens.get(0).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(1).Type);
        Assertions.assertEquals("Animal1", tokens.get(1).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(2).Type);
        Assertions.assertEquals("Dog", tokens.get(2).Value.orElseThrow());
    }

    @Test
    public void multiLine() throws SyntaxErrorException {
        var code = """
                Item Cake IceCream
                Flavor Chocolate Strawberry
                Dessert unique Item i Flavor f
                var Desserts Dessert
                """;
        var tokens = new Lexer(code).Lex();

        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(0).Type);
        Assertions.assertEquals("Item", tokens.get(0).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(1).Type);
        Assertions.assertEquals("Cake", tokens.get(1).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(2).Type);
        Assertions.assertEquals("IceCream", tokens.get(2).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(3).Type);

        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(4).Type);
        Assertions.assertEquals("Flavor", tokens.get(4).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(5).Type);
        Assertions.assertEquals("Chocolate", tokens.get(5).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(6).Type);
        Assertions.assertEquals("Strawberry", tokens.get(6).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(7).Type);

        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(8).Type);
        Assertions.assertEquals("Dessert", tokens.get(8).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.UNIQUE, tokens.get(9).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(10).Type);
        Assertions.assertEquals("Item", tokens.get(10).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(11).Type);
        Assertions.assertEquals("i", tokens.get(11).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(12).Type);
        Assertions.assertEquals("Flavor", tokens.get(12).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(13).Type);
        Assertions.assertEquals("f", tokens.get(13).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(14).Type);

        Assertions.assertEquals(Token.TokenTypes.VAR, tokens.get(15).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(16).Type);
        Assertions.assertEquals("Desserts", tokens.get(16).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(17).Type);
        Assertions.assertEquals("Dessert", tokens.get(17).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(18).Type);
    }
}
