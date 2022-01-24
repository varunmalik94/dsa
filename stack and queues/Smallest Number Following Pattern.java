/*

https://practice.geeksforgeeks.org/problems/number-following-a-pattern3126/1/

1. You are given a pattern of upto 8 length containing characters 'i' and 'd'.
2. 'd' stands for decreasing and 'i' stands for increasing
3. You have to print the smallest number, using the digits 1 to 9 only without repetition, such that 
the digit decreases following a d and increases follwing an i.

e.g.
d -> 21
i -> 12
ddd -> 4321
iii -> 1234
dddiddd -> 43218765
iiddd -> 126543

Input Format
Input is managed for you

Output Format
Smallest sequence of digits (from 1 to 9) without duplicacy and following the pattern

Constraints
0 < str.length <= 8
str contains only 'd' and 'i'

Sample Input
ddddiiii

Sample Output
543216789

*/

/*
 * @todo do using stacks also
 */
 
 import java.io.*;
import java.util.*;

public class Main{


public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String str = br.readLine();

    String result = "";

    int max = 0;
    int countofd= 0;
    for (int i = 0; i != str.length(); i++) {

        char ch = str.charAt(i);

        if (ch == 'd') countofd++;

        else {

            String ds = "";
            int current_i_result = ++max;
            int j = 0;

            while (j++ != countofd)
                ds = (++max) + "" + ds;

            countofd = 0;
            result = result + ds + current_i_result;
        }
    }
    
    // write answer for last character of str
    char ch = str.charAt(str.length() - 1);
    
    if (ch == 'i') result += max + 1;
    else {
        
        String ds = "";
        countofd++;
        int j = 0;
        while (j++ != countofd) {
            ds = (++max) + "" + ds;
        }
        result += ds;
    }
    System.out.println(result);
 }
}
