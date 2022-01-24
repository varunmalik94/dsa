/*

1. You are given an infix expression.
2. You are required to convert it to postfix and print it.
3. You are required to convert it to prefix and print it.

Input Format
Input is managed for you

Output Format
postfix
prefix

Constraints
1. Expression is balanced
2. The only operators used are +, -, *, /
3. Opening and closing brackets - () - are used to impact precedence of operations
4. + and - have equal precedence which is less than * and /. * and / also have equal precedence.
5. In two operators of equal precedence give preference to the one on left.
6. All operands are single digit numbers.

Sample Input
a*(b-c+d)/e

Sample Output
abc-d+*e/
/*a+-bcde

*/

import java.io.*;
import java.util.*;

public class Main {

  public static int precedence (char operator) {
    if (operator == '+' || operator == '-') return 1;
    if (operator == '*' || operator == '/') return 2;
    return 0;
  }

// public static int eval (int operator1, int operator2, char operand) {
//     switch (operand) {
//         case '+':
//             return operator1 + operator2;
//         case '-':
//             return operator1 - operator2;
//         case '*':
//             return operator1 * operator2;
//         case '/':
//             return operator1 / operator2;
//     }
//     return 0;
// }

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String exp = br.readLine();

    System.out.println(postfix(exp));
    System.out.println(prefix(exp));
  }

  public static String prefix(String exp) {
    Stack<String> operandStk = new Stack<>();
    Stack<Character> operatorStk = new Stack<>();

    int n = exp.length();

    for (int i = 0; i < n; i++) {
      char ch = exp.charAt(i);
      if (ch >= 'a' && ch <= 'z') operandStk.push(ch + "");
      else {
        if (ch == '(') operatorStk.push('(');

        else if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {

          while (operatorStk.size() != 0 && operatorStk.peek() != '(' && precedence(operatorStk.peek()) >= precedence(ch)) {
            String operand2 = operandStk.pop();
            String operand1 = operandStk.pop();
            char operator = operatorStk.pop();
            String result =  operator + operand1 + operand2;
            operandStk.push(result);
          }
          operatorStk.push(ch);
        }

        else if (ch == ')') {

          while (operatorStk.peek() != '(') {
            String operand2 = operandStk.pop();
            String operand1 = operandStk.pop();
            char operator = operatorStk.pop();
            String result = operator + operand1 + operand2;
            operandStk.push(result);
          }
          operatorStk.pop();
        }
      }
    }

    while (operatorStk.size() != 0) {
      String operand2 = operandStk.pop();
      String operand1 = operandStk.pop();
      char operator = operatorStk.pop();
      String result = operator + operand1 + operand2;
      operandStk.push(result);
    }
    return operandStk.pop();
  }

  public static String postfix(String exp) {
    Stack<String> operandStk = new Stack<>();
    Stack<Character> operatorStk = new Stack<>();

    int n = exp.length();

    for (int i = 0; i < n; i++) {
      char ch = exp.charAt(i);
      if (ch >= 'a' && ch <= 'z') operandStk.push(ch + "");

      if (ch == '(') operatorStk.push('(');

      else if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {

        while (operatorStk.size() != 0 && operatorStk.peek() != '(' && precedence(operatorStk.peek()) >= precedence(ch)) {
          String operand2 = operandStk.pop();
          String operand1 = operandStk.pop();
          char operator = operatorStk.pop();
          String result =  operand1 + operand2 + operator;
          operandStk.push(result);
        }
        operatorStk.push(ch);
      }

      else if (ch == ')') {
        while (operatorStk.peek() != '(') {
          String operand2 = operandStk.pop();
          String operand1 = operandStk.pop();
          char operator = operatorStk.pop();
          String result = operand1 + operand2 + operator;
          operandStk.push(result);
        }
        operatorStk.pop();
      }
    }

    while (operatorStk.size() != 0) {
      String operand2 = operandStk.pop();
      String operand1 = operandStk.pop();
      char operator = operatorStk.pop();
      String result = operand1 + operand2 + operator;
      operandStk.push(result);
    }
    return operandStk.pop();
  }
}
