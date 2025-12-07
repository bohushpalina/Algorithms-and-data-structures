package bohush.specialdatastructures;

import java.io.*;

public class RoadConstruction {

    public static void main(String[] args) throws IOException {
        BufferedReader objIn = new BufferedReader(new FileReader("input.txt"));
        BufferedWriter objOut = new BufferedWriter(new FileWriter("output.txt"));

        String[] parts = objIn.readLine().split(" ");
        int n = Integer.parseInt(parts[0]);
        int q = Integer.parseInt(parts[1]);

        int[] parent = new int[n + 1];
        for (int i = 1; i <= n; ++i) parent[i] = i;

        int[] res = new int[q];
        int uniqueCount = n;

        for (int i = 0; i < q; ++i) {
            parts = objIn.readLine().split(" ");
            int a = Integer.parseInt(parts[0]);
            int b = Integer.parseInt(parts[1]);

            int rootA = find(a, parent);
            int rootB = find(b, parent);

            if (rootA != rootB) {
                parent[rootB] = rootA;
                uniqueCount--;
            }

            res[i] = uniqueCount;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < q; ++i) {
            sb.append(res[i]).append('\n');
        }
        objOut.write(sb.toString());

        objIn.close();
        objOut.close();
    }
    
    private static int find(int x, int[] parent) {
        int root = x;
        while (parent[root] != root) {
            root = parent[root];
        }
        while (x != root) {
            int next = parent[x];
            parent[x] = root;
            x = next;
        }
        return root;
    }
}
