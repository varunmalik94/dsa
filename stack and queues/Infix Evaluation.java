/*

1. You are given an infix expression.
2. You are required to evaluate and print it's value.

Input Format
Input is managed for you

Output Format
Value of infix expression

Constraints
1. Expression is balanced
2. The only operators used are +, -, *, /
3. Opening and closing brackets - () - are used to impact precedence of operations
4. + and - have equal precedence which is less than * and /. * and / also have equal precedence.
5. In two operators of equal precedence give preference to the one on left.
6. All operands are single digit numbers.

Sample Input
2 + 6 * 4 / 8 - 3

Sample Output
2

*/

import java.io.*;
import java.util.*;

public class Main {

  public static int checkPrecedence(Stack<Character> operatorStk, char ch) {
    if (operatorStk.size() == 0) return 0;
    if ((ch == '-' || ch == '+') && (operatorStk.peek() == '-' || operatorStk.peek() == '+' || operatorStk.peek() == '*' || operatorStk.peek() == '/')) return 1;
    if ((ch == '*' || ch == '/') && (operatorStk.peek() == '*' || operatorStk.peek() == '/')) return 1;
    return 0;
  }

  public static int evaluateResult(int operand1, int operand2, char operator) {
    switch (operator) {
      case '+':
        return operand1 + operand2;
      case '-':
        return operand1 - operand2;
      case '*':
        return operand1 * operand2;
      case '/':
        return operand1 / operand2;
    }
    return 0;
  }

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String exp = br.readLine();
    System.out.println(infixEvaluation(exp));
  }

  public static int infixEvaluation(String exp) {
    // two stacks
    // one for operand, other for operator
    Stack<Integer> operandStk = new Stack<>();
    Stack<Character> operatorStk = new Stack<>();

    int n = exp.length();

    for (int i = 0; i < n; i++) {
      char ch = exp.charAt(i);
      if (ch >= '0' && ch <= '9') operandStk.push(ch - '0');
      else {
        if (ch == '+' || ch == '-' || ch == '/' || ch == '*') {
        
          // if higher precedence operators on top of stack, process them first
          while (checkPrecedence(operatorStk, ch) == 1 ) {
            int operand2 = operandStk.pop();
            int operand1 = operandStk.pop();
            char operator = operatorStk.pop();
            int result = evaluateResult(operand1, operand2, operator);
            operandStk.push(result);
          }
          
          operatorStk.push(ch);
        }
        
        else if (ch == '(') operatorStk.push('(');
        
        else if (ch == ')') {
          // pop untill we find matching '(' and do the evaluation
          while (operatorStk.peek() != '(') {
            int operand2 = operandStk.pop();
            int operand1 = operandStk.pop();
            char operator = operatorStk.pop();
            int result = evaluateResult(operand1, operand2, operator);
            operandStk.push(result);
          }
          operatorStk.pop();
        }
      }
    }

    // do the evaluation untll operator stk ends
    while (operatorStk.size() != 0) {
      int operand2 = operandStk.pop();
      int operand1 = operandStk.pop();
      char operator = operatorStk.pop();
      int result = evaluateResult(operand1, operand2, operator);
      operandStk.push(result);
    }
    return operandStk.pop();
  }
}
