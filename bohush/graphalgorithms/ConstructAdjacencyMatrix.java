package bohush.graphalgorithms;
import java.io.*;
import java.util.*;

public class ConstructAdjacencyMatrix {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        PrintWriter out = new PrintWriter(new FileWriter("output.txt"));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] matr = new int[n][n];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            matr[a - 1][b - 1] = 1;
            matr[b - 1][a - 1] = 1;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) 
            {
                out.print(matr[i][j]);
                if (j + 1 < n) 
                	out.print(" ");
            }
            out.println();
        }

        br.close();
        out.close();
    }
}