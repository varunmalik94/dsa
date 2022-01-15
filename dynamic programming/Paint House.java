/*

1. You are given a number n, representing the number of houses.
2. In the next n rows, you are given 3 space separated numbers representing the cost of painting nth house with red or blue or green color.
3. You are required to calculate and print the minimum cost of painting all houses without painting any consecutive house with same color.

Input Format
A number n
n1red n1blue n1green
n2red n2blue n2green
.. n number of elements

Output Format
A number representing the minimum cost of painting all houses without painting any consecutive house with same color.

Constraints
1 <= n <= 1000
0 <= n1red, n1blue, .. <= 1000

Sample Input
4
1 5 7
5 8 4
3 2 9
1 2 4

Sample Output
8

*/

import java.io.*;
import java.util.*;

/*
 * @todo - do using recursion and memoization
 * @todo - do using more optimization
 */

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int[][] colors = new int[n][3];
        
        for (int i = 0; i < n; i++)
            for (int j = 0; j < 3; j++)
                colors[i][j] = s.nextInt();
                
        // tabluation 
        System.out.println(minHousePaintCost(colors, n));
    }
    
    public static int minHousePaintCost(int[][] colors, int n) {
        // dp 
        int[][] dp = new int[3][n];
        
        for (int i = n - 1; i >= 0; i--) {
            // red
            dp[0][i] = colors[i][0] + (i+1 < n ? Math.min(dp[1][i+1], dp[2][i+1]) : 0);
            
            // blue
            dp[1][i] = colors[i][1] + (i+1 < n ? Math.min(dp[0][i+1], dp[2][i+1]) : 0);
            
            // green
            dp[2][i] = colors[i][2] + (i+1 < n ? Math.min(dp[0][i+1], dp[1][i+1]) : 0);
        }
        return Math.min(dp[0][0], Math.min(dp[1][0], dp[2][0]));
    }
}
