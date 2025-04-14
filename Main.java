public class Main {
    public static void main(String[] args) {
        // test out individual parsing
        String squareBrackets = "[SampleSquareBracketData]";
        String parsed = GetSquareBracketData(squareBrackets);
        System.out.println("bracket data: " + squareBrackets);
        System.out.println("bracket data Len: " + squareBrackets.length());
        System.out.println("parsed: " + parsed);
        System.out.println("parsed Len: " + parsed.length());
        System.out.println();
        String curlyBrackets = "{SampleCurlyBracketData}";
        parsed = GetCurlyBracketData(curlyBrackets);
        System.out.println("bracket data: " + curlyBrackets);
        System.out.println("parsed: " + parsed);
        System.out.println();
        String quotes = "\"SampleQuotesData\"";
        parsed = RemoveCharArrayFromString(quotes, new char[] { '\"' });
        System.out.println("quotes data: " + quotes);
        System.out.println("parsed: " + parsed);
        System.out.println();
        String numLiteral = "98";
        System.out.println("num data: " + numLiteral);
        System.out.println("parsed: " + Integer.parseInt(numLiteral));
        System.out.println();
        String boolLiteral = "true";
        if (IsStringBool(boolLiteral)) {
            System.out.println("bool data: " + boolLiteral);
            System.out.println("parsed: " + StringToBool(boolLiteral));
            System.out.println();
        }
    }

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
        return (inpuString == "true" || inpuString == "false");
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
}