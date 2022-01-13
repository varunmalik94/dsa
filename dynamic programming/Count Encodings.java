/*

1. You are given a string str of digits. (will never start with a 0)
2. You are required to encode the str as per following rules
    1 -> a
    2 -> b
    3 -> c
    ..
    25 -> y
    26 -> z
3. You are required to calculate and print the count of encodings for the string str.

     For 123 -> there are 3 encodings. abc, aw, lc
     For 993 -> there is 1 encoding. iic 
     For 013 -> This is an invalid input. A string starting with 0 will not be passed.
     For 103 -> there is 1 encoding. jc
     For 303 -> there are 0 encodings. But such a string maybe passed. In this case 
     print 0.

Input Format
A string str

Output Format
count of encodings

Constraints
0 < str.length <= 10

Sample Input
123

Sample Output
3
*/

import java.io.*;
import java.util.*;

/*
 * Tabulation is very good of this problem - try to build the intution
 * attempting such question from last string index will be better
 */

public class Main {

  public static void main(String[] args) throws Exception {
    Scanner s = new Scanner(System.in);
    String str = s.next();

    // recursive approach
    // System.out.println(countEncodings(str, 0));

    // using memoization
    // int[] memo = new int[str.length()];
    // Arrays.fill(memo, -1);
    // System.out.println(countEncodingsMemo(str, 0, memo));
    
    System.out.println(countEncodingsTab(str));
  }
  
  // using tabulation - very good intution
  public static int countEncodingsTab(String str) {
      int len = str.length();
      // dp
      int[] dp = new int[len + 1];
      
      // because of empty string - there will be one encoding
      // try to get this intution from recursive/memoization approach
      // this is the smallest problem
      // dp[0] is the biggest problem
      dp[len] = 1;   
      
      for (int i = len - 1; i >= 0; i--) {
          int len_till_end = len - 1 - i;
          
          // choose current element
          int num1 = str.charAt(i) - '0';
          if (num1 >= 1 && num1 <= 9) 
            dp[i] = dp[i+1];

          // choose two element including element at i+1 index
          if (len_till_end > 0) {
              int num2 = num1 * 10 + (str.charAt(i+1) - '0');
              if (num2 >= 10 && num2 <= 26) 
                dp[i] += dp[i+2];
          }
      }
      return dp[0];
  }

  // using memoization
  public static int countEncodingsMemo(String str, int idx, int[] memo) {
    if (idx == str.length()) return 1;
    
    if (memo[idx] != -1) return memo[idx];
    int count = 0;
    
    int one = str.charAt(idx) - '0';
    if (one >= 1 && one <= 9) 
        count = countEncodings(str, idx + 1);

    int two_chars = 0;

    if (idx + 1 < str.length()) {
        two_chars = one * 10 + (str.charAt(idx + 1) - '0');
        if (two_chars >= 10 && two_chars <= 26) count += countEncodings(str, idx + 2);
    }
    
    memo[idx] = count;
    
    return count;
  }

  // using recursion
  public static int countEncodings(String str, int idx) {
    if (idx == str.length()) return 1;
    
    int count = 0;
    
    int one = str.charAt(idx) - '0';
    if (one >= 1 && one <= 9) 
        count = countEncodings(str, idx + 1);

    int two_chars = 0;

    if (idx + 1 < str.length()) {
        two_chars = one * 10 + (str.charAt(idx + 1) - '0');
        if (two_chars >= 10 && two_chars <= 26) count += countEncodings(str, idx + 2);
    }
    
    
    return count;
  }
}
