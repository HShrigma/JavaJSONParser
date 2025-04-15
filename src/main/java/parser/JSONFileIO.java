package src.main.java.parser;

import src.main.java.model.JSONNode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class JSONFileIO {
    
    public static JSONNode parseFromFile(String filePath) throws IOException {
        String content = Files.readString(Path.of(filePath));
        return JSONParser.BuildNode(content);
    }
    
    public static void writeToFile(String filePath, JSONNode node) throws IOException {
        Files.writeString(Path.of(filePath), node.toString());
    }
    
    public static JSONNode parseFromFileDebug(String filePath) throws IOException {
        String content = Files.readString(Path.of(filePath));
        return JSONParser.BuildNodeDebug(content);
    }
}