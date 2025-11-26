import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Objects;
import AST.*;


public class Interpreter {
    // Type Definitions (Name -> [Choices])
    private HashMap<String, String[]> definitions = new HashMap<>();
    // Struct Definitions (Struct Name -> (Field Name -> Entry))
    private HashMap<String, HashMap<String, Entry>> structures = new HashMap<>();
    // Runtime State (Variable Name -> Array of Instances)
    private HashMap<String, VariableInstance[]> variables = new HashMap<>();

    public void Interpret(Nusha tree) throws Exception {
        // Process all definitions (choices and structs)
        for (Definition def : tree.definitions.definition) {
            if (def.choices.isPresent()) {
                // Store choice definition: definition name -> array of choice strings
                definitions.put(def.definitionName, def.choices.get().choice.toArray(new String[0]));
            } else if (def.nstruct.isPresent()) {
                HashMap<String, Entry> structEntries = new HashMap<>();
                for (Entry entry : def.nstruct.get().entry) {
                    structEntries.put(entry.name, entry);
                }
                structures.put(def.definitionName, structEntries);
            }
        }

        // Process all variable declarations
        for (Variable var : tree.variables.variable) {
            // Validate that variable type exists in definitions or is a built-in type
            if (!isValidType(var.type)) {
                throw new Exception("Invalid type '" + var.type + "' for variable '" + var.variableName);
            }
            // Fixed array size {default 1 if size not specified}
            int arraySize = 1;
            if (var.size.isPresent()) arraySize = Integer.parseInt(var.size.get());
            VariableInstance[] instances = new VariableInstance[arraySize];

            // Create each instance in the array
            for (int i = 0; i < arraySize; i++) {
                VariableInstance instance = new VariableInstance();
                instance.type = var.type;
                
                // Initialize to -1 for 'unassigned'
                if (definitions.containsKey(var.type)) {
                    instance.value = -1; 
                } 
                else if (structures.containsKey(var.type)) {
                    instance.structValues = new HashMap<>();
                    HashMap<String, Entry> structDef = structures.get(var.type);
                    for (String fieldName : structDef.keySet()) {
                        instance.structValues.put(fieldName, -1); 
                    }
                }
                instances[i] = instance;
            }
            variables.put(var.variableName, instances);
        }

        // Create a list of variables for the solver.
        List<SolverVariable> solverVars = new ArrayList<>();
        List<String> varNames = new ArrayList<>(variables.keySet());

        for (String varName : varNames) {
            VariableInstance[] arr = variables.get(varName);
            for (VariableInstance inst : arr) {
                if (inst.structValues != null) {
                    HashMap<String, Entry> structDef = structures.get(inst.type);
                    List<String> fields = new ArrayList<>(inst.structValues.keySet());
                    
                    for (String field : fields) {
                        Entry entry = structDef.get(field);
                        String fieldType = entry.type;
                        boolean isUnique = entry.unique; 
                        if (definitions.containsKey(fieldType)) {
                            int max = definitions.get(fieldType).length;
                            solverVars.add(new SolverVariable(inst, field, max, isUnique, varName));
                        }
                    }
                } else if (definitions.containsKey(inst.type)) {
                    int max = definitions.get(inst.type).length;
                    solverVars.add(new SolverVariable(inst, null, max, false, varName));
                }
            }
        }

        if (solve(0, solverVars, tree.rules)) {
            printSolution();
        } else {
            System.out.println("No solution found.");
        }
    }

    // Forward checking with candidate filtering
    private boolean solve(int index, List<SolverVariable> vars, Rules rules) throws Exception {
        // Initialize possible values for all variables
        List<List<Integer>> possibleValues = new ArrayList<>();
        for (SolverVariable sv : vars) {
            List<Integer> potentialValues = new ArrayList<>();
            for (int i = 0; i < sv.max; i++) {
                potentialValues.add(i);
            }
            possibleValues.add(potentialValues);
        }
        
        return assignValues(0, vars, possibleValues, rules);
    }
    
