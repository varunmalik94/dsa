/*

1. You are given a string exp representing an expression.
2. Assume that the expression is balanced  i.e. the opening and closing brackets match with each other.
3. But, some of the pair of brackets maybe extra/needless. 
4. You are required to print true if you detect extra brackets and false otherwise.

e.g.'
((a + b) + (c + d)) -> false
(a + b) + ((c + d)) -> true

Input Format
A string str

Output Format
true or false

Constraints
0 <= str.length <= 100

Sample Input
(a + b) + ((c + d))

Sample Output
true

*/

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner s = new Scanner(System.in);
        String exp = s.nextLine();
        
        System.out.println(duplicateBracket(exp));
    }
    
    public static boolean duplicateBracket(String exp) {
        
        Stack<Character> stk = new Stack<>();
        
        for (int i = 0; i < exp.length(); i++) {
            char ch = exp.charAt(i);
            if (ch != ')') stk.push(ch);
            else {
                if (stk.peek() == '(') 
                    return true;
                // remove elements from stack untill ( is found
                while (stk.peek() != '(')
                    stk.pop();
                stk.pop();
            }
        }
        return false;
    }
}
