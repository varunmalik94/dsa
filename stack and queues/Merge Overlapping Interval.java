/*

1. You are given a number n, representing the number of time-intervals.
2. In the next n lines, you are given a pair of space separated numbers.
3. The pair of numbers represent the start time and end time of a meeting (first number is start time and second number is end time)
4. You are required to merge the meetings and print the merged meetings output in increasing order of start time.

E.g. Let us say there are 6 meetings
1 8
5 12
14 19
22 28
25 27
27 30

Then the output of merged meetings will belongs
1 12
14 19
22 30

Note -> The given input maynot be sorted by start-time.

Input Format
Input is managed for you 

Output Format
Print a merged meeting start time and end time separated by a space in a line
.. print all merged meetings one in each line.

Constraints
1 <= n <= 10^4
0 <= ith start time < 100
ith start time < ith end time <= 100

Sample Input
6
22 28
1 8
25 27
14 19
27 30
5 12

Sample Output
1 12
14 19
22 30

*/

import java.io.*;
import java.util.*;

class Item {
    int start;
    int end;
    
    Item(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

public class Main {

    public static void main(String[] args) throws Exception {
        // write your code here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][2];

        for (int j = 0; j < n; j++) {
            String line = br.readLine();
            arr[j][0] = Integer.parseInt(line.split(" ")[0]);
            arr[j][1] = Integer.parseInt(line.split(" ")[1]);
        }

        mergeOverlappingIntervals(arr);
    }

    public static void mergeOverlappingIntervals(int[][] arr) {
        // sort array in increasing order of start tiem
        Arrays.sort(arr, (a, b) -> Integer.compare(a[0], b[0]));        
        
        Stack<Item> stk = new Stack<>();
        
        // base case
        stk.push(new Item(arr[0][0], arr[0][1]));
        
        for (int i = 1; i < arr.length; i++) {
            // check overlapping with stk.peek();
            Item tmp = stk.peek();
            int start = arr[i][0];
            int end = arr[i][1];
            
            if (start > tmp.end) {
                stk.push(new Item(start, end));
            }
            else if (end > tmp.end) {
                // pop stack and insert new Item
                tmp = stk.pop();
                stk.push(new Item(tmp.start, end));
            }
        }
        
        // here print stack result in reverse order -> use recursion
        printReverse(stk);
    }
    
    public static void printReverse(Stack<Item> stk) {
        // base case
        if (stk.isEmpty()) return;
        
        Item item = stk.pop();
        printReverse(stk);
        System.out.println(item.start + " " + item.end);
    }

}
