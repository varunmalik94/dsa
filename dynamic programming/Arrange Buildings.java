/*

1. You are given a number n, which represents the length of a road. The road has n plots on it's each side.
2. The road is to be so planned that there should not be consecutive buildings on either side of the road.
3. You are required to find and print the number of ways in which the buildings can be built on both side of roads.

Input Format
A number n

Output Format
A number representing the number of ways in which the buildings can be built on both side of roads.

Constraints
0 < n <= 45

Sample Input
6

Sample Output
441
*/

import java.io.*;
import java.util.*;

public class Main{

public static void main(String[] args) throws Exception {
    // write your code here
    Scanner s = new Scanner(System.in);
    int n = s.nextInt();
    
    System.out.println(arrangeBuidings(n));
 }
 
 
 // consider buildings equivalent to 0, streets to 1
 public static long arrangeBuidings(int n) {
     long buildings = 1;
     long streets = 1;
     
     // base case
     if (n == 0) return 0;
     
     for (int i = 2; i <= n; i++) {
         long temp = buildings;
         buildings = streets;
         streets += temp;
     }
     return (buildings + streets) * (buildings + streets);
 }

}
