package bohush.searchtrees;

import java.io.*;
import java.util.StringTokenizer;

public class IsBinaryTreeSearchTree {
	
	public static void main(String[] args) throws Exception
	{
		BufferedReader objIn = new BufferedReader(new FileReader("bst.in"));
		int n = Integer.parseInt(objIn.readLine().trim());
		long[][] arr = new long[5][n];

		StringTokenizer st = new StringTokenizer(objIn.readLine());
		arr[0][0] = Integer.parseInt(st.nextToken());
		arr[3][0] = Integer.MIN_VALUE - 1L;
		arr[4][0] = Integer.MAX_VALUE + 1L;

		for (int i = 1; i < n; ++i) {
		    st = new StringTokenizer(objIn.readLine());
		    arr[0][i] = Integer.parseInt(st.nextToken());
		    arr[1][i] = Integer.parseInt(st.nextToken());
		    arr[2][i] = (st.nextToken().charAt(0) == 'L') ? -1 : 1;
		 }
		objIn.close();
		
		boolean ex = true;
		for (int j = 1; j < n; ++j)
		{

		    arr[3][j] = arr[3][(int) arr[1][j] - 1];
		    arr[4][j] = arr[4][(int) arr[1][j] - 1];

		    if (arr[2][j] == -1) 
		    {
		        arr[4][j] = arr[0][(int) arr[1][j] - 1];
		    } 
		    else 
		    {
		        arr[3][j] = arr[0][(int) arr[1][j] - 1];
		    }
		    
	        if (arr[0][j] < arr[3][j] || arr[0][j] >= arr[4][j]) {
	            ex = false;
	            break;
	        }
		    
		
		}
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("bst.out"))); 
		writer.write(ex ? "YES" : "NO"); 
		System.out.println(ex ? "YES" : "NO");
		writer.flush(); 
		writer.close();
	}
	
	
}
