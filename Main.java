public class Main {
    public static void main(String[] args) {
        // test out individual parsing
        String squareBrackets = "[SampleSquareBracketData]";
        String parsed = GetSquareBracketData(squareBrackets);
        System.err.println("bracket data: " + squareBrackets);
        System.err.println("parsed: " + parsed);
        System.err.println();
        String curlyBrackets = "{SampleCurlyBracketData}";
        parsed = GetCurlyBracketData(curlyBrackets);
        System.err.println("bracket data: " + curlyBrackets);
        System.err.println("parsed: " + parsed);
        System.err.println();
        String quotes = "\"SampleQuotesData\"";
        String numLiteral = "98";
        String boolLiteral = "true";

    }

    public static String GetSquareBracketData(String input) {
        return GetStringExcludeTwoChars(input, '[',  ']');
    }

    public static String GetCurlyBracketData(String input) {
        return GetStringExcludeTwoChars(input, '{', '}');
    }

    public static String GetStringExcludeTwoChars(String input, char first, char second) {
        StringBuilder rStringBuilder = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) != first && input.charAt(i) != second)
                rStringBuilder.append(input.charAt(i));
        }
        return rStringBuilder.toString();
    }
}