/*

1. You are given a number n, representing the number of people in a party.
2. You are given n strings of n length containing 0's and 1's
3. If there is a '1' in ith row, jth spot, then person i knows about person j.
4. A celebrity is defined as somebody who knows no other person than himself but everybody else knows him.
5. If there is a celebrity print it's index otherwise print "none".

Note -> There can be only one celebrity. Why?

Input Format
Input is managed for you  

Output Format
Index of celebrity or none

Constraints
1 <= n <= 10^4
e1, e2, .. n * n elements belongs to the set (0, 1)

Sample Input
4
0000
1011
1101
1110

Sample Output
0

*/

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        // write your code here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][n];

        for (int j = 0; j < n; j++) {
            String line = br.readLine();
            for (int k = 0; k < n; k++) {
                arr[j][k] = line.charAt(k) - '0';
            }
        }

        findCelebrity(arr);

    }

    // can also be done by using any other data structure
    public static void findCelebrity(int[][] arr) {
        // if a celebrity is there print it's index (not position), if there is not then
        // print "none"
        Stack<Integer> stk = new Stack<>();
        
        // push every index into stack 
        for (int i = 0; i < arr.length; i++) stk.push(i);
        
        // now check at every pair whether we can have any of one into stack again
        while (stk.size() > 1) {
            // pop two items and validate them
            int elem1 = stk.pop();
            int elem2 = stk.pop();
            if (arr[elem1][elem2] == 0) // it means elem1 does not know elem2, remove elem2
                stk.push(elem1);
            else stk.push(elem2);
        }
    
        // now check last remaining elem in stack
        for (int col = 0; col < arr.length; col++)
            if (col != stk.peek() && arr[stk.peek()][col] != 0) {
                System.out.println("none");
                return;
            }
        for (int row = 0; row < arr.length; row++)
            if (row != stk.peek() && arr[row][stk.peek()] != 1) {
                System.out.println("none");
                return;
            }
        System.out.println(stk.peek());
    }

}
