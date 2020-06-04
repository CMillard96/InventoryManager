import java.util.HashMap;
import java.util.Set;

public class Collections {

	public static void main(String[] args) {
		
		HashMap<String, Integer> ids = new HashMap<String, Integer>();
		ids.put("Chad Millard", 5);
		
		ids.put("Sally", ids.getOrDefault("Sally", 0) + 1);
		
		Set<String> keys = ids.keySet();
		for(String key : keys) {
			System.out.println(key);
			System.out.println(ids.get(key));
			
		}
	}

}
