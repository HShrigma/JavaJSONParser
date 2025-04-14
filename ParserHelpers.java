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
}