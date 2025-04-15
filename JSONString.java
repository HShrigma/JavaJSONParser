public class JSONString extends JSONNode {
    String value;

    public JSONString(String input) {
        value = input;
        type = ParserHelpers.JSONDataType.STRING;
    }
    
    @Override
    public String AsString(){ return value;}
}