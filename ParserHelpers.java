import java.util.ArrayList;
// import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParserHelpers {

    public static String GetSquareBracketData(String input) {
        char[] toRemove = new char[] { '[', ']' };
        return RemoveCharArrayFromString(input, toRemove);
    }

    public static String GetCurlyBracketData(String input) {
        char[] toRemove = new char[] { '{', '}' };
        return RemoveCharArrayFromString(input, toRemove);
    }

    public static String RemoveCharArrayFromString(String input, char[] toRemove) {
        for (char c : toRemove) {
            String cString = "" + c;
            input = input.replace(cString, "");
        }
        return input;
    }

    public static boolean IsStringBool(String inpuString) {
        return (inpuString.equals("true") || inpuString.equals("false"));
    }

    public static boolean StringToBool(String inpuString) {
        switch (inpuString) {
            case "true":
                return true;
            case "false":
                return false;
            default:
                System.err.println("StringToBool: Error for inpuString\nNo Such boolean value: " + inpuString);
                return false;
        }
    }

    public static boolean IsStringNull(String input) {
        return input.equals("null");
    }

    public static boolean IsStringNumeric(String input) {
        try {
            double res = Double.parseDouble(input);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public static enum JSONDataType {
        NULL,
        STRING,
        INT,
        DOUBLE,
        BOOL,
        ARRAY,
        OBJECT
    }

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

        if (input.contains("\""))
            return JSONDataType.STRING;

        if (IsStringNumeric(input))
            return input.contains(".") ? JSONDataType.DOUBLE : JSONDataType.INT;

        System.err.println("JSONDataType: Type Error. Cannot get type for input: " + input);
        return null;
    }

    public static boolean IsCollection(String input) {
        return GetDataType(input) == JSONDataType.ARRAY || GetDataType(input) == JSONDataType.OBJECT;
    }

    public static boolean isNested(String collection) {
        return collection.substring(1).contains("[") || collection.substring(1).contains("{");
    }

    public static JSONNode BuildJSONNode(String input) {
        switch (GetDataType(input)) {
            case DOUBLE:
                return BuildJSONDouble(input);
            case INT:
                return BuildJSONInt(input);
            case STRING:
                return BuildJSONtring(input);
            case BOOL:
                return BuildJSONBool(input);
            case NULL:
                return BuildJsonNull();
            case ARRAY:
                return BuildJSONArray(input);
            case OBJECT:
                return BuildJsonObject(input);
            default:
                System.err.println("BuildJSONNode: ERROR - No such type " + GetDataType(input));
                return null;
        }
    }

    public static JSONBoolean BuildJSONBool(String input) {
        return new JSONBoolean(StringToBool(input));
    }

    public static JSONInt BuildJSONInt(String input) {
        return new JSONInt(Integer.parseInt(input));
    }

    public static JSONDouble BuildJSONDouble(String input) {
        return new JSONDouble(Double.parseDouble(input));
    }

    public static JSONString BuildJSONtring(String input) {
        return new JSONString(input.replaceAll("\"", ""));
    }

    public static JSONNull BuildJsonNull() {
        return new JSONNull();
    }

    public static JSONObject BuildJsonObject(String input) {
        input = input.trim();
        if (input.startsWith("{") && input.endsWith("}")) {
            input = input.substring(1, input.length() - 1); // strip braces
        } else {
            System.err.println("Invalid object input: " + input);
            return null;
        }
    
        Map<String, JSONNode> map = new HashMap<>();
        List<String> pairs = SplitTopLevelElements(input);
    
        for (String pair : pairs) {
            int colonIndex = pair.indexOf(':');
            if (colonIndex == -1) {
                System.err.println("Malformed key-value pair: " + pair);
                continue;
            }
    
            String key = pair.substring(0, colonIndex).trim().replaceAll("^\"|\"$", "");
            String value = pair.substring(colonIndex + 1).trim();
    
            map.put(key, BuildJSONNode(value));
        }
    
        return new JSONObject(map);
    }
    

    public static JSONArray BuildJSONArray(String input) {
        String data = input.trim();
        if (data.startsWith("[") && data.endsWith("]")) {
            data = data.substring(1, data.length() - 1); // strip brackets
        } else {
            System.err.println("Invalid array input: " + input);
            return null;
        }

        ArrayList<JSONNode> jList = new ArrayList<>();
        List<String> elements = SplitTopLevelElements(data);

        for (String item : elements) {
            jList.add(BuildJSONNode(item));
        }

        return new JSONArray(jList);
    }

    public static List<String> SplitTopLevelElements(String input) {
        List<String> result = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        int bracketDepth = 0;
        int braceDepth = 0;
        boolean inQuotes = false;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            if (c == '\"') {
                inQuotes = !inQuotes; // toggle quote state
            } else if (!inQuotes) {
                if (c == '{')
                    braceDepth++;
                else if (c == '}')
                    braceDepth--;
                else if (c == '[')
                    bracketDepth++;
                else if (c == ']')
                    bracketDepth--;
            }

            if (c == ',' && !inQuotes && bracketDepth == 0 && braceDepth == 0) {
                result.add(current.toString().trim());
                current.setLength(0);
            } else {
                current.append(c);
            }
        }

        if (current.length() > 0) {
            result.add(current.toString().trim());
        }

        return result;
    }
}