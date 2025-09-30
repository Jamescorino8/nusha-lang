import AST.*;
import AST.Token.TokenTypes;

import java.util.Optional;
import java.util.LinkedList;

public class NushaFall2025Parser {
    private TokenManager tokenManager;
    public NushaFall2025Parser() {
    }

    // Nusha = Definitions Variables Rules
    public Optional<Nusha> Nusha(LinkedList<Token> tokens) throws SyntaxErrorException {
        tokenManager = new TokenManager(tokens);
        Optional<Definitions> defs = DefinitionsNode();
        Optional<Variables> vars = VariablesNode();
        Optional<Rules> rules = RulesNode();

        Nusha n = new Nusha();
        n.definitions = defs.get();
        n.variables = vars.get();
        n.rules = rules.get();
        return Optional.of(n);
    }

    // Definitions = Definition*
    private Optional<Definitions> DefinitionsNode() throws SyntaxErrorException {
        return Optional.of(new Definitions());
    }

    // Definition = IDENTIFIER "=" (Choices | Struct ) NEWLINE
    private Optional<Definition> DefinitionNode() {
        return Optional.of(new Definition());
    }

    // Variables = Variable*
    private Optional<Variables> VariablesNode() throws SyntaxErrorException {
        Variables vars = new Variables();
        while (true) {
            // Skip new lines
            while (tokenManager.Peek(0).isPresent() && tokenManager.Peek(0).get().Type == TokenTypes.NEWLINE) {
                tokenManager.MatchAndRemove(TokenTypes.NEWLINE);
            }
            Optional<Variable> var = VariableNode();
            if (var.isPresent()) {
                vars.variable.add(var.get());
            } else {
                break;
            }
        }
        return Optional.of(vars);
    }

    // Variable = "var" IDENTIFIER ":" IDENTIFIER ( "[" NUMBER "]")? NEWLINE
    private Optional<Variable> VariableNode() throws SyntaxErrorException {

        // Return empty if var declaration is not present (kleene star case (0 or more))
        if (tokenManager.Peek(0).isEmpty() || tokenManager.Peek(0).get().Type != TokenTypes.VAR) {
            return Optional.empty();
        }
        Variable newVar = new Variable();
        tokenManager.MatchAndRemove(TokenTypes.VAR);
        
        Optional<Token> variableNameToken = tokenManager.MatchAndRemove(TokenTypes.IDENTIFIER);
        if (variableNameToken.isEmpty()) {
            throw new SyntaxErrorException("Variable statements must have an IDENTIFIER for the variable name.", tokenManager.getLine(), tokenManager.getColumn());
        }
        newVar.variableName = variableNameToken.get().Value.orElseThrow();

        if (tokenManager.MatchAndRemove(TokenTypes.COLON).isEmpty()) {
            throw new SyntaxErrorException("Variable statements must have COLON statement", tokenManager.getLine(), tokenManager.getColumn());
        }

        Optional<Token> typeToken = tokenManager.MatchAndRemove(TokenTypes.IDENTIFIER);
        if (typeToken.isEmpty()) {
            throw new SyntaxErrorException("Variable statements must have an IDENTIFIER for the type.", tokenManager.getLine(), tokenManager.getColumn());
        }
        newVar.type = typeToken.get().Value.orElseThrow();

        if (tokenManager.MatchAndRemove(TokenTypes.LEFTBRACE).isPresent()) {
            Optional<Token> numberToken = tokenManager.MatchAndRemove(TokenTypes.NUMBER);
            if (numberToken.isEmpty()) {
                throw new SyntaxErrorException("Variable array declarations must have a NUMBER inside the brackets.", tokenManager.getLine(), tokenManager.getColumn());
            }
            newVar.size = Optional.of(numberToken.get().Value.orElseThrow());

            if (tokenManager.MatchAndRemove(TokenTypes.RIGHTBRACE).isEmpty()) {
                throw new SyntaxErrorException("Variable array declarations must have a RIGHTBRACE.", tokenManager.getLine(), tokenManager.getColumn());
            }
        } else {
            newVar.size = Optional.empty();
        }

        tokenManager.RequireNewLine();
        return Optional.of(newVar);
    }

    // Rules = Rule*
    private Optional<Rules> RulesNode() {
        return Optional.of(new Rules());
    }

    // Expression = VariableReference Op VariableReference
    private Optional<Expression> ExpressionNode() {
        return Optional.of(new Expression());
    }

    // Op = "=" | "!="
    private Optional<Op> OpNode() {
        return Optional.of(new Op());
    }

    // VariableReference = IDENTIFIER  VRModifier?
    private Optional<VariableReference> VariableReferenceNode() {
        return Optional.of(new VariableReference());
    }

    // VRModifier = "." IDENTIFIER VRModifier? | ( "[" NUMBER "]") VRModifier?
    private Optional<VRModifier> VRModifierNode() {
        return Optional.of(new VRModifier());
    }
}