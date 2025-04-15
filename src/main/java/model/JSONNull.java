package src.main.java.model;
import src.main.java.parser.ParserHelpers.JSONDataType;

public class JSONNull extends JSONNode {
    public JSONNull() {
        type = JSONDataType.NULL;
    }

    @Override
    public String toString() {
        return "null";
    }
}