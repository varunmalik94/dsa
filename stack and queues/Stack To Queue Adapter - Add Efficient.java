/*

1. You are required to complete the code of our StackToQueueAdapter class. The class should mimic the behaviour of a Queue and implement FIFO semantic.
2. Here is the list of functions that are written in the class 
    2.1. add -> Accepts new data if there is space available in the underlying array or 
    print "Queue overflow" otherwise.
    2.2. remove -> Removes and returns value according to FIFO, if available or print 
    "Queue underflow" otherwise and return -1.
    2.3. peek -> Returns value according to FIFO, if available or print "Queue 
    underflow" otherwise and return -1.
    2.4. size -> Returns the number of elements available in the queue.
3. Input and Output is managed for you.

Input Format
Input is managed for you

Output Format
Output is managed for you

Constraints
Note -> add and size should work in constant time. remove and peek should work in linear time.

Sample Input
add 10
add 20
add 30
add 40
add 50
remove
remove
add 60
add 70
peek
remove
peek
remove
peek
remove
peek
remove
peek
remove
peek
remove
quit

Sample Output
10
20
30
30
40
40
50
50
60
60
70
70
Queue underflow
Queue underflow

*/

import java.io.*;
import java.util.*;

public class Main {

  public static class StackToQueueAdapter {
    Stack<Integer> mains;

    public StackToQueueAdapter() {
      mains = new Stack<>();
    }

    int size() {
        return mains.size();
    }

    void add(int val) {
        mains.push(val);
    }

    int remove() {
        if (mains.isEmpty()) {
            System.out.println("Queue underflow");
            return -1;
        }

        // remove all elems from mains. push to helper stack. do same operation 
        // from helper to main to make it add efficient
        
        Stack<Integer> helpers = new Stack<>();
        int elem;
        while (mains.size() != 1) 
            helpers.push(mains.pop());
    
        elem = mains.pop();
        
        while (helpers.size() != 0)
            mains.push(helpers.pop());
        
        return elem;
    }

    int peek() {
        if (mains.isEmpty()) {
            System.out.println("Queue underflow");
            return -1;
        }

        // remove all elems from mains. push to helper stack. do same operation 
        // from helper to main to make it add efficient
        
        Stack<Integer> helpers = new Stack<>();
        int elem;
        while (mains.size() != 1) 
            helpers.push(mains.pop());
    
        elem = mains.pop();
        
        helpers.push(elem);
        
        while (helpers.size() != 0)
            mains.push(helpers.pop());
        
        return elem;
    }
  }

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StackToQueueAdapter qu = new StackToQueueAdapter();

    String str = br.readLine();
    while (str.equals("quit") == false) {
      if (str.startsWith("add")) {
        int val = Integer.parseInt(str.split(" ")[1]);
        qu.add(val);
      } else if (str.startsWith("remove")) {
        int val = qu.remove();
        if (val != -1) {
          System.out.println(val);
        }
      } else if (str.startsWith("peek")) {
        int val = qu.peek();
        if (val != -1) {
          System.out.println(val);
        }
      } else if (str.startsWith("size")) {
        System.out.println(qu.size());
      }
      str = br.readLine();
    }
  }
}
