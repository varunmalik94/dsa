/*

1. You are given a number n, representing the number of stairs in a staircase.
2. You are on the 0th step and are required to climb to the top.
3. You are given n numbers, where ith element's value represents - till how far from the step you 
     could jump to in a single move.  
     You can of course jump fewer number of steps in the move.
4. You are required to print the number of different paths via which you can climb to the top.

Input format
A number n
.. n more elements

Output Format
A number representing the number of ways to climb the stairs from 0 to top.

Constraints
0 <= n <= 20
0 <= n1, n2, .. <= 20

Sample Input
10
3
3
0
2
1
2
4
2
0
0

Sample Output
5

*/


import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        // write your code here
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        
        int[] moves = new int[n];
        
        for (int i = 0; i<n; i++)
            moves[i] = s.nextInt();
        
        // memoization
        // int[] memo = new int[n+1];
        // Arrays.fill(memo, -1);
        // System.out.println(countStepsMemo(0, n, moves, memo));
        
        // tabular approach
        int[] table = new int[n+1];
        Arrays.fill(table, 0);
        System.out.println(countStepsTabular(n, moves, table));
    }

    public static int countStepsTabular(int n, int[] moves, int[] table) {
        // base case 
        // because from 0 to 0, there will always be 1 way
        // i.e., not go anywhere
        table[0] = 1;
        for (int i = 0; i < n; i++) {
            for (int jump = 1; jump <= moves[i]; jump++)
                if (i + jump <= n)
                    table[i + jump] += table[i];
        }
        return table[n];
    }
    
    public static int countStepsMemo(int src, int n, int[] moves, int[] memo) {
        // base cases
        if (src > n) return 0;
        if (src == n ) return 1;
        
        // memo case
        if (memo[src] != -1) return memo[src];
        
        memo[src] = 0;
        
        for (int jump = 1; jump <= moves[src]; jump++)
            memo[src] += countStepsMemo(src + jump, n, moves, memo);
            
        return memo[src];
    }

}
