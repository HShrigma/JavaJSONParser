public class JSONInt extends JSONNode {
    private int value;

    public JSONInt(int input) {
        value = input;
        type = ParserHelpers.JSONDataType.INT;
    }

    @Override
    public int AsInteger() {return value;}
    @Override
    public String toString(){
        return Integer.toString(value);
    }
}