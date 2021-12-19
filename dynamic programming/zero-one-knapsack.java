/*

Zero One Knapsack

1. You are given a number n, representing the count of items.
2. You are given n numbers, representing the values of n items.
3. You are given n numbers, representing the weights of n items.
3. You are given a number "cap", which is the capacity of a bag you've.
4. You are required to calculate and print the maximum value that can be created in the bag without overflowing it's capacity.

Note -> Each item can be taken 0 or 1 number of times. You are not allowed to put the same item again and again.

Input Format
A number n
v1 v2 .. n number of elements
w1 w2 .. n number of elements
A number cap

Output Format
A number representing the maximum value that can be created in the bag without overflowing it's capacity

Constraints
1 <= n <= 20
0 <= v1, v2, .. n elements <= 50
0 < w1, w2, .. n elements <= 10
0 < cap <= 10

Sample Input
5
15 14 10 45 30
2 5 1 3 4
7

Sample Output
75

*/

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int[] values = new int[n];
        int[] weights = new int[n];
        
        // read values
        for (int i = 0; i < n; i++)
            values[i] = s.nextInt();
            
        // read weights
        for (int i = 0; i < n; i++)
            weights[i] = s.nextInt();
            
        int capacity = s.nextInt();
        
        System.out.println(ZeroOneKnapsack(values, weights, capacity));
    }
    
    // using tabulation
    public static int ZeroOneKnapsack(int[] values, int[] weights, int capacity) {
        // create a table of n+1 * capacity+1 size
        int[][] table = new int[values.length+1][capacity+1];
        
        // fill it with zero
        for (int i = 0; i < table.length; i++)
            Arrays.fill(table[i], 0);
            
        int n = values.length;
            
        // iterate row and then column and find current maximum weight possible
        for (int row = 1; row <= n; row++) {
            for (int col = 1; col <= capacity; col++) {
                int currentWeight = weights[row-1];
                int currentValue = values[row-1];
                
                // yes means we are taking current weight
                int yes_weight = col - currentWeight >= 0 ? ( currentValue + table[row-1][col - currentWeight] ) : 0 ;
                
                // no means we are not taking current weight
                int no_weight = table[row-1][col];
                
                table[row][col] = Math.max(yes_weight, no_weight);
            }
        }
        return table[table.length-1][capacity];
    }
}
