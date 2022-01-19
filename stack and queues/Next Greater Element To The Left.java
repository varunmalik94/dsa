/*

1. You are given a number n, representing the size of array a.
2. You are given n numbers, representing elements of array a.
3. You are required to find "next greater element on the left" for all elements of array
4. Input and output is handled for you.

"Next greater element on the left" of an element x is defined as the first element to left of x having value greater than x.
Note -> If an element does not have any element on it's left side greater than it, consider -1 as it's "next greater element on left"
e.g.
for the array [2 5 9 3 1 12 6 8 7]
Next greater for 2 is -1
Next greater for 5 is -1
Next greater for 9 is -1
Next greater for 3 is 9
Next greater for 1 is 3
Next greater for 12 is -1
Next greater for 6 is 12
Next greater for 8 is 12
Next greater for 7 is 8

Input Format
Input is managed for you

Output Format
Output is managed for you

Constraints
0 <= n < 10^5
-10^9 <= a[i] <= 10^9

Sample Input
5
5
3
8
-2
7

Sample Output
-1
5
-1
8
8

*/

import java.io.*;
import java.util.*;

public class Main {
  public static void display(int[] a) {
    StringBuilder sb = new StringBuilder();

    for (int val : a) {
      sb.append(val + "\n");
    }
    System.out.println(sb);
  }

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int n = Integer.parseInt(br.readLine());
    int[] a = new int[n];
    for (int i = 0; i < n; i++) {
      a[i] = Integer.parseInt(br.readLine());
    }

    int[] ngl = solve(a);
    display(ngl);
  }

  public static int[] solve(int[] arr) {
    // array containing next smaller element on left
    int n = arr.length;
    int[] smaller = new int[n];
    Arrays.fill(smaller, -1);
    
    // stack containing track of smaller elements
    Stack<Integer> stk = new Stack<>();
    
    // tranversing from left to right
    for (int i = 0; i < n; i++) {
        if (stk.isEmpty() == true) stk.push(arr[i]);
        else {
            // check top of stack contains smaller element than arr[i]
            if (stk.peek() <= arr[i]) {
                stk.pop();
                i--;         // after popping, if next top of stack is also smaller than a[i]
            }
            else {
                smaller[i] = stk.peek();
                stk.push(arr[i]);
            }
        }
    }
    return smaller;
  }

}
