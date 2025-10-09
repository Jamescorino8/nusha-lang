import AST.Token;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

public class Parser2Tests {

   @Test
   public void TestPetsParser() throws Exception {
       var code =          "Author = {Alice, Bob, Carol, David}\n"+
               "Pet = {Dog, Cat, Bird, Fish}\n"+
               "House = {Red, Blue, Green, Yellow}\n"+
               "\n"+
               "Story = [unique Author a, unique Pet p, unique House h]\n"+
               "var Stories : Story[4]\n"+
               "\n"+
               "Stories[0].a = Alice\n"+
               "Stories[1].a = Bob\n"+
               "Stories[2].a = Carol\n"+
               "Stories[3].a = David\n"+
               "\n"+
               "Stories.a = Alice =>\n"+
               "    Stories.h != Red\n"+
               "Stories.h = Blue =>\n"+
               "    Stories.p = Cat\n"+
               "Stories.a = David =>\n"+
               "    Stories.p = Dog\n"+
               "Stories.a = Carol =>\n"+
               "    Stories.h = Green\n"+
               "Stories.a = Bob =>\n"+
               "    Stories.p != Fish\n"+
               "";

       var tokens = new LinkedList<Token>();
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 1, 6, "Author"));
       tokens.add(new Token(Token.TokenTypes.EQUAL, 1, 8));
       tokens.add(new Token(Token.TokenTypes.LEFTCURLY, 1, 10));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 1, 15, "Alice"));
       tokens.add(new Token(Token.TokenTypes.COMMA, 1, 16));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 1, 20, "Bob"));
       tokens.add(new Token(Token.TokenTypes.COMMA, 1, 21));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 1, 27, "Carol"));
       tokens.add(new Token(Token.TokenTypes.COMMA, 1, 28));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 1, 34, "David"));
       tokens.add(new Token(Token.TokenTypes.RIGHTCURLY, 1, 35));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 2, 0));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 2, 3, "Pet"));
       tokens.add(new Token(Token.TokenTypes.EQUAL, 2, 5));
       tokens.add(new Token(Token.TokenTypes.LEFTCURLY, 2, 7));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 2, 10, "Dog"));
       tokens.add(new Token(Token.TokenTypes.COMMA, 2, 11));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 2, 15, "Cat"));
       tokens.add(new Token(Token.TokenTypes.COMMA, 2, 16));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 2, 21, "Bird"));
       tokens.add(new Token(Token.TokenTypes.COMMA, 2, 22));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 2, 27, "Fish"));
       tokens.add(new Token(Token.TokenTypes.RIGHTCURLY, 2, 28));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 3, 0));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 3, 5, "House"));
       tokens.add(new Token(Token.TokenTypes.EQUAL, 3, 7));
       tokens.add(new Token(Token.TokenTypes.LEFTCURLY, 3, 9));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 3, 12, "Red"));
       tokens.add(new Token(Token.TokenTypes.COMMA, 3, 13));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 3, 18, "Blue"));
       tokens.add(new Token(Token.TokenTypes.COMMA, 3, 19));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 3, 25, "Green"));
       tokens.add(new Token(Token.TokenTypes.COMMA, 3, 26));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 3, 33, "Yellow"));
       tokens.add(new Token(Token.TokenTypes.RIGHTCURLY, 3, 34));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 4, 0));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 5, 0));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 5, 5, "Story"));
       tokens.add(new Token(Token.TokenTypes.EQUAL, 5, 7));
       tokens.add(new Token(Token.TokenTypes.LEFTBRACE, 5, 9));
       tokens.add(new Token(Token.TokenTypes.UNIQUE, 5, 15));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 5, 22, "Author"));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 5, 24, "a"));
       tokens.add(new Token(Token.TokenTypes.COMMA, 5, 25));
       tokens.add(new Token(Token.TokenTypes.UNIQUE, 5, 32));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 5, 36, "Pet"));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 5, 38, "p"));
       tokens.add(new Token(Token.TokenTypes.COMMA, 5, 39));
       tokens.add(new Token(Token.TokenTypes.UNIQUE, 5, 46));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 5, 52, "House"));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 5, 54, "h"));
       tokens.add(new Token(Token.TokenTypes.RIGHTBRACE, 5, 55));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 6, 0));
       tokens.add(new Token(Token.TokenTypes.VAR, 6, 3));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 6, 11, "Stories"));
       tokens.add(new Token(Token.TokenTypes.COLON, 6, 13));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 6, 19, "Story"));
       tokens.add(new Token(Token.TokenTypes.LEFTBRACE, 6, 20));
       tokens.add(new Token(Token.TokenTypes.NUMBER, 6, 21, "4"));
       tokens.add(new Token(Token.TokenTypes.RIGHTBRACE, 6, 22));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 7, 0));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 8, 0));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 8, 7, "Stories"));
       tokens.add(new Token(Token.TokenTypes.LEFTBRACE, 8, 8));
       tokens.add(new Token(Token.TokenTypes.NUMBER, 8, 9, "0"));
       tokens.add(new Token(Token.TokenTypes.RIGHTBRACE, 8, 10));
       tokens.add(new Token(Token.TokenTypes.DOT, 8, 11));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 8, 12, "a"));
       tokens.add(new Token(Token.TokenTypes.EQUAL, 8, 14));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 8, 20, "Alice"));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 9, 0));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 9, 7, "Stories"));
       tokens.add(new Token(Token.TokenTypes.LEFTBRACE, 9, 8));
       tokens.add(new Token(Token.TokenTypes.NUMBER, 9, 9, "1"));
       tokens.add(new Token(Token.TokenTypes.RIGHTBRACE, 9, 10));
       tokens.add(new Token(Token.TokenTypes.DOT, 9, 11));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 9, 12, "a"));
       tokens.add(new Token(Token.TokenTypes.EQUAL, 9, 14));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 9, 18, "Bob"));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 10, 0));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 10, 7, "Stories"));
       tokens.add(new Token(Token.TokenTypes.LEFTBRACE, 10, 8));
       tokens.add(new Token(Token.TokenTypes.NUMBER, 10, 9, "2"));
       tokens.add(new Token(Token.TokenTypes.RIGHTBRACE, 10, 10));
       tokens.add(new Token(Token.TokenTypes.DOT, 10, 11));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 10, 12, "a"));
       tokens.add(new Token(Token.TokenTypes.EQUAL, 10, 14));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 10, 20, "Carol"));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 11, 0));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 11, 7, "Stories"));
       tokens.add(new Token(Token.TokenTypes.LEFTBRACE, 11, 8));
       tokens.add(new Token(Token.TokenTypes.NUMBER, 11, 9, "3"));
       tokens.add(new Token(Token.TokenTypes.RIGHTBRACE, 11, 10));
       tokens.add(new Token(Token.TokenTypes.DOT, 11, 11));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 11, 12, "a"));
       tokens.add(new Token(Token.TokenTypes.EQUAL, 11, 14));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 11, 20, "David"));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 12, 0));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 13, 0));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 13, 7, "Stories"));
       tokens.add(new Token(Token.TokenTypes.DOT, 13, 8));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 13, 9, "a"));
       tokens.add(new Token(Token.TokenTypes.EQUAL, 13, 11));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 13, 17, "Alice"));
       tokens.add(new Token(Token.TokenTypes.YIELDS, 13, 20));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 14, 0));
       tokens.add(new Token(Token.TokenTypes.INDENT, 14, 4));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 14, 11, "Stories"));
       tokens.add(new Token(Token.TokenTypes.DOT, 14, 12));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 14, 13, "h"));
       tokens.add(new Token(Token.TokenTypes.NOTEQUAL, 14, 16));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 14, 20, "Red"));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 15, 0));
       tokens.add(new Token(Token.TokenTypes.DEDENT, 15, 0));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 15, 7, "Stories"));
       tokens.add(new Token(Token.TokenTypes.DOT, 15, 8));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 15, 9, "h"));
       tokens.add(new Token(Token.TokenTypes.EQUAL, 15, 11));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 15, 16, "Blue"));
       tokens.add(new Token(Token.TokenTypes.YIELDS, 15, 19));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 16, 0));
       tokens.add(new Token(Token.TokenTypes.INDENT, 16, 4));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 16, 11, "Stories"));
       tokens.add(new Token(Token.TokenTypes.DOT, 16, 12));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 16, 13, "p"));
       tokens.add(new Token(Token.TokenTypes.EQUAL, 16, 15));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 16, 19, "Cat"));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 17, 0));
       tokens.add(new Token(Token.TokenTypes.DEDENT, 17, 0));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 17, 7, "Stories"));
       tokens.add(new Token(Token.TokenTypes.DOT, 17, 8));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 17, 9, "a"));
       tokens.add(new Token(Token.TokenTypes.EQUAL, 17, 11));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 17, 17, "David"));
       tokens.add(new Token(Token.TokenTypes.YIELDS, 17, 20));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 18, 0));
       tokens.add(new Token(Token.TokenTypes.INDENT, 18, 4));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 18, 11, "Stories"));
       tokens.add(new Token(Token.TokenTypes.DOT, 18, 12));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 18, 13, "p"));
       tokens.add(new Token(Token.TokenTypes.EQUAL, 18, 15));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 18, 19, "Dog"));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 19, 0));
       tokens.add(new Token(Token.TokenTypes.DEDENT, 19, 0));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 19, 7, "Stories"));
       tokens.add(new Token(Token.TokenTypes.DOT, 19, 8));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 19, 9, "a"));
       tokens.add(new Token(Token.TokenTypes.EQUAL, 19, 11));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 19, 17, "Carol"));
       tokens.add(new Token(Token.TokenTypes.YIELDS, 19, 20));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 20, 0));
       tokens.add(new Token(Token.TokenTypes.INDENT, 20, 4));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 20, 11, "Stories"));
       tokens.add(new Token(Token.TokenTypes.DOT, 20, 12));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 20, 13, "h"));
       tokens.add(new Token(Token.TokenTypes.EQUAL, 20, 15));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 20, 21, "Green"));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 21, 0));
       tokens.add(new Token(Token.TokenTypes.DEDENT, 21, 0));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 21, 7, "Stories"));
       tokens.add(new Token(Token.TokenTypes.DOT, 21, 8));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 21, 9, "a"));
       tokens.add(new Token(Token.TokenTypes.EQUAL, 21, 11));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 21, 15, "Bob"));
       tokens.add(new Token(Token.TokenTypes.YIELDS, 21, 18));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 22, 0));
       tokens.add(new Token(Token.TokenTypes.INDENT, 22, 4));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 22, 11, "Stories"));
       tokens.add(new Token(Token.TokenTypes.DOT, 22, 12));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 22, 13, "p"));
       tokens.add(new Token(Token.TokenTypes.NOTEQUAL, 22, 16));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 22, 21, "Fish"));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 23, 0));
       tokens.add(new Token(Token.TokenTypes.DEDENT, 23, 0));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 23, 0));

       var ast = new NushaFall2025Parser().Nusha(tokens).orElseThrow();
       Assertions.assertEquals("Author", ast.definitions.definition.get(0).definitionName);
       Assertions.assertEquals("Alice", ast.definitions.definition.get(0).choices.orElseThrow().choice.get(0));
       Assertions.assertEquals("Bob", ast.definitions.definition.get(0).choices.orElseThrow().choice.get(1));
       Assertions.assertEquals("Carol", ast.definitions.definition.get(0).choices.orElseThrow().choice.get(2));
       Assertions.assertEquals("David", ast.definitions.definition.get(0).choices.orElseThrow().choice.get(3));
       Assertions.assertEquals("Pet", ast.definitions.definition.get(1).definitionName);
       Assertions.assertEquals("Dog", ast.definitions.definition.get(1).choices.orElseThrow().choice.get(0));
       Assertions.assertEquals("Cat", ast.definitions.definition.get(1).choices.orElseThrow().choice.get(1));
       Assertions.assertEquals("Bird", ast.definitions.definition.get(1).choices.orElseThrow().choice.get(2));
       Assertions.assertEquals("Fish", ast.definitions.definition.get(1).choices.orElseThrow().choice.get(3));
       Assertions.assertEquals("House", ast.definitions.definition.get(2).definitionName);
       Assertions.assertEquals("Red", ast.definitions.definition.get(2).choices.orElseThrow().choice.get(0));
       Assertions.assertEquals("Blue", ast.definitions.definition.get(2).choices.orElseThrow().choice.get(1));
       Assertions.assertEquals("Green", ast.definitions.definition.get(2).choices.orElseThrow().choice.get(2));
       Assertions.assertEquals("Yellow", ast.definitions.definition.get(2).choices.orElseThrow().choice.get(3));
       Assertions.assertEquals("Story", ast.definitions.definition.get(3).definitionName);
       Assertions.assertEquals("a", ast.definitions.definition.get(3).nstruct.orElseThrow().entry.get(0).name);
       Assertions.assertEquals("Author", ast.definitions.definition.get(3).nstruct.orElseThrow().entry.get(0).type);
       Assertions.assertEquals(true, ast.definitions.definition.get(3).nstruct.orElseThrow().entry.get(0).unique);
       Assertions.assertEquals("p", ast.definitions.definition.get(3).nstruct.orElseThrow().entry.get(1).name);
       Assertions.assertEquals("Pet", ast.definitions.definition.get(3).nstruct.orElseThrow().entry.get(1).type);
       Assertions.assertEquals(true, ast.definitions.definition.get(3).nstruct.orElseThrow().entry.get(1).unique);
       Assertions.assertEquals("h", ast.definitions.definition.get(3).nstruct.orElseThrow().entry.get(2).name);
       Assertions.assertEquals("House", ast.definitions.definition.get(3).nstruct.orElseThrow().entry.get(2).type);
       Assertions.assertEquals(true, ast.definitions.definition.get(3).nstruct.orElseThrow().entry.get(2).unique);
       Assertions.assertEquals("Story", ast.variables.variable.get(0).type);
       Assertions.assertEquals("Stories", ast.variables.variable.get(0).variableName);
       Assertions.assertEquals("4", ast.variables.variable.get(0).size.orElseThrow());
       Assertions.assertEquals("Stories", ast.rules.rule.get(0).expression.left.variableName);
       Assertions.assertEquals("0", ast.rules.rule.get(0).expression.left.vrmodifier.orElseThrow().size);
       Assertions.assertEquals(true, ast.rules.rule.get(0).expression.left.vrmodifier.orElseThrow().vrmodifier.orElseThrow().dot);
       Assertions.assertEquals("a", ast.rules.rule.get(0).expression.left.vrmodifier.orElseThrow().vrmodifier.orElseThrow().part.orElseThrow());
       Assertions.assertEquals("Equal", ast.rules.rule.get(0).expression.op.type.name());
       Assertions.assertEquals("Alice", ast.rules.rule.get(0).expression.right.variableName);
       Assertions.assertEquals("Stories", ast.rules.rule.get(1).expression.left.variableName);
       Assertions.assertEquals("1", ast.rules.rule.get(1).expression.left.vrmodifier.orElseThrow().size);
       Assertions.assertEquals(true, ast.rules.rule.get(1).expression.left.vrmodifier.orElseThrow().vrmodifier.orElseThrow().dot);
       Assertions.assertEquals("a", ast.rules.rule.get(1).expression.left.vrmodifier.orElseThrow().vrmodifier.orElseThrow().part.orElseThrow());
       Assertions.assertEquals("Equal", ast.rules.rule.get(1).expression.op.type.name());
       Assertions.assertEquals("Bob", ast.rules.rule.get(1).expression.right.variableName);
       Assertions.assertEquals("Stories", ast.rules.rule.get(2).expression.left.variableName);
       Assertions.assertEquals("2", ast.rules.rule.get(2).expression.left.vrmodifier.orElseThrow().size);
       Assertions.assertEquals(true, ast.rules.rule.get(2).expression.left.vrmodifier.orElseThrow().vrmodifier.orElseThrow().dot);
       Assertions.assertEquals("a", ast.rules.rule.get(2).expression.left.vrmodifier.orElseThrow().vrmodifier.orElseThrow().part.orElseThrow());
       Assertions.assertEquals("Equal", ast.rules.rule.get(2).expression.op.type.name());
       Assertions.assertEquals("Carol", ast.rules.rule.get(2).expression.right.variableName);
       Assertions.assertEquals("Stories", ast.rules.rule.get(3).expression.left.variableName);
       Assertions.assertEquals("3", ast.rules.rule.get(3).expression.left.vrmodifier.orElseThrow().size);
       Assertions.assertEquals(true, ast.rules.rule.get(3).expression.left.vrmodifier.orElseThrow().vrmodifier.orElseThrow().dot);
       Assertions.assertEquals("a", ast.rules.rule.get(3).expression.left.vrmodifier.orElseThrow().vrmodifier.orElseThrow().part.orElseThrow());
       Assertions.assertEquals("Equal", ast.rules.rule.get(3).expression.op.type.name());
       Assertions.assertEquals("David", ast.rules.rule.get(3).expression.right.variableName);
       Assertions.assertEquals("Stories", ast.rules.rule.get(4).expression.left.variableName);
       Assertions.assertEquals(true, ast.rules.rule.get(4).expression.left.vrmodifier.orElseThrow().dot);
       Assertions.assertEquals("a", ast.rules.rule.get(4).expression.left.vrmodifier.orElseThrow().part.orElseThrow());
       Assertions.assertEquals("Equal", ast.rules.rule.get(4).expression.op.type.name());
       Assertions.assertEquals("Alice", ast.rules.rule.get(4).expression.right.variableName);
       Assertions.assertEquals("Stories", ast.rules.rule.get(4).thens.get(0).left.variableName);
       Assertions.assertEquals(true, ast.rules.rule.get(4).thens.get(0).left.vrmodifier.orElseThrow().dot);
       Assertions.assertEquals("h", ast.rules.rule.get(4).thens.get(0).left.vrmodifier.orElseThrow().part.orElseThrow());
       Assertions.assertEquals("NotEqual", ast.rules.rule.get(4).thens.get(0).op.type.name());
       Assertions.assertEquals("Red", ast.rules.rule.get(4).thens.get(0).right.variableName);
       Assertions.assertEquals("Stories", ast.rules.rule.get(5).expression.left.variableName);
       Assertions.assertEquals(true, ast.rules.rule.get(5).expression.left.vrmodifier.orElseThrow().dot);
       Assertions.assertEquals("h", ast.rules.rule.get(5).expression.left.vrmodifier.orElseThrow().part.orElseThrow());
       Assertions.assertEquals("Equal", ast.rules.rule.get(5).expression.op.type.name());
       Assertions.assertEquals("Blue", ast.rules.rule.get(5).expression.right.variableName);
       Assertions.assertEquals("Stories", ast.rules.rule.get(5).thens.get(0).left.variableName);
       Assertions.assertEquals(true, ast.rules.rule.get(5).thens.get(0).left.vrmodifier.orElseThrow().dot);
       Assertions.assertEquals("p", ast.rules.rule.get(5).thens.get(0).left.vrmodifier.orElseThrow().part.orElseThrow());
       Assertions.assertEquals("Equal", ast.rules.rule.get(5).thens.get(0).op.type.name());
       Assertions.assertEquals("Cat", ast.rules.rule.get(5).thens.get(0).right.variableName);
       Assertions.assertEquals("Stories", ast.rules.rule.get(6).expression.left.variableName);
       Assertions.assertEquals(true, ast.rules.rule.get(6).expression.left.vrmodifier.orElseThrow().dot);
       Assertions.assertEquals("a", ast.rules.rule.get(6).expression.left.vrmodifier.orElseThrow().part.orElseThrow());
       Assertions.assertEquals("Equal", ast.rules.rule.get(6).expression.op.type.name());
       Assertions.assertEquals("David", ast.rules.rule.get(6).expression.right.variableName);
       Assertions.assertEquals("Stories", ast.rules.rule.get(6).thens.get(0).left.variableName);
       Assertions.assertEquals(true, ast.rules.rule.get(6).thens.get(0).left.vrmodifier.orElseThrow().dot);
       Assertions.assertEquals("p", ast.rules.rule.get(6).thens.get(0).left.vrmodifier.orElseThrow().part.orElseThrow());
       Assertions.assertEquals("Equal", ast.rules.rule.get(6).thens.get(0).op.type.name());
       Assertions.assertEquals("Dog", ast.rules.rule.get(6).thens.get(0).right.variableName);
       Assertions.assertEquals("Stories", ast.rules.rule.get(7).expression.left.variableName);
       Assertions.assertEquals(true, ast.rules.rule.get(7).expression.left.vrmodifier.orElseThrow().dot);
       Assertions.assertEquals("a", ast.rules.rule.get(7).expression.left.vrmodifier.orElseThrow().part.orElseThrow());
       Assertions.assertEquals("Equal", ast.rules.rule.get(7).expression.op.type.name());
       Assertions.assertEquals("Carol", ast.rules.rule.get(7).expression.right.variableName);
       Assertions.assertEquals("Stories", ast.rules.rule.get(7).thens.get(0).left.variableName);
       Assertions.assertEquals(true, ast.rules.rule.get(7).thens.get(0).left.vrmodifier.orElseThrow().dot);
       Assertions.assertEquals("h", ast.rules.rule.get(7).thens.get(0).left.vrmodifier.orElseThrow().part.orElseThrow());
       Assertions.assertEquals("Equal", ast.rules.rule.get(7).thens.get(0).op.type.name());
       Assertions.assertEquals("Green", ast.rules.rule.get(7).thens.get(0).right.variableName);
       Assertions.assertEquals("Stories", ast.rules.rule.get(8).expression.left.variableName);
       Assertions.assertEquals(true, ast.rules.rule.get(8).expression.left.vrmodifier.orElseThrow().dot);
       Assertions.assertEquals("a", ast.rules.rule.get(8).expression.left.vrmodifier.orElseThrow().part.orElseThrow());
       Assertions.assertEquals("Equal", ast.rules.rule.get(8).expression.op.type.name());
       Assertions.assertEquals("Bob", ast.rules.rule.get(8).expression.right.variableName);
       Assertions.assertEquals("Stories", ast.rules.rule.get(8).thens.get(0).left.variableName);
       Assertions.assertEquals(true, ast.rules.rule.get(8).thens.get(0).left.vrmodifier.orElseThrow().dot);
       Assertions.assertEquals("p", ast.rules.rule.get(8).thens.get(0).left.vrmodifier.orElseThrow().part.orElseThrow());
       Assertions.assertEquals("NotEqual", ast.rules.rule.get(8).thens.get(0).op.type.name());
       Assertions.assertEquals("Fish", ast.rules.rule.get(8).thens.get(0).right.variableName);
   }

   @Test
   public void TestFriendsParser() throws Exception {
       var code =          "Author = {Joanne, Lou, Ralph, Winnie}\n"+
               "\n"+
               "Place = {Circus, Band, Spaceship, Train}\n"+
               "\n"+
               "Animal = {Bear, Moose, Seal, Zebra}\n"+
               "\n"+
               "Story = [unique Author a, unique Place p, unique Animal f]\n"+
               "\n"+
               "\n"+
               "var Stories : Story[4]\n"+
               "\n"+
               "\n"+
               "Stories[0].a = Joanne\n"+
               "\n"+
               "Stories[1].a = Lou\n"+
               "\n"+
               "Stories[2].a = Ralph\n"+
               "\n"+
               "Stories[3].a = Winnie\n"+
               "\n"+
               "\n"+
               "Stories.f = Seal =>\n"+
               "    Stories.a != Joanne\n"+
               "    Stories.a != Lou\n"+
               "    Stories.p != Spaceship\n"+
               "    Stories.p != Train\n"+
               "\n"+
               "Stories.a = Joanne =>\n"+
               "    Stories.f != Bear\n"+
               "    Stories.p = Circus\n"+
               "\n"+
               "Stories.a = Winnie =>\n"+
               "    Stories.f = Zebra\n"+
               "\n"+
               "Stories.f = Bear =>\n"+
               "    Stories.p != Spaceship\n"+
               "";

       var tokens = new LinkedList<Token>();
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 1, 6, "Author"));
       tokens.add(new Token(Token.TokenTypes.EQUAL, 1, 8));
       tokens.add(new Token(Token.TokenTypes.LEFTCURLY, 1, 10));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 1, 16, "Joanne"));
       tokens.add(new Token(Token.TokenTypes.COMMA, 1, 17));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 1, 21, "Lou"));
       tokens.add(new Token(Token.TokenTypes.COMMA, 1, 22));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 1, 28, "Ralph"));
       tokens.add(new Token(Token.TokenTypes.COMMA, 1, 29));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 1, 36, "Winnie"));
       tokens.add(new Token(Token.TokenTypes.RIGHTCURLY, 1, 37));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 2, 0));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 3, 0));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 3, 5, "Place"));
       tokens.add(new Token(Token.TokenTypes.EQUAL, 3, 7));
       tokens.add(new Token(Token.TokenTypes.LEFTCURLY, 3, 9));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 3, 15, "Circus"));
       tokens.add(new Token(Token.TokenTypes.COMMA, 3, 16));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 3, 21, "Band"));
       tokens.add(new Token(Token.TokenTypes.COMMA, 3, 22));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 3, 32, "Spaceship"));
       tokens.add(new Token(Token.TokenTypes.COMMA, 3, 33));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 3, 39, "Train"));
       tokens.add(new Token(Token.TokenTypes.RIGHTCURLY, 3, 40));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 4, 0));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 5, 0));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 5, 6, "Animal"));
       tokens.add(new Token(Token.TokenTypes.EQUAL, 5, 8));
       tokens.add(new Token(Token.TokenTypes.LEFTCURLY, 5, 10));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 5, 14, "Bear"));
       tokens.add(new Token(Token.TokenTypes.COMMA, 5, 15));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 5, 21, "Moose"));
       tokens.add(new Token(Token.TokenTypes.COMMA, 5, 22));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 5, 27, "Seal"));
       tokens.add(new Token(Token.TokenTypes.COMMA, 5, 28));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 5, 34, "Zebra"));
       tokens.add(new Token(Token.TokenTypes.RIGHTCURLY, 5, 35));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 6, 0));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 7, 0));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 7, 5, "Story"));
       tokens.add(new Token(Token.TokenTypes.EQUAL, 7, 7));
       tokens.add(new Token(Token.TokenTypes.LEFTBRACE, 7, 9));
       tokens.add(new Token(Token.TokenTypes.UNIQUE, 7, 15));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 7, 22, "Author"));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 7, 24, "a"));
       tokens.add(new Token(Token.TokenTypes.COMMA, 7, 25));
       tokens.add(new Token(Token.TokenTypes.UNIQUE, 7, 32));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 7, 38, "Place"));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 7, 40, "p"));
       tokens.add(new Token(Token.TokenTypes.COMMA, 7, 41));
       tokens.add(new Token(Token.TokenTypes.UNIQUE, 7, 48));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 7, 55, "Animal"));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 7, 57, "f"));
       tokens.add(new Token(Token.TokenTypes.RIGHTBRACE, 7, 58));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 8, 0));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 9, 0));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 10, 0));
       tokens.add(new Token(Token.TokenTypes.VAR, 10, 3));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 10, 11, "Stories"));
       tokens.add(new Token(Token.TokenTypes.COLON, 10, 13));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 10, 19, "Story"));
       tokens.add(new Token(Token.TokenTypes.LEFTBRACE, 10, 20));
       tokens.add(new Token(Token.TokenTypes.NUMBER, 10, 21, "4"));
       tokens.add(new Token(Token.TokenTypes.RIGHTBRACE, 10, 22));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 11, 0));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 12, 0));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 13, 0));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 13, 7, "Stories"));
       tokens.add(new Token(Token.TokenTypes.LEFTBRACE, 13, 8));
       tokens.add(new Token(Token.TokenTypes.NUMBER, 13, 9, "0"));
       tokens.add(new Token(Token.TokenTypes.RIGHTBRACE, 13, 10));
       tokens.add(new Token(Token.TokenTypes.DOT, 13, 11));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 13, 12, "a"));
       tokens.add(new Token(Token.TokenTypes.EQUAL, 13, 14));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 13, 21, "Joanne"));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 14, 0));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 15, 0));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 15, 7, "Stories"));
       tokens.add(new Token(Token.TokenTypes.LEFTBRACE, 15, 8));
       tokens.add(new Token(Token.TokenTypes.NUMBER, 15, 9, "1"));
       tokens.add(new Token(Token.TokenTypes.RIGHTBRACE, 15, 10));
       tokens.add(new Token(Token.TokenTypes.DOT, 15, 11));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 15, 12, "a"));
       tokens.add(new Token(Token.TokenTypes.EQUAL, 15, 14));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 15, 18, "Lou"));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 16, 0));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 17, 0));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 17, 7, "Stories"));
       tokens.add(new Token(Token.TokenTypes.LEFTBRACE, 17, 8));
       tokens.add(new Token(Token.TokenTypes.NUMBER, 17, 9, "2"));
       tokens.add(new Token(Token.TokenTypes.RIGHTBRACE, 17, 10));
       tokens.add(new Token(Token.TokenTypes.DOT, 17, 11));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 17, 12, "a"));
       tokens.add(new Token(Token.TokenTypes.EQUAL, 17, 14));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 17, 20, "Ralph"));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 18, 0));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 19, 0));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 19, 7, "Stories"));
       tokens.add(new Token(Token.TokenTypes.LEFTBRACE, 19, 8));
       tokens.add(new Token(Token.TokenTypes.NUMBER, 19, 9, "3"));
       tokens.add(new Token(Token.TokenTypes.RIGHTBRACE, 19, 10));
       tokens.add(new Token(Token.TokenTypes.DOT, 19, 11));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 19, 12, "a"));
       tokens.add(new Token(Token.TokenTypes.EQUAL, 19, 14));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 19, 21, "Winnie"));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 20, 0));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 21, 0));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 22, 0));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 22, 7, "Stories"));
       tokens.add(new Token(Token.TokenTypes.DOT, 22, 8));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 22, 9, "f"));
       tokens.add(new Token(Token.TokenTypes.EQUAL, 22, 11));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 22, 16, "Seal"));
       tokens.add(new Token(Token.TokenTypes.YIELDS, 22, 19));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 23, 0));
       tokens.add(new Token(Token.TokenTypes.INDENT, 23, 4));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 23, 11, "Stories"));
       tokens.add(new Token(Token.TokenTypes.DOT, 23, 12));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 23, 13, "a"));
       tokens.add(new Token(Token.TokenTypes.NOTEQUAL, 23, 16));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 23, 23, "Joanne"));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 24, 0));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 24, 11, "Stories"));
       tokens.add(new Token(Token.TokenTypes.DOT, 24, 12));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 24, 13, "a"));
       tokens.add(new Token(Token.TokenTypes.NOTEQUAL, 24, 16));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 24, 20, "Lou"));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 25, 0));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 25, 11, "Stories"));
       tokens.add(new Token(Token.TokenTypes.DOT, 25, 12));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 25, 13, "p"));
       tokens.add(new Token(Token.TokenTypes.NOTEQUAL, 25, 16));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 25, 26, "Spaceship"));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 26, 0));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 26, 11, "Stories"));
       tokens.add(new Token(Token.TokenTypes.DOT, 26, 12));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 26, 13, "p"));
       tokens.add(new Token(Token.TokenTypes.NOTEQUAL, 26, 16));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 26, 22, "Train"));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 27, 0));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 28, 0));
       tokens.add(new Token(Token.TokenTypes.DEDENT, 28, 0));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 28, 7, "Stories"));
       tokens.add(new Token(Token.TokenTypes.DOT, 28, 8));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 28, 9, "a"));
       tokens.add(new Token(Token.TokenTypes.EQUAL, 28, 11));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 28, 18, "Joanne"));
       tokens.add(new Token(Token.TokenTypes.YIELDS, 28, 21));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 29, 0));
       tokens.add(new Token(Token.TokenTypes.INDENT, 29, 4));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 29, 11, "Stories"));
       tokens.add(new Token(Token.TokenTypes.DOT, 29, 12));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 29, 13, "f"));
       tokens.add(new Token(Token.TokenTypes.NOTEQUAL, 29, 16));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 29, 21, "Bear"));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 30, 0));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 30, 11, "Stories"));
       tokens.add(new Token(Token.TokenTypes.DOT, 30, 12));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 30, 13, "p"));
       tokens.add(new Token(Token.TokenTypes.EQUAL, 30, 15));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 30, 22, "Circus"));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 31, 0));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 32, 0));
       tokens.add(new Token(Token.TokenTypes.DEDENT, 32, 0));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 32, 7, "Stories"));
       tokens.add(new Token(Token.TokenTypes.DOT, 32, 8));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 32, 9, "a"));
       tokens.add(new Token(Token.TokenTypes.EQUAL, 32, 11));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 32, 18, "Winnie"));
       tokens.add(new Token(Token.TokenTypes.YIELDS, 32, 21));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 33, 0));
       tokens.add(new Token(Token.TokenTypes.INDENT, 33, 4));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 33, 11, "Stories"));
       tokens.add(new Token(Token.TokenTypes.DOT, 33, 12));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 33, 13, "f"));
       tokens.add(new Token(Token.TokenTypes.EQUAL, 33, 15));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 33, 21, "Zebra"));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 34, 0));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 35, 0));
       tokens.add(new Token(Token.TokenTypes.DEDENT, 35, 0));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 35, 7, "Stories"));
       tokens.add(new Token(Token.TokenTypes.DOT, 35, 8));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 35, 9, "f"));
       tokens.add(new Token(Token.TokenTypes.EQUAL, 35, 11));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 35, 16, "Bear"));
       tokens.add(new Token(Token.TokenTypes.YIELDS, 35, 19));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 36, 0));
       tokens.add(new Token(Token.TokenTypes.INDENT, 36, 4));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 36, 11, "Stories"));
       tokens.add(new Token(Token.TokenTypes.DOT, 36, 12));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 36, 13, "p"));
       tokens.add(new Token(Token.TokenTypes.NOTEQUAL, 36, 16));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 36, 26, "Spaceship"));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 37, 0));
       tokens.add(new Token(Token.TokenTypes.DEDENT, 37, 0));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 37, 0));

       var ast = new NushaFall2025Parser().Nusha(tokens).orElseThrow();
       Assertions.assertEquals("Author", ast.definitions.definition.get(0).definitionName);
       Assertions.assertEquals("Joanne", ast.definitions.definition.get(0).choices.orElseThrow().choice.get(0));
       Assertions.assertEquals("Lou", ast.definitions.definition.get(0).choices.orElseThrow().choice.get(1));
       Assertions.assertEquals("Ralph", ast.definitions.definition.get(0).choices.orElseThrow().choice.get(2));
       Assertions.assertEquals("Winnie", ast.definitions.definition.get(0).choices.orElseThrow().choice.get(3));
       Assertions.assertEquals("Place", ast.definitions.definition.get(1).definitionName);
       Assertions.assertEquals("Circus", ast.definitions.definition.get(1).choices.orElseThrow().choice.get(0));
       Assertions.assertEquals("Band", ast.definitions.definition.get(1).choices.orElseThrow().choice.get(1));
       Assertions.assertEquals("Spaceship", ast.definitions.definition.get(1).choices.orElseThrow().choice.get(2));
       Assertions.assertEquals("Train", ast.definitions.definition.get(1).choices.orElseThrow().choice.get(3));
       Assertions.assertEquals("Animal", ast.definitions.definition.get(2).definitionName);
       Assertions.assertEquals("Bear", ast.definitions.definition.get(2).choices.orElseThrow().choice.get(0));
       Assertions.assertEquals("Moose", ast.definitions.definition.get(2).choices.orElseThrow().choice.get(1));
       Assertions.assertEquals("Seal", ast.definitions.definition.get(2).choices.orElseThrow().choice.get(2));
       Assertions.assertEquals("Zebra", ast.definitions.definition.get(2).choices.orElseThrow().choice.get(3));
       Assertions.assertEquals("Story", ast.definitions.definition.get(3).definitionName);
       Assertions.assertEquals("a", ast.definitions.definition.get(3).nstruct.orElseThrow().entry.get(0).name);
       Assertions.assertEquals("Author", ast.definitions.definition.get(3).nstruct.orElseThrow().entry.get(0).type);
       Assertions.assertEquals(true, ast.definitions.definition.get(3).nstruct.orElseThrow().entry.get(0).unique);
       Assertions.assertEquals("p", ast.definitions.definition.get(3).nstruct.orElseThrow().entry.get(1).name);
       Assertions.assertEquals("Place", ast.definitions.definition.get(3).nstruct.orElseThrow().entry.get(1).type);
       Assertions.assertEquals(true, ast.definitions.definition.get(3).nstruct.orElseThrow().entry.get(1).unique);
       Assertions.assertEquals("f", ast.definitions.definition.get(3).nstruct.orElseThrow().entry.get(2).name);
       Assertions.assertEquals("Animal", ast.definitions.definition.get(3).nstruct.orElseThrow().entry.get(2).type);
       Assertions.assertEquals(true, ast.definitions.definition.get(3).nstruct.orElseThrow().entry.get(2).unique);
       Assertions.assertEquals("Story", ast.variables.variable.get(0).type);
       Assertions.assertEquals("Stories", ast.variables.variable.get(0).variableName);
       Assertions.assertEquals("4", ast.variables.variable.get(0).size.orElseThrow());
       Assertions.assertEquals("Stories", ast.rules.rule.get(0).expression.left.variableName);
       Assertions.assertEquals("0", ast.rules.rule.get(0).expression.left.vrmodifier.orElseThrow().size);
       Assertions.assertEquals(true, ast.rules.rule.get(0).expression.left.vrmodifier.orElseThrow().vrmodifier.orElseThrow().dot);
       Assertions.assertEquals("a", ast.rules.rule.get(0).expression.left.vrmodifier.orElseThrow().vrmodifier.orElseThrow().part.orElseThrow());
       Assertions.assertEquals("Equal", ast.rules.rule.get(0).expression.op.type.name());
       Assertions.assertEquals("Joanne", ast.rules.rule.get(0).expression.right.variableName);
       Assertions.assertEquals("Stories", ast.rules.rule.get(1).expression.left.variableName);
       Assertions.assertEquals("1", ast.rules.rule.get(1).expression.left.vrmodifier.orElseThrow().size);
       Assertions.assertEquals(true, ast.rules.rule.get(1).expression.left.vrmodifier.orElseThrow().vrmodifier.orElseThrow().dot);
       Assertions.assertEquals("a", ast.rules.rule.get(1).expression.left.vrmodifier.orElseThrow().vrmodifier.orElseThrow().part.orElseThrow());
       Assertions.assertEquals("Equal", ast.rules.rule.get(1).expression.op.type.name());
       Assertions.assertEquals("Lou", ast.rules.rule.get(1).expression.right.variableName);
       Assertions.assertEquals("Stories", ast.rules.rule.get(2).expression.left.variableName);
       Assertions.assertEquals("2", ast.rules.rule.get(2).expression.left.vrmodifier.orElseThrow().size);
       Assertions.assertEquals(true, ast.rules.rule.get(2).expression.left.vrmodifier.orElseThrow().vrmodifier.orElseThrow().dot);
       Assertions.assertEquals("a", ast.rules.rule.get(2).expression.left.vrmodifier.orElseThrow().vrmodifier.orElseThrow().part.orElseThrow());
       Assertions.assertEquals("Equal", ast.rules.rule.get(2).expression.op.type.name());
       Assertions.assertEquals("Ralph", ast.rules.rule.get(2).expression.right.variableName);
       Assertions.assertEquals("Stories", ast.rules.rule.get(3).expression.left.variableName);
       Assertions.assertEquals("3", ast.rules.rule.get(3).expression.left.vrmodifier.orElseThrow().size);
       Assertions.assertEquals(true, ast.rules.rule.get(3).expression.left.vrmodifier.orElseThrow().vrmodifier.orElseThrow().dot);
       Assertions.assertEquals("a", ast.rules.rule.get(3).expression.left.vrmodifier.orElseThrow().vrmodifier.orElseThrow().part.orElseThrow());
       Assertions.assertEquals("Equal", ast.rules.rule.get(3).expression.op.type.name());
       Assertions.assertEquals("Winnie", ast.rules.rule.get(3).expression.right.variableName);
       Assertions.assertEquals("Stories", ast.rules.rule.get(4).expression.left.variableName);
       Assertions.assertEquals(true, ast.rules.rule.get(4).expression.left.vrmodifier.orElseThrow().dot);
       Assertions.assertEquals("f", ast.rules.rule.get(4).expression.left.vrmodifier.orElseThrow().part.orElseThrow());
       Assertions.assertEquals("Equal", ast.rules.rule.get(4).expression.op.type.name());
       Assertions.assertEquals("Seal", ast.rules.rule.get(4).expression.right.variableName);
       Assertions.assertEquals("Stories", ast.rules.rule.get(4).thens.get(0).left.variableName);
       Assertions.assertEquals(true, ast.rules.rule.get(4).thens.get(0).left.vrmodifier.orElseThrow().dot);
       Assertions.assertEquals("a", ast.rules.rule.get(4).thens.get(0).left.vrmodifier.orElseThrow().part.orElseThrow());
       Assertions.assertEquals("NotEqual", ast.rules.rule.get(4).thens.get(0).op.type.name());
       Assertions.assertEquals("Joanne", ast.rules.rule.get(4).thens.get(0).right.variableName);
       Assertions.assertEquals("Stories", ast.rules.rule.get(4).thens.get(1).left.variableName);
       Assertions.assertEquals(true, ast.rules.rule.get(4).thens.get(1).left.vrmodifier.orElseThrow().dot);
       Assertions.assertEquals("a", ast.rules.rule.get(4).thens.get(1).left.vrmodifier.orElseThrow().part.orElseThrow());
       Assertions.assertEquals("NotEqual", ast.rules.rule.get(4).thens.get(1).op.type.name());
       Assertions.assertEquals("Lou", ast.rules.rule.get(4).thens.get(1).right.variableName);
       Assertions.assertEquals("Stories", ast.rules.rule.get(4).thens.get(2).left.variableName);
       Assertions.assertEquals(true, ast.rules.rule.get(4).thens.get(2).left.vrmodifier.orElseThrow().dot);
       Assertions.assertEquals("p", ast.rules.rule.get(4).thens.get(2).left.vrmodifier.orElseThrow().part.orElseThrow());
       Assertions.assertEquals("NotEqual", ast.rules.rule.get(4).thens.get(2).op.type.name());
       Assertions.assertEquals("Spaceship", ast.rules.rule.get(4).thens.get(2).right.variableName);
       Assertions.assertEquals("Stories", ast.rules.rule.get(4).thens.get(3).left.variableName);
       Assertions.assertEquals(true, ast.rules.rule.get(4).thens.get(3).left.vrmodifier.orElseThrow().dot);
       Assertions.assertEquals("p", ast.rules.rule.get(4).thens.get(3).left.vrmodifier.orElseThrow().part.orElseThrow());
       Assertions.assertEquals("NotEqual", ast.rules.rule.get(4).thens.get(3).op.type.name());
       Assertions.assertEquals("Train", ast.rules.rule.get(4).thens.get(3).right.variableName);
       Assertions.assertEquals("Stories", ast.rules.rule.get(5).expression.left.variableName);
       Assertions.assertEquals(true, ast.rules.rule.get(5).expression.left.vrmodifier.orElseThrow().dot);
       Assertions.assertEquals("a", ast.rules.rule.get(5).expression.left.vrmodifier.orElseThrow().part.orElseThrow());
       Assertions.assertEquals("Equal", ast.rules.rule.get(5).expression.op.type.name());
       Assertions.assertEquals("Joanne", ast.rules.rule.get(5).expression.right.variableName);
       Assertions.assertEquals("Stories", ast.rules.rule.get(5).thens.get(0).left.variableName);
       Assertions.assertEquals(true, ast.rules.rule.get(5).thens.get(0).left.vrmodifier.orElseThrow().dot);
       Assertions.assertEquals("f", ast.rules.rule.get(5).thens.get(0).left.vrmodifier.orElseThrow().part.orElseThrow());
       Assertions.assertEquals("NotEqual", ast.rules.rule.get(5).thens.get(0).op.type.name());
       Assertions.assertEquals("Bear", ast.rules.rule.get(5).thens.get(0).right.variableName);
       Assertions.assertEquals("Stories", ast.rules.rule.get(5).thens.get(1).left.variableName);
       Assertions.assertEquals(true, ast.rules.rule.get(5).thens.get(1).left.vrmodifier.orElseThrow().dot);
       Assertions.assertEquals("p", ast.rules.rule.get(5).thens.get(1).left.vrmodifier.orElseThrow().part.orElseThrow());
       Assertions.assertEquals("Equal", ast.rules.rule.get(5).thens.get(1).op.type.name());
       Assertions.assertEquals("Circus", ast.rules.rule.get(5).thens.get(1).right.variableName);
       Assertions.assertEquals("Stories", ast.rules.rule.get(6).expression.left.variableName);
       Assertions.assertEquals(true, ast.rules.rule.get(6).expression.left.vrmodifier.orElseThrow().dot);
       Assertions.assertEquals("a", ast.rules.rule.get(6).expression.left.vrmodifier.orElseThrow().part.orElseThrow());
       Assertions.assertEquals("Equal", ast.rules.rule.get(6).expression.op.type.name());
       Assertions.assertEquals("Winnie", ast.rules.rule.get(6).expression.right.variableName);
       Assertions.assertEquals("Stories", ast.rules.rule.get(6).thens.get(0).left.variableName);
       Assertions.assertEquals(true, ast.rules.rule.get(6).thens.get(0).left.vrmodifier.orElseThrow().dot);
       Assertions.assertEquals("f", ast.rules.rule.get(6).thens.get(0).left.vrmodifier.orElseThrow().part.orElseThrow());
       Assertions.assertEquals("Equal", ast.rules.rule.get(6).thens.get(0).op.type.name());
       Assertions.assertEquals("Zebra", ast.rules.rule.get(6).thens.get(0).right.variableName);
       Assertions.assertEquals("Stories", ast.rules.rule.get(7).expression.left.variableName);
       Assertions.assertEquals(true, ast.rules.rule.get(7).expression.left.vrmodifier.orElseThrow().dot);
       Assertions.assertEquals("f", ast.rules.rule.get(7).expression.left.vrmodifier.orElseThrow().part.orElseThrow());
       Assertions.assertEquals("Equal", ast.rules.rule.get(7).expression.op.type.name());
       Assertions.assertEquals("Bear", ast.rules.rule.get(7).expression.right.variableName);
       Assertions.assertEquals("Stories", ast.rules.rule.get(7).thens.get(0).left.variableName);
       Assertions.assertEquals(true, ast.rules.rule.get(7).thens.get(0).left.vrmodifier.orElseThrow().dot);
       Assertions.assertEquals("p", ast.rules.rule.get(7).thens.get(0).left.vrmodifier.orElseThrow().part.orElseThrow());
       Assertions.assertEquals("NotEqual", ast.rules.rule.get(7).thens.get(0).op.type.name());
       Assertions.assertEquals("Spaceship", ast.rules.rule.get(7).thens.get(0).right.variableName);
   }

   @Test
   public void TestBirthdayParser() throws Exception {
       var code =          "Child = {John, Ram, Zara, Sana}\n"+
               "Cake = {Chocolate, Vanilla, Strawberry, Pineapple}\n"+
               "Gift = {Pen, Game, Candy, Frame}\n"+
               "Balloon = {Red, Orange, Purple, Black}\n"+
               "\n"+
               "Party = [unique Child c, unique Cake k, unique Gift g, unique Balloon b]\n"+
               "\n"+
               "var Parties : Party[4]\n"+
               "\n"+
               "Parties.k = Strawberry =>\n"+
               "    Parties.g != Game\n"+
               "    Parties.c != John\n"+
               "\n"+
               "Parties.c = Ram =>\n"+
               "    Parties.b = Orange\n"+
               "    Parties.k != Chocolate\n"+
               "\n"+
               "Parties.g = Candy =>\n"+
               "    Parties.k = Vanilla\n"+
               "\n"+
               "Parties.c = Zara =>\n"+
               "    Parties.g = Pen\n"+
               "\n"+
               "Parties.b = Purple =>\n"+
               "    Parties.k = Pineapple\n"+
               "";

       var tokens = new LinkedList<Token>();
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 1, 5, "Child"));
       tokens.add(new Token(Token.TokenTypes.EQUAL, 1, 7));
       tokens.add(new Token(Token.TokenTypes.LEFTCURLY, 1, 9));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 1, 13, "John"));
       tokens.add(new Token(Token.TokenTypes.COMMA, 1, 14));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 1, 18, "Ram"));
       tokens.add(new Token(Token.TokenTypes.COMMA, 1, 19));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 1, 24, "Zara"));
       tokens.add(new Token(Token.TokenTypes.COMMA, 1, 25));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 1, 30, "Sana"));
       tokens.add(new Token(Token.TokenTypes.RIGHTCURLY, 1, 31));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 2, 0));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 2, 4, "Cake"));
       tokens.add(new Token(Token.TokenTypes.EQUAL, 2, 6));
       tokens.add(new Token(Token.TokenTypes.LEFTCURLY, 2, 8));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 2, 17, "Chocolate"));
       tokens.add(new Token(Token.TokenTypes.COMMA, 2, 18));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 2, 26, "Vanilla"));
       tokens.add(new Token(Token.TokenTypes.COMMA, 2, 27));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 2, 38, "Strawberry"));
       tokens.add(new Token(Token.TokenTypes.COMMA, 2, 39));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 2, 49, "Pineapple"));
       tokens.add(new Token(Token.TokenTypes.RIGHTCURLY, 2, 50));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 3, 0));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 3, 4, "Gift"));
       tokens.add(new Token(Token.TokenTypes.EQUAL, 3, 6));
       tokens.add(new Token(Token.TokenTypes.LEFTCURLY, 3, 8));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 3, 11, "Pen"));
       tokens.add(new Token(Token.TokenTypes.COMMA, 3, 12));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 3, 17, "Game"));
       tokens.add(new Token(Token.TokenTypes.COMMA, 3, 18));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 3, 24, "Candy"));
       tokens.add(new Token(Token.TokenTypes.COMMA, 3, 25));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 3, 31, "Frame"));
       tokens.add(new Token(Token.TokenTypes.RIGHTCURLY, 3, 32));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 4, 0));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 4, 7, "Balloon"));
       tokens.add(new Token(Token.TokenTypes.EQUAL, 4, 9));
       tokens.add(new Token(Token.TokenTypes.LEFTCURLY, 4, 11));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 4, 14, "Red"));
       tokens.add(new Token(Token.TokenTypes.COMMA, 4, 15));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 4, 22, "Orange"));
       tokens.add(new Token(Token.TokenTypes.COMMA, 4, 23));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 4, 30, "Purple"));
       tokens.add(new Token(Token.TokenTypes.COMMA, 4, 31));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 4, 37, "Black"));
       tokens.add(new Token(Token.TokenTypes.RIGHTCURLY, 4, 38));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 5, 0));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 6, 0));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 6, 5, "Party"));
       tokens.add(new Token(Token.TokenTypes.EQUAL, 6, 7));
       tokens.add(new Token(Token.TokenTypes.LEFTBRACE, 6, 9));
       tokens.add(new Token(Token.TokenTypes.UNIQUE, 6, 15));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 6, 21, "Child"));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 6, 23, "c"));
       tokens.add(new Token(Token.TokenTypes.COMMA, 6, 24));
       tokens.add(new Token(Token.TokenTypes.UNIQUE, 6, 31));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 6, 36, "Cake"));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 6, 38, "k"));
       tokens.add(new Token(Token.TokenTypes.COMMA, 6, 39));
       tokens.add(new Token(Token.TokenTypes.UNIQUE, 6, 46));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 6, 51, "Gift"));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 6, 53, "g"));
       tokens.add(new Token(Token.TokenTypes.COMMA, 6, 54));
       tokens.add(new Token(Token.TokenTypes.UNIQUE, 6, 61));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 6, 69, "Balloon"));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 6, 71, "b"));
       tokens.add(new Token(Token.TokenTypes.RIGHTBRACE, 6, 72));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 7, 0));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 8, 0));
       tokens.add(new Token(Token.TokenTypes.VAR, 8, 3));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 8, 11, "Parties"));
       tokens.add(new Token(Token.TokenTypes.COLON, 8, 13));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 8, 19, "Party"));
       tokens.add(new Token(Token.TokenTypes.LEFTBRACE, 8, 20));
       tokens.add(new Token(Token.TokenTypes.NUMBER, 8, 21, "4"));
       tokens.add(new Token(Token.TokenTypes.RIGHTBRACE, 8, 22));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 9, 0));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 10, 0));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 10, 7, "Parties"));
       tokens.add(new Token(Token.TokenTypes.DOT, 10, 8));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 10, 9, "k"));
       tokens.add(new Token(Token.TokenTypes.EQUAL, 10, 11));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 10, 22, "Strawberry"));
       tokens.add(new Token(Token.TokenTypes.YIELDS, 10, 25));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 11, 0));
       tokens.add(new Token(Token.TokenTypes.INDENT, 11, 4));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 11, 11, "Parties"));
       tokens.add(new Token(Token.TokenTypes.DOT, 11, 12));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 11, 13, "g"));
       tokens.add(new Token(Token.TokenTypes.NOTEQUAL, 11, 16));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 11, 21, "Game"));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 12, 0));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 12, 11, "Parties"));
       tokens.add(new Token(Token.TokenTypes.DOT, 12, 12));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 12, 13, "c"));
       tokens.add(new Token(Token.TokenTypes.NOTEQUAL, 12, 16));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 12, 21, "John"));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 13, 0));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 14, 0));
       tokens.add(new Token(Token.TokenTypes.DEDENT, 14, 0));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 14, 7, "Parties"));
       tokens.add(new Token(Token.TokenTypes.DOT, 14, 8));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 14, 9, "c"));
       tokens.add(new Token(Token.TokenTypes.EQUAL, 14, 11));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 14, 15, "Ram"));
       tokens.add(new Token(Token.TokenTypes.YIELDS, 14, 18));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 15, 0));
       tokens.add(new Token(Token.TokenTypes.INDENT, 15, 4));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 15, 11, "Parties"));
       tokens.add(new Token(Token.TokenTypes.DOT, 15, 12));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 15, 13, "b"));
       tokens.add(new Token(Token.TokenTypes.EQUAL, 15, 15));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 15, 22, "Orange"));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 16, 0));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 16, 11, "Parties"));
       tokens.add(new Token(Token.TokenTypes.DOT, 16, 12));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 16, 13, "k"));
       tokens.add(new Token(Token.TokenTypes.NOTEQUAL, 16, 16));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 16, 26, "Chocolate"));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 17, 0));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 18, 0));
       tokens.add(new Token(Token.TokenTypes.DEDENT, 18, 0));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 18, 7, "Parties"));
       tokens.add(new Token(Token.TokenTypes.DOT, 18, 8));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 18, 9, "g"));
       tokens.add(new Token(Token.TokenTypes.EQUAL, 18, 11));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 18, 17, "Candy"));
       tokens.add(new Token(Token.TokenTypes.YIELDS, 18, 20));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 19, 0));
       tokens.add(new Token(Token.TokenTypes.INDENT, 19, 4));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 19, 11, "Parties"));
       tokens.add(new Token(Token.TokenTypes.DOT, 19, 12));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 19, 13, "k"));
       tokens.add(new Token(Token.TokenTypes.EQUAL, 19, 15));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 19, 23, "Vanilla"));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 20, 0));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 21, 0));
       tokens.add(new Token(Token.TokenTypes.DEDENT, 21, 0));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 21, 7, "Parties"));
       tokens.add(new Token(Token.TokenTypes.DOT, 21, 8));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 21, 9, "c"));
       tokens.add(new Token(Token.TokenTypes.EQUAL, 21, 11));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 21, 16, "Zara"));
       tokens.add(new Token(Token.TokenTypes.YIELDS, 21, 19));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 22, 0));
       tokens.add(new Token(Token.TokenTypes.INDENT, 22, 4));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 22, 11, "Parties"));
       tokens.add(new Token(Token.TokenTypes.DOT, 22, 12));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 22, 13, "g"));
       tokens.add(new Token(Token.TokenTypes.EQUAL, 22, 15));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 22, 19, "Pen"));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 23, 0));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 24, 0));
       tokens.add(new Token(Token.TokenTypes.DEDENT, 24, 0));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 24, 7, "Parties"));
       tokens.add(new Token(Token.TokenTypes.DOT, 24, 8));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 24, 9, "b"));
       tokens.add(new Token(Token.TokenTypes.EQUAL, 24, 11));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 24, 18, "Purple"));
       tokens.add(new Token(Token.TokenTypes.YIELDS, 24, 21));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 25, 0));
       tokens.add(new Token(Token.TokenTypes.INDENT, 25, 4));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 25, 11, "Parties"));
       tokens.add(new Token(Token.TokenTypes.DOT, 25, 12));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 25, 13, "k"));
       tokens.add(new Token(Token.TokenTypes.EQUAL, 25, 15));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 25, 25, "Pineapple"));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 26, 0));
       tokens.add(new Token(Token.TokenTypes.DEDENT, 26, 0));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 26, 0));

       var ast = new NushaFall2025Parser().Nusha(tokens).orElseThrow();
       Assertions.assertEquals("Child", ast.definitions.definition.get(0).definitionName);
       Assertions.assertEquals("John", ast.definitions.definition.get(0).choices.orElseThrow().choice.get(0));
       Assertions.assertEquals("Ram", ast.definitions.definition.get(0).choices.orElseThrow().choice.get(1));
       Assertions.assertEquals("Zara", ast.definitions.definition.get(0).choices.orElseThrow().choice.get(2));
       Assertions.assertEquals("Sana", ast.definitions.definition.get(0).choices.orElseThrow().choice.get(3));
       Assertions.assertEquals("Cake", ast.definitions.definition.get(1).definitionName);
       Assertions.assertEquals("Chocolate", ast.definitions.definition.get(1).choices.orElseThrow().choice.get(0));
       Assertions.assertEquals("Vanilla", ast.definitions.definition.get(1).choices.orElseThrow().choice.get(1));
       Assertions.assertEquals("Strawberry", ast.definitions.definition.get(1).choices.orElseThrow().choice.get(2));
       Assertions.assertEquals("Pineapple", ast.definitions.definition.get(1).choices.orElseThrow().choice.get(3));
       Assertions.assertEquals("Gift", ast.definitions.definition.get(2).definitionName);
       Assertions.assertEquals("Pen", ast.definitions.definition.get(2).choices.orElseThrow().choice.get(0));
       Assertions.assertEquals("Game", ast.definitions.definition.get(2).choices.orElseThrow().choice.get(1));
       Assertions.assertEquals("Candy", ast.definitions.definition.get(2).choices.orElseThrow().choice.get(2));
       Assertions.assertEquals("Frame", ast.definitions.definition.get(2).choices.orElseThrow().choice.get(3));
       Assertions.assertEquals("Balloon", ast.definitions.definition.get(3).definitionName);
       Assertions.assertEquals("Red", ast.definitions.definition.get(3).choices.orElseThrow().choice.get(0));
       Assertions.assertEquals("Orange", ast.definitions.definition.get(3).choices.orElseThrow().choice.get(1));
       Assertions.assertEquals("Purple", ast.definitions.definition.get(3).choices.orElseThrow().choice.get(2));
       Assertions.assertEquals("Black", ast.definitions.definition.get(3).choices.orElseThrow().choice.get(3));
       Assertions.assertEquals("Party", ast.definitions.definition.get(4).definitionName);
       Assertions.assertEquals("c", ast.definitions.definition.get(4).nstruct.orElseThrow().entry.get(0).name);
       Assertions.assertEquals("Child", ast.definitions.definition.get(4).nstruct.orElseThrow().entry.get(0).type);
       Assertions.assertEquals(true, ast.definitions.definition.get(4).nstruct.orElseThrow().entry.get(0).unique);
       Assertions.assertEquals("k", ast.definitions.definition.get(4).nstruct.orElseThrow().entry.get(1).name);
       Assertions.assertEquals("Cake", ast.definitions.definition.get(4).nstruct.orElseThrow().entry.get(1).type);
       Assertions.assertEquals(true, ast.definitions.definition.get(4).nstruct.orElseThrow().entry.get(1).unique);
       Assertions.assertEquals("g", ast.definitions.definition.get(4).nstruct.orElseThrow().entry.get(2).name);
       Assertions.assertEquals("Gift", ast.definitions.definition.get(4).nstruct.orElseThrow().entry.get(2).type);
       Assertions.assertEquals(true, ast.definitions.definition.get(4).nstruct.orElseThrow().entry.get(2).unique);
       Assertions.assertEquals("b", ast.definitions.definition.get(4).nstruct.orElseThrow().entry.get(3).name);
       Assertions.assertEquals("Balloon", ast.definitions.definition.get(4).nstruct.orElseThrow().entry.get(3).type);
       Assertions.assertEquals(true, ast.definitions.definition.get(4).nstruct.orElseThrow().entry.get(3).unique);
       Assertions.assertEquals("Party", ast.variables.variable.get(0).type);
       Assertions.assertEquals("Parties", ast.variables.variable.get(0).variableName);
       Assertions.assertEquals("4", ast.variables.variable.get(0).size.orElseThrow());
       Assertions.assertEquals("Parties", ast.rules.rule.get(0).expression.left.variableName);
       Assertions.assertEquals(true, ast.rules.rule.get(0).expression.left.vrmodifier.orElseThrow().dot);
       Assertions.assertEquals("k", ast.rules.rule.get(0).expression.left.vrmodifier.orElseThrow().part.orElseThrow());
       Assertions.assertEquals("Equal", ast.rules.rule.get(0).expression.op.type.name());
       Assertions.assertEquals("Strawberry", ast.rules.rule.get(0).expression.right.variableName);
       Assertions.assertEquals("Parties", ast.rules.rule.get(0).thens.get(0).left.variableName);
       Assertions.assertEquals(true, ast.rules.rule.get(0).thens.get(0).left.vrmodifier.orElseThrow().dot);
       Assertions.assertEquals("g", ast.rules.rule.get(0).thens.get(0).left.vrmodifier.orElseThrow().part.orElseThrow());
       Assertions.assertEquals("NotEqual", ast.rules.rule.get(0).thens.get(0).op.type.name());
       Assertions.assertEquals("Game", ast.rules.rule.get(0).thens.get(0).right.variableName);
       Assertions.assertEquals("Parties", ast.rules.rule.get(0).thens.get(1).left.variableName);
       Assertions.assertEquals(true, ast.rules.rule.get(0).thens.get(1).left.vrmodifier.orElseThrow().dot);
       Assertions.assertEquals("c", ast.rules.rule.get(0).thens.get(1).left.vrmodifier.orElseThrow().part.orElseThrow());
       Assertions.assertEquals("NotEqual", ast.rules.rule.get(0).thens.get(1).op.type.name());
       Assertions.assertEquals("John", ast.rules.rule.get(0).thens.get(1).right.variableName);
       Assertions.assertEquals("Parties", ast.rules.rule.get(1).expression.left.variableName);
       Assertions.assertEquals(true, ast.rules.rule.get(1).expression.left.vrmodifier.orElseThrow().dot);
       Assertions.assertEquals("c", ast.rules.rule.get(1).expression.left.vrmodifier.orElseThrow().part.orElseThrow());
       Assertions.assertEquals("Equal", ast.rules.rule.get(1).expression.op.type.name());
       Assertions.assertEquals("Ram", ast.rules.rule.get(1).expression.right.variableName);
       Assertions.assertEquals("Parties", ast.rules.rule.get(1).thens.get(0).left.variableName);
       Assertions.assertEquals(true, ast.rules.rule.get(1).thens.get(0).left.vrmodifier.orElseThrow().dot);
       Assertions.assertEquals("b", ast.rules.rule.get(1).thens.get(0).left.vrmodifier.orElseThrow().part.orElseThrow());
       Assertions.assertEquals("Equal", ast.rules.rule.get(1).thens.get(0).op.type.name());
       Assertions.assertEquals("Orange", ast.rules.rule.get(1).thens.get(0).right.variableName);
       Assertions.assertEquals("Parties", ast.rules.rule.get(1).thens.get(1).left.variableName);
       Assertions.assertEquals(true, ast.rules.rule.get(1).thens.get(1).left.vrmodifier.orElseThrow().dot);
       Assertions.assertEquals("k", ast.rules.rule.get(1).thens.get(1).left.vrmodifier.orElseThrow().part.orElseThrow());
       Assertions.assertEquals("NotEqual", ast.rules.rule.get(1).thens.get(1).op.type.name());
       Assertions.assertEquals("Chocolate", ast.rules.rule.get(1).thens.get(1).right.variableName);
       Assertions.assertEquals("Parties", ast.rules.rule.get(2).expression.left.variableName);
       Assertions.assertEquals(true, ast.rules.rule.get(2).expression.left.vrmodifier.orElseThrow().dot);
       Assertions.assertEquals("g", ast.rules.rule.get(2).expression.left.vrmodifier.orElseThrow().part.orElseThrow());
       Assertions.assertEquals("Equal", ast.rules.rule.get(2).expression.op.type.name());
       Assertions.assertEquals("Candy", ast.rules.rule.get(2).expression.right.variableName);
       Assertions.assertEquals("Parties", ast.rules.rule.get(2).thens.get(0).left.variableName);
       Assertions.assertEquals(true, ast.rules.rule.get(2).thens.get(0).left.vrmodifier.orElseThrow().dot);
       Assertions.assertEquals("k", ast.rules.rule.get(2).thens.get(0).left.vrmodifier.orElseThrow().part.orElseThrow());
       Assertions.assertEquals("Equal", ast.rules.rule.get(2).thens.get(0).op.type.name());
       Assertions.assertEquals("Vanilla", ast.rules.rule.get(2).thens.get(0).right.variableName);
       Assertions.assertEquals("Parties", ast.rules.rule.get(3).expression.left.variableName);
       Assertions.assertEquals(true, ast.rules.rule.get(3).expression.left.vrmodifier.orElseThrow().dot);
       Assertions.assertEquals("c", ast.rules.rule.get(3).expression.left.vrmodifier.orElseThrow().part.orElseThrow());
       Assertions.assertEquals("Equal", ast.rules.rule.get(3).expression.op.type.name());
       Assertions.assertEquals("Zara", ast.rules.rule.get(3).expression.right.variableName);
       Assertions.assertEquals("Parties", ast.rules.rule.get(3).thens.get(0).left.variableName);
       Assertions.assertEquals(true, ast.rules.rule.get(3).thens.get(0).left.vrmodifier.orElseThrow().dot);
       Assertions.assertEquals("g", ast.rules.rule.get(3).thens.get(0).left.vrmodifier.orElseThrow().part.orElseThrow());
       Assertions.assertEquals("Equal", ast.rules.rule.get(3).thens.get(0).op.type.name());
       Assertions.assertEquals("Pen", ast.rules.rule.get(3).thens.get(0).right.variableName);
       Assertions.assertEquals("Parties", ast.rules.rule.get(4).expression.left.variableName);
       Assertions.assertEquals(true, ast.rules.rule.get(4).expression.left.vrmodifier.orElseThrow().dot);
       Assertions.assertEquals("b", ast.rules.rule.get(4).expression.left.vrmodifier.orElseThrow().part.orElseThrow());
       Assertions.assertEquals("Equal", ast.rules.rule.get(4).expression.op.type.name());
       Assertions.assertEquals("Purple", ast.rules.rule.get(4).expression.right.variableName);
       Assertions.assertEquals("Parties", ast.rules.rule.get(4).thens.get(0).left.variableName);
       Assertions.assertEquals(true, ast.rules.rule.get(4).thens.get(0).left.vrmodifier.orElseThrow().dot);
       Assertions.assertEquals("k", ast.rules.rule.get(4).thens.get(0).left.vrmodifier.orElseThrow().part.orElseThrow());
       Assertions.assertEquals("Equal", ast.rules.rule.get(4).thens.get(0).op.type.name());
       Assertions.assertEquals("Pineapple", ast.rules.rule.get(4).thens.get(0).right.variableName);
   }

   @Test
   public void TestStationeryParser() throws Exception {
       var code =          "Student = {Aarav, Maya, Kuldeep, Renu}\n"+
               "Book = {Mystery, Science, History, Economy}\n"+
               "Bookmark = {Star, Flower, Emoji, Feather}\n"+
               "Bag = {Red, Orange, Yellow, Black}\n"+
               "\n"+
               "Day = [unique Student s, unique Book b, unique Bookmark m, unique Bag g]\n"+
               "var Days : Day[4]\n"+
               "\n"+
               "\n"+
               "Days.b = History =>\n"+
               "    Days.m != Feather\n"+
               "    Days.s != Aarav\n"+
               "\n"+
               "Days.s = Meera =>\n"+
               "    Days.g = Orange\n"+
               "    Days.b != Mystery\n"+
               "\n"+
               "Days.m = Emoji =>\n"+
               "    Days.b = Science\n"+
               "\n"+
               "Days.s = Kuldeep =>\n"+
               "    Days.b = Economy\n"+
               "\n"+
               "Days.g = Black =>\n"+
               "    Days.b = Mystery\n"+
               "";

       var tokens = new LinkedList<Token>();
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 1, 7, "Student"));
       tokens.add(new Token(Token.TokenTypes.EQUAL, 1, 9));
       tokens.add(new Token(Token.TokenTypes.LEFTCURLY, 1, 11));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 1, 16, "Aarav"));
       tokens.add(new Token(Token.TokenTypes.COMMA, 1, 17));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 1, 22, "Maya"));
       tokens.add(new Token(Token.TokenTypes.COMMA, 1, 23));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 1, 31, "Kuldeep"));
       tokens.add(new Token(Token.TokenTypes.COMMA, 1, 32));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 1, 37, "Renu"));
       tokens.add(new Token(Token.TokenTypes.RIGHTCURLY, 1, 38));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 2, 0));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 2, 4, "Book"));
       tokens.add(new Token(Token.TokenTypes.EQUAL, 2, 6));
       tokens.add(new Token(Token.TokenTypes.LEFTCURLY, 2, 8));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 2, 15, "Mystery"));
       tokens.add(new Token(Token.TokenTypes.COMMA, 2, 16));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 2, 24, "Science"));
       tokens.add(new Token(Token.TokenTypes.COMMA, 2, 25));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 2, 33, "History"));
       tokens.add(new Token(Token.TokenTypes.COMMA, 2, 34));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 2, 42, "Economy"));
       tokens.add(new Token(Token.TokenTypes.RIGHTCURLY, 2, 43));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 3, 0));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 3, 8, "Bookmark"));
       tokens.add(new Token(Token.TokenTypes.EQUAL, 3, 10));
       tokens.add(new Token(Token.TokenTypes.LEFTCURLY, 3, 12));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 3, 16, "Star"));
       tokens.add(new Token(Token.TokenTypes.COMMA, 3, 17));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 3, 24, "Flower"));
       tokens.add(new Token(Token.TokenTypes.COMMA, 3, 25));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 3, 31, "Emoji"));
       tokens.add(new Token(Token.TokenTypes.COMMA, 3, 32));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 3, 40, "Feather"));
       tokens.add(new Token(Token.TokenTypes.RIGHTCURLY, 3, 41));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 4, 0));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 4, 3, "Bag"));
       tokens.add(new Token(Token.TokenTypes.EQUAL, 4, 5));
       tokens.add(new Token(Token.TokenTypes.LEFTCURLY, 4, 7));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 4, 10, "Red"));
       tokens.add(new Token(Token.TokenTypes.COMMA, 4, 11));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 4, 18, "Orange"));
       tokens.add(new Token(Token.TokenTypes.COMMA, 4, 19));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 4, 26, "Yellow"));
       tokens.add(new Token(Token.TokenTypes.COMMA, 4, 27));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 4, 33, "Black"));
       tokens.add(new Token(Token.TokenTypes.RIGHTCURLY, 4, 34));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 5, 0));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 6, 0));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 6, 3, "Day"));
       tokens.add(new Token(Token.TokenTypes.EQUAL, 6, 5));
       tokens.add(new Token(Token.TokenTypes.LEFTBRACE, 6, 7));
       tokens.add(new Token(Token.TokenTypes.UNIQUE, 6, 13));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 6, 21, "Student"));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 6, 23, "s"));
       tokens.add(new Token(Token.TokenTypes.COMMA, 6, 24));
       tokens.add(new Token(Token.TokenTypes.UNIQUE, 6, 31));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 6, 36, "Book"));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 6, 38, "b"));
       tokens.add(new Token(Token.TokenTypes.COMMA, 6, 39));
       tokens.add(new Token(Token.TokenTypes.UNIQUE, 6, 46));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 6, 55, "Bookmark"));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 6, 57, "m"));
       tokens.add(new Token(Token.TokenTypes.COMMA, 6, 58));
       tokens.add(new Token(Token.TokenTypes.UNIQUE, 6, 65));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 6, 69, "Bag"));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 6, 71, "g"));
       tokens.add(new Token(Token.TokenTypes.RIGHTBRACE, 6, 72));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 7, 0));
       tokens.add(new Token(Token.TokenTypes.VAR, 7, 3));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 7, 8, "Days"));
       tokens.add(new Token(Token.TokenTypes.COLON, 7, 10));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 7, 14, "Day"));
       tokens.add(new Token(Token.TokenTypes.LEFTBRACE, 7, 15));
       tokens.add(new Token(Token.TokenTypes.NUMBER, 7, 16, "4"));
       tokens.add(new Token(Token.TokenTypes.RIGHTBRACE, 7, 17));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 8, 0));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 9, 0));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 10, 0));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 10, 4, "Days"));
       tokens.add(new Token(Token.TokenTypes.DOT, 10, 5));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 10, 6, "b"));
       tokens.add(new Token(Token.TokenTypes.EQUAL, 10, 8));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 10, 16, "History"));
       tokens.add(new Token(Token.TokenTypes.YIELDS, 10, 19));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 11, 0));
       tokens.add(new Token(Token.TokenTypes.INDENT, 11, 4));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 11, 8, "Days"));
       tokens.add(new Token(Token.TokenTypes.DOT, 11, 9));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 11, 10, "m"));
       tokens.add(new Token(Token.TokenTypes.NOTEQUAL, 11, 13));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 11, 21, "Feather"));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 12, 0));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 12, 8, "Days"));
       tokens.add(new Token(Token.TokenTypes.DOT, 12, 9));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 12, 10, "s"));
       tokens.add(new Token(Token.TokenTypes.NOTEQUAL, 12, 13));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 12, 19, "Aarav"));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 13, 0));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 14, 0));
       tokens.add(new Token(Token.TokenTypes.DEDENT, 14, 0));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 14, 4, "Days"));
       tokens.add(new Token(Token.TokenTypes.DOT, 14, 5));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 14, 6, "s"));
       tokens.add(new Token(Token.TokenTypes.EQUAL, 14, 8));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 14, 14, "Meera"));
       tokens.add(new Token(Token.TokenTypes.YIELDS, 14, 17));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 15, 0));
       tokens.add(new Token(Token.TokenTypes.INDENT, 15, 4));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 15, 8, "Days"));
       tokens.add(new Token(Token.TokenTypes.DOT, 15, 9));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 15, 10, "g"));
       tokens.add(new Token(Token.TokenTypes.EQUAL, 15, 12));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 15, 19, "Orange"));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 16, 0));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 16, 8, "Days"));
       tokens.add(new Token(Token.TokenTypes.DOT, 16, 9));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 16, 10, "b"));
       tokens.add(new Token(Token.TokenTypes.NOTEQUAL, 16, 13));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 16, 21, "Mystery"));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 17, 0));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 18, 0));
       tokens.add(new Token(Token.TokenTypes.DEDENT, 18, 0));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 18, 4, "Days"));
       tokens.add(new Token(Token.TokenTypes.DOT, 18, 5));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 18, 6, "m"));
       tokens.add(new Token(Token.TokenTypes.EQUAL, 18, 8));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 18, 14, "Emoji"));
       tokens.add(new Token(Token.TokenTypes.YIELDS, 18, 17));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 19, 0));
       tokens.add(new Token(Token.TokenTypes.INDENT, 19, 4));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 19, 8, "Days"));
       tokens.add(new Token(Token.TokenTypes.DOT, 19, 9));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 19, 10, "b"));
       tokens.add(new Token(Token.TokenTypes.EQUAL, 19, 12));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 19, 20, "Science"));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 20, 0));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 21, 0));
       tokens.add(new Token(Token.TokenTypes.DEDENT, 21, 0));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 21, 4, "Days"));
       tokens.add(new Token(Token.TokenTypes.DOT, 21, 5));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 21, 6, "s"));
       tokens.add(new Token(Token.TokenTypes.EQUAL, 21, 8));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 21, 16, "Kuldeep"));
       tokens.add(new Token(Token.TokenTypes.YIELDS, 21, 19));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 22, 0));
       tokens.add(new Token(Token.TokenTypes.INDENT, 22, 4));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 22, 8, "Days"));
       tokens.add(new Token(Token.TokenTypes.DOT, 22, 9));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 22, 10, "b"));
       tokens.add(new Token(Token.TokenTypes.EQUAL, 22, 12));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 22, 20, "Economy"));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 23, 0));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 24, 0));
       tokens.add(new Token(Token.TokenTypes.DEDENT, 24, 0));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 24, 4, "Days"));
       tokens.add(new Token(Token.TokenTypes.DOT, 24, 5));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 24, 6, "g"));
       tokens.add(new Token(Token.TokenTypes.EQUAL, 24, 8));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 24, 14, "Black"));
       tokens.add(new Token(Token.TokenTypes.YIELDS, 24, 17));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 25, 0));
       tokens.add(new Token(Token.TokenTypes.INDENT, 25, 4));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 25, 8, "Days"));
       tokens.add(new Token(Token.TokenTypes.DOT, 25, 9));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 25, 10, "b"));
       tokens.add(new Token(Token.TokenTypes.EQUAL, 25, 12));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 25, 20, "Mystery"));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 26, 0));
       tokens.add(new Token(Token.TokenTypes.DEDENT, 26, 0));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 26, 0));

       var ast = new NushaFall2025Parser().Nusha(tokens).orElseThrow();
       Assertions.assertEquals("Student", ast.definitions.definition.get(0).definitionName);
       Assertions.assertEquals("Aarav", ast.definitions.definition.get(0).choices.orElseThrow().choice.get(0));
       Assertions.assertEquals("Maya", ast.definitions.definition.get(0).choices.orElseThrow().choice.get(1));
       Assertions.assertEquals("Kuldeep", ast.definitions.definition.get(0).choices.orElseThrow().choice.get(2));
       Assertions.assertEquals("Renu", ast.definitions.definition.get(0).choices.orElseThrow().choice.get(3));
       Assertions.assertEquals("Book", ast.definitions.definition.get(1).definitionName);
       Assertions.assertEquals("Mystery", ast.definitions.definition.get(1).choices.orElseThrow().choice.get(0));
       Assertions.assertEquals("Science", ast.definitions.definition.get(1).choices.orElseThrow().choice.get(1));
       Assertions.assertEquals("History", ast.definitions.definition.get(1).choices.orElseThrow().choice.get(2));
       Assertions.assertEquals("Economy", ast.definitions.definition.get(1).choices.orElseThrow().choice.get(3));
       Assertions.assertEquals("Bookmark", ast.definitions.definition.get(2).definitionName);
       Assertions.assertEquals("Star", ast.definitions.definition.get(2).choices.orElseThrow().choice.get(0));
       Assertions.assertEquals("Flower", ast.definitions.definition.get(2).choices.orElseThrow().choice.get(1));
       Assertions.assertEquals("Emoji", ast.definitions.definition.get(2).choices.orElseThrow().choice.get(2));
       Assertions.assertEquals("Feather", ast.definitions.definition.get(2).choices.orElseThrow().choice.get(3));
       Assertions.assertEquals("Bag", ast.definitions.definition.get(3).definitionName);
       Assertions.assertEquals("Red", ast.definitions.definition.get(3).choices.orElseThrow().choice.get(0));
       Assertions.assertEquals("Orange", ast.definitions.definition.get(3).choices.orElseThrow().choice.get(1));
       Assertions.assertEquals("Yellow", ast.definitions.definition.get(3).choices.orElseThrow().choice.get(2));
       Assertions.assertEquals("Black", ast.definitions.definition.get(3).choices.orElseThrow().choice.get(3));
       Assertions.assertEquals("Day", ast.definitions.definition.get(4).definitionName);
       Assertions.assertEquals("s", ast.definitions.definition.get(4).nstruct.orElseThrow().entry.get(0).name);
       Assertions.assertEquals("Student", ast.definitions.definition.get(4).nstruct.orElseThrow().entry.get(0).type);
       Assertions.assertEquals(true, ast.definitions.definition.get(4).nstruct.orElseThrow().entry.get(0).unique);
       Assertions.assertEquals("b", ast.definitions.definition.get(4).nstruct.orElseThrow().entry.get(1).name);
       Assertions.assertEquals("Book", ast.definitions.definition.get(4).nstruct.orElseThrow().entry.get(1).type);
       Assertions.assertEquals(true, ast.definitions.definition.get(4).nstruct.orElseThrow().entry.get(1).unique);
       Assertions.assertEquals("m", ast.definitions.definition.get(4).nstruct.orElseThrow().entry.get(2).name);
       Assertions.assertEquals("Bookmark", ast.definitions.definition.get(4).nstruct.orElseThrow().entry.get(2).type);
       Assertions.assertEquals(true, ast.definitions.definition.get(4).nstruct.orElseThrow().entry.get(2).unique);
       Assertions.assertEquals("g", ast.definitions.definition.get(4).nstruct.orElseThrow().entry.get(3).name);
       Assertions.assertEquals("Bag", ast.definitions.definition.get(4).nstruct.orElseThrow().entry.get(3).type);
       Assertions.assertEquals(true, ast.definitions.definition.get(4).nstruct.orElseThrow().entry.get(3).unique);
       Assertions.assertEquals("Day", ast.variables.variable.get(0).type);
       Assertions.assertEquals("Days", ast.variables.variable.get(0).variableName);
       Assertions.assertEquals("4", ast.variables.variable.get(0).size.orElseThrow());
       Assertions.assertEquals("Days", ast.rules.rule.get(0).expression.left.variableName);
       Assertions.assertEquals(true, ast.rules.rule.get(0).expression.left.vrmodifier.orElseThrow().dot);
       Assertions.assertEquals("b", ast.rules.rule.get(0).expression.left.vrmodifier.orElseThrow().part.orElseThrow());
       Assertions.assertEquals("Equal", ast.rules.rule.get(0).expression.op.type.name());
       Assertions.assertEquals("History", ast.rules.rule.get(0).expression.right.variableName);
       Assertions.assertEquals("Days", ast.rules.rule.get(0).thens.get(0).left.variableName);
       Assertions.assertEquals(true, ast.rules.rule.get(0).thens.get(0).left.vrmodifier.orElseThrow().dot);
       Assertions.assertEquals("m", ast.rules.rule.get(0).thens.get(0).left.vrmodifier.orElseThrow().part.orElseThrow());
       Assertions.assertEquals("NotEqual", ast.rules.rule.get(0).thens.get(0).op.type.name());
       Assertions.assertEquals("Feather", ast.rules.rule.get(0).thens.get(0).right.variableName);
       Assertions.assertEquals("Days", ast.rules.rule.get(0).thens.get(1).left.variableName);
       Assertions.assertEquals(true, ast.rules.rule.get(0).thens.get(1).left.vrmodifier.orElseThrow().dot);
       Assertions.assertEquals("s", ast.rules.rule.get(0).thens.get(1).left.vrmodifier.orElseThrow().part.orElseThrow());
       Assertions.assertEquals("NotEqual", ast.rules.rule.get(0).thens.get(1).op.type.name());
       Assertions.assertEquals("Aarav", ast.rules.rule.get(0).thens.get(1).right.variableName);
       Assertions.assertEquals("Days", ast.rules.rule.get(1).expression.left.variableName);
       Assertions.assertEquals(true, ast.rules.rule.get(1).expression.left.vrmodifier.orElseThrow().dot);
       Assertions.assertEquals("s", ast.rules.rule.get(1).expression.left.vrmodifier.orElseThrow().part.orElseThrow());
       Assertions.assertEquals("Equal", ast.rules.rule.get(1).expression.op.type.name());
       Assertions.assertEquals("Meera", ast.rules.rule.get(1).expression.right.variableName);
       Assertions.assertEquals("Days", ast.rules.rule.get(1).thens.get(0).left.variableName);
       Assertions.assertEquals(true, ast.rules.rule.get(1).thens.get(0).left.vrmodifier.orElseThrow().dot);
       Assertions.assertEquals("g", ast.rules.rule.get(1).thens.get(0).left.vrmodifier.orElseThrow().part.orElseThrow());
       Assertions.assertEquals("Equal", ast.rules.rule.get(1).thens.get(0).op.type.name());
       Assertions.assertEquals("Orange", ast.rules.rule.get(1).thens.get(0).right.variableName);
       Assertions.assertEquals("Days", ast.rules.rule.get(1).thens.get(1).left.variableName);
       Assertions.assertEquals(true, ast.rules.rule.get(1).thens.get(1).left.vrmodifier.orElseThrow().dot);
       Assertions.assertEquals("b", ast.rules.rule.get(1).thens.get(1).left.vrmodifier.orElseThrow().part.orElseThrow());
       Assertions.assertEquals("NotEqual", ast.rules.rule.get(1).thens.get(1).op.type.name());
       Assertions.assertEquals("Mystery", ast.rules.rule.get(1).thens.get(1).right.variableName);
       Assertions.assertEquals("Days", ast.rules.rule.get(2).expression.left.variableName);
       Assertions.assertEquals(true, ast.rules.rule.get(2).expression.left.vrmodifier.orElseThrow().dot);
       Assertions.assertEquals("m", ast.rules.rule.get(2).expression.left.vrmodifier.orElseThrow().part.orElseThrow());
       Assertions.assertEquals("Equal", ast.rules.rule.get(2).expression.op.type.name());
       Assertions.assertEquals("Emoji", ast.rules.rule.get(2).expression.right.variableName);
       Assertions.assertEquals("Days", ast.rules.rule.get(2).thens.get(0).left.variableName);
       Assertions.assertEquals(true, ast.rules.rule.get(2).thens.get(0).left.vrmodifier.orElseThrow().dot);
       Assertions.assertEquals("b", ast.rules.rule.get(2).thens.get(0).left.vrmodifier.orElseThrow().part.orElseThrow());
       Assertions.assertEquals("Equal", ast.rules.rule.get(2).thens.get(0).op.type.name());
       Assertions.assertEquals("Science", ast.rules.rule.get(2).thens.get(0).right.variableName);
       Assertions.assertEquals("Days", ast.rules.rule.get(3).expression.left.variableName);
       Assertions.assertEquals(true, ast.rules.rule.get(3).expression.left.vrmodifier.orElseThrow().dot);
       Assertions.assertEquals("s", ast.rules.rule.get(3).expression.left.vrmodifier.orElseThrow().part.orElseThrow());
       Assertions.assertEquals("Equal", ast.rules.rule.get(3).expression.op.type.name());
       Assertions.assertEquals("Kuldeep", ast.rules.rule.get(3).expression.right.variableName);
       Assertions.assertEquals("Days", ast.rules.rule.get(3).thens.get(0).left.variableName);
       Assertions.assertEquals(true, ast.rules.rule.get(3).thens.get(0).left.vrmodifier.orElseThrow().dot);
       Assertions.assertEquals("b", ast.rules.rule.get(3).thens.get(0).left.vrmodifier.orElseThrow().part.orElseThrow());
       Assertions.assertEquals("Equal", ast.rules.rule.get(3).thens.get(0).op.type.name());
       Assertions.assertEquals("Economy", ast.rules.rule.get(3).thens.get(0).right.variableName);
       Assertions.assertEquals("Days", ast.rules.rule.get(4).expression.left.variableName);
       Assertions.assertEquals(true, ast.rules.rule.get(4).expression.left.vrmodifier.orElseThrow().dot);
       Assertions.assertEquals("g", ast.rules.rule.get(4).expression.left.vrmodifier.orElseThrow().part.orElseThrow());
       Assertions.assertEquals("Equal", ast.rules.rule.get(4).expression.op.type.name());
       Assertions.assertEquals("Black", ast.rules.rule.get(4).expression.right.variableName);
       Assertions.assertEquals("Days", ast.rules.rule.get(4).thens.get(0).left.variableName);
       Assertions.assertEquals(true, ast.rules.rule.get(4).thens.get(0).left.vrmodifier.orElseThrow().dot);
       Assertions.assertEquals("b", ast.rules.rule.get(4).thens.get(0).left.vrmodifier.orElseThrow().part.orElseThrow());
       Assertions.assertEquals("Equal", ast.rules.rule.get(4).thens.get(0).op.type.name());
       Assertions.assertEquals("Mystery", ast.rules.rule.get(4).thens.get(0).right.variableName);
   }

   @Test
   public void TestDishParser() throws Exception {
       var code =          "Chef = {Ava, Diego, Mei, Sam}\n"+
               "Dish= {Pasta, Curry, Tacos, Sushi}\n"+
               "City= {Rome, Mumbai, MexicoCity, Tokyo}\n"+
               "\n"+
               "Story = [unique Chef c, unique Dish d, unique City p]\n"+
               "\n"+
               "var Stories : Story[4]\n"+
               "\n"+
               "Stories[0].c = Ava\n"+
               "Stories[1].c = Diego\n"+
               "Stories[2].c = Ava\n"+
               "Stories[3].c=Sam\n"+
               "\n"+
               "Stories.d = Sushi =>\n"+
               "	Stories.p != Rome\n"+
               "	Stories.p != Mumbai\n"+
               "	Stories.p != MexicoCity\n"+
               "\n"+
               "Stories.c = MexicoCity =>\n"+
               "	Stories.d != Pasta\n"+
               "	Stories.d != Curry\n"+
               "	Stories.d != Sushi\n"+
               "\n"+
               "Stories.c = Diego =>\n"+
               "	Stories.d = Curry\n"+
               "	Stories.p != Tokyo\n"+
               "\n"+
               "Stories.c = Ava =>\n"+
               "	Stories.d != Pasta\n"+
               "	Stories.p != Rome\n"+
               "\n"+
               "Stories.c = Mei =>\n"+
               "	Stories.p != Mumbai\n"+
               "	Stories.d != Sushi\n"+
               "\n"+
               "Stories.c = Sam =>\n"+
               "	Stories.p != Rome\n"+
               "";

       var tokens = new LinkedList<Token>();
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 1, 4, "Chef"));
       tokens.add(new Token(Token.TokenTypes.EQUAL, 1, 6));
       tokens.add(new Token(Token.TokenTypes.LEFTCURLY, 1, 8));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 1, 11, "Ava"));
       tokens.add(new Token(Token.TokenTypes.COMMA, 1, 12));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 1, 18, "Diego"));
       tokens.add(new Token(Token.TokenTypes.COMMA, 1, 19));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 1, 23, "Mei"));
       tokens.add(new Token(Token.TokenTypes.COMMA, 1, 24));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 1, 28, "Sam"));
       tokens.add(new Token(Token.TokenTypes.RIGHTCURLY, 1, 29));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 2, 0));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 2, 4, "Dish"));
       tokens.add(new Token(Token.TokenTypes.EQUAL, 2, 5));
       tokens.add(new Token(Token.TokenTypes.LEFTCURLY, 2, 7));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 2, 12, "Pasta"));
       tokens.add(new Token(Token.TokenTypes.COMMA, 2, 13));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 2, 19, "Curry"));
       tokens.add(new Token(Token.TokenTypes.COMMA, 2, 20));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 2, 26, "Tacos"));
       tokens.add(new Token(Token.TokenTypes.COMMA, 2, 27));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 2, 33, "Sushi"));
       tokens.add(new Token(Token.TokenTypes.RIGHTCURLY, 2, 34));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 3, 0));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 3, 4, "City"));
       tokens.add(new Token(Token.TokenTypes.EQUAL, 3, 5));
       tokens.add(new Token(Token.TokenTypes.LEFTCURLY, 3, 7));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 3, 11, "Rome"));
       tokens.add(new Token(Token.TokenTypes.COMMA, 3, 12));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 3, 19, "Mumbai"));
       tokens.add(new Token(Token.TokenTypes.COMMA, 3, 20));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 3, 31, "MexicoCity"));
       tokens.add(new Token(Token.TokenTypes.COMMA, 3, 32));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 3, 38, "Tokyo"));
       tokens.add(new Token(Token.TokenTypes.RIGHTCURLY, 3, 39));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 4, 0));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 5, 0));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 5, 5, "Story"));
       tokens.add(new Token(Token.TokenTypes.EQUAL, 5, 7));
       tokens.add(new Token(Token.TokenTypes.LEFTBRACE, 5, 9));
       tokens.add(new Token(Token.TokenTypes.UNIQUE, 5, 15));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 5, 20, "Chef"));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 5, 22, "c"));
       tokens.add(new Token(Token.TokenTypes.COMMA, 5, 23));
       tokens.add(new Token(Token.TokenTypes.UNIQUE, 5, 30));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 5, 35, "Dish"));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 5, 37, "d"));
       tokens.add(new Token(Token.TokenTypes.COMMA, 5, 38));
       tokens.add(new Token(Token.TokenTypes.UNIQUE, 5, 45));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 5, 50, "City"));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 5, 52, "p"));
       tokens.add(new Token(Token.TokenTypes.RIGHTBRACE, 5, 53));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 6, 0));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 7, 0));
       tokens.add(new Token(Token.TokenTypes.VAR, 7, 3));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 7, 11, "Stories"));
       tokens.add(new Token(Token.TokenTypes.COLON, 7, 13));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 7, 19, "Story"));
       tokens.add(new Token(Token.TokenTypes.LEFTBRACE, 7, 20));
       tokens.add(new Token(Token.TokenTypes.NUMBER, 7, 21, "4"));
       tokens.add(new Token(Token.TokenTypes.RIGHTBRACE, 7, 22));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 8, 0));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 9, 0));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 9, 7, "Stories"));
       tokens.add(new Token(Token.TokenTypes.LEFTBRACE, 9, 8));
       tokens.add(new Token(Token.TokenTypes.NUMBER, 9, 9, "0"));
       tokens.add(new Token(Token.TokenTypes.RIGHTBRACE, 9, 10));
       tokens.add(new Token(Token.TokenTypes.DOT, 9, 11));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 9, 12, "c"));
       tokens.add(new Token(Token.TokenTypes.EQUAL, 9, 14));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 9, 18, "Ava"));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 10, 0));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 10, 7, "Stories"));
       tokens.add(new Token(Token.TokenTypes.LEFTBRACE, 10, 8));
       tokens.add(new Token(Token.TokenTypes.NUMBER, 10, 9, "1"));
       tokens.add(new Token(Token.TokenTypes.RIGHTBRACE, 10, 10));
       tokens.add(new Token(Token.TokenTypes.DOT, 10, 11));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 10, 12, "c"));
       tokens.add(new Token(Token.TokenTypes.EQUAL, 10, 14));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 10, 20, "Diego"));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 11, 0));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 11, 7, "Stories"));
       tokens.add(new Token(Token.TokenTypes.LEFTBRACE, 11, 8));
       tokens.add(new Token(Token.TokenTypes.NUMBER, 11, 9, "2"));
       tokens.add(new Token(Token.TokenTypes.RIGHTBRACE, 11, 10));
       tokens.add(new Token(Token.TokenTypes.DOT, 11, 11));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 11, 12, "c"));
       tokens.add(new Token(Token.TokenTypes.EQUAL, 11, 14));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 11, 18, "Ava"));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 12, 0));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 12, 7, "Stories"));
       tokens.add(new Token(Token.TokenTypes.LEFTBRACE, 12, 8));
       tokens.add(new Token(Token.TokenTypes.NUMBER, 12, 9, "3"));
       tokens.add(new Token(Token.TokenTypes.RIGHTBRACE, 12, 10));
       tokens.add(new Token(Token.TokenTypes.DOT, 12, 11));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 12, 12, "c"));
       tokens.add(new Token(Token.TokenTypes.EQUAL, 12, 13));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 12, 16, "Sam"));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 13, 0));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 14, 0));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 14, 7, "Stories"));
       tokens.add(new Token(Token.TokenTypes.DOT, 14, 8));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 14, 9, "d"));
       tokens.add(new Token(Token.TokenTypes.EQUAL, 14, 11));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 14, 17, "Sushi"));
       tokens.add(new Token(Token.TokenTypes.YIELDS, 14, 20));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 15, 0));
       tokens.add(new Token(Token.TokenTypes.INDENT, 15, 4));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 15, 11, "Stories"));
       tokens.add(new Token(Token.TokenTypes.DOT, 15, 12));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 15, 13, "p"));
       tokens.add(new Token(Token.TokenTypes.NOTEQUAL, 15, 16));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 15, 21, "Rome"));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 16, 0));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 16, 11, "Stories"));
       tokens.add(new Token(Token.TokenTypes.DOT, 16, 12));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 16, 13, "p"));
       tokens.add(new Token(Token.TokenTypes.NOTEQUAL, 16, 16));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 16, 23, "Mumbai"));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 17, 0));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 17, 11, "Stories"));
       tokens.add(new Token(Token.TokenTypes.DOT, 17, 12));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 17, 13, "p"));
       tokens.add(new Token(Token.TokenTypes.NOTEQUAL, 17, 16));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 17, 27, "MexicoCity"));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 18, 0));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 19, 0));
       tokens.add(new Token(Token.TokenTypes.DEDENT, 19, 0));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 19, 7, "Stories"));
       tokens.add(new Token(Token.TokenTypes.DOT, 19, 8));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 19, 9, "c"));
       tokens.add(new Token(Token.TokenTypes.EQUAL, 19, 11));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 19, 22, "MexicoCity"));
       tokens.add(new Token(Token.TokenTypes.YIELDS, 19, 25));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 20, 0));
       tokens.add(new Token(Token.TokenTypes.INDENT, 20, 4));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 20, 11, "Stories"));
       tokens.add(new Token(Token.TokenTypes.DOT, 20, 12));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 20, 13, "d"));
       tokens.add(new Token(Token.TokenTypes.NOTEQUAL, 20, 16));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 20, 22, "Pasta"));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 21, 0));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 21, 11, "Stories"));
       tokens.add(new Token(Token.TokenTypes.DOT, 21, 12));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 21, 13, "d"));
       tokens.add(new Token(Token.TokenTypes.NOTEQUAL, 21, 16));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 21, 22, "Curry"));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 22, 0));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 22, 11, "Stories"));
       tokens.add(new Token(Token.TokenTypes.DOT, 22, 12));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 22, 13, "d"));
       tokens.add(new Token(Token.TokenTypes.NOTEQUAL, 22, 16));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 22, 22, "Sushi"));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 23, 0));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 24, 0));
       tokens.add(new Token(Token.TokenTypes.DEDENT, 24, 0));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 24, 7, "Stories"));
       tokens.add(new Token(Token.TokenTypes.DOT, 24, 8));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 24, 9, "c"));
       tokens.add(new Token(Token.TokenTypes.EQUAL, 24, 11));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 24, 17, "Diego"));
       tokens.add(new Token(Token.TokenTypes.YIELDS, 24, 20));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 25, 0));
       tokens.add(new Token(Token.TokenTypes.INDENT, 25, 4));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 25, 11, "Stories"));
       tokens.add(new Token(Token.TokenTypes.DOT, 25, 12));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 25, 13, "d"));
       tokens.add(new Token(Token.TokenTypes.EQUAL, 25, 15));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 25, 21, "Curry"));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 26, 0));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 26, 11, "Stories"));
       tokens.add(new Token(Token.TokenTypes.DOT, 26, 12));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 26, 13, "p"));
       tokens.add(new Token(Token.TokenTypes.NOTEQUAL, 26, 16));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 26, 22, "Tokyo"));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 27, 0));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 28, 0));
       tokens.add(new Token(Token.TokenTypes.DEDENT, 28, 0));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 28, 7, "Stories"));
       tokens.add(new Token(Token.TokenTypes.DOT, 28, 8));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 28, 9, "c"));
       tokens.add(new Token(Token.TokenTypes.EQUAL, 28, 11));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 28, 15, "Ava"));
       tokens.add(new Token(Token.TokenTypes.YIELDS, 28, 18));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 29, 0));
       tokens.add(new Token(Token.TokenTypes.INDENT, 29, 4));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 29, 11, "Stories"));
       tokens.add(new Token(Token.TokenTypes.DOT, 29, 12));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 29, 13, "d"));
       tokens.add(new Token(Token.TokenTypes.NOTEQUAL, 29, 16));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 29, 22, "Pasta"));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 30, 0));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 30, 11, "Stories"));
       tokens.add(new Token(Token.TokenTypes.DOT, 30, 12));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 30, 13, "p"));
       tokens.add(new Token(Token.TokenTypes.NOTEQUAL, 30, 16));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 30, 21, "Rome"));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 31, 0));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 32, 0));
       tokens.add(new Token(Token.TokenTypes.DEDENT, 32, 0));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 32, 7, "Stories"));
       tokens.add(new Token(Token.TokenTypes.DOT, 32, 8));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 32, 9, "c"));
       tokens.add(new Token(Token.TokenTypes.EQUAL, 32, 11));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 32, 15, "Mei"));
       tokens.add(new Token(Token.TokenTypes.YIELDS, 32, 18));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 33, 0));
       tokens.add(new Token(Token.TokenTypes.INDENT, 33, 4));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 33, 11, "Stories"));
       tokens.add(new Token(Token.TokenTypes.DOT, 33, 12));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 33, 13, "p"));
       tokens.add(new Token(Token.TokenTypes.NOTEQUAL, 33, 16));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 33, 23, "Mumbai"));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 34, 0));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 34, 11, "Stories"));
       tokens.add(new Token(Token.TokenTypes.DOT, 34, 12));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 34, 13, "d"));
       tokens.add(new Token(Token.TokenTypes.NOTEQUAL, 34, 16));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 34, 22, "Sushi"));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 35, 0));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 36, 0));
       tokens.add(new Token(Token.TokenTypes.DEDENT, 36, 0));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 36, 7, "Stories"));
       tokens.add(new Token(Token.TokenTypes.DOT, 36, 8));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 36, 9, "c"));
       tokens.add(new Token(Token.TokenTypes.EQUAL, 36, 11));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 36, 15, "Sam"));
       tokens.add(new Token(Token.TokenTypes.YIELDS, 36, 18));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 37, 0));
       tokens.add(new Token(Token.TokenTypes.INDENT, 37, 4));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 37, 11, "Stories"));
       tokens.add(new Token(Token.TokenTypes.DOT, 37, 12));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 37, 13, "p"));
       tokens.add(new Token(Token.TokenTypes.NOTEQUAL, 37, 16));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 37, 21, "Rome"));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 38, 0));
       tokens.add(new Token(Token.TokenTypes.DEDENT, 38, 0));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 38, 0));

       var ast = new NushaFall2025Parser().Nusha(tokens).orElseThrow();
       Assertions.assertEquals("Chef", ast.definitions.definition.get(0).definitionName);
       Assertions.assertEquals("Ava", ast.definitions.definition.get(0).choices.orElseThrow().choice.get(0));
       Assertions.assertEquals("Diego", ast.definitions.definition.get(0).choices.orElseThrow().choice.get(1));
       Assertions.assertEquals("Mei", ast.definitions.definition.get(0).choices.orElseThrow().choice.get(2));
       Assertions.assertEquals("Sam", ast.definitions.definition.get(0).choices.orElseThrow().choice.get(3));
       Assertions.assertEquals("Dish", ast.definitions.definition.get(1).definitionName);
       Assertions.assertEquals("Pasta", ast.definitions.definition.get(1).choices.orElseThrow().choice.get(0));
       Assertions.assertEquals("Curry", ast.definitions.definition.get(1).choices.orElseThrow().choice.get(1));
       Assertions.assertEquals("Tacos", ast.definitions.definition.get(1).choices.orElseThrow().choice.get(2));
       Assertions.assertEquals("Sushi", ast.definitions.definition.get(1).choices.orElseThrow().choice.get(3));
       Assertions.assertEquals("City", ast.definitions.definition.get(2).definitionName);
       Assertions.assertEquals("Rome", ast.definitions.definition.get(2).choices.orElseThrow().choice.get(0));
       Assertions.assertEquals("Mumbai", ast.definitions.definition.get(2).choices.orElseThrow().choice.get(1));
       Assertions.assertEquals("MexicoCity", ast.definitions.definition.get(2).choices.orElseThrow().choice.get(2));
       Assertions.assertEquals("Tokyo", ast.definitions.definition.get(2).choices.orElseThrow().choice.get(3));
       Assertions.assertEquals("Story", ast.definitions.definition.get(3).definitionName);
       Assertions.assertEquals("c", ast.definitions.definition.get(3).nstruct.orElseThrow().entry.get(0).name);
       Assertions.assertEquals("Chef", ast.definitions.definition.get(3).nstruct.orElseThrow().entry.get(0).type);
       Assertions.assertEquals(true, ast.definitions.definition.get(3).nstruct.orElseThrow().entry.get(0).unique);
       Assertions.assertEquals("d", ast.definitions.definition.get(3).nstruct.orElseThrow().entry.get(1).name);
       Assertions.assertEquals("Dish", ast.definitions.definition.get(3).nstruct.orElseThrow().entry.get(1).type);
       Assertions.assertEquals(true, ast.definitions.definition.get(3).nstruct.orElseThrow().entry.get(1).unique);
       Assertions.assertEquals("p", ast.definitions.definition.get(3).nstruct.orElseThrow().entry.get(2).name);
       Assertions.assertEquals("City", ast.definitions.definition.get(3).nstruct.orElseThrow().entry.get(2).type);
       Assertions.assertEquals(true, ast.definitions.definition.get(3).nstruct.orElseThrow().entry.get(2).unique);
       Assertions.assertEquals("Story", ast.variables.variable.get(0).type);
       Assertions.assertEquals("Stories", ast.variables.variable.get(0).variableName);
       Assertions.assertEquals("4", ast.variables.variable.get(0).size.orElseThrow());
       Assertions.assertEquals("Stories", ast.rules.rule.get(0).expression.left.variableName);
       Assertions.assertEquals("0", ast.rules.rule.get(0).expression.left.vrmodifier.orElseThrow().size);
       Assertions.assertEquals(true, ast.rules.rule.get(0).expression.left.vrmodifier.orElseThrow().vrmodifier.orElseThrow().dot);
       Assertions.assertEquals("c", ast.rules.rule.get(0).expression.left.vrmodifier.orElseThrow().vrmodifier.orElseThrow().part.orElseThrow());
       Assertions.assertEquals("Equal", ast.rules.rule.get(0).expression.op.type.name());
       Assertions.assertEquals("Ava", ast.rules.rule.get(0).expression.right.variableName);
       Assertions.assertEquals("Stories", ast.rules.rule.get(1).expression.left.variableName);
       Assertions.assertEquals("1", ast.rules.rule.get(1).expression.left.vrmodifier.orElseThrow().size);
       Assertions.assertEquals(true, ast.rules.rule.get(1).expression.left.vrmodifier.orElseThrow().vrmodifier.orElseThrow().dot);
       Assertions.assertEquals("c", ast.rules.rule.get(1).expression.left.vrmodifier.orElseThrow().vrmodifier.orElseThrow().part.orElseThrow());
       Assertions.assertEquals("Equal", ast.rules.rule.get(1).expression.op.type.name());
       Assertions.assertEquals("Diego", ast.rules.rule.get(1).expression.right.variableName);
       Assertions.assertEquals("Stories", ast.rules.rule.get(2).expression.left.variableName);
       Assertions.assertEquals("2", ast.rules.rule.get(2).expression.left.vrmodifier.orElseThrow().size);
       Assertions.assertEquals(true, ast.rules.rule.get(2).expression.left.vrmodifier.orElseThrow().vrmodifier.orElseThrow().dot);
       Assertions.assertEquals("c", ast.rules.rule.get(2).expression.left.vrmodifier.orElseThrow().vrmodifier.orElseThrow().part.orElseThrow());
       Assertions.assertEquals("Equal", ast.rules.rule.get(2).expression.op.type.name());
       Assertions.assertEquals("Ava", ast.rules.rule.get(2).expression.right.variableName);
       Assertions.assertEquals("Stories", ast.rules.rule.get(3).expression.left.variableName);
       Assertions.assertEquals("3", ast.rules.rule.get(3).expression.left.vrmodifier.orElseThrow().size);
       Assertions.assertEquals(true, ast.rules.rule.get(3).expression.left.vrmodifier.orElseThrow().vrmodifier.orElseThrow().dot);
       Assertions.assertEquals("c", ast.rules.rule.get(3).expression.left.vrmodifier.orElseThrow().vrmodifier.orElseThrow().part.orElseThrow());
       Assertions.assertEquals("Equal", ast.rules.rule.get(3).expression.op.type.name());
       Assertions.assertEquals("Sam", ast.rules.rule.get(3).expression.right.variableName);
       Assertions.assertEquals("Stories", ast.rules.rule.get(4).expression.left.variableName);
       Assertions.assertEquals(true, ast.rules.rule.get(4).expression.left.vrmodifier.orElseThrow().dot);
       Assertions.assertEquals("d", ast.rules.rule.get(4).expression.left.vrmodifier.orElseThrow().part.orElseThrow());
       Assertions.assertEquals("Equal", ast.rules.rule.get(4).expression.op.type.name());
       Assertions.assertEquals("Sushi", ast.rules.rule.get(4).expression.right.variableName);
       Assertions.assertEquals("Stories", ast.rules.rule.get(4).thens.get(0).left.variableName);
       Assertions.assertEquals(true, ast.rules.rule.get(4).thens.get(0).left.vrmodifier.orElseThrow().dot);
       Assertions.assertEquals("p", ast.rules.rule.get(4).thens.get(0).left.vrmodifier.orElseThrow().part.orElseThrow());
       Assertions.assertEquals("NotEqual", ast.rules.rule.get(4).thens.get(0).op.type.name());
       Assertions.assertEquals("Rome", ast.rules.rule.get(4).thens.get(0).right.variableName);
       Assertions.assertEquals("Stories", ast.rules.rule.get(4).thens.get(1).left.variableName);
       Assertions.assertEquals(true, ast.rules.rule.get(4).thens.get(1).left.vrmodifier.orElseThrow().dot);
       Assertions.assertEquals("p", ast.rules.rule.get(4).thens.get(1).left.vrmodifier.orElseThrow().part.orElseThrow());
       Assertions.assertEquals("NotEqual", ast.rules.rule.get(4).thens.get(1).op.type.name());
       Assertions.assertEquals("Mumbai", ast.rules.rule.get(4).thens.get(1).right.variableName);
       Assertions.assertEquals("Stories", ast.rules.rule.get(4).thens.get(2).left.variableName);
       Assertions.assertEquals(true, ast.rules.rule.get(4).thens.get(2).left.vrmodifier.orElseThrow().dot);
       Assertions.assertEquals("p", ast.rules.rule.get(4).thens.get(2).left.vrmodifier.orElseThrow().part.orElseThrow());
       Assertions.assertEquals("NotEqual", ast.rules.rule.get(4).thens.get(2).op.type.name());
       Assertions.assertEquals("MexicoCity", ast.rules.rule.get(4).thens.get(2).right.variableName);
       Assertions.assertEquals("Stories", ast.rules.rule.get(5).expression.left.variableName);
       Assertions.assertEquals(true, ast.rules.rule.get(5).expression.left.vrmodifier.orElseThrow().dot);
       Assertions.assertEquals("c", ast.rules.rule.get(5).expression.left.vrmodifier.orElseThrow().part.orElseThrow());
       Assertions.assertEquals("Equal", ast.rules.rule.get(5).expression.op.type.name());
       Assertions.assertEquals("MexicoCity", ast.rules.rule.get(5).expression.right.variableName);
       Assertions.assertEquals("Stories", ast.rules.rule.get(5).thens.get(0).left.variableName);
       Assertions.assertEquals(true, ast.rules.rule.get(5).thens.get(0).left.vrmodifier.orElseThrow().dot);
       Assertions.assertEquals("d", ast.rules.rule.get(5).thens.get(0).left.vrmodifier.orElseThrow().part.orElseThrow());
       Assertions.assertEquals("NotEqual", ast.rules.rule.get(5).thens.get(0).op.type.name());
       Assertions.assertEquals("Pasta", ast.rules.rule.get(5).thens.get(0).right.variableName);
       Assertions.assertEquals("Stories", ast.rules.rule.get(5).thens.get(1).left.variableName);
       Assertions.assertEquals(true, ast.rules.rule.get(5).thens.get(1).left.vrmodifier.orElseThrow().dot);
       Assertions.assertEquals("d", ast.rules.rule.get(5).thens.get(1).left.vrmodifier.orElseThrow().part.orElseThrow());
       Assertions.assertEquals("NotEqual", ast.rules.rule.get(5).thens.get(1).op.type.name());
       Assertions.assertEquals("Curry", ast.rules.rule.get(5).thens.get(1).right.variableName);
       Assertions.assertEquals("Stories", ast.rules.rule.get(5).thens.get(2).left.variableName);
       Assertions.assertEquals(true, ast.rules.rule.get(5).thens.get(2).left.vrmodifier.orElseThrow().dot);
       Assertions.assertEquals("d", ast.rules.rule.get(5).thens.get(2).left.vrmodifier.orElseThrow().part.orElseThrow());
       Assertions.assertEquals("NotEqual", ast.rules.rule.get(5).thens.get(2).op.type.name());
       Assertions.assertEquals("Sushi", ast.rules.rule.get(5).thens.get(2).right.variableName);
       Assertions.assertEquals("Stories", ast.rules.rule.get(6).expression.left.variableName);
       Assertions.assertEquals(true, ast.rules.rule.get(6).expression.left.vrmodifier.orElseThrow().dot);
       Assertions.assertEquals("c", ast.rules.rule.get(6).expression.left.vrmodifier.orElseThrow().part.orElseThrow());
       Assertions.assertEquals("Equal", ast.rules.rule.get(6).expression.op.type.name());
       Assertions.assertEquals("Diego", ast.rules.rule.get(6).expression.right.variableName);
       Assertions.assertEquals("Stories", ast.rules.rule.get(6).thens.get(0).left.variableName);
       Assertions.assertEquals(true, ast.rules.rule.get(6).thens.get(0).left.vrmodifier.orElseThrow().dot);
       Assertions.assertEquals("d", ast.rules.rule.get(6).thens.get(0).left.vrmodifier.orElseThrow().part.orElseThrow());
       Assertions.assertEquals("Equal", ast.rules.rule.get(6).thens.get(0).op.type.name());
       Assertions.assertEquals("Curry", ast.rules.rule.get(6).thens.get(0).right.variableName);
       Assertions.assertEquals("Stories", ast.rules.rule.get(6).thens.get(1).left.variableName);
       Assertions.assertEquals(true, ast.rules.rule.get(6).thens.get(1).left.vrmodifier.orElseThrow().dot);
       Assertions.assertEquals("p", ast.rules.rule.get(6).thens.get(1).left.vrmodifier.orElseThrow().part.orElseThrow());
       Assertions.assertEquals("NotEqual", ast.rules.rule.get(6).thens.get(1).op.type.name());
       Assertions.assertEquals("Tokyo", ast.rules.rule.get(6).thens.get(1).right.variableName);
       Assertions.assertEquals("Stories", ast.rules.rule.get(7).expression.left.variableName);
       Assertions.assertEquals(true, ast.rules.rule.get(7).expression.left.vrmodifier.orElseThrow().dot);
       Assertions.assertEquals("c", ast.rules.rule.get(7).expression.left.vrmodifier.orElseThrow().part.orElseThrow());
       Assertions.assertEquals("Equal", ast.rules.rule.get(7).expression.op.type.name());
       Assertions.assertEquals("Ava", ast.rules.rule.get(7).expression.right.variableName);
       Assertions.assertEquals("Stories", ast.rules.rule.get(7).thens.get(0).left.variableName);
       Assertions.assertEquals(true, ast.rules.rule.get(7).thens.get(0).left.vrmodifier.orElseThrow().dot);
       Assertions.assertEquals("d", ast.rules.rule.get(7).thens.get(0).left.vrmodifier.orElseThrow().part.orElseThrow());
       Assertions.assertEquals("NotEqual", ast.rules.rule.get(7).thens.get(0).op.type.name());
       Assertions.assertEquals("Pasta", ast.rules.rule.get(7).thens.get(0).right.variableName);
       Assertions.assertEquals("Stories", ast.rules.rule.get(7).thens.get(1).left.variableName);
       Assertions.assertEquals(true, ast.rules.rule.get(7).thens.get(1).left.vrmodifier.orElseThrow().dot);
       Assertions.assertEquals("p", ast.rules.rule.get(7).thens.get(1).left.vrmodifier.orElseThrow().part.orElseThrow());
       Assertions.assertEquals("NotEqual", ast.rules.rule.get(7).thens.get(1).op.type.name());
       Assertions.assertEquals("Rome", ast.rules.rule.get(7).thens.get(1).right.variableName);
       Assertions.assertEquals("Stories", ast.rules.rule.get(8).expression.left.variableName);
       Assertions.assertEquals(true, ast.rules.rule.get(8).expression.left.vrmodifier.orElseThrow().dot);
       Assertions.assertEquals("c", ast.rules.rule.get(8).expression.left.vrmodifier.orElseThrow().part.orElseThrow());
       Assertions.assertEquals("Equal", ast.rules.rule.get(8).expression.op.type.name());
       Assertions.assertEquals("Mei", ast.rules.rule.get(8).expression.right.variableName);
       Assertions.assertEquals("Stories", ast.rules.rule.get(8).thens.get(0).left.variableName);
       Assertions.assertEquals(true, ast.rules.rule.get(8).thens.get(0).left.vrmodifier.orElseThrow().dot);
       Assertions.assertEquals("p", ast.rules.rule.get(8).thens.get(0).left.vrmodifier.orElseThrow().part.orElseThrow());
       Assertions.assertEquals("NotEqual", ast.rules.rule.get(8).thens.get(0).op.type.name());
       Assertions.assertEquals("Mumbai", ast.rules.rule.get(8).thens.get(0).right.variableName);
       Assertions.assertEquals("Stories", ast.rules.rule.get(8).thens.get(1).left.variableName);
       Assertions.assertEquals(true, ast.rules.rule.get(8).thens.get(1).left.vrmodifier.orElseThrow().dot);
       Assertions.assertEquals("d", ast.rules.rule.get(8).thens.get(1).left.vrmodifier.orElseThrow().part.orElseThrow());
       Assertions.assertEquals("NotEqual", ast.rules.rule.get(8).thens.get(1).op.type.name());
       Assertions.assertEquals("Sushi", ast.rules.rule.get(8).thens.get(1).right.variableName);
       Assertions.assertEquals("Stories", ast.rules.rule.get(9).expression.left.variableName);
       Assertions.assertEquals(true, ast.rules.rule.get(9).expression.left.vrmodifier.orElseThrow().dot);
       Assertions.assertEquals("c", ast.rules.rule.get(9).expression.left.vrmodifier.orElseThrow().part.orElseThrow());
       Assertions.assertEquals("Equal", ast.rules.rule.get(9).expression.op.type.name());
       Assertions.assertEquals("Sam", ast.rules.rule.get(9).expression.right.variableName);
       Assertions.assertEquals("Stories", ast.rules.rule.get(9).thens.get(0).left.variableName);
       Assertions.assertEquals(true, ast.rules.rule.get(9).thens.get(0).left.vrmodifier.orElseThrow().dot);
       Assertions.assertEquals("p", ast.rules.rule.get(9).thens.get(0).left.vrmodifier.orElseThrow().part.orElseThrow());
       Assertions.assertEquals("NotEqual", ast.rules.rule.get(9).thens.get(0).op.type.name());
       Assertions.assertEquals("Rome", ast.rules.rule.get(9).thens.get(0).right.variableName);
   }

   @Test
   public void TestCafeParser() throws Exception {
       var code =          "Name = {Brian, Matilda, Ursula}\n"+
               "Drink = {Coffee, Lemonade, Tea}\n"+
               "Pastry = {Croissants, Eclairs, Macarons}\n"+
               "\n"+
               "Puzzle = [unique Name n, unique Drink d, unique Pastry p]\n"+
               "\n"+
               "var Puzzles : Puzzle[3]\n"+
               "\n"+
               "Puzzles[0].c = Brian\n"+
               "\n"+
               "Puzzles[1].c = Matilda\n"+
               "\n"+
               "Puzzles[2].c = Ursula\n"+
               "\n"+
               "Puzzles.p = Eclairs =>\n"+
               "    Puzzle.n = Brian\n"+
               "\n"+
               "Puzzle.n = Ursula =>\n"+
               "    Puzzle.p != Croissants\n"+
               "    Puzzle.d = Lemonade\n"+
               "\n"+
               "Puzzle.p = Croissants =>\n"+
               "    Puzzle.d = Tea\n"+
               "";

       var tokens = new LinkedList<Token>();
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 1, 4, "Name"));
       tokens.add(new Token(Token.TokenTypes.EQUAL, 1, 6));
       tokens.add(new Token(Token.TokenTypes.LEFTCURLY, 1, 8));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 1, 13, "Brian"));
       tokens.add(new Token(Token.TokenTypes.COMMA, 1, 14));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 1, 22, "Matilda"));
       tokens.add(new Token(Token.TokenTypes.COMMA, 1, 23));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 1, 30, "Ursula"));
       tokens.add(new Token(Token.TokenTypes.RIGHTCURLY, 1, 31));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 2, 0));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 2, 5, "Drink"));
       tokens.add(new Token(Token.TokenTypes.EQUAL, 2, 7));
       tokens.add(new Token(Token.TokenTypes.LEFTCURLY, 2, 9));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 2, 15, "Coffee"));
       tokens.add(new Token(Token.TokenTypes.COMMA, 2, 16));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 2, 25, "Lemonade"));
       tokens.add(new Token(Token.TokenTypes.COMMA, 2, 26));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 2, 30, "Tea"));
       tokens.add(new Token(Token.TokenTypes.RIGHTCURLY, 2, 31));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 3, 0));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 3, 6, "Pastry"));
       tokens.add(new Token(Token.TokenTypes.EQUAL, 3, 8));
       tokens.add(new Token(Token.TokenTypes.LEFTCURLY, 3, 10));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 3, 20, "Croissants"));
       tokens.add(new Token(Token.TokenTypes.COMMA, 3, 21));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 3, 29, "Eclairs"));
       tokens.add(new Token(Token.TokenTypes.COMMA, 3, 30));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 3, 39, "Macarons"));
       tokens.add(new Token(Token.TokenTypes.RIGHTCURLY, 3, 40));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 4, 0));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 5, 0));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 5, 6, "Puzzle"));
       tokens.add(new Token(Token.TokenTypes.EQUAL, 5, 8));
       tokens.add(new Token(Token.TokenTypes.LEFTBRACE, 5, 10));
       tokens.add(new Token(Token.TokenTypes.UNIQUE, 5, 16));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 5, 21, "Name"));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 5, 23, "n"));
       tokens.add(new Token(Token.TokenTypes.COMMA, 5, 24));
       tokens.add(new Token(Token.TokenTypes.UNIQUE, 5, 31));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 5, 37, "Drink"));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 5, 39, "d"));
       tokens.add(new Token(Token.TokenTypes.COMMA, 5, 40));
       tokens.add(new Token(Token.TokenTypes.UNIQUE, 5, 47));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 5, 54, "Pastry"));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 5, 56, "p"));
       tokens.add(new Token(Token.TokenTypes.RIGHTBRACE, 5, 57));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 6, 0));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 7, 0));
       tokens.add(new Token(Token.TokenTypes.VAR, 7, 3));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 7, 11, "Puzzles"));
       tokens.add(new Token(Token.TokenTypes.COLON, 7, 13));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 7, 20, "Puzzle"));
       tokens.add(new Token(Token.TokenTypes.LEFTBRACE, 7, 21));
       tokens.add(new Token(Token.TokenTypes.NUMBER, 7, 22, "3"));
       tokens.add(new Token(Token.TokenTypes.RIGHTBRACE, 7, 23));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 8, 0));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 9, 0));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 9, 7, "Puzzles"));
       tokens.add(new Token(Token.TokenTypes.LEFTBRACE, 9, 8));
       tokens.add(new Token(Token.TokenTypes.NUMBER, 9, 9, "0"));
       tokens.add(new Token(Token.TokenTypes.RIGHTBRACE, 9, 10));
       tokens.add(new Token(Token.TokenTypes.DOT, 9, 11));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 9, 12, "c"));
       tokens.add(new Token(Token.TokenTypes.EQUAL, 9, 14));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 9, 20, "Brian"));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 10, 0));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 11, 0));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 11, 7, "Puzzles"));
       tokens.add(new Token(Token.TokenTypes.LEFTBRACE, 11, 8));
       tokens.add(new Token(Token.TokenTypes.NUMBER, 11, 9, "1"));
       tokens.add(new Token(Token.TokenTypes.RIGHTBRACE, 11, 10));
       tokens.add(new Token(Token.TokenTypes.DOT, 11, 11));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 11, 12, "c"));
       tokens.add(new Token(Token.TokenTypes.EQUAL, 11, 14));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 11, 22, "Matilda"));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 12, 0));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 13, 0));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 13, 7, "Puzzles"));
       tokens.add(new Token(Token.TokenTypes.LEFTBRACE, 13, 8));
       tokens.add(new Token(Token.TokenTypes.NUMBER, 13, 9, "2"));
       tokens.add(new Token(Token.TokenTypes.RIGHTBRACE, 13, 10));
       tokens.add(new Token(Token.TokenTypes.DOT, 13, 11));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 13, 12, "c"));
       tokens.add(new Token(Token.TokenTypes.EQUAL, 13, 14));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 13, 21, "Ursula"));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 14, 0));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 15, 0));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 15, 7, "Puzzles"));
       tokens.add(new Token(Token.TokenTypes.DOT, 15, 8));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 15, 9, "p"));
       tokens.add(new Token(Token.TokenTypes.EQUAL, 15, 11));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 15, 19, "Eclairs"));
       tokens.add(new Token(Token.TokenTypes.YIELDS, 15, 22));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 16, 0));
       tokens.add(new Token(Token.TokenTypes.INDENT, 16, 4));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 16, 10, "Puzzle"));
       tokens.add(new Token(Token.TokenTypes.DOT, 16, 11));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 16, 12, "n"));
       tokens.add(new Token(Token.TokenTypes.EQUAL, 16, 14));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 16, 20, "Brian"));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 17, 0));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 18, 0));
       tokens.add(new Token(Token.TokenTypes.DEDENT, 18, 0));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 18, 6, "Puzzle"));
       tokens.add(new Token(Token.TokenTypes.DOT, 18, 7));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 18, 8, "n"));
       tokens.add(new Token(Token.TokenTypes.EQUAL, 18, 10));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 18, 17, "Ursula"));
       tokens.add(new Token(Token.TokenTypes.YIELDS, 18, 20));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 19, 0));
       tokens.add(new Token(Token.TokenTypes.INDENT, 19, 4));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 19, 10, "Puzzle"));
       tokens.add(new Token(Token.TokenTypes.DOT, 19, 11));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 19, 12, "p"));
       tokens.add(new Token(Token.TokenTypes.NOTEQUAL, 19, 15));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 19, 26, "Croissants"));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 20, 0));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 20, 10, "Puzzle"));
       tokens.add(new Token(Token.TokenTypes.DOT, 20, 11));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 20, 12, "d"));
       tokens.add(new Token(Token.TokenTypes.EQUAL, 20, 14));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 20, 23, "Lemonade"));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 21, 0));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 22, 0));
       tokens.add(new Token(Token.TokenTypes.DEDENT, 22, 0));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 22, 6, "Puzzle"));
       tokens.add(new Token(Token.TokenTypes.DOT, 22, 7));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 22, 8, "p"));
       tokens.add(new Token(Token.TokenTypes.EQUAL, 22, 10));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 22, 21, "Croissants"));
       tokens.add(new Token(Token.TokenTypes.YIELDS, 22, 24));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 23, 0));
       tokens.add(new Token(Token.TokenTypes.INDENT, 23, 4));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 23, 10, "Puzzle"));
       tokens.add(new Token(Token.TokenTypes.DOT, 23, 11));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 23, 12, "d"));
       tokens.add(new Token(Token.TokenTypes.EQUAL, 23, 14));
       tokens.add(new Token(Token.TokenTypes.IDENTIFIER, 23, 18, "Tea"));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 24, 0));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 25, 0));
       tokens.add(new Token(Token.TokenTypes.DEDENT, 25, 0));
       tokens.add(new Token(Token.TokenTypes.NEWLINE, 25, 0));

       var ast = new NushaFall2025Parser().Nusha(tokens).orElseThrow();
       Assertions.assertEquals("Name", ast.definitions.definition.get(0).definitionName);
       Assertions.assertEquals("Brian", ast.definitions.definition.get(0).choices.orElseThrow().choice.get(0));
       Assertions.assertEquals("Matilda", ast.definitions.definition.get(0).choices.orElseThrow().choice.get(1));
       Assertions.assertEquals("Ursula", ast.definitions.definition.get(0).choices.orElseThrow().choice.get(2));
       Assertions.assertEquals("Drink", ast.definitions.definition.get(1).definitionName);
       Assertions.assertEquals("Coffee", ast.definitions.definition.get(1).choices.orElseThrow().choice.get(0));
       Assertions.assertEquals("Lemonade", ast.definitions.definition.get(1).choices.orElseThrow().choice.get(1));
       Assertions.assertEquals("Tea", ast.definitions.definition.get(1).choices.orElseThrow().choice.get(2));
       Assertions.assertEquals("Pastry", ast.definitions.definition.get(2).definitionName);
       Assertions.assertEquals("Croissants", ast.definitions.definition.get(2).choices.orElseThrow().choice.get(0));
       Assertions.assertEquals("Eclairs", ast.definitions.definition.get(2).choices.orElseThrow().choice.get(1));
       Assertions.assertEquals("Macarons", ast.definitions.definition.get(2).choices.orElseThrow().choice.get(2));
       Assertions.assertEquals("Puzzle", ast.definitions.definition.get(3).definitionName);
       Assertions.assertEquals("n", ast.definitions.definition.get(3).nstruct.orElseThrow().entry.get(0).name);
       Assertions.assertEquals("Name", ast.definitions.definition.get(3).nstruct.orElseThrow().entry.get(0).type);
       Assertions.assertEquals(true, ast.definitions.definition.get(3).nstruct.orElseThrow().entry.get(0).unique);
       Assertions.assertEquals("d", ast.definitions.definition.get(3).nstruct.orElseThrow().entry.get(1).name);
       Assertions.assertEquals("Drink", ast.definitions.definition.get(3).nstruct.orElseThrow().entry.get(1).type);
       Assertions.assertEquals(true, ast.definitions.definition.get(3).nstruct.orElseThrow().entry.get(1).unique);
       Assertions.assertEquals("p", ast.definitions.definition.get(3).nstruct.orElseThrow().entry.get(2).name);
       Assertions.assertEquals("Pastry", ast.definitions.definition.get(3).nstruct.orElseThrow().entry.get(2).type);
       Assertions.assertEquals(true, ast.definitions.definition.get(3).nstruct.orElseThrow().entry.get(2).unique);
       Assertions.assertEquals("Puzzle", ast.variables.variable.get(0).type);
       Assertions.assertEquals("Puzzles", ast.variables.variable.get(0).variableName);
       Assertions.assertEquals("3", ast.variables.variable.get(0).size.orElseThrow());
       Assertions.assertEquals("Puzzles", ast.rules.rule.get(0).expression.left.variableName);
       Assertions.assertEquals("0", ast.rules.rule.get(0).expression.left.vrmodifier.orElseThrow().size);
       Assertions.assertEquals(true, ast.rules.rule.get(0).expression.left.vrmodifier.orElseThrow().vrmodifier.orElseThrow().dot);
       Assertions.assertEquals("c", ast.rules.rule.get(0).expression.left.vrmodifier.orElseThrow().vrmodifier.orElseThrow().part.orElseThrow());
       Assertions.assertEquals("Equal", ast.rules.rule.get(0).expression.op.type.name());
       Assertions.assertEquals("Brian", ast.rules.rule.get(0).expression.right.variableName);
       Assertions.assertEquals("Puzzles", ast.rules.rule.get(1).expression.left.variableName);
       Assertions.assertEquals("1", ast.rules.rule.get(1).expression.left.vrmodifier.orElseThrow().size);
       Assertions.assertEquals(true, ast.rules.rule.get(1).expression.left.vrmodifier.orElseThrow().vrmodifier.orElseThrow().dot);
       Assertions.assertEquals("c", ast.rules.rule.get(1).expression.left.vrmodifier.orElseThrow().vrmodifier.orElseThrow().part.orElseThrow());
       Assertions.assertEquals("Equal", ast.rules.rule.get(1).expression.op.type.name());
       Assertions.assertEquals("Matilda", ast.rules.rule.get(1).expression.right.variableName);
       Assertions.assertEquals("Puzzles", ast.rules.rule.get(2).expression.left.variableName);
       Assertions.assertEquals("2", ast.rules.rule.get(2).expression.left.vrmodifier.orElseThrow().size);
       Assertions.assertEquals(true, ast.rules.rule.get(2).expression.left.vrmodifier.orElseThrow().vrmodifier.orElseThrow().dot);
       Assertions.assertEquals("c", ast.rules.rule.get(2).expression.left.vrmodifier.orElseThrow().vrmodifier.orElseThrow().part.orElseThrow());
       Assertions.assertEquals("Equal", ast.rules.rule.get(2).expression.op.type.name());
       Assertions.assertEquals("Ursula", ast.rules.rule.get(2).expression.right.variableName);
       Assertions.assertEquals("Puzzles", ast.rules.rule.get(3).expression.left.variableName);
       Assertions.assertEquals(true, ast.rules.rule.get(3).expression.left.vrmodifier.orElseThrow().dot);
       Assertions.assertEquals("p", ast.rules.rule.get(3).expression.left.vrmodifier.orElseThrow().part.orElseThrow());
       Assertions.assertEquals("Equal", ast.rules.rule.get(3).expression.op.type.name());
       Assertions.assertEquals("Eclairs", ast.rules.rule.get(3).expression.right.variableName);
       Assertions.assertEquals("Puzzle", ast.rules.rule.get(3).thens.get(0).left.variableName);
       Assertions.assertEquals(true, ast.rules.rule.get(3).thens.get(0).left.vrmodifier.orElseThrow().dot);
       Assertions.assertEquals("n", ast.rules.rule.get(3).thens.get(0).left.vrmodifier.orElseThrow().part.orElseThrow());
       Assertions.assertEquals("Equal", ast.rules.rule.get(3).thens.get(0).op.type.name());
       Assertions.assertEquals("Brian", ast.rules.rule.get(3).thens.get(0).right.variableName);
       Assertions.assertEquals("Puzzle", ast.rules.rule.get(4).expression.left.variableName);
       Assertions.assertEquals(true, ast.rules.rule.get(4).expression.left.vrmodifier.orElseThrow().dot);
       Assertions.assertEquals("n", ast.rules.rule.get(4).expression.left.vrmodifier.orElseThrow().part.orElseThrow());
       Assertions.assertEquals("Equal", ast.rules.rule.get(4).expression.op.type.name());
       Assertions.assertEquals("Ursula", ast.rules.rule.get(4).expression.right.variableName);
       Assertions.assertEquals("Puzzle", ast.rules.rule.get(4).thens.get(0).left.variableName);
       Assertions.assertEquals(true, ast.rules.rule.get(4).thens.get(0).left.vrmodifier.orElseThrow().dot);
       Assertions.assertEquals("p", ast.rules.rule.get(4).thens.get(0).left.vrmodifier.orElseThrow().part.orElseThrow());
       Assertions.assertEquals("NotEqual", ast.rules.rule.get(4).thens.get(0).op.type.name());
       Assertions.assertEquals("Croissants", ast.rules.rule.get(4).thens.get(0).right.variableName);
       Assertions.assertEquals("Puzzle", ast.rules.rule.get(4).thens.get(1).left.variableName);
       Assertions.assertEquals(true, ast.rules.rule.get(4).thens.get(1).left.vrmodifier.orElseThrow().dot);
       Assertions.assertEquals("d", ast.rules.rule.get(4).thens.get(1).left.vrmodifier.orElseThrow().part.orElseThrow());
       Assertions.assertEquals("Equal", ast.rules.rule.get(4).thens.get(1).op.type.name());
       Assertions.assertEquals("Lemonade", ast.rules.rule.get(4).thens.get(1).right.variableName);
       Assertions.assertEquals("Puzzle", ast.rules.rule.get(5).expression.left.variableName);
       Assertions.assertEquals(true, ast.rules.rule.get(5).expression.left.vrmodifier.orElseThrow().dot);
       Assertions.assertEquals("p", ast.rules.rule.get(5).expression.left.vrmodifier.orElseThrow().part.orElseThrow());
       Assertions.assertEquals("Equal", ast.rules.rule.get(5).expression.op.type.name());
       Assertions.assertEquals("Croissants", ast.rules.rule.get(5).expression.right.variableName);
       Assertions.assertEquals("Puzzle", ast.rules.rule.get(5).thens.get(0).left.variableName);
       Assertions.assertEquals(true, ast.rules.rule.get(5).thens.get(0).left.vrmodifier.orElseThrow().dot);
       Assertions.assertEquals("d", ast.rules.rule.get(5).thens.get(0).left.vrmodifier.orElseThrow().part.orElseThrow());
       Assertions.assertEquals("Equal", ast.rules.rule.get(5).thens.get(0).op.type.name());
       Assertions.assertEquals("Tea", ast.rules.rule.get(5).thens.get(0).right.variableName);
   }
}
