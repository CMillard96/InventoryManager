import java.util.HashMap;
import java.util.HashSet;

public class Collections {

	public static void main(String[] args) {
		
		HashSet<Person> people = new HashSet<Person>();
				
		Person p = new Person("email@email.com", "Millard");
		Position p1 = new Position(10, 10);
		p.position = p1;
		
		Person q = new Person("other@email.com", "Millardus");
		Position p2 = new Position(8, 9);
		q.position = p2;
		
		System.out.println(p.hashCode());
		System.out.println(q.hashCode());
		
		people.add(p);
		people.add(q);
		
		HashMap<String, Person> peeps = new HashMap<String, Person>();
		peeps.put("email@email.com", p);
		System.out.println("HashMap: " + peeps.containsKey("email@email.com"));
		
	}

}
