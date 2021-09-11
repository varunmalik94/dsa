import java.io.*;
import java.util.*;

public class Main{

/**
 * Tabulation is bottom up approach
 * Memoization is top down approach
 **/

public static void main(String[] args) throws Exception {
    // write your code here
    Scanner s = new Scanner(System.in);
    int n = s.nextInt();
    
    // using memoization
    int[] memo = new int[n+1];
    memo[0] = 0;
    memo[1] = 1;
    // System.out.print(fib(n, memo));
    
    // using tabulation
    for (int i = 2; i <= n; i++)
        memo[i] = memo[i-1] + memo[i-2];
        
    System.out.print(memo[n]);
 }
 
 public static int fib(int n, int[] memo){
     // base case 
     if (n <= 1) return n;
        
     // base case - dp
     if (memo[n] != 0) return memo[n];
     
     int fib1 = fib(n-1, memo);
     int fib2 = fib(n-2, memo);
     
     memo[n] = fib1 + fib2;
     return memo[n];
 }

}
