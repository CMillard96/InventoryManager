import java.util.ArrayList;

public class Collections {

	public static void main(String[] args) {
		
		
		ArrayList<Person> people = new ArrayList<Person>();
		
		//Admin class extends Person therefore Admin object can be used in same way as Person
		Admin a  = new Admin();
		people.add(a);
		
		doSomething(a);
	}
	
	static void doSomething(Person p) {
		System.out.println(p);
	}

}
