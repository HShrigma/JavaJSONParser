import java.util.List;

public class JSONArray extends JSONNode {
    List<JSONNode> data;

    public JSONArray(List<JSONNode> input) {
        data = input;
        type = ParserHelpers.JSONDataType.ARRAY;
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