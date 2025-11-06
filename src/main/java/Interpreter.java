import java.util.HashMap;

import AST.*;

public class Interpreter {
    // Stores definition name -> array of choice strings (e.g., Boy -> ["Tom", "Fred", "Barney", "George"])
    private HashMap<String, String[]> definitions = new HashMap<>();
    // Stores struct definition name -> HashMap of (field name -> field type)
    private HashMap<String, HashMap<String, String>> structures = new HashMap<>();
    // Stores variable name -> array of VariableInstances
    private HashMap<String, VariableInstance[]> variables = new HashMap<>();

    public void Interpret(Nusha tree) throws Exception {
        // Process all definitions (choices and structs)
        for (Definition def : tree.definitions.definition) {
            if (def.choices.isPresent()) {
                // Store choice definition: definition name -> array of choice strings
                definitions.put(def.definitionName, def.choices.get().choice.toArray(new String[0]));
            } else if (def.nstruct.isPresent()) {
                // Store struct definition: struct name -> HashMap of (field name -> field type)
                HashMap<String, String> structEntries = new HashMap<>();
                for (Entry entry : def.nstruct.get().entry) {
                    structEntries.put(entry.name, entry.type);
                }
                structures.put(def.definitionName, structEntries);
            }
        }
        for (Variable var : tree.variables.variable) {
            // Validate that variable type exists in definitions or is a built-in type
            if (!isValidType(var.type)) {
                throw new Exception("Invalid type '" + var.type + "' for variable '" + var.variableName + "'. Type not found in definitions.");
            }
            int arraySize = 1;
            if (var.size.isPresent()) arraySize = Integer.parseInt(var.size.get());
            
            VariableInstance[] instances = new VariableInstance[arraySize];

            for (int i = 0; i < arraySize; i++) {
                VariableInstance instance = new VariableInstance();
                instance.name = var.variableName;
                instance.type = var.type;
                
                if (definitions.containsKey(var.type)) {
                    // Choice type: value is index into definitions array (0 = first choice, 1 = second choice, etc.)
                    instance.value = 0;
                } else if (structures.containsKey(var.type)) {
                    // Struct type: create HashMap to store field values as integers
                    instance.structValues = new HashMap<>();
                    // Initialize all struct fields with 0 (index into their respective definition arrays)
                    HashMap<String, String> structDef = structures.get(var.type);
                    for (String fieldName : structDef.keySet()) {
                        instance.structValues.put(fieldName, 0);
                    }
                } else {
                    // Built-in types (Integer, Float, Bool, String) - initialize to 0
                    instance.value = 0;
                }
                instances[i] = instance;
            }
            variables.put(var.variableName, instances);
        }
    }

    private class VariableInstance {
        public String name;
        public String type;
        public int value;
        public int size;
        public HashMap<String, Integer> structValues;
    }

    private boolean isValidType(String type) {
        // Check if it's a built-in type (String, Integer, Float, Bool)
        if (type.equals("String") || type.equals("Integer") || type.equals("Float") || type.equals("Bool")) {
            return true;
        }
        // Check if it's a custom type (defined as Choice or Struct)
        if (definitions.containsKey(type) || structures.containsKey(type)) {
            return true;
        }
        return false;
    }
}