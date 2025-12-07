package bohush.graphalgorithms;
import java.io.*;
import java.util.*;

public class CanonicalView {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        PrintWriter out = new PrintWriter(new FileWriter("output.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        int[] matr = new int[n];
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            if (line == null || line.trim().isEmpty()) break;  // защита

            st = new StringTokenizer(line);
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            matr[b - 1] = a;
        }
        for (int i = 0; i < n; i++) {
            out.print(matr[i] + " ");
        }

        br.close();
        out.close();
    }
}
