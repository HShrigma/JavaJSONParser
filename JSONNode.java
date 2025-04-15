import java.util.List;
import java.util.Map;

public abstract class JSONNode {
    // get data type
    ParserHelpers.JSONDataType type;

    public ParserHelpers.JSONDataType GetDataType() {
        return type;
    }

    public String AsString() {
        System.err.println("Not String! Data type is: " + type);
        return null;
    }

    public boolean AsBool() {
        System.err.println("Not Bool! Data type is: " + type);
        return false;
    }

    public double AsDouble() {
        System.err.println("Not Double! Data type is: " + type);
        return 0;
    }

    public int AsInteger() {
        System.err.println("Not Integer! Data type is: " + type);
        return 0;
    }

    public List<JSONNode> AsArray() {
        System.err.println("Not Array! Data type is: " + type);
        return null;
    }

    public Map<String, JSONNode> AsObject() {
        System.err.println("Not Object! Data type is: " + type);
        return null;
    }

    @Override
    public String toString() {
        return "[JSONNode type=" + type + "]";
    }

}