package bohush.dynamicprogramming;

import java.util.Scanner;

public class FrogWay {
    
    public static void main(String[] args) {
        int n;
        Scanner objIn = new Scanner(System.in);
        n = objIn.nextInt();
        long[] arr = new long[n];
        long[] b = new long[n];
        int counter = 0;

        for (int i = 0; i < n; ++i)
        {
            arr[i] = objIn.nextInt();
        }
        objIn.close();

        long[] dp = new long[n];
        if (n > 0)
        {dp[0] = arr[0];}
        if (n > 1)
        {dp[1] = -1;}
        if (n > 2)
        {dp[2] = arr[0] + arr[2];}
        for (int i = 3; i < n; ++i)
        {
            dp[i] = arr[i] + Math.max(dp[i - 2], dp[i - 3]);
            if (dp[i - 2] > dp [i - 3])
            {
                b[i] = i - 2;
            }
            else
            {
                b[i] = i - 3;
            }
        }
        System.out.println(dp[n - 1] );
        long current = n - 1;
        for (int i = n - 1; i >= 0; --i)
        {
            if (current == i)
            {
                current = b[i];
                arr[counter] = i + 1;
                ++counter;
            }
        }
        if (dp[n - 1] != -1)
        {for (int i = counter - 1; i >= 0; --i)
        {
            System.out.print(arr[i] + " ");
        }}
       }
}
