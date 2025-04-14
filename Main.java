
public class Main {
    public static void main(String[] args) {
        // test out Data detection
        String squareBrackets = "[SampleSquareBracketData]";

        String curlyBrackets = "{SampleCurlyBracketData}";

        String quotes = "\"SampleQuotesData\"";

        String numLiteral = "98";
        String doubleLiteral = "235.5";
        String boolLiteral = "true";

        String nullString = "null";
        String[] values = new String[] { squareBrackets, curlyBrackets, doubleLiteral, nullString, numLiteral, boolLiteral, quotes };
        //expected output
        // - OBJECT, ARRAY, DOUBLE, NULL, INT, BOOL, STRING
        for (String value : values) {
            ParserHelpers.JSONDataType jSONType =  ParserHelpers.GetDataType(value);
            System.out.println("Value: " + value + " | Type: " + jSONType);
        }
    }
}