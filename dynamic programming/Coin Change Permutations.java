/*

1. You are given a number n, representing the count of coins.
2. You are given n numbers, representing the denominations of n coins.
3. You are given a number "amt".
4. You are required to calculate and print the number of permutations of the n coins using which the 
     amount "amt" can be paid.

Note1 -> You have an infinite supply of each coin denomination i.e. same coin denomination can be 
                  used for many installments in payment of "amt"
Note2 -> You are required to find the count of permutations and not combinations i.e.
                  2 + 2 + 3 = 7 and 2 + 3 + 2 = 7 and 3 + 2 + 2 = 7 are different permutations of same 
                  combination. You should treat them as 3 and not 1.

Input Format
A number n
n1
n2
.. n number of elements
A number amt

Output Format
A number representing the count of combinations of coins which can be used to pay the amount "amt"

Constraints
1 <= n <= 20
0 <= n1, n2, .. n elements <= 20
0 <= amt <= 30

Sample Input
4
2
3
5
6
7

Sample Output
5

*/

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        
        int[] coins = new int[n];
        
        // read coins
        for (int i=0; i<n; i++)
            coins[i] = s.nextInt();
        
        int target = s.nextInt();
        
        // recursive approach
        // System.out.println(permutations(coins, target, 0));
        
        // memoization approach
        // int[] memo = new int[target+1];
        // Arrays.fill(memo, -1);
        // System.out.println(permutationsMemo(coins, target, 0, memo));
        
        // tabulation approach
        System.out.println(permutationsTabular(coins, target));
    }
    
    // tabulation approach
    public static int permutationsTabular(int[] coins, int target) {
        // table at ith index contains total number of permutations 
        // at ith index using provided suppy of coins
        int[] dp = new int[target+1];
        // set it to 1 because there exists 1 permutation that can be made 
        // by choosing no coin
        dp[0] = 1;
        
        for (int i = 0; i <= target; i++) {
            // choose each coin
            for (int j = 0; j < coins.length; j++) {
                int coin = coins[j];
                int currentDpItem = dp[i];
                if (coin + i <= target) dp[coin + i] += currentDpItem;
            }
        }
        return dp[target];
    }
    
    // memoization approach
    public static int permutationsMemo(int[] coins, int target, int sum, int[] memo) {
        // base cases
        if (sum > target) return 0;
        if (sum == target) return 1;
        if (memo[sum] != -1) return memo[sum];
        
        int total = 0;
        for (int i = 0; i < coins.length; i++)
            total += permutationsMemo(coins, target, sum + coins[i], memo);

        memo[sum] = total;
        return total;
    }

    // recursive approach
    public static int permutations(int[] coins, int target, int sum) {
        // base cases
        if (sum > target) return 0;
        if (sum == target) return 1;
        
        int total = 0;
        for (int i = 0; i < coins.length; i++)
            total += permutations(coins, target, sum + coins[i]);
            
        return total;
    }
}
