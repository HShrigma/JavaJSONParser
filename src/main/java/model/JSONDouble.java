package src.main.java.model;

import src.main.java.parser.ParserHelpers.JSONDataType;

public class JSONDouble extends JSONNode{
    double value;
    public JSONDouble(double input){
        value = input;
        type = JSONDataType.DOUBLE;
    }
    
    @Override
    public double AsDouble() {return value;}
    @Override
    public String toString(){
        return Double.toString(value);
    }
}