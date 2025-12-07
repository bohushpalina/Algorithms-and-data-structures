package bohush.specialdatastructures;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class BinomialHeap {
	public static void main(String[] args) throws Exception{
		
		Scanner objIn = new Scanner(new File("input.txt"));
		//Scanner objIn = new Scanner(System.in);

		long n = objIn.nextLong();
		FileWriter objOut = new FileWriter("output.txt");
		
		int index = 0;
        while (n > 0) {
            if ((n & 1L) == 1L) {
                objOut.write(index + "\n");
            }
            n >>= 1;
            index++;
        }
		
		objOut.close();
	}

}
