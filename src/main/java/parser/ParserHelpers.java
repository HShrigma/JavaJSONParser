package src.main.java.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import src.main.java.model.*;

// Utility class for type detection, type conversions, and bracket handling
public class ParserHelpers {
    // Removes square brackets from input string
    public static String GetSquareBracketData(String input) {
        return RemoveCharArrayFromString(input, new char[] { '[', ']' });
    }

    // Removes curly brackets from input string
    public static String GetCurlyBracketData(String input) {
        return RemoveCharArrayFromString(input, new char[] { '{', '}' });
    }

    // Removes specified characters (like brackets) from input string
    public static String RemoveCharArrayFromString(String input, char[] toRemove) {
        for (char c : toRemove) {
            input = input.replace(String.valueOf(c), "");
        }
        return input;
    }

    // Checks if input is bool literal
    public static boolean IsStringBool(String inputString) {
        return "true".equals(inputString) || "false".equals(inputString);
    }

    // Converts string input "true"/"false" to boolean
    public static boolean StringToBool(String inputString) {
        switch (inputString) {
            case "true":
                return true;
            case "false":
                return false;
            default:
                System.err.println("StringToBool: Invalid boolean string: " + inputString);
                return false;
        }
    }

    // Checks if string equals "null"
    public static boolean IsStringNull(String input) {
        return "null".equals(input);
    }

    //// Checks if string is a valid number
    public static boolean IsStringNumeric(String input) {
        try {
            Double.parseDouble(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public enum JSONDataType {
        NULL,
        STRING,
        INT,
        DOUBLE,
        BOOL,
        ARRAY,
        OBJECT
    }

    // Determines the JSON data type of the given input string
    public static JSONDataType GetDataType(String input) {
        input = input.trim();

        if (IsStringBool(input))
            return JSONDataType.BOOL;
        if (IsStringNull(input))
            return JSONDataType.NULL;
        if (input.startsWith("{") && input.endsWith("}"))
            return JSONDataType.OBJECT;
        if (input.startsWith("[") && input.endsWith("]"))
            return JSONDataType.ARRAY;
        if (input.startsWith("\"") && input.endsWith("\""))
            return JSONDataType.STRING;
        if (IsStringNumeric(input))
            return input.contains(".") ? JSONDataType.DOUBLE : JSONDataType.INT;

        System.err.println("JSONDataType: Could not determine type of input: " + input);
        return null;
    }

    // Determines whether input is an array or object
    public static boolean IsCollection(String input) {
        JSONDataType type = GetDataType(input);
        return type == JSONDataType.ARRAY || type == JSONDataType.OBJECT;
    }
    // Checks if the input has nested structures
    public static boolean IsNested(String collection) {
        return collection.substring(1).contains("[") || collection.substring(1).contains("{");
    }
}