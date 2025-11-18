import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import AST.*;

public class Interpreter {
    // Stores definition name -> array of choice strings
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

        // Process all variable declarations
        for (Variable var : tree.variables.variable) {
            // Validate that variable type exists in definitions or is a built-in type
            if (!isValidType(var.type)) {
                throw new Exception("Invalid type '" + var.type + "' for variable '" + var.variableName + "'. Type not found in definitions.");
            }
            // Fixed array size {default 1 if size not specified}
            int arraySize = 1;
            if (var.size.isPresent()) arraySize = Integer.parseInt(var.size.get());
            VariableInstance[] instances = new VariableInstance[arraySize];

            // Create each instance in the array
            for (int i = 0; i < arraySize; i++) {
                VariableInstance instance = new VariableInstance();
                instance.name = var.variableName;
                instance.type = var.type;
                
                if (definitions.containsKey(var.type)) {
                    instance.value = 0; // 0 represents first choice.
                } else if (structures.containsKey(var.type)) {
                    instance.structValues = new HashMap<>();
                    HashMap<String, String> structDef = structures.get(var.type);
                    for (String fieldName : structDef.keySet()) {
                        instance.structValues.put(fieldName, 0);
                    }
                } else {
                    // Built-in types
                    instance.value = 0;
                }
                instances[i] = instance;
            }
            variables.put(var.variableName, instances);
        }

        // Print the initial state of all data structures:
        System.out.println("SUCCESS:");

        List<String> varNames = new ArrayList<>(variables.keySet());

        for (String varName : varNames) {
            VariableInstance[] instances = variables.get(varName);

            // Iterate over each instance in the array (e.g., Fleet[0], Fleet[1]...)
            for (int i = 0; i < instances.length; i++) {
                VariableInstance instance = instances[i];

                if (structures.containsKey(instance.type)) {
                    HashMap<String, String> structDef = structures.get(instance.type);

                    List<String> fieldNames = new ArrayList<>(structDef.keySet());

                    for (String fieldName : fieldNames) {
                        String fieldType = structDef.get(fieldName);
                        int valueIndex = instance.structValues.get(fieldName);
                        
                        String stringValue = "Error:Undefined";
                        if (definitions.containsKey(fieldType)) {
                            String[] choices = definitions.get(fieldType);
                            if (valueIndex >= 0 && valueIndex < choices.length) {
                                stringValue = choices[valueIndex];
                            }
                        } else {
                            stringValue = String.valueOf(valueIndex);
                        }
                        System.out.println(varName + "[" + i + "]." + fieldName + " = " + stringValue);
                    }
                    if (instances.length > 1) {
                            System.out.println();
                    }
                } else if (definitions.containsKey(instance.type)) {
                    int valueIndex = instance.value;
                    String stringValue = "Error:Undefined";
                    String[] choices = definitions.get(instance.type);
                    if (valueIndex >= 0 && valueIndex < choices.length) {
                        stringValue = choices[valueIndex];
                    }
                    
                    System.out.println(varName + "[" + i + "] = " + stringValue);
                    if (instances.length > 1) {
                            System.out.print("\n");
                    }
                }
            }
        }
    }

    // Class to represent the instance of a variable.
    private class VariableInstance {
        public String name;
        public String type;
        public int value; 
        public int size;
        public HashMap<String, Integer> structValues;
    }

    // Checks if a type is a built-in type or either a choice or struct.
    private boolean isValidType(String type) {
        // Check if it's a built-in type (String, Integer, Float, Bool)
        if (type.equals("String") || type.equals("Integer") || type.equals("Float") || type.equals("Bool")) {
            return true;
        }
        // Check if it's a custom type (Choice or Struct)
        if (definitions.containsKey(type) || structures.containsKey(type)) {
            return true;
        }
        return false;
    }
}