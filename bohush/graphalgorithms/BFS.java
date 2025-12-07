package bohush.graphalgorithms;

import java.io.*;
import java.util.*;

public class BFS {
	
	private static int id = 0;
	private static int[][] matr;
	private static boolean[] visited;
	private static int n;
	
	static void bfs(int start, int[] ord) {
		Queue<Integer> queue = new LinkedList<>();

	    visited[start] = true;
	    ord[start] = id++;
	    queue.add(start);

	    while (!queue.isEmpty()) {
	        int current = queue.poll();
	        for (int j = 0; j < n; j++) {
	            if (matr[current][j] == 1 && !visited[j]) {
	                visited[j] = true;
	                ord[j] = id++;
	                queue.add(j);
	            }
	        }
	    }
	}
	 
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        PrintWriter out = new PrintWriter(new FileWriter("output.txt"));
        n = Integer.parseInt(br.readLine().trim());
        matr = new int[n][n];
        visited = new boolean[n];
        for (int i = 0; i < n; ++i)
        {
        	visited[i] = false;
        }
        
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            StringTokenizer st = new StringTokenizer(line);
            for (int j = 0; j < n; j++) {
                matr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        int[] ord = new int[n];
        Arrays.fill(ord, -1);
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                bfs(i, ord);
            }
        }
        
        for (int i = 0; i < n; ++i)
        {
        	out.print(ord[i] + 1 + " ");
        }
        
        

        br.close();
        out.close();
    }
}
