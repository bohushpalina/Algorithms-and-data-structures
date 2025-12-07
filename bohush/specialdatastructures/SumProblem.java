package bohush.specialdatastructures;
import java.io.*;
import java.util.Scanner;

public class SumProblem {
	
	private static long[] arr;
	private static long[] sqrArr;
	private static int newN;
	
	private static void FindSum(int l, long r)
	{
		int L = l;
        int R = (int) r - 1;

        long sum = 0;
        int blockL = L / newN;
        int blockR = R / newN;

        if (blockL == blockR)
        {
            for (int i = L; i <= R; i++)
                sum += arr[i];
        }
        else
        {
            int endL = (blockL + 1) * newN - 1;
            for (int i = L; i <= endL; i++)
                sum += arr[i];

            for (int b = blockL + 1; b <= blockR - 1; b++)
                sum += sqrArr[b];

            int startR = blockR * newN;
            for (int i = startR; i <= R; i++)
                sum += arr[i];
        }

        System.out.println(sum);
	}
	
	private static void Add(int a, long b)
	{
		arr[a] += b;
		sqrArr[a/ newN] += b;
	}

    public static void main(String[] args){
    	Scanner objIn = new Scanner(System.in);
    	int n = objIn.nextInt();
    	arr = new long[n];
    	newN = (int) Math.ceil(Math.sqrt(n));
    	sqrArr = new long[(n + newN - 1) / newN];  
    	
    	int counter = 0;
    	long currentSum = 0;
    	for (int i = 0; i < n; ++i)
    	{
    		arr[i] = objIn.nextLong();
    	    currentSum += arr[i];
    		if ((i + 1) % newN == 0)
    		{
    			sqrArr[counter++] = currentSum;
    			currentSum = 0;
    		}
    	}
    	if (currentSum != 0)
    	{
    		sqrArr[counter++] = currentSum;
    	}
    	objIn.nextLine();
    	int countOperations = objIn.nextInt();
    	objIn.nextLine();
    	for (int i = 0; i < countOperations; ++i)
    	{
    		String line = objIn.nextLine();
    		String[] parts = line.split("\\s+");
    		String operation = parts[0];
    		int a = Integer.parseInt(parts[1]);
    		long b = Long.parseLong(parts[2]);
    		
    		switch(operation)
    		{
    		case "FindSum": FindSum(a, b); break;
    		case "Add": Add(a, b); break;
    		}

    	}
    }
}