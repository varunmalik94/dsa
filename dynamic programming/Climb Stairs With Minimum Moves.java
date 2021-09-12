/*
1. You are given a number n, representing the number of stairs in a staircase.
2. You are on the 0th step and are required to climb to the top.
3. You are given n numbers, where ith element's value represents - till how far from the step you 
     could jump to in a single move.  You can of-course fewer number of steps in the move.
4. You are required to print the number of minimum moves in which you can reach the top of 
     staircase.
Note -> If there is no path through the staircase print null.

Input Format
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
4

*/


import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        // write your code here
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        
        // moves
        int[] moves = new int[n];
        for (int i = 0; i< n; i++)
            moves[i] = s.nextInt();
        
        // exponential
        // System.out.println(minSteps(0, n, moves));
        
        // Memoization
        // int[] memo = new int[n+1];
        // Arrays.fill(memo, -1);
        // int result = minStepsMemo(0, n, moves, memo);
        // System.out.println(result == 0 ? null : result);
        
        // Tabulation
        int[] table = new int[n+1];
        Arrays.fill(table, Integer.MAX_VALUE);
        int result = minStepsTabular(n, moves, table);
        System.out.println(result == 0 ? null : result);
    }
    
    // exponentional recursion
    // TC - O(n^n)
    public static int minSteps(int src, int dest, int[] moves) {
        // base case 
        if (src > dest) return Integer.MAX_VALUE;
        if (src == dest) return 0;
        
        int minMoves = Integer.MAX_VALUE;
        for (int jump = 1; jump <= moves[src]; jump++) {
            int currentMoves = minSteps(src + jump, dest, moves);
            minMoves = Math.min(minMoves, currentMoves);
        }
        if (minMoves == Integer.MAX_VALUE) return Integer.MAX_VALUE;
        return minMoves + 1;
    }
    
    // Memoization approach - top to bottom approach
    // TC - O(n^2)
    public static int minStepsMemo(int src, int dest, int[] moves, int[] memo) {
        // base case 
        if (src > dest) return Integer.MAX_VALUE;
        if (src == dest) return 0;
        
        // memo base case
        if (memo[src] != -1) return memo[src];

        int minMoves = Integer.MAX_VALUE;
        for (int jump = 1; jump <= moves[src]; jump++) {
            int currentMoves = minSteps(src + jump, dest, moves);
            minMoves = Math.min(minMoves, currentMoves);
        }
        if (minMoves == Integer.MAX_VALUE) { 
            memo[src] = Integer.MAX_VALUE;
        }
        else memo[src] = minMoves + 1;
        return memo[src];
    }
    
    // Tabulation approach - bottom to top approach
    // TC - O(n^2)
    public static int minStepsTabular(int n, int[] moves, int[] table) {
        int minSteps = Integer.MAX_VALUE;
        
        // base case, because from n to n there is no move possible
        table[n] = 0;

        // start from last and go till first element
        for (int i = n - 1; i >= 0; i--) {
            minSteps = Integer.MAX_VALUE;
            for (int jump = 1; jump <= moves[i]; jump++) {
                if (i + jump <= n)
                    minSteps = Math.min(minSteps, table[ i + jump ]);
            }
            if (minSteps != Integer.MAX_VALUE) table[i] = minSteps + 1;
        }
        return table[0];
    }
}
