public class JSONParser {

    public static JSONNode BuildNodeDebug(String input){
        ParserHelpers.JSONDataType type = ParserHelpers.GetDataType(input);
        if(type != null)
            System.out.println("Type: " + type);
        return ParserHelpers.BuildJSONNode(input);
    }
    public static JSONNode BuildNode(String input){
        return ParserHelpers.BuildJSONNode(input);
    }
}