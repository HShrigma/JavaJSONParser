public class JSONBoolean extends JSONNode {
    private boolean value;

    public JSONBoolean(boolean input) {
        value = input;
        type = ParserHelpers.JSONDataType.BOOL;
    }

    @Override
    public boolean AsBool() {
        return value;
    }
    @Override
    public String toString(){
        return Boolean.toString(value);
    }
}