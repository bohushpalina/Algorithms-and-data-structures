package bohush.specialdatastructures;
import java.io.*;
import java.util.*;

public class HuffmanCoding {

    public static void main(String[] args) throws IOException {

        BufferedInputStream in = new BufferedInputStream(new FileInputStream("huffman.in"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("huffman.out"));

        int c = in.read();
        int n = 0;
        while (c >= '0') {
            n = n * 10 + (c - '0');
            c = in.read();
        }

        long[] arr = new long[n];
        int idx = 0;
        long num = 0;
        boolean reading = false;

        while ((c = in.read()) != -1) {
            if (c >= '0' && c <= '9') {
                num = num * 10 + (c - '0');
                reading = true;
            } else if (reading) {
                arr[idx++] = num;
                num = 0;
                reading = false;
            }
        }
        if (reading) arr[idx++] = num;

        Arrays.sort(arr);

        long[] sum = new long[n];
        int aIndex = 0;
        int sIndex = 0;
        int sSize = 0;
        long cost = 0;

        for (int i = 0; i < n - 1; i++) {
            long a;
            if (aIndex < n && (sIndex >= sSize || arr[aIndex] <= sum[sIndex])) {
                a = arr[aIndex++];
            } else {
                a = sum[sIndex++];
            }

            long b;
            if (aIndex < n && (sIndex >= sSize || arr[aIndex] <= sum[sIndex])) {
                b = arr[aIndex++];
            } else {
                b = sum[sIndex++];
            }

            long s = a + b;
            cost += s;
            sum[sSize++] = s;
        }

        bw.write(Long.toString(cost));
        bw.close();
    }
}
