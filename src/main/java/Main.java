import AST.Nusha;
import AST.Token;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.Optional;

/**
 * Command-line entry point: reads a Nusha program, runs it through the
 * lexer, parser and interpreter, and prints the solution.
 *
 *   java -cp target/classes Main examples/stories.nsh
 */
public class Main {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("usage: nusha <program.nsh>");
            System.exit(2);
        }

        try {
            String source = Files.readString(Path.of(args[0]));

            LinkedList<Token> tokens = new Lexer(source).Lex();
            Optional<Nusha> tree = new NushaFall2025Parser().Nusha(tokens);

            if (tree.isEmpty()) {
                System.err.println("parse error: no program found in " + args[0]);
                System.exit(1);
            }

            new Interpreter().Interpret(tree.get());

        } catch (SyntaxErrorException e) {
            System.err.println("syntax error: " + e.getMessage());
            System.exit(1);
        } catch (Exception e) {
            System.err.println("error: " + e.getMessage());
            System.exit(1);
        }
    }
}
