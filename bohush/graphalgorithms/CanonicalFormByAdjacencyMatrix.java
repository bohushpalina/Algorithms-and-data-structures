package bohush.graphalgorithms;
import java.io.*;
import java.util.*;

public class CanonicalFormByAdjacencyMatrix {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        PrintWriter out = new PrintWriter(new FileWriter("output.txt"));
        int n = Integer.parseInt(br.readLine().trim());
        int[][] matr = new int[n][n];
        int[] res = new int[n];
        
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            StringTokenizer st = new StringTokenizer(line);
            for (int j = 0; j < n; j++) {
                matr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        for (int i = 0; i < n; ++i)
        {
        	for (int j = 0; j < n; ++j)
        	{
        		if (matr[i][j] == 1)
        		{
        			res[j] = i + 1;
        		}
        	}
        }
        
        for (int i = 0; i < n; ++i)
        {
        	out.print(res[i] + " ");
        }

        br.close();
        out.close();
    }
}

