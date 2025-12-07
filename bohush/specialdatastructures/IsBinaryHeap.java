package bohush.specialdatastructures;

import java.util.Scanner;
import java.io.*;

public class IsBinaryHeap {

	public static void main(String[] args) throws Exception{
		
		Scanner objIn = new Scanner(new File("input.txt"));
		int n = objIn.nextInt();
		int[] heap = new int[n];
		
		for (int i = 0; i < n; ++i)
		{
			heap[i] = objIn.nextInt();
		}
		objIn.close();
		
		boolean trust = true;
		for (int i = 0; i < n; ++i)
		{
			if ((2*i + 2 < n) && (heap[i] > heap[2*i + 2]))
			{
				trust = false;
				break;
			}
			else if ((2*i + 1 < n) && (heap[i] > heap[2*i + 1]))
			{
				trust = false;
				break;
			}
		}
		
		FileWriter objOut = new FileWriter("output.txt");
		objOut.write((trust ? "YES" : "NO"));
		objOut.close();
	}

}