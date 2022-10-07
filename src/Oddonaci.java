import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class Oddonaci {
	
	/*Inner Class*/
	private static class OddonacciImplementation {
		
		/*Variables and Constants of Inner Class*/
		static long temp = 0;
		
		/*Oddonacci Methods*/
		/**
		 * Recursive exponential method for calculating the oddonacci sequence. The method invokes 
		 * three method calls to itself, making its time complexity n^3. The base cases consider
		 * when the method might be called with a number = 0, and when the number used to call the
		 * function reduces to <= 3. In such a case, given that the starting clause for the oddonacci sequence
		 * is three 1's, the base case will return three 1's to the previous recursive calls. The recursive 
		 * case calls the previous oddonacci number by one place, by two places and by three places 
		 * and returns their sum. In such a way, oddonacci is calculated. 
		 * 
		 * @param nb
		 * @return long
		 */
		private static long oddExponential(int nb) { //expect long oddonacci values when going to nb -> 100
			if(nb == 0) { //if oddonacci(0) is called
				return 0;
			}
			else if(nb <= 3) //base case : base numbers, everything adds from here
				return 1;
			else //recursive case
				return oddExponential(nb-1) + oddExponential(nb-2) + oddExponential(nb-3);			
		}
		
		/**
		 * Recursive linear method for calculating oddonacci, thus the time complexity is n. 
		 * Considers a base case where the input to the function is 1 and when the input reaches 
		 * one before it, thus the absolute start of the oddonacci sequence. In such a case, 
		 * given that the starting cases to oddonacci sequence are always three 1's (predetermined), the method 
		 * will return three 1's. Otherwise, every other case is calculated. The function
		 * shifts the values of the three oddoacci numbers under consideration by the use of 
		 * three variables and a temporary holder. Each variable will change to become 
		 * the next one in the sequence, while the 'temp' holds the value of the number
		 * that will be disapearing from the current considered three numbers, as the sequence
		 * shifts. Variable 'temp' is static to preserve its value. The third variable 'z' becomes
		 * the next, new, value in the oddonacci sequence. The value of each 'x', 'y' and 'z' is 
		 * preserved through the method call, but assumed to be 1 at the start. 
		 * 
		 * @param nb
		 * @param x
		 * @param y
		 * @param z
		 * @return long
		 */
		private static long oddLinear(int nb, long x, long y, long z) {
			if(nb == 0) {
				return 0; //if i of the for loop is 0, then the respective oddonacci nb is 0
			}
			else if(nb == 1) { //base case
				return x; 
			}
			else { //recursive case
				temp = x; //temporary value holds x to swap the value of x with the next value in the oddonacci sequence
				x = y; //because sequence is moving forward, value of x will be forgotten and replaced with the next new value of the start of 1-3, which will become y
				y = z; //oddonacci values move forward
				z = x + y + temp; //sum of previous three, new nb to be obtained at method call, value of z is kept through method call
				return oddLinear(nb-1, x, y, z); //all x, y, z are preserved through method call 
			}
		}
	} //end of inner class	
	
	/*Outer Class*/
	public static void main(String[] args) {
		
		/*Variables and Constants of Outer Class*/
		PrintWriter outputFile = null;
		final long ODD_LINEAR_BASE = 1;
		long startTime;
		long endTime;
		long executionTime;
		
		/*Opening Message*/
		System.out.println("Welcome to Oddonacci Program!");
		
		/*Print Data to Output File*/
		try{
			
			/*Write to File*/
			System.out.println("Attempting to write to output file...");
			outputFile = new PrintWriter(new FileOutputStream("OddoOut.txt"));
			
			/*Linear Method*/
			outputFile.println("Consider Oddonacci Linear...");
			for(int i = 0; i <= 100; i += 5) { 
				startTime = System.nanoTime(); //start the clock
				outputFile.print("Oddonnacci(" + i + ") = " + OddonacciImplementation.oddLinear(i, ODD_LINEAR_BASE, ODD_LINEAR_BASE, ODD_LINEAR_BASE));
				endTime = System.nanoTime(); 
				executionTime = (endTime - startTime);
				outputFile.println(" ... execution time: " + executionTime + " ns");
				outputFile.flush();
			}
			
			outputFile.println();
			outputFile.println("----------------------------------------------");
			outputFile.println();
			
			/*Exponential Method*/
			outputFile.println("Consider Oddonacci Exponential...");
			for(int i = 0; i < 100; i += 5) { 
				startTime = System.nanoTime(); //start the clock
				outputFile.print("Oddonnacci(" + i + ") = " + OddonacciImplementation.oddExponential(i));
				endTime = System.nanoTime(); 
				executionTime = (endTime - startTime);
				outputFile.println(" ... execution time: " + executionTime + " ns");
				outputFile.flush();
			}
		}
		catch(FileNotFoundException e) {
			System.out.println("File could not be found. Program will terminate. ");
			System.exit(0);
			
		}
		catch(IOException e) {
			System.out.println("IO error encountered. Program will terminate. ");
			System.exit(0);
		}
		finally {
			
			/*Close Output File*/
			if(outputFile != null) {
				outputFile.close();
				System.out.println("Successfully written to output file OddoOut.txt.");
			}
			
			/*End Program*/
			System.out.println("Program has completed. ");
		}
	}
} //end of outer class
