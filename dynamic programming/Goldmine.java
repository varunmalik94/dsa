/**

https://www.geeksforgeeks.org/maximum-path-sum-matrix/

https://www.youtube.com/watch?v=5KdvH15NJjc&list=TLGGJa7_0O5uq7YwNjEyMjAyMQ&ab_channel=Pepcoding

1. You are given a number n, representing the number of rows.
2. You are given a number m, representing the number of columns.
3. You are given n*m numbers, representing elements of 2d array a, which represents a gold mine.
4. You are standing in front of left wall and are supposed to dig to the right wall. You can start from 
     any row in the left wall.
5. You are allowed to move 1 cell right-up (d1), 1 cell right (d2) or 1 cell right-down(d3).
6. Each cell has a value that is the amount of gold available in the cell.
7. You are required to identify the maximum amount of gold that can be dug out from the mine.
Input Format
A number n
A number m
e11
e12..
e21
e22..
.. n * m number of elements

Output Format
An integer representing Maximum gold available.

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
33

*/


import java.io.*;
import java.util.*;

public class Main {

  public static void main(String[] args) throws Exception {
    // write your code here
    Scanner s = new  Scanner(System.in);
    int rows = s.nextInt();
    int cols = s.nextInt();

    // read gold mine
    int[][] mine = new int[rows][cols];
    for (int i = 0; i < rows; i++)
      for (int j = 0; j < cols; j++)
        mine[i][j] = s.nextInt();

    // recursive approach
    // System.out.println(mineRec(rows, cols, mine));

    // via memoization approach
    // int[][] memo = new int[rows][cols];
    // for (int r = 0; r < rows; r++)
    //   Arrays.fill(memo[r], -1);
    // System.out.println(mineMemo(rows, cols, mine, memo));
    
    // tabulation approach
    System.out.println(mineTabulation(rows, cols, mine));
  }

    // via tabulation
    public static int mineTabulation(int rows, int cols, int[][] mine) {
        int[][] table = new int[rows][cols];
        for (int r = 0; r < rows; r++)
            table[r][0] = mine[r][0];
            
        for (int c = 1; c < cols; c++) {
            for (int r = 0; r < rows; r++) {
                int v1 = Integer.MIN_VALUE, v2 = Integer.MIN_VALUE, v3 = Integer.MIN_VALUE;
                if (r-1 >= 0 && r-1 < rows) v1 = table[r-1][c-1];
                v2 = table[r][c-1];
                if (r+1 >= 0 && r+1 < rows) v3 = table[r+1][c-1];
                table[r][c] = mine[r][c] + Math.max(v1, Math.max(v2, v3));
            }
        }
        int max = Integer.MIN_VALUE;
        for (int r = 0; r < rows; r++)
            max = Math.max(max, table[r][cols-1]);
        return max;
        
    }

  // via memoization
  public static int mineMemo(int rows, int cols, int[][] mine, int[][] memo) {
    int max = Integer.MIN_VALUE;

    for (int startrow = 0; startrow < rows; startrow++) {
      int maxAmount = mineMemo(startrow, 0, rows, cols, mine, memo);
      max = Math.max(max, maxAmount);
    }
    return max;
  }

  // via memoization
  public static int mineMemo(int startrow, int startcol, int rows, int cols, int[][] mine, int[][] memo) {
    // base case
    if (startrow < 0 || startrow >= rows || startcol < 0 || startcol >= cols) return Integer.MIN_VALUE;
    if (startcol == cols - 1) return mine[startrow][cols - 1];
    if (memo[startrow][startcol] != -1) return memo[startrow][startcol];

    int max = Integer.MIN_VALUE;
    int maxAmount1 = mineMemo(startrow - 1, startcol + 1, rows, cols, mine, memo);
    int maxAmount2 = mineMemo(startrow, startcol + 1, rows, cols, mine, memo);
    int maxAmount3 = mineMemo(startrow + 1, startcol + 1, rows, cols, mine, memo);

    max = Math.max(maxAmount1, Math.max(maxAmount2, maxAmount3)) + mine[startrow][startcol];

    memo[startrow][startcol] = max;
    return max;
  }

  // via recursion
  public static int mineRec(int rows, int cols, int[][] mine) {
    int max = Integer.MIN_VALUE;

    for (int startrow = 0; startrow < rows; startrow++) {
      int maxAmount = mineRec(startrow, 0, rows, cols, mine);
      max = Math.max(max, maxAmount);
    }
    return max;
  }

  // via recursion
  public static int mineRec(int startrow, int startcol, int rows, int cols, int[][] mine) {
    // base case
    if (startrow < 0 || startrow >= rows || startcol < 0 || startcol >= cols) return Integer.MIN_VALUE;
    if (startcol == cols - 1) return mine[startrow][cols - 1];

    int max = Integer.MIN_VALUE;
    int maxAmount1 = mineRec(startrow - 1, startcol + 1, rows, cols, mine);
    int maxAmount2 = mineRec(startrow, startcol + 1, rows, cols, mine);
    int maxAmount3 = mineRec(startrow + 1, startcol + 1, rows, cols, mine);

    max = Math.max(maxAmount1, Math.max(maxAmount2, maxAmount3)) + mine[startrow][startcol];

    return max;
  }

}
