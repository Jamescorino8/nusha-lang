# Nusha

A compiler and interpreter for **Nusha**, a domain-specific language for expressing and solving constraint satisfaction problems (CSPs). Written in Java as part of ICSI311 (Programming Languages).

## Overview

Nusha programs describe a set of typed variables and logical constraints. The interpreter finds a variable assignment that satisfies all constraints, or reports that no solution exists. It is well-suited for logic puzzles (e.g., "who lives in the red house?", "which ship is at which grid position?").

The pipeline is:

```
Source text → Lexer → Token list → Parser → AST → Interpreter/Solver → Solution
```

![A logic puzzle in Nusha, and the unique assignment the solver derives from it](docs/demo.png)

## Language Syntax

A Nusha program has three ordered sections: **Definitions**, **Variables**, and **Rules**.

### Definitions

Define enumeration types or record (struct) types.

```
Color = {Red, Blue, Green, Yellow}

Person = [unique Color favoriteColor, unique Pet pet]
```

- `{ ... }` — a **Choices** type (enumeration of named values)
- `[ ... ]` — an **NStruct** type (record with typed fields)
- `unique` on a struct field means no two elements of the same array may share that field's value (all-different constraint)

### Variables

Declare variables with a type and an optional array size.

```
var myColor : Color
var People : Person[4]
```

### Rules

Constraints are binary expressions. Two forms:

**Simple constraint** — must always hold:
```
People[0].favoriteColor = Red
```

**Conditional constraint** — if the left condition holds for some array element, the then-clause must also hold for that element:
```
People.favoriteColor = Blue =>
    People.pet = Cat
```

**Operators:** `=` (equal), `!=` (not equal)

**Variable references** support dot access and array indexing:
- `varName` — a scalar variable
- `varName[i]` — element `i` of an array
- `varName.field` — field access on a scalar struct
- `varName[i].field` — field access on array element `i`

### Complete example

```
Author = {Alice, Bob, Carol, David}
Pet    = {Dog, Cat, Bird, Fish}
House  = {Red, Blue, Green, Yellow}

Story = [unique Author a, unique Pet p, unique House h]
var Stories : Story[4]

Stories[0].a = Alice
Stories[1].a = Bob
Stories[2].a = Carol
Stories[3].a = David

Stories.a = Alice =>
    Stories.h != Red
Stories.h = Blue =>
    Stories.p = Cat
Stories.a = David =>
    Stories.p = Dog
Stories.a = Carol =>
    Stories.h = Green
Stories.a = Bob =>
    Stories.p != Fish
    Stories.h = Red
```

## Architecture

| Class | Responsibility |
|---|---|
| `TextManager` | Character-level cursor over the source string |
| `Lexer` | Converts source text to a `LinkedList<Token>`; handles indentation (INDENT/DEDENT) |
| `TokenManager` | Cursor over the token list used by the parser |
| `NushaFall2025Parser` | Recursive-descent parser; produces a `Nusha` AST |
| `Interpreter` | Walks the AST, initialises domains, and runs the solver |
| `AST/` | Plain data classes — `Nusha`, `Definitions`, `Variables`, `Rules`, `Expression`, etc. |

### Solver

The interpreter uses **backtracking search with forward checking**. At each step:

1. Assign a candidate value to the next unresolved variable.
2. Enforce `unique` constraints across array elements.
3. Check all current rules (short-circuit on violation).
4. Prune candidate sets for future variables based on uniqueness.
5. Recurse; backtrack on failure.

## Building and Testing

Requires Java 17 and Maven.

```bash
# From the Nusha/ directory
mvn compile          # compile sources
mvn test             # run all JUnit 5 tests
mvn package          # build target/my-project-1.0-SNAPSHOT.jar
```

### Test suites

| File | What it covers |
|---|---|
| `Lexer1Test` | Identifiers, keywords (`var`, `unique`), numbers |
| `Lexer2Tests` | Choices, structs, indentation, operators |
| `Parser1Tests` | Variable declarations |
| `Parser2Tests` | Full programs including definitions, variables, and rules |
| `InterpreterTests` | End-to-end solving: Battleship, Birthday, Cafe, Dating, Dish, Friends, Pets, Stationery |

## Project Structure

```
Nusha/
├── pom.xml
└── src/
    ├── main/java/
    │   ├── AST/          # AST node classes
    │   ├── Lexer.java
    │   ├── TextManager.java
    │   ├── TokenManager.java
    │   ├── NushaFall2025Parser.java
    │   ├── Interpreter.java
    │   └── SyntaxErrorException.java
    └── test/java/
        ├── Lexer1Test.java
        ├── Lexer2Tests.java
        ├── Parser1Tests.java
        ├── Parser2Tests.java
        └── InterpreterTests.java
```
