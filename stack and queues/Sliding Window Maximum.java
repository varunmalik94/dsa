/*

1. You are given a number n, representing the size of array a.
2. You are given n numbers, representing the elements of array a.
3. You are given a number k, representing the size of window.
4. You are required to find and print the maximum element in every window of size k.

e.g.
for the array [2 9 3 8 1 7 12 6 14 4 32 0 7 19 8 12 6] and k = 4, the answer is [9 9 8 12 12 14 14 32 32 32 32 19 19 19]

Input Format
Input is managed for you

Output Format
Maximum of each window in separate line

Constraints
0 <= n < 100000
-10^9 <= a[i] <= 10^9
0 < k < n

Sample Input
17
2
9
3
8
1
7
12
6
14
4
32
0
7
19
8
12
6
4

Sample Output
9
9
8
12
12
14
14
32
32
32
32
19
19
19

*/

import java.io.*;
import java.util.*;

public class Main{

public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int n = Integer.parseInt(br.readLine());
    int[] a = new int[n];
    for(int i = 0; i < n; i++){
       a[i] = Integer.parseInt(br.readLine());
    }
    int k = Integer.parseInt(br.readLine());

    int[] nextGreaterIndex = nextGreaterToRightIndex(a);
    
    int currentNextGreaerIndex = 0;
    
    for (int i = 0; i < n - k + 1; i++) {
        
        if (currentNextGreaerIndex < i) currentNextGreaerIndex = i;
        
        int temp = currentNextGreaerIndex;

        while (currentNextGreaerIndex < i + k ) {
            temp = currentNextGreaerIndex;
            currentNextGreaerIndex = nextGreaterIndex[temp];
        }

        currentNextGreaerIndex = temp;
        System.out.println(a[currentNextGreaerIndex]);
        // else  - this case will not occur
     }
 }

  // returns array containing indexes of next greater element to the right
  public static int[] nextGreaterToRightIndex(int[] arr){
   // array containing result
   int[] result = new int[arr.length];
   Arrays.fill(result, arr.length);
   
   Stack<Integer> stk = new Stack<>();
   
   // tranverse from right to left
   for (int i = arr.length - 1; i >= 0; i--) {
       if (stk.isEmpty() == true) stk.push(i);
       else {
            // if element on top of stack is lesser than current element, then pop it
           if (arr[stk.peek()] < arr[i]) {
               stk.pop();
               i++;   // why i++, because we have to pop all smaller elements
           }
           else {
               result[i] = stk.peek();
               stk.push(i);
           }
       }
   }
   
   return result;
 }
}
