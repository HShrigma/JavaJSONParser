import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // test out base JSONNode Object
        JSONNode node = new JSONBoolean(true);
        System.out.println(node);

        node = new JSONInt(25);
        System.out.println(node);

        node = new JSONDouble(3.14);
        System.out.println(node);

        node = new JSONString("This is a string");
        System.out.println(node);

        
        List<JSONNode> valueList = new ArrayList<JSONNode>();
        valueList.add(new JSONBoolean(false));
        valueList.add(new JSONInt(2));
        valueList.add(new JSONDouble(5.23));
        valueList.add(new JSONString("Words words WORDS"));

        node = new JSONArray(valueList);
        System.out.println(node);

        Map<String, JSONNode> map = new HashMap<String, JSONNode>();
        map.put("Double", new JSONDouble(3.14 ));
        map.put("Bool", new JSONBoolean(true));
        map.put("String", new JSONString("Words"));
        map.put("Int", new JSONInt(23456));
        map.put("Null", new JSONNull());

        node = new JSONObject(map);
        System.out.println(node);
    }
}