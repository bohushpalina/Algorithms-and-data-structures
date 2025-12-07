package bohush.dynamicprogramming;

import java.util.Scanner;

public class BinarySearch {
    public static void main(String[] args)
    {
        int n;
        long found;
        Scanner objIn = new Scanner(System.in);
        n = objIn.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; ++i)
        {
            arr[i] = objIn.nextInt();
        }

        int asks;
        asks = objIn.nextInt();
        int[] arr_asks = new int[asks];

        for (int i = 0; i < asks; ++i)
        {
            arr_asks[i] = objIn.nextInt();
        }
        objIn.close();

        for (int i = 0; i < asks; ++i)
        {
            int b = 0, l, r;
            int x = arr_asks[i];

            int left = 0, right = n;
            while(left < right)
            {
                int k = (left + right) / 2;
                if (x <= arr[k])
                {
                    right = k;
                }
                else
                {
                    left = k + 1;
                }
            }
            l = left;

            left = 0;
            right = n;
            while(left < right)
            {
                int k = (left + right) / 2;
                if (x < arr[k])
                {
                    right = k;
                }
                else
                {
                    left = k + 1;
                }
            }
            r = left;

            if (l < r && arr[l] == arr_asks[i])
            {
                b = 1;
            }

            System.out.println(b + " " + l + " " + r);
        }

    }
}