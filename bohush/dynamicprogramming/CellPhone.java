package bohush.dynamicprogramming;
import java.io.*;
import java.util.*;

public class CellPhone {

    static long[] prefix;
    static long[] weightedPrefix;
    static long[] arr;
    static long[][] dp;

    static long getWeightedSum(int l, int r) {
        long total = weightedPrefix[r + 1] - weightedPrefix[l];
        long plain = prefix[r + 1] - prefix[l];
        return total - l * plain;
    }

    static long func(int m, int n, int start_ind) {
        if (n == 1) {
            return getWeightedSum(start_ind, start_ind + m - 1);
        }
        if (dp[start_ind][n] != -1) return dp[start_ind][n];

        long min = Long.MAX_VALUE;
        for (int i = 0; i < m - n + 1; ++i) {
            long sum = getWeightedSum(start_ind, start_ind + i);
            long cur = sum + func(m - i - 1, n - 1, start_ind + i + 1);
            if (cur < min) {
                min = cur;
            }
        }
        dp[start_ind][n] = min;
        return min;
    }

    public static void main(String[] args) throws IOException {
        Scanner objIn = new Scanner(new File("in.txt"));
        int n = objIn.nextInt();
        int m = objIn.nextInt();
        arr = new long[m];
        for (int i = 0; i < m; ++i) {
            arr[i] = objIn.nextLong();
        }
        objIn.close();

        dp = new long[m + 1][n + 1];
        for (long[] row : dp) Arrays.fill(row, -1);

        prefix = new long[m + 1];
        weightedPrefix = new long[m + 1];
        for (int i = 0; i < m; i++) {
            prefix[i + 1] = prefix[i] + arr[i];
            weightedPrefix[i + 1] = weightedPrefix[i] + arr[i] * (i + 1);
        }

        PrintWriter out = new PrintWriter(new File("out.txt"));
        if (n >= m) {
            long sum = 0;
            for (long v : arr) sum += v;
            out.println(sum);
            out.close();
            return;
        }

        long result = func(m, n, 0);
        out.println(result);
        out.close();
    }
}