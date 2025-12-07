package bohush.dynamicprogramming;
import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;

public class TheOrderOfMatrixMultiplication {

	public static void main(String[] args) throws Exception{
		int n;
		Scanner objIn = new Scanner(new File("input.txt"));
		n = objIn.nextInt();
		int[][] input_arr = new int[n][2];
		for (int i = 0; i < n; ++i)
		{
			input_arr[i][0] = objIn.nextInt();
			input_arr[i][1] = objIn.nextInt();
		}
		objIn.close();
		int[][] arr = new int[n][n];
		
		for (int i = 1; i < n; ++i)
		{
			for (int j = i - 1; j >= 0; --j)
			{
				
				if (j == i - 1)
				{
					arr[j][i] = input_arr[i][0] * input_arr[i][1] * input_arr[j][0];
				}
				else
				{
					int min = Integer.MAX_VALUE;
					for (int k = j; k < i; ++k)
					{
						if (arr[j][k] + arr[k + 1][i] + input_arr[i][1] * input_arr[j][0] * input_arr[k][1] < min)
						{
							min = arr[j][k] + arr[k + 1][i] + input_arr[i][1] * input_arr[j][0] * input_arr[k][1];
						}
					}
					arr[j][i] = min;
				}
			}
		}

		PrintWriter writer = new PrintWriter("output.txt");
		writer.print(arr[0][n - 1]);
		writer.close();
		

	}

}