    // Assign values with forward checking
    private boolean assignValues(int index, List<SolverVariable> vars, List<List<Integer>> possibleValues, Rules rules) throws Exception {
        if (index == vars.size()) {
            return checkRules(rules);
        }
        
        SolverVariable sv = vars.get(index);
        List<Integer> currentPotentialValues = new ArrayList<>(possibleValues.get(index));
        
        for (int val : currentPotentialValues) {
            sv.setValue(val);
            
            // Check uniqueness constraint
            boolean valid = true;
            if (sv.isUnique) {
                int arraySize = variables.get(sv.varName).length;
                if (sv.max >= arraySize) {
                    for (int i = 0; i < index; i++) {
                        SolverVariable prev = vars.get(i);
                        if (prev.varName.equals(sv.varName) && 
                            Objects.equals(prev.fieldKey, sv.fieldKey) &&
                            prev.getValue() == val) {
                            valid = false;
                            break;
                        }
                    }
                }
            }
            
            if (!valid) continue;
            
            // Check rules with current assignment
            if (!checkRules(rules)) continue;
            
            // Forward check: update possible values for future variables
            List<List<Integer>> updatedPossibleValues = new ArrayList<>();
            for (int i = 0; i <= index; i++) {
                updatedPossibleValues.add(new ArrayList<>(possibleValues.get(i)));
            }
            
            boolean noValidOptions = false;
            for (int i = index + 1; i < vars.size(); i++) {
                List<Integer> futurePotentialValues = new ArrayList<>();
                SolverVariable futureVar = vars.get(i);
                
                // Check uniqueness constraints
                for (int futureVal : possibleValues.get(i)) {
                    boolean canUse = true;
                    
                    if (futureVar.isUnique) {
                        int arraySize = variables.get(futureVar.varName).length;
                        if (futureVar.max >= arraySize) {
                            // Check against already assigned variables
                            for (int j = 0; j <= index; j++) {
                                SolverVariable assigned = vars.get(j);
                                if (assigned.varName.equals(futureVar.varName) &&
                                    Objects.equals(assigned.fieldKey, futureVar.fieldKey) &&
                                    assigned.getValue() == futureVal) {
                                    canUse = false;
                                    break;
                                }
                            }
                        }
                    }
                    
                    if (canUse) {
                        futurePotentialValues.add(futureVal);
                    }
                }
                
                if (futurePotentialValues.isEmpty()) {
                    noValidOptions = true;
                    break;
                }
                updatedPossibleValues.add(futurePotentialValues);
            }
            
            if (noValidOptions) continue;
            
            if (assignValues(index + 1, vars, updatedPossibleValues, rules)) {
                return true;
            }
        }
        
        sv.setValue(-1);
        return false;
    }

    // Check all rules; return false for any violation.
    private boolean checkRules(Rules rules) throws Exception {
        for (Rule rule : rules.rule) {
            if (!runRule(rule)) return false; 
        }
        return true;
    }

    // Evaluate a single rule
    private boolean runRule(Rule r) throws Exception {
        if (r.thens.isEmpty()) {
            Boolean res = evaluate(r.expression, -1);
            // If evaluate returns null, it means variables are unassigned
            return res == null || res;
        } 
        else {
            // "Global" rules apply to every element in the array.
            String varName = r.expression.left.variableName;
            if (!variables.containsKey(varName)) return false; 
            
            int size = variables.get(varName).length;
            for (int i = 0; i < size; i++) {
                Boolean cond = evaluate(r.expression, i);
                
                if (cond != null && cond) {
                    for (Expression thenExpr : r.thens) {
                        Boolean res = evaluate(thenExpr, i);
                        if (res != null && !res) return false;
                    }
                }
            }
            return true;
        }
    }

    // Evaluate binary expression
    private Boolean evaluate(Expression e, int contextIndex) throws Exception {
        ResolvedValue left = resolve(e.left, contextIndex, null);
        
        // Use left side's type to disambiguate Enum literals on the right.
        ResolvedValue right = resolve(e.right, contextIndex, left.type); 

        // If either value is unassigned (-1), we cannot determine the result yet.
        if (left.value == -1 || right.value == -1) return null;

        boolean isEqual = (left.value == right.value);
        
        if (e.op.type == Op.OpTypes.Equal) return isEqual;
        else return !isEqual; 
    }

