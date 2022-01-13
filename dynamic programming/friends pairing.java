/*
1. You are given a number n, representing the number of friends.
2. Each friend can stay single or pair up with any of it's friends.
3. You are required to print the number of ways in which these friends can stay single or pair up.
E.g.
1 person can stay single or pair up in 1 way.
2 people can stay singles or pair up in 2 ways. 12 => 1-2, 12.
3 people (123) can stay singles or pair up in 4 ways. 123 => 1-2-3, 12-3, 13-2, 23-1.

Input Format
A number n

Output Format
A number representing the number of ways in which n friends can stay single or pair up.

Constraints
0 <= n <= 20

Sample Input
4

Sample Output
10
*/
import java.io.*;
import java.util.*;

/*
 * apply memo and dp here 
 */

public class Main {

    public static void main(String[] args) throws Exception {
        // write your code here
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        
        System.out.println(friendPairs(n));
    }
    
    public static int friendPairs(int n) {
        if (n < 0) return 0;
        if (n == 0) return 1;
        
        int count = 0;
        
        // first single
        count = friendPairs(n-1);
        
        // now in pairs i.e., 12, 13, 14 and so on
        // for (int i = 0; i < n-1; i++)
        //     count += friendPairs(n-2);
        
        // or we can also do below one
        int count2 = friendPairs(n-2);
        count += count2 * (n-1);
        
        return count;
    }
}
