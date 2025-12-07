package bohush.graphalgorithms;
import java.io.*;
import java.util.*;

public class DFS {
	
	private static int id = 1;
	private static int[][] matr;
	private static boolean[] visited;
	private static int n;
	
	static void dfs(int start, int[] ord) {
        visited[start] = true;
        ord[start] = id++;
        
        for (int j = 0; j < n; j++) {
            if (matr[start][j] == 1 && !visited[j]) {
                dfs(j, ord);
            }
        }
    }
	
	
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        PrintWriter out = new PrintWriter(new FileWriter("output.txt"));
        n = Integer.parseInt(br.readLine().trim());
        matr = new int[n][n];
        visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            StringTokenizer st = new StringTokenizer(line);
            for (int j = 0; j < n; j++) {
                matr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        int[] ord = new int[n];
        Arrays.fill(ord, -1);
        
        for (int i = 0; i < n; ++i)
        {
        	if (ord[i] == -1)
        	{
        		dfs(i, ord);
        	}
        }
        
        for (int i = 0; i < n; ++i)
        {
        	out.print(ord[i] + " ");
        }
        
        

        br.close();
        out.close();
    }
}