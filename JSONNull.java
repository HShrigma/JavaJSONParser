public class JSONNull extends JSONNode {
    public JSONNull() {
        type = ParserHelpers.JSONDataType.NULL;
    }

    @Override
    public String toString() {
        return "null";
    }
}