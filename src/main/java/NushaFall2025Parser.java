import AST.*;
import AST.Op.OpTypes;
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
        Definitions defs = new Definitions();
        while(true) {
            // Skip new lines
            while (tokenManager.Peek(0).isPresent() && tokenManager.Peek(0).get().Type == TokenTypes.NEWLINE) {
                tokenManager.MatchAndRemove(TokenTypes.NEWLINE);
            }
            Optional<Definition> def = DefinitionNode();
            if (def.isPresent()) {
                defs.definition.add(def.get());
            } else {
                break;
            }
        }
        return Optional.of(defs);
    }

    // Definition = IDENTIFIER "=" (Choices | NStruct ) NEWLINE
    private Optional<Definition> DefinitionNode() throws SyntaxErrorException {
        if (tokenManager.Peek(0).isEmpty() || tokenManager.Peek(0).get().Type != TokenTypes.IDENTIFIER)
            return Optional.empty();
        
        Definition def = new Definition();
        def.definitionName = tokenManager.MatchAndRemove(TokenTypes.IDENTIFIER).get().Value.orElse("");
        tokenManager.MatchAndRemove(TokenTypes.EQUAL);

        // Decide between Choices or NStruct by next token
        if (tokenManager.Peek(0).isPresent() && tokenManager.Peek(0).get().Type == TokenTypes.LEFTCURLY) {
            def.choices = ChoicesNode();
            def.nstruct = Optional.empty();
        } else if (tokenManager.Peek(0).isPresent() && tokenManager.Peek(0).get().Type == TokenTypes.LEFTBRACE) {
            def.nstruct = NStructNode();
            def.choices = Optional.empty();
        } else {
            throw new SyntaxErrorException("Definition must be followed by '{' (choices) or '[' (struct)", tokenManager.getLine(), tokenManager.getColumn());
        }
        tokenManager.RequireNewLine();
        return Optional.of(def);
    }

    // Choices = '{' IDENTIFIER (',' IDENTIFIER )* '}'
    public Optional<Choices> ChoicesNode() throws SyntaxErrorException {
        if (tokenManager.MatchAndRemove(TokenTypes.LEFTCURLY).isEmpty()) {
            throw new SyntaxErrorException("Choices must start with '{'", tokenManager.getLine(), tokenManager.getColumn());
        }
        Choices choices = new Choices();
        // At least one identifier
        Optional<Token> id = tokenManager.MatchAndRemove(TokenTypes.IDENTIFIER);
        if (id.isEmpty()) throw new SyntaxErrorException("Choices must have at least one identifier", tokenManager.getLine(), tokenManager.getColumn());
        choices.choice.add(id.get().Value.orElse(""));
        while (tokenManager.MatchAndRemove(TokenTypes.COMMA).isPresent()) {
            Optional<Token> next = tokenManager.MatchAndRemove(TokenTypes.IDENTIFIER);
            if (next.isEmpty()) throw new SyntaxErrorException("Comma in choices must be followed by identifier", tokenManager.getLine(), tokenManager.getColumn());
            choices.choice.add(next.get().Value.orElse(""));
        }
        if (tokenManager.MatchAndRemove(TokenTypes.RIGHTCURLY).isEmpty()) {
            throw new SyntaxErrorException("Choices must end with '}'", tokenManager.getLine(), tokenManager.getColumn());
        }
        return Optional.of(choices);
    }

    // NStruct = '[' Entry (',' Entry)* ']'
    public Optional<NStruct> NStructNode() throws SyntaxErrorException {
        if (tokenManager.MatchAndRemove(TokenTypes.LEFTBRACE).isEmpty()) {
            throw new SyntaxErrorException("Struct must start with '['", tokenManager.getLine(), tokenManager.getColumn());
        }
        NStruct nstruct = new NStruct();
        nstruct.entry.add(EntryNode());
        while (tokenManager.MatchAndRemove(TokenTypes.COMMA).isPresent()) {
            nstruct.entry.add(EntryNode());
        }
        if (tokenManager.MatchAndRemove(TokenTypes.RIGHTBRACE).isEmpty()) {
            throw new SyntaxErrorException("Struct must end with ']'", tokenManager.getLine(), tokenManager.getColumn());
        }
        return Optional.of(nstruct);
    }

    private Entry EntryNode() throws SyntaxErrorException {
        Entry e = new Entry();
        e.unique = false;
        if (tokenManager.MatchAndRemove(TokenTypes.UNIQUE).isPresent()) {
            e.unique = true;
        }
        Optional<Token> typeTok = tokenManager.MatchAndRemove(TokenTypes.IDENTIFIER);
        if (typeTok.isEmpty()) throw new SyntaxErrorException("Entry requires a type identifier", tokenManager.getLine(), tokenManager.getColumn());
        e.type = typeTok.get().Value.orElse("");
        Optional<Token> nameTok = tokenManager.MatchAndRemove(TokenTypes.IDENTIFIER);
        if (nameTok.isEmpty()) throw new SyntaxErrorException("Entry requires a name identifier", tokenManager.getLine(), tokenManager.getColumn());
        e.name = nameTok.get().Value.orElse("");
        return e;
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
    private Optional<Rules> RulesNode() throws SyntaxErrorException {
        Rules rules = new Rules();
        while (true) {
            // Skip blank lines
            while (tokenManager.Peek(0).isPresent() && tokenManager.Peek(0).get().Type == TokenTypes.NEWLINE) {
                tokenManager.MatchAndRemove(TokenTypes.NEWLINE);
            }
            Optional<Rule> r = RuleNode();
            if (r.isPresent()) rules.rule.add(r.get()); else break;
        }
        return Optional.of(rules);
    }

    // Rule = Expression ("=>" NEWLINE INDENT Expression* DEDENT)?
    private Optional<Rule> RuleNode() throws SyntaxErrorException {
        // A rule must start with IDENTIFIER
        if (tokenManager.Peek(0).isEmpty() || tokenManager.Peek(0).get().Type != TokenTypes.IDENTIFIER) return Optional.empty();

        Optional<Expression> exprOpt = ExpressionNode();
        if (exprOpt.isEmpty()) return Optional.empty();
        Rule rule = new Rule();
        rule.expression = exprOpt.get();

        // Expect NEWLINE or YIELDS
        if (tokenManager.MatchAndRemove(TokenTypes.YIELDS).isPresent()) {
            // after YIELDS must be NEWLINE INDENT then one or more expressions each ending NEWLINE then DEDENT
            if (tokenManager.MatchAndRemove(TokenTypes.NEWLINE).isEmpty()) throw new SyntaxErrorException("Expected newline after '=>'", tokenManager.getLine(), tokenManager.getColumn());
            if (tokenManager.MatchAndRemove(TokenTypes.INDENT).isEmpty()) throw new SyntaxErrorException("Expected indent after rule yields", tokenManager.getLine(), tokenManager.getColumn());
            while (true) {
                // Stop if DEDENT ahead
                if (tokenManager.Peek(0).isPresent() && tokenManager.Peek(0).get().Type == TokenTypes.DEDENT) break;
                // Skip blank lines inside block
                while (tokenManager.Peek(0).isPresent() && tokenManager.Peek(0).get().Type == TokenTypes.NEWLINE) {
                    tokenManager.MatchAndRemove(TokenTypes.NEWLINE);
                }
                if (tokenManager.Peek(0).isPresent() && tokenManager.Peek(0).get().Type == TokenTypes.DEDENT) break;
                Optional<Expression> thenOpt = ExpressionNode();
                if (thenOpt.isEmpty()) break; // nothing more
                rule.thens.add(thenOpt.get());
                if (tokenManager.MatchAndRemove(TokenTypes.NEWLINE).isEmpty()) throw new SyntaxErrorException("Expected newline after then-expression", tokenManager.getLine(), tokenManager.getColumn());
            }
            if (tokenManager.MatchAndRemove(TokenTypes.DEDENT).isEmpty()) throw new SyntaxErrorException("Expected DEDENT after rule block", tokenManager.getLine(), tokenManager.getColumn());
            // Optional trailing newline already consumed in loop; caller will skip blanks
        } else {
            // No yields: require newline
            if (tokenManager.MatchAndRemove(TokenTypes.NEWLINE).isEmpty()) throw new SyntaxErrorException("Rule must end with newline", tokenManager.getLine(), tokenManager.getColumn());
        }
        return Optional.of(rule);
    }

    // Expression = VariableReference Op VariableReference
    private Optional<Expression> ExpressionNode() throws SyntaxErrorException {
        // Left variable reference must exist
        Optional<VariableReference> left = VariableReferenceNode();
        if (left.isEmpty()) return Optional.empty();
        Op op = OpNode().orElseThrow();
        Optional<VariableReference> right = VariableReferenceNode();
        if (right.isEmpty()) throw new SyntaxErrorException("Expression requires right variable reference", tokenManager.getLine(), tokenManager.getColumn());
        Expression e = new Expression();
        e.left = left.get();
        e.op = op;
        e.right = right.get();
        return Optional.of(e);
    }

    // VariableReference = IDENTIFIER  VRModifier?
    private Optional<VariableReference> VariableReferenceNode() throws SyntaxErrorException {
        if (tokenManager.Peek(0).isEmpty() || tokenManager.Peek(0).get().Type != TokenTypes.IDENTIFIER) return Optional.empty();
        VariableReference vr = new VariableReference();
        vr.variableName = tokenManager.MatchAndRemove(TokenTypes.IDENTIFIER).get().Value.orElse("");
        Optional<VRModifier> mod = VRModifierNode();
        vr.vrmodifier = mod;
        return Optional.of(vr);
    }

    // Op = "=" | "!="
    private Optional<Op> OpNode() throws SyntaxErrorException {
        if (tokenManager.Peek(0).isEmpty()) return Optional.empty();
        Token t = tokenManager.Peek(0).get();
        Op op = new Op();
        if (t.Type == TokenTypes.EQUAL) {
            tokenManager.MatchAndRemove(TokenTypes.EQUAL);
            op.type = OpTypes.Equal;
        } else if (t.Type == TokenTypes.NOTEQUAL) {
            tokenManager.MatchAndRemove(TokenTypes.NOTEQUAL);
            op.type = OpTypes.NotEqual;
        } else {
            throw new SyntaxErrorException("Expected '=' or '!=' in expression", tokenManager.getLine(), tokenManager.getColumn());
        }
        return Optional.of(op);
    }

    // VRModifier = "." IDENTIFIER VRModifier? | ( "[" NUMBER "]") VRModifier?
    private Optional<VRModifier> VRModifierNode() throws SyntaxErrorException {
        // Parse a chain of modifiers. Return empty if none.
        if (tokenManager.Peek(0).isEmpty()) return Optional.empty();
        if (tokenManager.Peek(0).get().Type != TokenTypes.DOT && tokenManager.Peek(0).get().Type != TokenTypes.LEFTBRACE)
            return Optional.empty();

        VRModifier head = null;
        VRModifier tail = null;
        while (tokenManager.Peek(0).isPresent()) {
            Token.TokenTypes tt = tokenManager.Peek(0).get().Type;
            if (tt != TokenTypes.DOT && tt != TokenTypes.LEFTBRACE) break;
            VRModifier part = new VRModifier();
            if (tt == TokenTypes.DOT) {
                tokenManager.MatchAndRemove(TokenTypes.DOT);
                part.dot = true;
                Optional<Token> id = tokenManager.MatchAndRemove(TokenTypes.IDENTIFIER);
                if (id.isEmpty()) throw new SyntaxErrorException("Dot modifier requires identifier", tokenManager.getLine(), tokenManager.getColumn());
                part.part = Optional.of(id.get().Value.orElse(""));
                part.size = null;
            } else { // '[' NUMBER ']'
                tokenManager.MatchAndRemove(TokenTypes.LEFTBRACE);
                part.dot = false;
                Optional<Token> num = tokenManager.MatchAndRemove(TokenTypes.NUMBER);
                if (num.isEmpty()) throw new SyntaxErrorException("Array index requires number", tokenManager.getLine(), tokenManager.getColumn());
                part.size = num.get().Value.orElse("");
                part.part = Optional.empty();
                if (tokenManager.MatchAndRemove(TokenTypes.RIGHTBRACE).isEmpty()) throw new SyntaxErrorException("Missing closing ']' in index", tokenManager.getLine(), tokenManager.getColumn());
            }
            part.vrmodifier = Optional.empty();
            if (head == null) {
                head = part;
                tail = part;
            } else {
                tail.vrmodifier = Optional.of(part);
                tail = part;
            }
        }
        return Optional.ofNullable(head);
    }
}