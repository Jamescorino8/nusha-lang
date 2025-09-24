import AST.Token;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class Lexer2Tests {

    @Test
    public void TestStructuresLexer() throws Exception {
        var code =          "Shape = {Cube, Cone, Sphere, Pyramid}\n"+
                "Block = [unique Shape s, color c]\n"+
                "var Blocks : Block[4]\n"+
                "";
        var tokens = new Lexer(code).Lex();
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(0).Type);
        Assertions.assertEquals("Shape",  tokens.get(0).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(1).Type);
        Assertions.assertEquals(Token.TokenTypes.LEFTCURLY, tokens.get(2).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(3).Type);
        Assertions.assertEquals("Cube",  tokens.get(3).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(4).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(5).Type);
        Assertions.assertEquals("Cone",  tokens.get(5).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(6).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(7).Type);
        Assertions.assertEquals("Sphere",  tokens.get(7).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(8).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(9).Type);
        Assertions.assertEquals("Pyramid",  tokens.get(9).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.RIGHTCURLY, tokens.get(10).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(11).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(12).Type);
        Assertions.assertEquals("Block",  tokens.get(12).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(13).Type);
        Assertions.assertEquals(Token.TokenTypes.LEFTBRACE, tokens.get(14).Type);
        Assertions.assertEquals(Token.TokenTypes.UNIQUE, tokens.get(15).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(16).Type);
        Assertions.assertEquals("Shape",  tokens.get(16).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(17).Type);
        Assertions.assertEquals("s",  tokens.get(17).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(18).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(19).Type);
        Assertions.assertEquals("color",  tokens.get(19).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(20).Type);
        Assertions.assertEquals("c",  tokens.get(20).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.RIGHTBRACE, tokens.get(21).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(22).Type);
        Assertions.assertEquals(Token.TokenTypes.VAR, tokens.get(23).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(24).Type);
        Assertions.assertEquals("Blocks",  tokens.get(24).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COLON, tokens.get(25).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(26).Type);
        Assertions.assertEquals("Block",  tokens.get(26).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.LEFTBRACE, tokens.get(27).Type);
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(28).Type);
        Assertions.assertEquals("4",  tokens.get(28).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.RIGHTBRACE, tokens.get(29).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(30).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(31).Type);
    }

    @Test
    public void TestRulesLexer() throws Exception {
        var code =          "Blocks[0].s = Cube\n"+
                "Blocks.s = Cone =>\n"+
                "    Blocks.c != Red\n"+
                "Blocks.a = Sphere =>\n"+
                "    Blocks.c = Blue\n"+
                "";
        var tokens = new Lexer(code).Lex();
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(0).Type);
        Assertions.assertEquals("Blocks",  tokens.get(0).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.LEFTBRACE, tokens.get(1).Type);
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(2).Type);
        Assertions.assertEquals("0",  tokens.get(2).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.RIGHTBRACE, tokens.get(3).Type);
        Assertions.assertEquals(Token.TokenTypes.DOT, tokens.get(4).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(5).Type);
        Assertions.assertEquals("s",  tokens.get(5).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(6).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(7).Type);
        Assertions.assertEquals("Cube",  tokens.get(7).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(8).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(9).Type);
        Assertions.assertEquals("Blocks",  tokens.get(9).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.DOT, tokens.get(10).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(11).Type);
        Assertions.assertEquals("s",  tokens.get(11).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(12).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(13).Type);
        Assertions.assertEquals("Cone",  tokens.get(13).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.YIELDS, tokens.get(14).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(15).Type);
        Assertions.assertEquals(Token.TokenTypes.INDENT, tokens.get(16).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(17).Type);
        Assertions.assertEquals("Blocks",  tokens.get(17).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.DOT, tokens.get(18).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(19).Type);
        Assertions.assertEquals("c",  tokens.get(19).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NOTEQUAL, tokens.get(20).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(21).Type);
        Assertions.assertEquals("Red",  tokens.get(21).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(22).Type);
        Assertions.assertEquals(Token.TokenTypes.DEDENT, tokens.get(23).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(24).Type);
        Assertions.assertEquals("Blocks",  tokens.get(24).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.DOT, tokens.get(25).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(26).Type);
        Assertions.assertEquals("a",  tokens.get(26).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(27).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(28).Type);
        Assertions.assertEquals("Sphere",  tokens.get(28).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.YIELDS, tokens.get(29).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(30).Type);
        Assertions.assertEquals(Token.TokenTypes.INDENT, tokens.get(31).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(32).Type);
        Assertions.assertEquals("Blocks",  tokens.get(32).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.DOT, tokens.get(33).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(34).Type);
        Assertions.assertEquals("c",  tokens.get(34).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(35).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(36).Type);
        Assertions.assertEquals("Blue",  tokens.get(36).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(37).Type);
        Assertions.assertEquals(Token.TokenTypes.DEDENT, tokens.get(38).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(39).Type);
    }

    @Test
    public void TestPetsLexer() throws Exception {
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
        var tokens = new Lexer(code).Lex();
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(0).Type);
        Assertions.assertEquals("Author",  tokens.get(0).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(1).Type);
        Assertions.assertEquals(Token.TokenTypes.LEFTCURLY, tokens.get(2).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(3).Type);
        Assertions.assertEquals("Alice",  tokens.get(3).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(4).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(5).Type);
        Assertions.assertEquals("Bob",  tokens.get(5).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(6).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(7).Type);
        Assertions.assertEquals("Carol",  tokens.get(7).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(8).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(9).Type);
        Assertions.assertEquals("David",  tokens.get(9).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.RIGHTCURLY, tokens.get(10).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(11).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(12).Type);
        Assertions.assertEquals("Pet",  tokens.get(12).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(13).Type);
        Assertions.assertEquals(Token.TokenTypes.LEFTCURLY, tokens.get(14).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(15).Type);
        Assertions.assertEquals("Dog",  tokens.get(15).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(16).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(17).Type);
        Assertions.assertEquals("Cat",  tokens.get(17).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(18).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(19).Type);
        Assertions.assertEquals("Bird",  tokens.get(19).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(20).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(21).Type);
        Assertions.assertEquals("Fish",  tokens.get(21).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.RIGHTCURLY, tokens.get(22).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(23).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(24).Type);
        Assertions.assertEquals("House",  tokens.get(24).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(25).Type);
        Assertions.assertEquals(Token.TokenTypes.LEFTCURLY, tokens.get(26).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(27).Type);
        Assertions.assertEquals("Red",  tokens.get(27).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(28).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(29).Type);
        Assertions.assertEquals("Blue",  tokens.get(29).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(30).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(31).Type);
        Assertions.assertEquals("Green",  tokens.get(31).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(32).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(33).Type);
        Assertions.assertEquals("Yellow",  tokens.get(33).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.RIGHTCURLY, tokens.get(34).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(35).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(36).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(37).Type);
        Assertions.assertEquals("Story",  tokens.get(37).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(38).Type);
        Assertions.assertEquals(Token.TokenTypes.LEFTBRACE, tokens.get(39).Type);
        Assertions.assertEquals(Token.TokenTypes.UNIQUE, tokens.get(40).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(41).Type);
        Assertions.assertEquals("Author",  tokens.get(41).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(42).Type);
        Assertions.assertEquals("a",  tokens.get(42).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(43).Type);
        Assertions.assertEquals(Token.TokenTypes.UNIQUE, tokens.get(44).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(45).Type);
        Assertions.assertEquals("Pet",  tokens.get(45).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(46).Type);
        Assertions.assertEquals("p",  tokens.get(46).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(47).Type);
        Assertions.assertEquals(Token.TokenTypes.UNIQUE, tokens.get(48).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(49).Type);
        Assertions.assertEquals("House",  tokens.get(49).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(50).Type);
        Assertions.assertEquals("h",  tokens.get(50).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.RIGHTBRACE, tokens.get(51).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(52).Type);
        Assertions.assertEquals(Token.TokenTypes.VAR, tokens.get(53).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(54).Type);
        Assertions.assertEquals("Stories",  tokens.get(54).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COLON, tokens.get(55).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(56).Type);
        Assertions.assertEquals("Story",  tokens.get(56).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.LEFTBRACE, tokens.get(57).Type);
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(58).Type);
        Assertions.assertEquals("4",  tokens.get(58).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.RIGHTBRACE, tokens.get(59).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(60).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(61).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(62).Type);
        Assertions.assertEquals("Stories",  tokens.get(62).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.LEFTBRACE, tokens.get(63).Type);
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(64).Type);
        Assertions.assertEquals("0",  tokens.get(64).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.RIGHTBRACE, tokens.get(65).Type);
        Assertions.assertEquals(Token.TokenTypes.DOT, tokens.get(66).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(67).Type);
        Assertions.assertEquals("a",  tokens.get(67).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(68).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(69).Type);
        Assertions.assertEquals("Alice",  tokens.get(69).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(70).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(71).Type);
        Assertions.assertEquals("Stories",  tokens.get(71).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.LEFTBRACE, tokens.get(72).Type);
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(73).Type);
        Assertions.assertEquals("1",  tokens.get(73).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.RIGHTBRACE, tokens.get(74).Type);
        Assertions.assertEquals(Token.TokenTypes.DOT, tokens.get(75).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(76).Type);
        Assertions.assertEquals("a",  tokens.get(76).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(77).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(78).Type);
        Assertions.assertEquals("Bob",  tokens.get(78).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(79).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(80).Type);
        Assertions.assertEquals("Stories",  tokens.get(80).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.LEFTBRACE, tokens.get(81).Type);
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(82).Type);
        Assertions.assertEquals("2",  tokens.get(82).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.RIGHTBRACE, tokens.get(83).Type);
        Assertions.assertEquals(Token.TokenTypes.DOT, tokens.get(84).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(85).Type);
        Assertions.assertEquals("a",  tokens.get(85).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(86).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(87).Type);
        Assertions.assertEquals("Carol",  tokens.get(87).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(88).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(89).Type);
        Assertions.assertEquals("Stories",  tokens.get(89).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.LEFTBRACE, tokens.get(90).Type);
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(91).Type);
        Assertions.assertEquals("3",  tokens.get(91).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.RIGHTBRACE, tokens.get(92).Type);
        Assertions.assertEquals(Token.TokenTypes.DOT, tokens.get(93).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(94).Type);
        Assertions.assertEquals("a",  tokens.get(94).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(95).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(96).Type);
        Assertions.assertEquals("David",  tokens.get(96).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(97).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(98).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(99).Type);
        Assertions.assertEquals("Stories",  tokens.get(99).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.DOT, tokens.get(100).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(101).Type);
        Assertions.assertEquals("a",  tokens.get(101).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(102).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(103).Type);
        Assertions.assertEquals("Alice",  tokens.get(103).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.YIELDS, tokens.get(104).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(105).Type);
        Assertions.assertEquals(Token.TokenTypes.INDENT, tokens.get(106).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(107).Type);
        Assertions.assertEquals("Stories",  tokens.get(107).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.DOT, tokens.get(108).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(109).Type);
        Assertions.assertEquals("h",  tokens.get(109).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NOTEQUAL, tokens.get(110).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(111).Type);
        Assertions.assertEquals("Red",  tokens.get(111).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(112).Type);
        Assertions.assertEquals(Token.TokenTypes.DEDENT, tokens.get(113).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(114).Type);
        Assertions.assertEquals("Stories",  tokens.get(114).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.DOT, tokens.get(115).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(116).Type);
        Assertions.assertEquals("h",  tokens.get(116).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(117).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(118).Type);
        Assertions.assertEquals("Blue",  tokens.get(118).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.YIELDS, tokens.get(119).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(120).Type);
        Assertions.assertEquals(Token.TokenTypes.INDENT, tokens.get(121).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(122).Type);
        Assertions.assertEquals("Stories",  tokens.get(122).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.DOT, tokens.get(123).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(124).Type);
        Assertions.assertEquals("p",  tokens.get(124).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(125).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(126).Type);
        Assertions.assertEquals("Cat",  tokens.get(126).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(127).Type);
        Assertions.assertEquals(Token.TokenTypes.DEDENT, tokens.get(128).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(129).Type);
        Assertions.assertEquals("Stories",  tokens.get(129).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.DOT, tokens.get(130).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(131).Type);
        Assertions.assertEquals("a",  tokens.get(131).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(132).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(133).Type);
        Assertions.assertEquals("David",  tokens.get(133).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.YIELDS, tokens.get(134).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(135).Type);
        Assertions.assertEquals(Token.TokenTypes.INDENT, tokens.get(136).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(137).Type);
        Assertions.assertEquals("Stories",  tokens.get(137).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.DOT, tokens.get(138).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(139).Type);
        Assertions.assertEquals("p",  tokens.get(139).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(140).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(141).Type);
        Assertions.assertEquals("Dog",  tokens.get(141).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(142).Type);
        Assertions.assertEquals(Token.TokenTypes.DEDENT, tokens.get(143).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(144).Type);
        Assertions.assertEquals("Stories",  tokens.get(144).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.DOT, tokens.get(145).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(146).Type);
        Assertions.assertEquals("a",  tokens.get(146).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(147).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(148).Type);
        Assertions.assertEquals("Carol",  tokens.get(148).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.YIELDS, tokens.get(149).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(150).Type);
        Assertions.assertEquals(Token.TokenTypes.INDENT, tokens.get(151).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(152).Type);
        Assertions.assertEquals("Stories",  tokens.get(152).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.DOT, tokens.get(153).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(154).Type);
        Assertions.assertEquals("h",  tokens.get(154).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(155).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(156).Type);
        Assertions.assertEquals("Green",  tokens.get(156).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(157).Type);
        Assertions.assertEquals(Token.TokenTypes.DEDENT, tokens.get(158).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(159).Type);
        Assertions.assertEquals("Stories",  tokens.get(159).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.DOT, tokens.get(160).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(161).Type);
        Assertions.assertEquals("a",  tokens.get(161).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(162).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(163).Type);
        Assertions.assertEquals("Bob",  tokens.get(163).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.YIELDS, tokens.get(164).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(165).Type);
        Assertions.assertEquals(Token.TokenTypes.INDENT, tokens.get(166).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(167).Type);
        Assertions.assertEquals("Stories",  tokens.get(167).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.DOT, tokens.get(168).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(169).Type);
        Assertions.assertEquals("p",  tokens.get(169).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NOTEQUAL, tokens.get(170).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(171).Type);
        Assertions.assertEquals("Fish",  tokens.get(171).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(172).Type);
        Assertions.assertEquals(Token.TokenTypes.DEDENT, tokens.get(173).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(174).Type);
    }

    @Test
    public void TestFriendsLexer() throws Exception {
        var code =          "Author = {Joanne, Lou, Ralph, Winnie}\n"+
                "Place = {Circus, Band, Spaceship, Train}\n"+
                "Animal = {Bear, Moose, Seal, Zebra}\n"+
                "Story = [unique Author a, unique Place p, unique Animal f]\n"+
                "var Stories : Story[4]\n"+
                "Stories[0].a = Joanne\n"+
                "Stories[1].a = Lou\n"+
                "Stories[2].a = Ralph\n"+
                "Stories[3].a = Winnie\n"+
                "Stories.f = Seal =>\n"+
                "    Stories.a != Joanne\n"+
                "    Stories.a != Lou\n"+
                "    Stories.p != Spaceship\n"+
                "    Stories.p != Train\n"+
                "Stories.a = Joanne =>\n"+
                "    Stories.f != Bear\n"+
                "    Stories.p = Circus\n"+
                "Stories.a = Winnie =>\n"+
                "    Stories.f = Zebra\n"+
                "Stories.f = Bear =>\n"+
                "    Stories.p != Spaceship\n"+
                "";
        var tokens = new Lexer(code).Lex();
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(0).Type);
        Assertions.assertEquals("Author",  tokens.get(0).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(1).Type);
        Assertions.assertEquals(Token.TokenTypes.LEFTCURLY, tokens.get(2).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(3).Type);
        Assertions.assertEquals("Joanne",  tokens.get(3).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(4).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(5).Type);
        Assertions.assertEquals("Lou",  tokens.get(5).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(6).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(7).Type);
        Assertions.assertEquals("Ralph",  tokens.get(7).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(8).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(9).Type);
        Assertions.assertEquals("Winnie",  tokens.get(9).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.RIGHTCURLY, tokens.get(10).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(11).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(12).Type);
        Assertions.assertEquals("Place",  tokens.get(12).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(13).Type);
        Assertions.assertEquals(Token.TokenTypes.LEFTCURLY, tokens.get(14).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(15).Type);
        Assertions.assertEquals("Circus",  tokens.get(15).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(16).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(17).Type);
        Assertions.assertEquals("Band",  tokens.get(17).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(18).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(19).Type);
        Assertions.assertEquals("Spaceship",  tokens.get(19).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(20).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(21).Type);
        Assertions.assertEquals("Train",  tokens.get(21).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.RIGHTCURLY, tokens.get(22).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(23).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(24).Type);
        Assertions.assertEquals("Animal",  tokens.get(24).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(25).Type);
        Assertions.assertEquals(Token.TokenTypes.LEFTCURLY, tokens.get(26).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(27).Type);
        Assertions.assertEquals("Bear",  tokens.get(27).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(28).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(29).Type);
        Assertions.assertEquals("Moose",  tokens.get(29).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(30).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(31).Type);
        Assertions.assertEquals("Seal",  tokens.get(31).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(32).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(33).Type);
        Assertions.assertEquals("Zebra",  tokens.get(33).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.RIGHTCURLY, tokens.get(34).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(35).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(36).Type);
        Assertions.assertEquals("Story",  tokens.get(36).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(37).Type);
        Assertions.assertEquals(Token.TokenTypes.LEFTBRACE, tokens.get(38).Type);
        Assertions.assertEquals(Token.TokenTypes.UNIQUE, tokens.get(39).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(40).Type);
        Assertions.assertEquals("Author",  tokens.get(40).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(41).Type);
        Assertions.assertEquals("a",  tokens.get(41).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(42).Type);
        Assertions.assertEquals(Token.TokenTypes.UNIQUE, tokens.get(43).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(44).Type);
        Assertions.assertEquals("Place",  tokens.get(44).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(45).Type);
        Assertions.assertEquals("p",  tokens.get(45).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(46).Type);
        Assertions.assertEquals(Token.TokenTypes.UNIQUE, tokens.get(47).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(48).Type);
        Assertions.assertEquals("Animal",  tokens.get(48).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(49).Type);
        Assertions.assertEquals("f",  tokens.get(49).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.RIGHTBRACE, tokens.get(50).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(51).Type);
        Assertions.assertEquals(Token.TokenTypes.VAR, tokens.get(52).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(53).Type);
        Assertions.assertEquals("Stories",  tokens.get(53).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COLON, tokens.get(54).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(55).Type);
        Assertions.assertEquals("Story",  tokens.get(55).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.LEFTBRACE, tokens.get(56).Type);
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(57).Type);
        Assertions.assertEquals("4",  tokens.get(57).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.RIGHTBRACE, tokens.get(58).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(59).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(60).Type);
        Assertions.assertEquals("Stories",  tokens.get(60).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.LEFTBRACE, tokens.get(61).Type);
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(62).Type);
        Assertions.assertEquals("0",  tokens.get(62).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.RIGHTBRACE, tokens.get(63).Type);
        Assertions.assertEquals(Token.TokenTypes.DOT, tokens.get(64).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(65).Type);
        Assertions.assertEquals("a",  tokens.get(65).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(66).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(67).Type);
        Assertions.assertEquals("Joanne",  tokens.get(67).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(68).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(69).Type);
        Assertions.assertEquals("Stories",  tokens.get(69).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.LEFTBRACE, tokens.get(70).Type);
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(71).Type);
        Assertions.assertEquals("1",  tokens.get(71).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.RIGHTBRACE, tokens.get(72).Type);
        Assertions.assertEquals(Token.TokenTypes.DOT, tokens.get(73).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(74).Type);
        Assertions.assertEquals("a",  tokens.get(74).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(75).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(76).Type);
        Assertions.assertEquals("Lou",  tokens.get(76).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(77).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(78).Type);
        Assertions.assertEquals("Stories",  tokens.get(78).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.LEFTBRACE, tokens.get(79).Type);
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(80).Type);
        Assertions.assertEquals("2",  tokens.get(80).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.RIGHTBRACE, tokens.get(81).Type);
        Assertions.assertEquals(Token.TokenTypes.DOT, tokens.get(82).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(83).Type);
        Assertions.assertEquals("a",  tokens.get(83).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(84).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(85).Type);
        Assertions.assertEquals("Ralph",  tokens.get(85).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(86).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(87).Type);
        Assertions.assertEquals("Stories",  tokens.get(87).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.LEFTBRACE, tokens.get(88).Type);
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(89).Type);
        Assertions.assertEquals("3",  tokens.get(89).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.RIGHTBRACE, tokens.get(90).Type);
        Assertions.assertEquals(Token.TokenTypes.DOT, tokens.get(91).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(92).Type);
        Assertions.assertEquals("a",  tokens.get(92).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(93).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(94).Type);
        Assertions.assertEquals("Winnie",  tokens.get(94).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(95).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(96).Type);
        Assertions.assertEquals("Stories",  tokens.get(96).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.DOT, tokens.get(97).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(98).Type);
        Assertions.assertEquals("f",  tokens.get(98).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(99).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(100).Type);
        Assertions.assertEquals("Seal",  tokens.get(100).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.YIELDS, tokens.get(101).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(102).Type);
        Assertions.assertEquals(Token.TokenTypes.INDENT, tokens.get(103).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(104).Type);
        Assertions.assertEquals("Stories",  tokens.get(104).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.DOT, tokens.get(105).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(106).Type);
        Assertions.assertEquals("a",  tokens.get(106).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NOTEQUAL, tokens.get(107).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(108).Type);
        Assertions.assertEquals("Joanne",  tokens.get(108).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(109).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(110).Type);
        Assertions.assertEquals("Stories",  tokens.get(110).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.DOT, tokens.get(111).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(112).Type);
        Assertions.assertEquals("a",  tokens.get(112).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NOTEQUAL, tokens.get(113).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(114).Type);
        Assertions.assertEquals("Lou",  tokens.get(114).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(115).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(116).Type);
        Assertions.assertEquals("Stories",  tokens.get(116).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.DOT, tokens.get(117).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(118).Type);
        Assertions.assertEquals("p",  tokens.get(118).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NOTEQUAL, tokens.get(119).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(120).Type);
        Assertions.assertEquals("Spaceship",  tokens.get(120).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(121).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(122).Type);
        Assertions.assertEquals("Stories",  tokens.get(122).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.DOT, tokens.get(123).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(124).Type);
        Assertions.assertEquals("p",  tokens.get(124).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NOTEQUAL, tokens.get(125).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(126).Type);
        Assertions.assertEquals("Train",  tokens.get(126).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(127).Type);
        Assertions.assertEquals(Token.TokenTypes.DEDENT, tokens.get(128).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(129).Type);
        Assertions.assertEquals("Stories",  tokens.get(129).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.DOT, tokens.get(130).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(131).Type);
        Assertions.assertEquals("a",  tokens.get(131).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(132).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(133).Type);
        Assertions.assertEquals("Joanne",  tokens.get(133).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.YIELDS, tokens.get(134).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(135).Type);
        Assertions.assertEquals(Token.TokenTypes.INDENT, tokens.get(136).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(137).Type);
        Assertions.assertEquals("Stories",  tokens.get(137).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.DOT, tokens.get(138).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(139).Type);
        Assertions.assertEquals("f",  tokens.get(139).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NOTEQUAL, tokens.get(140).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(141).Type);
        Assertions.assertEquals("Bear",  tokens.get(141).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(142).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(143).Type);
        Assertions.assertEquals("Stories",  tokens.get(143).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.DOT, tokens.get(144).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(145).Type);
        Assertions.assertEquals("p",  tokens.get(145).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(146).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(147).Type);
        Assertions.assertEquals("Circus",  tokens.get(147).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(148).Type);
        Assertions.assertEquals(Token.TokenTypes.DEDENT, tokens.get(149).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(150).Type);
        Assertions.assertEquals("Stories",  tokens.get(150).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.DOT, tokens.get(151).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(152).Type);
        Assertions.assertEquals("a",  tokens.get(152).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(153).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(154).Type);
        Assertions.assertEquals("Winnie",  tokens.get(154).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.YIELDS, tokens.get(155).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(156).Type);
        Assertions.assertEquals(Token.TokenTypes.INDENT, tokens.get(157).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(158).Type);
        Assertions.assertEquals("Stories",  tokens.get(158).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.DOT, tokens.get(159).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(160).Type);
        Assertions.assertEquals("f",  tokens.get(160).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(161).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(162).Type);
        Assertions.assertEquals("Zebra",  tokens.get(162).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(163).Type);
        Assertions.assertEquals(Token.TokenTypes.DEDENT, tokens.get(164).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(165).Type);
        Assertions.assertEquals("Stories",  tokens.get(165).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.DOT, tokens.get(166).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(167).Type);
        Assertions.assertEquals("f",  tokens.get(167).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(168).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(169).Type);
        Assertions.assertEquals("Bear",  tokens.get(169).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.YIELDS, tokens.get(170).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(171).Type);
        Assertions.assertEquals(Token.TokenTypes.INDENT, tokens.get(172).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(173).Type);
        Assertions.assertEquals("Stories",  tokens.get(173).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.DOT, tokens.get(174).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(175).Type);
        Assertions.assertEquals("p",  tokens.get(175).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NOTEQUAL, tokens.get(176).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(177).Type);
        Assertions.assertEquals("Spaceship",  tokens.get(177).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(178).Type);
        Assertions.assertEquals(Token.TokenTypes.DEDENT, tokens.get(179).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(180).Type);
    }

}
