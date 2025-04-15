import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
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
    + "};";

    System.out.println(JSONParser.BuildNode(complexInput));

    }
}