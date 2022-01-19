/*

1. You are given a number n, representing the size of array a.
2. You are given n numbers, representing elements of array a.
3. You are required to "next greater element on the right" for all elements of array
4. Input and output is handled for you.

"Next greater element on the right" of an element x is defined as the first element to right of x having value greater than x.
Note -> If an element does not have any element on it's right side greater than it, consider -1 as it's "next greater element on right"
e.g.
for the array [2 5 9 3 1 12 6 8 7]
Next greater for 2 is 5
Next greater for 5 is 9
Next greater for 9 is 12
Next greater for 3 is 12
Next greater for 1 is 12
Next greater for 12 is -1
Next greater for 6 is 8
Next greater for 8 is -1
Next greater for 7 is -1

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
8
8
-1
7
-1

*/

import java.io.*;
import java.util.*;

public class Main{
  public static void display(int[] a){
    StringBuilder sb = new StringBuilder();

    for(int val: a){
      sb.append(val + "\n");
    }
    System.out.println(sb);
  }

public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int n = Integer.parseInt(br.readLine());
    int[] a = new int[n];
    for(int i = 0; i < n; i++){
       a[i] = Integer.parseInt(br.readLine());
    }

    int[] nge = solve(a);
    display(nge);
 }

 public static int[] solve(int[] arr){
   // array containing result
   int[] result = new int[arr.length];
   Arrays.fill(result, -1);
   
   Stack<Integer> stk = new Stack<>();
   
   // tranverse from right to left
   for (int i = arr.length - 1; i >= 0; i--) {
       if (stk.isEmpty() == true) stk.push(arr[i]);
       else {
            // if element on top of stack is lesser than current element, then pop it
           if (stk.peek() <= arr[i]) {
               stk.pop();
               i++;   // why i++, because we have to pop all smaller elements
           }
           else {
               result[i] = stk.peek();
               stk.push(arr[i]);
           }
       }
   }
   
   return result;
 }

}
