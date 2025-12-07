package bohush.specialdatastructures;

import java.io.*;
import java.util.*;

public class HashTable {
    public static void main(String[] args) throws Exception {
    	BufferedReader objIn = new BufferedReader(new FileReader("input.txt"));
        PrintWriter objOut = new PrintWriter(new FileWriter("output.txt"));
        
        StringTokenizer st = new StringTokenizer(objIn.readLine());
        int m = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        long[] arr = new long[m];         
        Arrays.fill(arr, -1L);             
        
        for (int k = 0; k < n; k++) {
        	String line = objIn.readLine();
            long x = Long.parseLong(line);
            
            int base = (int)(x % m);
            if (base < 0) base += m;

            int idx = base;

            for (int i = 0; i < m; i++) {
                long cur = arr[idx];

                if (cur == x) break;
                if (cur == -1L) {
                    arr[idx] = x;
                    break;
                }
                idx += c;
                if (idx >= m) idx -= m;
            }
        }

        StringBuilder sb = new StringBuilder(m * 3);
        for (int i = 0; i < m; i++) {
            if (i > 0) sb.append(' ');
            sb.append(arr[i]);
        }
        objOut.println(sb.toString());
        objIn.close();
        objOut.close();
    }
}
