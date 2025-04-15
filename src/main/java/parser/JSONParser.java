package src.main.java.parser;

import src.main.java.model.*;
import src.main.java.parser.ParserHelpers.JSONDataType;
import src.main.java.parser.JSONParser;

// Entry point for building JSON nodes from raw input.
// Use BuildNodeDebug for logging type info during parsing.
public class JSONParser {

    public static JSONNode BuildNodeDebug(String input){
        JSONDataType type = ParserHelpers.GetDataType(input);
        if(type != null)
            System.out.println("Type: " + type);
        return JSONBuilder.BuildJSONNode(input);
    }
    public static JSONNode BuildNode(String input){
        return JSONBuilder.BuildJSONNode(input);
    }
}