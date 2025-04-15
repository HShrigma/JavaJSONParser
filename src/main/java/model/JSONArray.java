package src.main.java.model;
import java.util.List;

import src.main.java.parser.ParserHelpers.JSONDataType;;

public class JSONArray extends JSONNode {
    List<JSONNode> data;

    public JSONArray(List<JSONNode> input) {
        data = input;
        type = JSONDataType.ARRAY;
    }

    @Override
    public List<JSONNode> AsArray() {
        return data;
    }

    @Override
    public String toString() {
        return data.stream()
                .map(JSONNode::toString)
                .reduce("[", (acc, curr) -> acc.equals("[") ? acc + curr : acc + ", " + curr) + "]";
    }

}