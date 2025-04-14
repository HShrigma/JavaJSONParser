
public class Main {
    public static void main(String[] args) {
        // test out Data detection
        String input = "[\"Item\", 92, {\"Key\": 25} ]"; 
        if(ParserHelpers.IsCollection(input)){
            if(ParserHelpers.isNested(input)){
                System.out.println("input collection is nested: " + input);
            }
            else{
                System.out.println("input collection is NOT nested: " + input);
            }
        }
        else{
            System.out.println("input isn't collection: " + input);
        }
    }
}