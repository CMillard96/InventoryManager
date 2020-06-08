import java.util.ArrayList;
import java.util.List;

public class Collections {

	public static void main(String[] args) {
		
		Admin a = new Admin();
		a.email = "Chad@hotmail.com";
		a.ln = "Millard";
		
		ArrayList<Admin> admins = new ArrayList<Admin>();
		admins.add(a);
		
		List<Person> people = (List<Person>)(List<?>)admins;
		
		doSomething(people);	
		
		
	}
	
	
	//(<? extends Person>) wildcard - allows arraylist of types that extend given class
	static void doSomething(List<Person> peeps) {
		for(Person p : peeps) {
			System.out.println(p.email + ", " + p.ln);
		}
	}

}
