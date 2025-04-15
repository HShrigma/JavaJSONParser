import src.main.java.parser.JSONFileIO;

import src.main.java.model.*;

import java.io.FileOutputStream;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            // Read from file
            JSONNode test = JSONFileIO.parseFromFile("test.json");
            System.out.println("Parsed test: " + test);
            
            // Modify and write back
            test.AsObject().put("lastModified", new JSONString(java.time.LocalDateTime.now().toString()));
            JSONFileIO.writeToFile("test_updated.json", test);
            
            System.out.println("Updated config written to file!");
            
        } catch (IOException e) {
            System.err.println("File error: " + e.getMessage());
        }
    }
}