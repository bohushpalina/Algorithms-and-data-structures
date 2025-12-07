package bohush.dynamicprogramming;
import java.util.Scanner;
import java.io.*;
import java.util.Arrays;

public class StrictlyIncreasingSubsequence {
	public static void main(String[] args) throws Exception
	{
		Scanner objIn = new Scanner(new File("input.txt"));
	    int n = objIn.nextInt();
	    long[] arr_in = new long[n];
	    for (int i = 0; i < n; ++i)
	    {
	    	arr_in[i] = objIn.nextLong();
	    }
	    objIn.close();
	    long[] arr = new long[n];
	    for (int i = 0; i < n; ++i)
	    {
	    	arr[i] = arr_in[0];
	    }
	    
	    int current = 1;
	    for (int i = 1; i < n; ++i)
	    {
	        int pos = Arrays.binarySearch(arr, 0, current, arr_in[i]);
	        
	        if (pos < 0) {
	            pos = -(pos + 1); 
	        }
	        
	        arr[pos] = arr_in[i];
	        if (pos == current) {
	            current++;
	        }
	    }
	    
	    FileWriter writer = new FileWriter("output.txt");
	    writer.write(String.valueOf(current));
	    writer.close();
	    
	}
}
