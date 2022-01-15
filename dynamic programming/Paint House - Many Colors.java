/*

1. You are given a number n and a number k separated by a space, representing the number of houses and number of colors.
2. In the next n rows, you are given k space separated numbers representing the cost of painting nth house with one of the k colors.
3. You are required to calculate and print the minimum cost of painting all houses without painting any consecutive house with same color.

Input Format
A number n
n1-0th n1-1st n1-2nd .. n1-kth
n2-0th n2-1st n2-2nd .. n2-kth
.. n number of elements

Output Format
A number representing the minimum cost of painting all houses without painting any consecutive house with same color.

Constraints
1 <= n <= 1000
1 <= k <= 10
0 <= n1-0th, n1-1st, .. <= 1000

Sample Input
4 3
1 5 7
5 8 4
3 2 9
1 2 4

Sample Output
8

*/

// @todo can also be done in O(n*k). Use min and second min elements to remove extra array

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int k = s.nextInt();
        
        int[][] colors = new int[n][k];
        
        for (int i = 0; i < n; i++)
            for (int j = 0; j < k; j++)
                colors[i][j] = s.nextInt();
                
        // tabluation 
        // System.out.println(minHousePaintCostTab(colors, n, k));
        
        // efficient tabluation 
        System.out.println(minHousePaintCostTabEfficient(colors, n, k));
    }

    public static int minHousePaintCostTab(int[][] colors, int n, int k) {
        // dp 
        int[][] dp = new int[k][n];
        
        for (int i = n - 1; i >= 0; i--) {
            for (int k_ = 0; k_ < k; k_++) {
                int nextMin = Integer.MAX_VALUE;

                // find previous minimum from dp
                if (i+1 < n) {
                    for (int idx = 0; idx < k; idx++) 
                        if (k_ != idx) 
                            nextMin = Math.min(dp[idx][i+1], nextMin);
                }
                else nextMin = 0;
                
                dp[k_][i] = colors[i][k_] + nextMin;
            }
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i<k; i++)
            if (ans > dp[i][0]) 
                ans = dp[i][0];
        
        return ans;
    }
    
    public static int minHousePaintCostTabEfficient(int[][] colors, int n, int k) {
        // efficient dp tables
        // there will be only 2 dp table of 1d array of k size
        int[] dp1 = new int[k];
        
        for (int k_ = 0; k_ < k; k_++)
            dp1[k_] = colors[n-1][k_];
        
        for (int i = n-2; i >= 0; i--) {
            int[] dp_temp = new int[k];
            
            // fill dp_temp table by using dp1 table
            for (int k_ = 0; k_ < k; k_++) {
                int nextMin = findNextMinfromDP(dp1, k_, k);
                dp_temp[k_] = nextMin + colors[i][k_];
            }
            
            // fill dp1 table with dp_temp
            for (int k_ = 0; k_ < k; k_++)
                dp1[k_] = dp_temp[k_];
        }
        
        // return min color
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < k; i++)
            if (ans > dp1[i]) 
                ans = dp1[i];
        
        return ans;
    }
    
    public static int findNextMinfromDP(int[] dp1, int k_idx, int k) {
        int min = Integer.MAX_VALUE;
        for (int k_ = 0; k_ < k; k_++)
            if (k_ != k_idx && min > dp1[k_])
                min = dp1[k_];
        return min;
    }
}
