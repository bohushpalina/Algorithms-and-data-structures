package bohush.specialdatastructures;
import java.io.*;

public class RoadDestruction1 {

    public static void main(String[] args) throws IOException {
        BufferedReader objIn = new BufferedReader(new FileReader("input.txt"));
        BufferedWriter objOut = new BufferedWriter(new FileWriter("output.txt"));

        String[] parts = objIn.readLine().split(" ");
        int n = Integer.parseInt(parts[0]); //количество городов
        int q = Integer.parseInt(parts[1]); // количество дорог
        int m = Integer.parseInt(parts[2]); // количество разрешенных дорог

        int[] parent = new int[n + 1];
        for (int i = 1; i <= n; ++i) parent[i] = i;
        
        int[] roads1 = new int[q];
        int[] roads2 = new int[q];
        boolean[] delRoads = new boolean[q];
        int[] delRoadsNum = new int[m];

        int[] res = new int[m];
        int uniqueCount = n;
        

        for (int i = 0; i < q; ++i) {
            parts = objIn.readLine().split(" ");
            roads1[i] = Integer.parseInt(parts[0]);
            roads2[i] = Integer.parseInt(parts[1]);
        }
        
        for (int i = 0; i < m; ++i)
        {
        	parts = objIn.readLine().split(" ");
            delRoads[Integer.parseInt(parts[0]) - 1] = true;
            delRoadsNum[m - i - 1] = Integer.parseInt(parts[0]);
            
        }
        
        for (int i = 0; i < q; ++i)
        {
        	if (!delRoads[i])
        	{
        		int rootA = find(roads1[i], parent);
                int rootB = find(roads2[i], parent);

                if (rootA != rootB) {
                    parent[rootB] = rootA;
                    uniqueCount--;
                }
        	}
        }
        
        for (int i = 0; i < m; ++i)
        {
        	
        	res[i] = (uniqueCount == 1 ? 1 : 0);
        	int rootA = find(roads1[delRoadsNum[i] - 1], parent);
            int rootB = find(roads2[delRoadsNum[i] - 1], parent);

            if (rootA != rootB) {
                parent[rootB] = rootA;
                uniqueCount--;
            }
            
        }
        

        StringBuilder sb = new StringBuilder();
        for (int i = m - 1; i >= 0; --i) 
        	sb.append(res[i]);
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
