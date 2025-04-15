
public class Main {
    public static void main(String[] args) {
        // test out base JSONNode Object
        JSONNode node = new JSONBoolean(true);
        boolean valueBool = node.AsBool();
        System.out.println("Value as bool: " + valueBool);
        // should return type error
        String valueStr = node.AsString();
        if (valueStr == null) {
            System.out.println("(BOOL NODE) String value is null (expected)");
        }

        node = new JSONInt(25);
        int valueInteger = node.AsInteger();
        System.out.println("Value as int: " + valueInteger);

        valueStr = node.AsString();
        if (valueStr == null) {
            System.out.println("(INT NODE) String value is null (expected)");
        }

        node = new JSONDouble(3.14);
        double valueDouble = node.AsInteger();
        System.out.println("Value as double: " + valueDouble);

        valueStr = node.AsString();
        if (valueStr == null) {
            System.out.println("(DOUBLE NODE) String value is null (expected)");
        }

        node = new JSONString("This is a string");

        valueStr = node.AsString();
        if (valueStr == null) {
            System.err.println("(STRING NODE) String value is null (NOT expected)");
        } else {
            System.out.println("Value as string: " + valueStr);
        }
        
    }
}