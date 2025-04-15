public class JSONParser {

    public static JSONNode BuildNode(String input){
        ParserHelpers.JSONDataType type = ParserHelpers.GetDataType(input);
        System.out.println("Type: " + type);
        return ParserHelpers.BuildJSONNode(input);
    }
}