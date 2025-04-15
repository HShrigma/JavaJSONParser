package src.main.java.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import src.main.java.model.*;
import src.main.java.parser.JSONValidator;
import src.main.java.parser.ParserHelpers;
import src.main.java.parser.ParserHelpers.JSONDataType;

// Responsible for constructing typed JSONNode instances from strings
public class JSONBuilder {
    // Main dispatcher that routes input to the appropriate build method
    public static JSONNode BuildJSONNode(String input) {
        JSONDataType dataType = ParserHelpers.GetDataType(input);

        if (dataType == null) {
            System.err.println("BuildJSONNode: Invalid or malformed JSON input: " + input);
            return new JSONNull();
        }

        switch (dataType) {
            case DOUBLE:
                return BuildJSONDouble(input);
            case INT:
                return BuildJSONInt(input);
            case STRING:
                return BuildJSONString(input);
            case BOOL:
                return BuildJSONBool(input);
            case NULL:
                return BuildJSONNull();
            case ARRAY:
                return BuildJSONArray(input);
            case OBJECT:
                return BuildJSONObject(input);
            default:
                System.err.println("BuildJSONNode: Unknown type for input: " + input);
                return null;
        }
    }

    // Each of the following build methods creates a specific JSON node type:
    public static JSONBoolean BuildJSONBool(String input) {
        return new JSONBoolean(ParserHelpers.StringToBool(input));
    }

    public static JSONInt BuildJSONInt(String input) {
        return new JSONInt(Integer.parseInt(input));
    }

    public static JSONDouble BuildJSONDouble(String input) {
        return new JSONDouble(Double.parseDouble(input));
    }

    public static JSONString BuildJSONString(String input) {
        String content = input.trim();

        // Check for unterminated string
        if (!content.startsWith("\"") || !content.endsWith("\"")) {
            System.err.println("Malformed string (unterminated): " + input);
            return null;
        }

        // Remove the surrounding quotes and decode escape sequences
        content = content.substring(1, content.length() - 1); // Remove quotes
        content = JSONValidator.DecodeEscapes(content);

        // Return a properly built JSON string
        return new JSONString(content);
    }

    public static JSONNull BuildJSONNull() {
        return new JSONNull();
    }

    // Parses key-value pairs from input and builds nested JSONNodes
    public static JSONObject BuildJSONObject(String input) {
        input = input.trim();
        if (!input.startsWith("{") || !input.endsWith("}")) {
            System.err.println("Malformed object (unmatched brackets): " + input);
            return null;
        }

        input = input.substring(1, input.length() - 1); // Remove the outer braces
        Map<String, JSONNode> map = new HashMap<>();
        List<String> pairs = JSONValidator.SplitTopLevelElements(input);

        for (String pair : pairs) {
            int colonIndex = pair.indexOf(':');
            if (colonIndex == -1) {
                System.err.println("Malformed object entry: " + pair);
                continue;
            }

            String key = pair.substring(0, colonIndex).trim().replaceAll("^\"|\"$", "");
            String value = pair.substring(colonIndex + 1).trim();

            map.put(key, BuildJSONNode(value));
        }

        return new JSONObject(map);
    }

    // Parses each array element and builds nested JSONNodes
    public static JSONArray BuildJSONArray(String input) {
        input = input.trim();
        if (!input.startsWith("[") || !input.endsWith("]")) {
            System.err.println("Malformed array (unmatched brackets): " + input);
            return null;
        }

        input = input.substring(1, input.length() - 1); // Remove the outer brackets
        ArrayList<JSONNode> list = new ArrayList<>();
        List<String> elements = JSONValidator.SplitTopLevelElements(input);

        for (String item : elements) {
            list.add(BuildJSONNode(item));
        }

        return new JSONArray(list);
    }
}