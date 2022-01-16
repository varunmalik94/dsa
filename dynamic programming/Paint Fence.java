/*

1. You are given a number n and a number k in separate lines, representing the number of fences and number of colors.
2. You are required to calculate and print the number of ways in which the fences could be painted so that not more than two consecutive  fences have same colors.

Input Format
A number n
A number k

Output Format
A number representing the number of ways in which the fences could be painted so that not more than two fences have same colors.

Constraints
1 <= n <= 10
1 <= k <= 10

Sample Input
8
3

Sample Output
3672

*/

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int k = s.nextInt();
        
        // tabular standard approach
        // System.out.println(paintFenceTotalNumbers(n, k));
        
        // easier approach - from recursion to tabulation
        System.out.println(paintFenceTotalNumbersEasier(n, k));
    }
    
    public static int paintFenceTotalNumbersEasier(int n, int k) {
        // dp 
        int[][] dp = new int[k][n];

        // base case 
        // fill first column with 1 and second column with k
        for (int j = 0; j < k; j++) {
            dp[j][0] = 1;
            dp[j][1] = k;
        }
        
        for (int i = 2; i < n; i++) {
            // loop for each color
            for (int j = 0; j < k; j++) {
                // at each dp[i][j], we need to fill sum of dp[rows][j-1] + dp[rows][j-2]
                int prevSum = getPreviColumnSums(dp, i, j, k);
                dp[j][i] = prevSum;
            }
        }
        
        int sum = 0;
        for (int i = 0; i < k; i++)
            sum += dp[i][n-1];
        return sum;
    }
    
    public static int getPreviColumnSums(int[][] dp, int i, int j, int k) {
        int prevSum = 0;
        for (int j_ = 0; j_ < k; j_++) {
            if (j != j_)
                prevSum += dp[j_][i-1] + dp[j_][i-2];
        }
        return prevSum;
    }
    
    // tabular approacu
    public static int paintFenceTotalNumbers(int n, int k) {
        // 2d dp of 2 rows - one for ii, other for ij
        int[][] dp = new int[2][n];
        dp[0][0] = 1;
        dp[1][0] = 1;
        
        // fill for n = 2
        dp[0][1] = k;
        dp[1][1] = k*(k-1);
        
        for (int i = 2; i < n; i++) {
            dp[0][i] = dp[1][i-1];
            dp[1][i] = (dp[0][i-1] + dp[1][i-1] ) * (k-1);
        }
        return dp[0][n-1] + dp[1][n-1];
    }
}
