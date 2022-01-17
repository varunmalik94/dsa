/*

1. You are given a number n, representing the number of days.
2. You are given n numbers, where ith number represents price of stock on ith day.
3. You are required to print the maximum profit you can make if you are allowed infinite transactions.
Note - There can be no overlapping transaction. One transaction needs to be closed (a buy followed by a sell) before opening another transaction (another buy)

Input Format
A number n
.. n more elements

Output Format
A number representing the maximum profit you can make if you are allowed infinite transactions.

Constraints
0 <= n <= 20
0 <= n1, n2, .. <= 10

Sample Input
9
11
6
7
19
4
1
6
18
4

Sample Output
30

*/

import java.io.*;
import java.util.*;

/*
 * @todo use Math.max() instead of if
 * @todo do using Greedy Technique
 */ 

public class Main {

    public static void main(String[] args) throws Exception {
        // write your code here
        Scanner s = new Scanner(System.in);
        int days = s.nextInt();
        int[] prices = new int[days];
        for (int day = 0; day < days; day++)
            prices[day] = s.nextInt();
            
        System.out.println(maxProfitTab(prices, days));
    }
    
    public static int maxProfitTab(int[] prices, int days) {
        // dp
        // contains extra buy
        int[] buyExtra = new int[days];
        // sells balances with previous buys
        int[] sell = new int[days];
        
        // base case
        buyExtra[0] = prices[0] * -1;
        
        for (int day = 1; day < days; day++) {
            
            // buying option
            if (buyExtra[day-1] > sell[day-1] - prices[day])
                buyExtra[day] = buyExtra[day-1];
            else buyExtra[day] = sell[day-1] - prices[day];
            
            // selling option
            if (buyExtra[day - 1] + prices[day] > sell[day - 1]) 
                sell[day] = buyExtra[day - 1] + prices[day];
            else sell[day] = sell[day - 1];
        }
        return sell[days-1];
    }

}
