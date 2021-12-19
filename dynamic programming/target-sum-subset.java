/*

1. You are given a number n, representing the count of elements.
2. You are given n numbers.
3. You are given a number "tar".
4. You are required to calculate and print true or false, if there is a subset the elements of which add up to "tar" or not.

Input Format
A number n
n1
n2
.. n number of elements
A number tar

Output Format
true or false as required

Constraints
1 <= n <= 30
0 <= n1, n2, .. n elements <= 20
0 <= tar <= 50

Sample Input
5
4
2
7
1
3
10
Sample Output
true

*/

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        
        // read array
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++)
            numbers[i] = s.nextInt();
            
        int target = s.nextInt();
            
        // via recursion
        // System.out.println(targetSum(0, 0, n, numbers, target));
        
        // via memoization
        // int[][] memo = new int[n+1][target+1];
        // for (int i = 0; i <= n; i++)
        //     Arrays.fill(memo[i], -1);
            
        // System.out.print(targetSumMemo(0, 0, n, numbers, target, memo));
        System.out.println(targetSumTabulation(numbers, n, target));
    }
    
    // tabulation approach
    // Note: target should be affordable in size as we have to create 2d array of (numbers+1) * (target+1) size -  memory limited
    public static boolean targetSumTabulation(int[] numbers, int n, int target) {
        // table
        boolean table[][] = new boolean[n+1][target+1];
        
        // fill first row with false
        Arrays.fill(table[0], false);
        
        // fill first column with true values
        for (int firstRow = 0; firstRow < table.length; firstRow++)
            table[firstRow][0] = true;
            
        // loop over rows and then columns & identify whether current 
        // table value can be filled if we take current number or we dont take this number 
        // and just take previous numbers subset
        for (int row = 1; row <= n; row++) {
            for (int col = 1; col <= target; col++) {
                int thisNumber = numbers[row-1];
                
                // if we take thisNumber, can we create remainig sum from already taken sumbset containing numbers
                int takenDiff = col - thisNumber;
                if (takenDiff >= 0 && table[row-1][takenDiff] == true) {
                    table[row][col] = true;
                    continue;
                }
                
                // else we dont take thisNumber, can we create the current target from previous already taken subsets
                else if (table[row-1][col] == true) {
                    table[row][col] = true;
                }
            }
        }
        return table[n][target];    
    }
    
    // memoization approach
    public static boolean targetSumMemo(int sum, int i, int n, int[] numbers, int target, int[][] memo) {
        // base case
        if (sum > target || i >= n) return false;
        if (sum == target) return true;
        if (memo[i][sum] != -1) return memo[i][sum] == 1;

        int currentNumber = numbers[i];
        memo[i][sum] = ( targetSumMemo(sum + currentNumber, i + 1, n, numbers, target, memo) || targetSumMemo(sum, i + 1, n, numbers, target, memo) ) == false ? 0 : 1;
        return memo[i][sum] == 1;
    }
    
    // recursive approach
    public static boolean targetSum(int sum, int i, int n, int[] numbers, int target) {
        // base case
        if (sum > target || i >= n) return false;
        if (sum == target) return true;
        
        int currentNumber = numbers[i];
        return targetSum(sum + currentNumber, i + 1, n, numbers, target) || targetSum(sum, i + 1, n, numbers, target);
    }
}
