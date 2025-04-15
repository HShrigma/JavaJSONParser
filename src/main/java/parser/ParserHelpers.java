package src.main.java.parser;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import src.main.java.model.JSONArray;
import src.main.java.model.JSONBoolean;
import src.main.java.model.JSONDouble;
import src.main.java.model.JSONInt;
import src.main.java.model.JSONNode;
import src.main.java.model.JSONNull;
import src.main.java.model.JSONObject;
import src.main.java.model.JSONString;

public class ParserHelpers {

    public static String GetSquareBracketData(String input) {
        return RemoveCharArrayFromString(input, new char[] { '[', ']' });
    }

    public static String GetCurlyBracketData(String input) {
        return RemoveCharArrayFromString(input, new char[] { '{', '}' });
    }

    public static String RemoveCharArrayFromString(String input, char[] toRemove) {
        for (char c : toRemove) {
            input = input.replace(String.valueOf(c), "");
        }
        return input;
    }

    public static boolean IsStringBool(String inputString) {
        return "true".equals(inputString) || "false".equals(inputString);
    }

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

    public static boolean IsStringNull(String input) {
        return "null".equals(input);
    }

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

    public static boolean IsCollection(String input) {
        JSONDataType type = GetDataType(input);
        return type == JSONDataType.ARRAY || type == JSONDataType.OBJECT;
    }

    public static boolean IsNested(String collection) {
        return collection.substring(1).contains("[") || collection.substring(1).contains("{");
    }

    public static JSONNode BuildJSONNode(String input) {
        JSONDataType dataType = GetDataType(input);

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

    public static JSONBoolean BuildJSONBool(String input) {
        return new JSONBoolean(StringToBool(input));
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
        content = DecodeEscapes(content);

        // Return a properly built JSON string
        return new JSONString(content);
    }

    public static JSONNull BuildJSONNull() {
        return new JSONNull();
    }

    public static JSONObject BuildJSONObject(String input) {
        input = input.trim();
        if (!input.startsWith("{") || !input.endsWith("}")) {
            System.err.println("Malformed object (unmatched brackets): " + input);
            return null;
        }

        input = input.substring(1, input.length() - 1); // Remove the outer braces
        Map<String, JSONNode> map = new HashMap<>();
        List<String> pairs = SplitTopLevelElements(input);

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

    public static JSONArray BuildJSONArray(String input) {
        input = input.trim();
        if (!input.startsWith("[") || !input.endsWith("]")) {
            System.err.println("Malformed array (unmatched brackets): " + input);
            return null;
        }

        input = input.substring(1, input.length() - 1); // Remove the outer brackets
        ArrayList<JSONNode> list = new ArrayList<>();
        List<String> elements = SplitTopLevelElements(input);

        for (String item : elements) {
            list.add(BuildJSONNode(item));
        }

        return new JSONArray(list);
    }

    public static List<String> SplitTopLevelElements(String input) {
        List<String> result = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        int bracketDepth = 0;
        int braceDepth = 0;
        boolean inQuotes = false;
        boolean isEscaped = false;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            if (c == '\\' && !isEscaped) {
                isEscaped = true;
                current.append(c);
                continue;
            }

            if (c == '"' && !isEscaped) {
                inQuotes = !inQuotes;
            }

            isEscaped = false;

            if (c == ',' && !inQuotes && bracketDepth == 0 && braceDepth == 0) {
                String trimmed = current.toString().trim();
                if (!trimmed.isEmpty()) {
                    result.add(trimmed);
                }
                current.setLength(0); // Reset for next element
            } else {
                if (!inQuotes) {
                    if (c == '[')
                        bracketDepth++;
                    else if (c == ']')
                        bracketDepth--;
                    else if (c == '{')
                        braceDepth++;
                    else if (c == '}')
                        braceDepth--;
                }
                current.append(c);
            }
        }

        // Add the final element, ensuring it's not empty
        String trimmed = current.toString().trim();
        if (!trimmed.isEmpty()) {
            result.add(trimmed);
        }

        return result;
    }

    public static String DecodeEscapes(String s) {
        StringBuilder sb = new StringBuilder();
        boolean isEscaping = false;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (isEscaping) {
                switch (c) {
                    case 'n':
                        sb.append('\n');
                        break;
                    case 't':
                        sb.append('\t');
                        break;
                    case 'b':
                        sb.append('\b');
                        break;
                    case 'r':
                        sb.append('\r');
                        break;
                    case 'f':
                        sb.append('\f');
                        break;
                    case '"':
                        sb.append('\"');
                        break;
                    case '\\':
                        sb.append('\\');
                        break;
                    case '/':
                        sb.append('/');
                        break;
                    case 'u':
                        if (i + 4 < s.length()) {
                            String hex = s.substring(i + 1, i + 5);
                            try {
                                sb.append((char) Integer.parseInt(hex, 16));
                                i += 4;
                            } catch (NumberFormatException e) {
                                sb.append("\\u").append(hex);
                                i += 4;
                            }
                        } else {
                            sb.append("\\u");
                        }
                        break;
                    default:
                        sb.append(c); // unknown escape — keep as is
                        break;
                }
                isEscaping = false;
            } else {
                if (c == '\\') {
                    isEscaping = true;
                } else {
                    sb.append(c);
                }
            }
        }

        return sb.toString();
    }
}
