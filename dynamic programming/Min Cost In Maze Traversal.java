/*

1. You are given a number n, representing the number of rows.
2. You are given a number m, representing the number of columns.
3. You are given n*m numbers, representing elements of 2d array a, which represents a maze.
4. You are standing in top-left cell and are required to move to bottom-right cell.
5. You are allowed to move 1 cell right (h move) or 1 cell down (v move) in 1 motion.
6. Each cell has a value that will have to be paid to enter that cell (even for the top-left and bottom- 
     right cell).
7. You are required to traverse through the matrix and print the cost of path which is least costly.

Input Format
A number n
A number m
e11
e12..
e21
e22..
.. n * m number of elements

Output Format
The cost of least costly path.

Constraints
1 <= n <= 10^2
1 <= m <= 10^2
0 <= e1, e2, .. n * m elements <= 1000

Sample Input
6
6
0 1 4 2 8 2
4 3 6 5 0 4
1 2 4 1 4 6
2 0 7 3 2 2
3 1 5 9 2 4
2 7 0 8 5 1

Sample Output
23

*/


import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        // write your code here
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int m = s.nextInt();
        
        int[][] costs = new int[n][m];
        
        for (int r = 0; r < n; r++) 
            for (int c = 0; c < m; c++)
                costs[r][c] = s.nextInt();
                
        // recursive exponential
        // System.out.print(minCost(costs, 0, 0, n-1, m-1));
        
        // memoization approach 
        // int[][] memo = new int[n][m];
        // System.out.print(minCostMemo(costs, 0, 0, n-1, m-1, memo));
        
        // tabular approach
        System.out.print(minCostTabular(costs, n, m));
    }

    // exponential recursive - here time limit exceeded error occurs
    public static int minCost(int[][] maze, int row, int col, int n, int m) {
        // base case
        if (row > n || col > m) return Integer.MAX_VALUE;
        if (row == n && col == m) return maze[row][col];
        
        // find horizontal cost
        int hCost = minCost(maze, row, col+1, n, m);
        
        // find vertical(down) cost
        int vCost = minCost(maze, row+1, col, n, m);
        
        int currentCost = maze[row][col];
        int remainingMinCost = Math.min(hCost, vCost);
        if (remainingMinCost != Integer.MAX_VALUE)
            return remainingMinCost + currentCost;
        return remainingMinCost;
    }
    
    public static int minCostMemo(int[][] costs, int sr, int sc, int dr, int dc, int[][] memo) {

        // base case 
        if (sr > dr || sc > dc) return Integer.MAX_VALUE;
        if (sr == dr && sc == dc) return costs[dr][dc];
        
        // base case
        if (memo[sr][sc] != 0) return memo[sr][sc];
        
        int c1 = minCostMemo(costs, sr+1, sc, dr, dc, memo);
        int c2 = minCostMemo(costs, sr, sc+1, dr, dc, memo);
        
        memo[sr][sc] = costs[sr][sc] + Math.min(c1, c2);
        return memo[sr][sc];
    }
    
    public static int minCostTabular(int[][] maze, int n, int m) {
        int[][] table = new int[n][m];
        
        // this is the atleast condition that needs to be added
        table[0][0] = maze[0][0];
        
        for (int col = 0; col < m; col++) {
            for (int row = 0; row < n; row++) {
                int top = Integer.MAX_VALUE;
                int left = Integer.MAX_VALUE;
                
                if (row > 0) top = table[row-1][col];
                if (col > 0) left = table[row][col-1];
                
                int currentCost = maze[row][col];
                if (row == 0 && col == 0) 
                    continue;
                else    
                    table[row][col] = currentCost + Math.min(top, left);
            }
        }
        return table[n-1][m-1];
    }

}
