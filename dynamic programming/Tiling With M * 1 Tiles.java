/*
1. You are given a number n and a number m separated by line-break representing the length and breadth of a m * n floor.
2. You've an infinite supply of m * 1 tiles.
3. You are required to calculate and print the number of ways floor can be tiled using tiles.

Input Format
A number n
A number m

Output Format
A number representing the number of ways in which the number of ways floor can be tiled using tiles.

Constraints
1 <= n <= 100
1 <= m <= 50

Sample Input
39
16

Sample Output
61
*/
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner s =new Scanner (System.in);
        int n = s.nextInt();
        int m = s.nextInt();
        
        // recursive approach
        // System.out.println(dynamicTiles(m, n));
        
        // memoization approach
        // int[] memo = new int[n+1];
        // Arrays.fill(memo, -1);
        // System.out.println(dynamicTilesMemo(m, n, memo));
        
        // tabulation approach
        System.out.println(dynamicTilesTab(m, n));
    }
    
    // tab approach
    public static int dynamicTilesTab(int m, int n) {
        // dp table
        int[] dp = new int[n+1];
        dp[0] = dp[1] = 1;
        
        for (int i = 2; i <= n; i++)
            dp[i] = (i-m >= 0? dp[i-m]: 0) + dp[i-1];
        return dp[n];
    }

    //  memo approach
    public static int dynamicTilesMemo(int m, int n, int[] memo) {
        if (n < 0) return 0;
        if (n == 1 || n == 0) return 1;
        if (memo[n] != -1) return memo[n];

        int count = 0;
        
        // vertical
        count = dynamicTilesMemo(m, n - 1, memo);
        
        // horizontal
        count += dynamicTilesMemo(m, n - m, memo);
        memo[n] = count;
        return count;
    }
    
    // recursive approach
    public static int dynamicTiles(int m, int n) {
        if (n < 0) return 0;
        if (n == 1 || n == 0) return 1;
        
        int count = 0;
        
        // vertical
        count = dynamicTiles(m, n - 1);
        
        // horizontal
        count += dynamicTiles(m, n - m);
        return count;
    }
}
