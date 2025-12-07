package bohush.graphalgorithms;
import java.io.*;
import java.util.*;

public class BuildAdjacencyList {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        PrintWriter out = new PrintWriter(new FileWriter("output.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        List<Integer>[] g = new ArrayList[n];
        for (int i = 0; i < n; i++) g[i] = new ArrayList<>();
        int[] count = new int[n];

        for (int i = 0; i < m; i++) {
            String line = br.readLine();
            if (line == null || line.trim().isEmpty()) break;

            st = new StringTokenizer(line);
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            g[a].add(b);
            g[b].add(a);

            count[a]++;
            count[b]++;
        }

        for (int i = 0; i < n; i++) {
            out.print(count[i] + " ");
            for (int v : g[i]) {
                out.print((v + 1) + " ");
            }
            out.println();
        }

        br.close();
        out.close();
    }
}

