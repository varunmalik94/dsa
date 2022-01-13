/*

1. You are given a number n representing the length of a floor space which is 2m wide. It's a 2 * n board.
2. You've an infinite supply of 2 * 1 tiles.
3. You are required to calculate and print the number of ways floor can be tiled using tiles.

Input Format
A number n

Output Format
A number representing the number of ways in which the number of ways floor can be tiled using tiles.

Constraints
1 <= n <= 100

Sample Input
8

Sample Output
34
*/

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        
        // recursive approach
        // System.out.println(totalTiles(n));
        
        // memoization approach
        // int[] memo = new int[n+1];
        // Arrays.fill(memo, -1);
        // System.out.println(totalTilesMemo(n, memo));
    
        // tabulation approach
        System.out.println(totalTilesTab(n));
    }
    
    // tabulation approach - start from base case to last case in tabulation always
    public static int totalTilesTab(int n) {
        // base cases
        if (n == 0 || n == 1) return 1;
        
        // table
        int[] dp = new int[n+1];
        dp[0] = dp[1] = 1;
        
        for (int i = 2; i <= n; i++)
            dp[i] = dp[i-1] + dp[i-2];
        
        return dp[n];
        
    }
    
    // memoization approach
    public static int totalTilesMemo(int n, int[] memo) {
        // base cases
        if (n == 0 || n == 1) return 1;
        if (memo[n] != -1) return memo[n];
        
        memo[n] = totalTilesMemo(n-1, memo);
        memo[n] += totalTilesMemo(n-2, memo);
        
        return memo[n];
    }
    
    // recursive approach, O(2^n) - Tc, O(n) - SC
    public static int totalTiles(int n) {
        if (n == 0 || n == 1) return 1;
        
        int count = 0;
        count = totalTiles(n-1);
        count += totalTiles(n-2);
        return count;
    }
}
