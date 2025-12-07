package bohush.dynamicprogramming;
import java.util.Scanner;

public class Units2 {
    static final long MOD = 1_000_000_007L;

    static long Factorial(long n)
    {
        long res = 1;
        for (long i = 1; i <= n; ++i)
        {
            res *= i;
            res = res % MOD;
        }
        return res;
    }

    static long PowInMod(long n, long exp)
    {
        long res = 1;
        n %= MOD;
        while(exp > 0)
        {
            if (exp % 2 == 1)
            {
                res = (res * n) % MOD;
            }
            n = (n * n) % MOD;
            exp /= 2;
        }
        return res;
    }

    public static void main(String[] args) {
        long n, k;
        Scanner objIn = new Scanner(System.in);
        n = objIn.nextLong();
        k = objIn.nextLong();
        objIn.close();
        long factN = Factorial(n);
        long afactK = PowInMod(Factorial(k), MOD - 2);
        long afactNK = PowInMod(Factorial(n - k), MOD - 2);
        factN = (((factN * afactK) % MOD) * afactNK) % MOD;
        System.out.println(factN) ;

    }
}