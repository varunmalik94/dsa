/*

1. You are given a number n, representing the number of days.
2. You are given n numbers, where ith number represents price of stock on ith day.
3. You are required to print the maximum profit you can make if you are allowed infinite transactions, but have to cooldown for 1 day after 1 transaction
i.e. you cannot buy on the next day after you sell, you have to cooldown for a day at-least before buying again.
Note - There can be no overlapping transaction. One transaction needs to be closed (a buy followed by a sell) before opening another transaction (another buy).

Input Format
A number n
.. n more elements

Output Format
A number representing the maximum profit you can make if you are allowed infinite transactions with cooldown of 1 day.

Constraints
0 <= n <= 20
0 <= n1, n2, .. <= 10

Sample Input
12
10
15
17
20
16
18
22
20
22
20
23
25

Sample Output
19

*/

import java.io.*;
import java.util.*;

/*
 * @todo - do it using 2 columns without taking extra cooldown column
 * this is very good insight - basicall day-1 will be cooldown and 
 * we will read from dp[day-2][1]
 */

public class Main {

    public static void main(String[] args) throws Exception {
        // write your code here
        Scanner s = new Scanner(System.in);
        int days = s.nextInt();
        int[] prices = new int[days];
        
        for (int day = 0; day < days; day++)
            prices[day] = s.nextInt();
            
        // System.out.println(maxPriceCooldown(prices, days));
        System.out.println(maxPriceCooldownEfficient(prices, days));
    }
    
    // here combination should be like BSCBS
    // B for buy first, S for Sell after Buy. C for Buy first Sell then and then Cooldown
    public static int maxPriceCooldown(int[] prices, int days) {
        // dp with 3 columns 
        // dp[][0] for buy extra
        // dp[][1] for sell
        // dp[][2] for cooldown state
        int[][] dp = new int[days][3];
        dp[0][0] = -prices[0];
        
        for (int day = 1; day < days; day++) {
            // fill buy extra column
            dp[day][0] = Math.max(dp[day - 1][0], dp[day - 1][2] - prices[day]);
            
            // fill sell column
            dp[day][1] = Math.max(dp[day - 1][1], dp[day-1][0] + prices[day]);
            
            // maintain the cooldown period
            dp[day][2] = Math.max(dp[day - 1][2], dp[day - 1][1]);
        }
        return dp[days-1][1];
    }
    
    public static int maxPriceCooldownEfficient(int[] prices, int days) {
        // dp
        int[][] dp = new int[days][2];

        // base cases
        dp[0][0] = -prices[0];
        // dp[1][0] = Math.max(dp[0][0], -prices[1]);      // here as previously there is no cooldown so we can only
        
        for (int day = 2; day < days; day++) {
            // fill buy extra column first
            dp[day][0] = Math.max(dp[day - 1][0], (i-2) >= 0? (dp[day - 2][1] - prices[day]) : -prices[day]);
            
            // fill sell column
            dp[day][1] = Math.max(dp[day - 1][1], dp[day-1][0] + prices[day]);
        }
        return dp[days-1][1];
    }
}
