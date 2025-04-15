package src.main.java.parser;

import java.util.ArrayList;
import java.util.List;

// Utilities for validating and processing JSON string structure
public class JSONValidator {
    // Splits a top-level JSON array/object into individual elements/pairs
    // Respects nesting and quoted strings
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
    // Decodes escape sequences (e.g., \n, \t, unicode) 
    // from JSON strings
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
