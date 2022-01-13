/*

1. You are given a number n.
2. You are required to print the number of binary strings of length n with no consecutive 0's.

Input Format
A number n

Output Format
A number representing the number of binary strings of length n with no consecutive 0's.

Constraints
0 < n <= 45

Sample Input
6

Sample Output
21

*/

import java.io.*;
import java.util.*;

public class Main {

  public static void main(String[] args) throws Exception {
    // write your code here

    Scanner s = new Scanner(System.in);
    int n = s.nextInt();

    // System.out.println(validStrings(n));
    System.out.println(validStringsEffieienc(n));
  }

    public static int validStringsEffieienc(int n) {
        int zeroes = 0;
        int ones = 0;
        
        if (n == 0) return 0;
        
        zeroes = 1;
        ones = 1;
        
        for (int i = 2 ; i <= n; i++) {
            int temp = zeroes;
            zeroes = ones;
            ones = ones + temp;
        }
        return zeroes + ones;
    }

  // tabulation approach, O(n) - TC, O(n) - SC
  public static int validStrings(int n) {
    // dp tables
    int[] zeroes = new int[n];
    int[] ones = new int[n];

    zeroes[0] = 1;
    ones[0] = 1;

    for (int i = 1; i < n; i++){
        zeroes[i] = ones[i-1];
        ones[i] = zeroes[i-1] + ones[i-1];
    }
    return zeroes[n-1] + ones[n-1];
  }
}


/*
 * this is a fibonacci problem
 */
