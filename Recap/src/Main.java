import java.awt.event.*;
import java.util.Scanner;

import javax.swing.*;
public class Main {
	
	
	
	

	public static void main(String[] args) {
		System.out.println("Enter your name:");
		
		Scanner in = new Scanner(System.in);
		//String s - in.nextLine();
		
		int i = 0;
		
		do {
			System.out.println("i is: " + i);
			i++;
		} while(i < 10);
		
		in.close();

	}

}
