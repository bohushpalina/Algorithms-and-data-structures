package bohush.dynamicprogramming;
import java.util.Scanner;
import java.io.*;

public class Palindrome {
 public static void main(String[] args) throws Exception
 {
  Scanner objIn = new Scanner(new File("input.txt"));
     String s = objIn.nextLine();
     objIn.close();
     
     int n = s.length();
        

     int[][] arr = new int[n][n];
     for (int i = 0; i < n; ++i)
     {
      for (int j = i; j >= 0; --j)
      {
       if (j == i)
        arr[j][i] = 1;
       else if (i == j + 1)
       {
        if (s.charAt(i) == s.charAt(j))
        {
         arr[j][i] = 2;
        }
        else
        {
         arr[j][i] = 1;
        }
       }
       else
       {
        if (s.charAt(i) == s.charAt(j))
        {
         arr[j][i] = 2 + arr[j + 1][i - 1];
        }
        else
        {
         arr[j][i] = Math.max(arr[j + 1][i], arr[j][i - 1]);
        }
       }
      }
     }
     
    
     FileWriter writer = new FileWriter("output.txt");
     writer.write(String.valueOf(arr[0][n - 1]));
     writer.write(System.lineSeparator());
     
        StringBuilder leftPart = new StringBuilder();
        StringBuilder rightPart = new StringBuilder();
        
     int i = 0, j = n - 1;
        
     while(i <= j)
     {
      if (s.charAt(i) == s.charAt(j))
      {
                if (i == j) {
                    leftPart.append(s.charAt(i));
                } else {
                    leftPart.append(s.charAt(i)); 
                    rightPart.append(s.charAt(j));
                }
       
       i += 1;
       j -= 1;
      }
      else
      {
               
       if (arr[i + 1][j] >= arr[i][j - 1])
       {
        i += 1;
       }
       else
       {
        j -= 1;
       }
  
      }
     }
     String leftPartString = leftPart.toString();
     for (int k = 0; k < leftPartString.length(); k++) {
         writer.write(leftPartString.charAt(k));
     }
     String rightPartString = rightPart.toString();
     for (int k = rightPartString.length() - 1; k >= 0; k--) {
         writer.write(rightPartString.charAt(k));
     }
  writer.close();
  
 }
}

