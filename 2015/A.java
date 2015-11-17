
import java.util.*;
import java.io.*;


public class A {
	
	static boolean dev = true;
	public static void main(String[] args) throws Exception {

		Scanner in = dev ? new Scanner(System.in) : new Scanner(new FileInputStream("A-large-practice.in"));
 		PrintStream out = dev ? new PrintStream(System.out) : new PrintStream(new FileOutputStream("A.out"));

		int numCases = in.nextInt();

		for (int i = 0; i < numCases; i++) {
			
			int maxSl = in.nextInt();
			String audience = in.next();

			int numFriends = 0;
			int total = 0;
			for (int j = 0; j < maxSl; j++) {
				int numPersons = audience.charAt(j) - '0';
				
				if(numPersons == 0 && total <= j){
					numFriends++;
					total++;
				}
				total += numPersons;
			}

			out.print("Case #" + (i+1) + ": " + numFriends + "\n");
		}
	}
}