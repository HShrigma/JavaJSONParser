package src.main.java.model;

import src.main.java.parser.ParserHelpers.JSONDataType;


public class JSONBoolean extends JSONNode {
    private boolean value;

    public JSONBoolean(boolean input) {
        value = input;
        type = JSONDataType.BOOL;
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