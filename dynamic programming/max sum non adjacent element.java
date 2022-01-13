/*

1. You are given a number n, representing the count of elements.
2. You are given n numbers, representing n elements.
3. You are required to find the maximum sum of a subsequence with no adjacent elements.

Input Format
A number n
n1
n2
.. n number of elements

Output Format
A number representing the maximum sum of a subsequence with no adjacent elements.

Constraints
1 <= n <= 1000
-1000 <= n1, n2, .. n elements <= 1000

Sample Input
6
5
10
10
100
5
6

Sample Output
116
*/

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int[] elements = new int[n];
        for (int i = 0; i< n; i++)
            elements[i]  = s.nextInt();
            
        // recursion
        // System.out.println(maxAdjacentSum(elements, n, 0));
        
        // memoization 
        // int[] memo = new int[n+1];
        // Arrays.fill(memo, Integer.MIN_VALUE);
        // System.out.println(maxAdjacentSumMemo(elements, n, 0, memo));
        
        // tabular approach
        System.out.println(maxAdjacentTab(elements, n));
    }
    
    // tabular approach
    public static int maxAdjacentTab(int[] elements, int n) {
        // dp table
        int[] dp = new int[n];

        // why n+1 size, because to hold base conddition in dp[n] = 0
        // which can further be used in previous indexes
        // can also be done - for it we would need to check below if array out of bounds, use 0
        dp[n-1] = elements[n-1];
        dp [n-2] = Math.max(dp[n-1], elements[n-2]);

        for (int i = n-3; i >= 0; i--) {
            int sum1 = 0;
            int sum2 = 0;

            // take current element
            sum1 = elements[i] + dp[i+2];
            
            // dont take current element, instead take next element
            sum2 = dp[i+1];
            dp[i] = Math.max(sum1, Math.max(sum2, Math.max(elements[i], dp[i+2])));
            // why this one in above statement - Math.max(elements[i], dp[i+2])
            // because of -ve element, sum1 can be lower but elements[i] or dp[i+2] alone can be bigger
        }
        return dp[0];        
    }
    
    // either take current element or dont take current element
    public static int maxAdjacentSumMemo(int[] elements, int n, int idx, int[] memo) {
        if (idx >= n) return 0;
        if (memo[idx] != Integer.MIN_VALUE) return memo[idx];
            
        int sum1 = 0;
        int sum2 = 0;
        
        // take current element 
        sum1 += elements[idx] + maxAdjacentSumMemo(elements, n, idx+2, memo);
        
        // don't take current element so take next element
        sum2 = maxAdjacentSumMemo(elements, n, idx+1, memo);
        
        memo[idx] = Math.max(sum1, sum2);
        return memo[idx];
    }
}
