package src.main.java.model;

import src.main.java.parser.ParserHelpers.JSONDataType;

public class JSONString extends JSONNode {
    String value;

    public JSONString(String input) {
        value = input;
        type = JSONDataType.STRING;
    }

    @Override
    public String AsString() {
        return value;
    }

    @Override
    public String toString() {
        return '\"' + value + '\"';
    }
}