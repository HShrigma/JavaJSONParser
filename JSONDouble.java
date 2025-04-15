public class JSONDouble extends JSONNode{
    double value;
    public JSONDouble(double input){
        value = input;
        type = ParserHelpers.JSONDataType.DOUBLE;
    }
    
    @Override
    public double AsDouble() {return value;}
    @Override
    public String toString(){
        return Double.toString(value);
    }
}