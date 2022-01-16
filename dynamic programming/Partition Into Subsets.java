/*

1. You are given a number n, representing the number of elements.
2. You are given a number k, representing the number of subsets.
3. You are required to print the number of ways in which these elements can be partitioned in k non-empty subsets.
E.g.
For n = 4 and k = 3 total ways is 6
12-3-4
1-23-4
13-2-4
14-2-3
1-24-3
1-2-34

Input Format
A number n
A number k

Output Format
A number representing the number of ways in which these elements can be partitioned in k non-empty subsets.

Constraints
0 <= n <= 20
0 <= k <= n

Sample Input
4
3

Sample Output
6

*/

import java.io.*;
import java.util.*;

public class Main {

  public static void main(String[] args) throws Exception {
    Scanner scn = new Scanner(System.in);
    int n = scn.nextInt();
    int k = scn.nextInt();

    // long res = partitionKSubset(n, k);
    // System.out.println(res);
    
    long res = partitionKSubsetTab(n, k);
    System.out.println(res);
  }
  
  // recursive
  public static long partitionKSubset(int n, int k) {
    // base cases
    if (k > n || n == 0) return 0;
    if (k == n || k == 1) return 1;
    
    long leftCount = partitionKSubset(n-1, k) * k;
    long rightCount = partitionKSubset(n-1, k-1);
    return leftCount + rightCount;
  }

    public static long partitionKSubsetTab(int n, int k) {
        // dp table
        long[][] dp = new long[n+1][k+1];
        
        // base cases
        Arrays.fill(dp[0], 0);
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 0;
            dp[i][1] = 1;
        }
        
        // dp[i][j] = j * dp[i-1][j] + dp[i-1][j-1]
        for (int i = 1; i <= n; i++) {
            for (int j = 2; j <= k; j++) {
                if (j > i) dp[i][j] = 0;
                else if (j == i) dp[i][j] = 1;
                else {
                    dp[i][j] = j * dp[i-1][j] + dp[i-1][j-1];
                }
            }
        }
        return dp[n][k];
    }
}
