
public class Main {
    public static void main(String[] args) {
        // test out base JSONNode Object
        JSONNode node = new JSONBoolean(true);
        boolean value = node.AsBool();
        System.out.println("Value as bool: " + value);
        //should return type error
        String strValue = node.AsString();
        if(strValue == null){
            System.out.println("String value is null (expected)");
        }
                
    }
}