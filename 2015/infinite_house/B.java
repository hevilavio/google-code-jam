

import java.util.*;
import java.io.*;
import java.lang.*;
// 14:16 - inicio
// 14:18 - pausa
// 14:28 - retorno
// 14:58 - still undone - rethinjing:(
// 17:23 - fucked again


public class B {
	static boolean dev = true;
	public static void main(String[] args) throws Exception {
		
	 
		Scanner input = dev ? new Scanner(System.in) : new Scanner(new FileInputStream("B-small-practice.in"));
		PrintStream out = dev ? new PrintStream(System.out) : new PrintStream(new FileOutputStream("B.out"));

        int numCases = input.nextInt();
        for (int n = 0; n < numCases; n++)
        {
            int N = input.nextInt();
            int[] plates = new int[N];
            for (int i = 0; i < N; i++)
                plates[i] = input.nextInt();

            // printArray(plates, out);

            // divide to conquer:
            int numMinutes = 0, countEaters = 0, maxValue = -1;
            int[] eaters = new int[1000];
            
            // do all chunks
            for (int i = 0; i < N; i++) {
        		
        		if((plates[i] * 2) > N){ // should be chunked

        			int first = plates[i] / 2;
        			int second = plates[i] - first;

        			int max = Math.max(first, second);
        			if(max > maxValue) maxValue = max;

        			eaters[countEaters++] = first;
					eaters[countEaters++] = second;

        			numMinutes++;
        		} else {
        			eaters[countEaters++] = plates[i];

        			if(plates[i] > maxValue){
        				maxValue = plates[i];
        			}
        		}
            }

            // printArray(eaters, out);
            // out.print("numMinutes=" + numMinutes + "\n");
            // out.print("maxValue=" + maxValue + "\n");
            // out.print("total=" + (numMinutes + maxValue) + "\n");

            out.print(String.format("Case #%s: %s\n", (n+1), (numMinutes + maxValue)));
        }
    }

    static void printArray(int[] array, PrintStream out){


		for (int i = 0; i < array.length; i++) {
			if(array[i] != 0) out.print("e[" + i + "]=" + array[i] + "\n");
		}
    }

}


