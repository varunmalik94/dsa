import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        // write your code here
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        
        // using Memoization
        // int[] memo = new int[n+1];
        // memo[0] = 0;
        // System.out.print(countSteps(n, memo));
        
        // using tabulation
        int[] table = new int[n+1];
        table[0] = 0;
        System.out.print(countStepsTabular(n, table));
    }
    
    // memo version
    public static int countStepsMemo(int n, int[] memo) {
        // base cases
        if (n < 0) return 0;
        if (n == 0) return 1;
        
        if (memo[n] != 0)  return memo[n];
        
        memo[n] = countStepsMemo(n - 1,memo) + 
                    countStepsMemo(n - 2,memo) +
                    countStepsMemo(n - 3,memo);
        return  memo[n];
    }
    
    public static int countStepsTabular(int n, int[] table) {
        if (n == 0) return 0;
        table[1] = 1;
        table[2] = 2;
        table[3] = 4;
        if (n <= 3) return table[n];
        
        for (int i = 4; i <= n; i++) {
            table[n] = table[n-1] + table[n-2] + table[n-3];
        }
        return table[n];
    }
}
