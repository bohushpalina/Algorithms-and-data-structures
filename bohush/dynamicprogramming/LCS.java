package bohush.dynamicprogramming;

import java.util.Scanner;

public class LCS {

	public static void main(String[] args) {
		int n;
		Scanner objIn = new Scanner(System.in);
		n = objIn.nextInt();
		int[] arr1 = new int[n];
		int[] arr2 = new int[n];
		for (int i = 0; i < n; ++i)
		{
			arr1[i] = objIn.nextInt();
		}
		for (int i = 0; i < n; ++i)
		{
			arr2[i] = objIn.nextInt();
		}
		objIn.close();
		int[][] arr = new int[n + 1][n + 1];
		for (int i = 1; i <= n; ++i)
		{
			for (int j = 1; j <= n; ++j)
			{
				if (arr1[i - 1] == arr2[j - 1])
				{
					arr[i][j] = arr[i - 1][j - 1] + 1;
				}
				else
				{
					arr[i][j] = Math.max(arr[i - 1][j], arr[i][j - 1]);
				}
			}
		}
		int answer = arr[n][n];
		int counter = 0;
		int[] res1 = new int[n];
		int[] res2 = new int[n];
		
		int i = n, j = n;
		while(i > 0 && j > 0)
		{
			if (arr1[i - 1] != arr2[j - 1])
			{
				if (arr[i - 1][j] == arr[i][j])
				{
					--i;
				}
				else
				{
					--j;
				}
			}
			else
			{
				res1[counter] = i - 1;
				res2[counter] = j - 1;
				++counter;
				--i;
				--j;
			}
		}
		
		System.out.println(answer);
		for (int k = counter - 1; k >= 0; --k)
		{
			System.out.print(res1[k] + " ");
		}
		System.out.println("");
		for (int k = counter - 1; k >= 0; --k)
		{
			System.out.print(res2[k] + " ");
		}
	}

}