    // Resolve a variable reference or literal to an integer value and type.
    private ResolvedValue resolve(VariableReference vr, int contextIndex, String typeHint) throws Exception {
        if (variables.containsKey(vr.variableName)) {
            VariableInstance[] arr = variables.get(vr.variableName);
            int index = contextIndex;
            
            Optional<VRModifier> mod = vr.vrmodifier;
            
            // Handle explicit array access {ex [0]}
            if (mod.isPresent() && !mod.get().dot) {
                index = Integer.parseInt(mod.get().size);
                mod = mod.get().vrmodifier;
            }
            
            if (index == -1) index = 0; 
            
            VariableInstance inst = arr[index];
            
            // Handle struct field access (ex .name)
            if (mod.isPresent() && mod.get().dot) {
                String fieldName = mod.get().part.get();
                int val = inst.structValues.get(fieldName);
                String type = structures.get(inst.type).get(fieldName).type;
                return new ResolvedValue(val, type);
            } else {
                return new ResolvedValue(inst.value, inst.type);
            }
        } else {
            // If not a variable, treat as a Literal {ex "Red", "Alice"}
            if (typeHint == null) {
                for (String defName : definitions.keySet()) {
                    String[] choices = definitions.get(defName);
                    for (int i = 0; i < choices.length; i++) {
                        if (choices[i].equals(vr.variableName)) return new ResolvedValue(i, defName);
                    }
                }
                throw new Exception("Literal " + vr.variableName + " not found.");
            } else {
                // Fast search: use the type hint to look in the specific definition
                String[] choices = definitions.get(typeHint);
                if (choices == null) throw new Exception("Type " + typeHint + " not found.");
                for (int i = 0; i < choices.length; i++) {
                    if (choices[i].equals(vr.variableName)) return new ResolvedValue(i, typeHint);
                }
                throw new Exception("Literal " + vr.variableName + " not found in " + typeHint);
            }
        }
    }

    private void printSolution() {
        System.out.println("SUCCESS:");
        List<String> varNames = new ArrayList<>(variables.keySet());

        for (String varName : varNames) {
            VariableInstance[] instances = variables.get(varName);
            for (int i = 0; i < instances.length; i++) {
                VariableInstance instance = instances[i];
                if (instance.structValues != null) {
                    HashMap<String, Entry> structDef = structures.get(instance.type);
                    List<String> fieldNames = new ArrayList<>(instance.structValues.keySet());
                    
                    for (String fieldName : fieldNames) {
                        String fieldType = structDef.get(fieldName).type;
                        Integer val = instance.structValues.get(fieldName);
                        String strVal;
                        if (val == null || val == -1) strVal = "UNASSIGNED";
                        else if (definitions.containsKey(fieldType)) strVal = definitions.get(fieldType)[val];
                        else strVal = "?";
                        System.out.println(varName + "[" + i + "]." + fieldName + " = " + strVal);
                    }
                    if (instances.length > 1) System.out.println();
                } else {
                    String strVal;
                    if (instance.value == -1) strVal = "UNASSIGNED";
                    else strVal = definitions.get(instance.type)[instance.value];
                    System.out.println(varName + "[" + i + "] = " + strVal);
                    if (instances.length > 1) System.out.println();
                }
            }
        }
    }

    private class VariableInstance {
        public String type;
        public int value; 
        public HashMap<String, Integer> structValues; 
    }

    private class SolverVariable {
        VariableInstance instance; 
        String fieldKey;           
        int max;                   
        boolean isUnique;          
        String varName;            

        SolverVariable(VariableInstance i, String k, int m, boolean u, String vName) {
            instance = i; fieldKey = k; max = m; isUnique = u; varName = vName;
        }

        int getValue() {
            return (fieldKey == null) ? instance.value : instance.structValues.get(fieldKey);
        }

        void setValue(int v) {
            if (fieldKey == null) instance.value = v;
            else instance.structValues.put(fieldKey, v);
        }
    }

    private class ResolvedValue {
        int value;
        String type;
        ResolvedValue(int v, String t) { value = v; type = t; }
    }

    private boolean isValidType(String type) {
        return definitions.containsKey(type) || structures.containsKey(type) 
            || type.equals("String") || type.equals("Integer") 
            || type.equals("Float") || type.equals("Bool");
    }
}