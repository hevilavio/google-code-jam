
import java.util.*;
import java.io.*;


public class C {
	
	static boolean dev = !true;

	public static void main(String[] args) throws Exception {

		Scanner in = dev ? new Scanner(System.in) : new Scanner(new FileInputStream("C-small-practice.in"));
 		PrintStream out = dev ? new PrintStream(System.out) : new PrintStream(new FileOutputStream("A.out"));

 		// testMult(out);
 		// testReduce(out);
 		// testPossible(out);
 		// testMultiply(out);

		int numCases = in.nextInt();

		for (int i = 0; i < numCases; i++) {
			
			int numChars = in.nextInt();
			int times = in.nextInt(); // how many times

			String content = in.next();

			// out.print(String.format("Cases=%s, numChars=%s, times=%s, content=%s", numCases, numChars, times, content));

			boolean isPossible = isPossible(multiply(content, times));

            out.print(String.format("Case #%s: %s\n", (i+1), isPossible ? "YES" : "NO"));
		}
	}

	static String[][] table = new String[][]
		{
			{"1", "i", "j", "k"},
			{"i", "-1", "k", "-j"},
			{"j", "-k", "-1", "i"},
			{"k", "j", "-i", "-1"}
		};


	static int idx(String x){
		
		if(negative(x)) { 
			x = x.substring(1, 2);
		}

		if("1".equals(x)){
			return 0;
		}
		if("i".equals(x)){
			return 1;
		}
		if("j".equals(x)){
			return 2;
		}
		if("k".equals(x)){
			return 3;
		}

		return -1;
	}

	static boolean negative (String x){
		return (x.length() > 1);
	}

	public static String mult(String a, String b){

		boolean an = negative(a);
		boolean bn = negative(b);

		if((an && !bn) || (!an && bn)){
			String carry = (a.contains("1") || b.contains("1")) ? "-" : "";
			return carry + table[idx(b)][idx(a)];
		}

		return table[idx(a)][idx(b)];
	}

	static String reduce (String s){

		int head = 0;
		String bag = "";

		while(head < s.length()){
			String st = s.substring(head, head+1);
			log("head=" + head + ", bag=" + bag + ", st=" + st);
			head++;

			if(bag == ""){
				bag = st;
			} 
			else{
				bag = mult(bag, st);
			}

		}
		return bag;
	}
	
	static String exp = "ijk";
	static boolean isPossible(String s){
		// all possible partitions!

		if(s.length() < 3) return false;
		if("ijk".equals(s)) return true;

		int l = s.length();

		for (int i = 0; i < l; i++) {
			String left = s.substring(0, i);
			if("i".equals(reduce(left))){
				for (int j = i; j < l; j++) {
					String center = s.substring(i, j);
					if("j".equals(reduce(center))){
						String right = s.substring(j, l);
						if("k".equals(reduce(right))){
							return true;
						} 						
					}
				}	
			}
		}

		return false;

	}

	static String multiply(String s, int howMuch){

		String stash = "";

		for (int i = 0; i < howMuch; i++) {
			stash += s;			
		}
		return stash;
	}

	// nothing above here

	static void testMultiply(PrintStream out){

		out.print("[" + multiply("abc", 1) + "]" + "\n");
		out.print("[" + multiply("abc", 2) + "]" + "\n");
		out.print("[" + multiply("ik", 2) + "]" + "\n");
	}

	static void testPossible(PrintStream out){

		out.print("[" + isPossible("ijk") + "]" + "\n");
		out.print("[" + isPossible("jijijijijiji") + "]" + "\n");
		out.print("[" + isPossible("jijijijijijij") + "]" + "\n");
	}

	static void testReduce(PrintStream out){

		out.print("[" + reduce("jij") + "]" + "\n"); // i
		out.print("[" + reduce("iji") + "]" + "\n"); // j
		out.print("[" + reduce("jijiji") + "]" + "\n"); // k

		test(reduce("jij"), "i");
		test(reduce("iji"), "j");
		test(reduce("jijiji"), "k");

	}

	static void testMult(PrintStream out){
		out.print(mult("i", "i") + "\n");
		out.print(mult("j", "j") + "\n");
		out.print(mult("k", "k") + "\n");
	
		out.print(mult("1", "k") + "\n");
		out.print(mult("k", "1") + "\n");
		out.print(mult("j", "i") + "\n");


		out.print(mult("-k", "j") + "\n"); // i
	
		test(mult("i", "i"), "-1");
		test(mult("-k", "j"), "i");

	}

	static void test(String given, String expected){
		if(!given.equals(expected)){
			throw new RuntimeException("failed!");
		}
	}

	static PrintStream log = new PrintStream(System.out);
	static void log(String s){
		// log.print(s + "\n");
	}

}