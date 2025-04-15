import src.main.java.model.JSONNode;
import src.main.java.parser.JSONParser;

public class Main {
    public static void main(String[] args) {
        StressTest();
    }

    public static void StressTest() {
        String complexInput = "{"
            + "\"users\": ["
                + "{\"name\": \"Alice\", \"age\": 30},"
                + "{\"name\": \"Bob\", \"hobbies\": [\"chess\", {\"type\": \"strategy\", \"difficulty\": \"high\"}]},"
                + "true,"
                + "null,"
                + "42.5"
            + "],"
            + "\"metadata\": {"
                + "\"count\": 2,"
                + "\"active\": true,"
                + "\"nested\": {"
                    + "\"deep\": {"
                        + "\"deeper\": {"
                            + "\"deepest\": \"🥽\""
                        + "}"
                    + "}"
                + "}"
            + "}"
        + "}";
    
        // Test with a complex input
        JSONNode node = JSONParser.BuildNodeDebug(complexInput);
        if (node != null) {
            System.out.println(node);
        } else {
            System.out.println("Failed to parse complex input.");
        }
    
        // Test with malformed input
        String malformedInput = "{\n" + 
                                "  \"name\": \"Alice,\n" + 
                                "  \"age\": 30\n" + 
                                "}\n" + 
                                "";
        node = JSONParser.BuildNodeDebug(malformedInput);
        if (node != null) {
            System.out.println(node);
        } else {
            System.out.println("Failed to parse malformed input.");
        }
    }
    
}