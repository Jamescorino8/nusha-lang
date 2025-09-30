import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Parser1Tests {
    @Test
    public void singleVarTest() throws Exception {
        var code =          "var Birds : Bird[6]\n"+
                "";
        var tokens = new Lexer(code).Lex();
        var ast = new NushaFall2025Parser().Nusha(tokens).orElseThrow();
        Assertions.assertEquals("Bird", ast.variables.variable.get(0).type);
        Assertions.assertEquals("Birds", ast.variables.variable.get(0).variableName);
        Assertions.assertEquals("6", ast.variables.variable.get(0).size.orElseThrow());
    }

    @Test
    public void multiVarTest() throws Exception {
        var code =          "\n" +
                "var Places : Place [5]\n"+
                "\n"+
                "var Songs : Song [5]\n"+
                "";
        var tokens = new Lexer(code).Lex();
        var ast = new NushaFall2025Parser().Nusha(tokens).orElseThrow();
        Assertions.assertEquals("Place", ast.variables.variable.get(0).type);
        Assertions.assertEquals("Places", ast.variables.variable.get(0).variableName);
        Assertions.assertEquals("5", ast.variables.variable.get(0).size.orElseThrow());
        Assertions.assertEquals("Song", ast.variables.variable.get(1).type);
        Assertions.assertEquals("Songs", ast.variables.variable.get(1).variableName);
        Assertions.assertEquals("5", ast.variables.variable.get(1).size.orElseThrow());
    }
}
