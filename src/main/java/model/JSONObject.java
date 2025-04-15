package src.main.java.model;
import java.util.Map;

import src.main.java.parser.ParserHelpers.JSONDataType;

public class JSONObject extends JSONNode {
    Map<String, JSONNode> data;

    public JSONObject(Map<String, JSONNode> input) {
        data = input;
        type = JSONDataType.ARRAY;
    }

    @Override
    public Map<String, JSONNode> AsObject() {
        return data;
    }

    @Override
    public String toString() {
        return data.entrySet().stream()
                .map(entry -> "\"" + entry.getKey() + "\": " + entry.getValue().toString())
                .reduce("{", (acc, curr) -> acc.equals("{") ? acc + curr : acc + ", " + curr) + "}";
    }

}