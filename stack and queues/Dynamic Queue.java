/*

1. You are required to complete the code of our CustomQueue class. The class should mimic the behaviour of a Queue and implement FIFO semantic.
2. Here is the list of functions that are written in the class
    2.1. add -> Accepts new data if there is space available in the underlying array or 
    print "Queue overflow" otherwise.
    2.2. remove -> Removes and returns value according to FIFO, if available or print 
    "Queue underflow" otherwise and return -1.
    2.3. peek -> Returns value according to FIFO, if available or print "Queue 
     underflow" otherwise and return -1.
    2.4. size -> Returns the number of elements available in the queue.
    2.5. display -> Prints the elements of queue in FIFO manner (space-separated) 
    ending with a line-break.
3. Input and Output is managed for you.

Input Format
Input is managed for you

Output Format
Output is managed for you

Constraints
None

Sample Input
5
add 10
display
add 20
display
add 30
display
add 40
display
add 50
display
add 60
display
peek
remove
display
peek
remove
display
peek
remove
display
peek
remove
display
peek
remove
display
peek
remove
quit

Sample Output
10 
10 20 
10 20 30 
10 20 30 40 
10 20 30 40 50 
10 20 30 40 50 60 
10
10
20 30 40 50 60 
20
20
30 40 50 60 
30
30
40 50 60 
40
40
50 60 
50
50
60 
60
60

*/

import java.io.*;
import java.util.*;

public class Main {

  public static class CustomQueue {
    int[] data;
    int front;
    int rear;      // rear is back where we insert new element
    int size;

    public CustomQueue(int cap) {
      data = new int[cap];
      front = 0;
      rear = 0;
      size = 0;
    }

    int size() {
      return size;
    }

    void display() {
      for(int i = 0; i < size; i++){
        int idx = (front + i) % data.length;
        System.out.print(data[idx] + " ");
      }
      System.out.println();
    }

    void add(int val) {
      if(size == data.length){
        
        // create a new q of double size
        int[] temp = new int[data.length * 2];
        
        // insert old values into new q
        int idx = 0;
        for (int i = 0; i < size; i++) 
            temp[idx++] = data[ ( front + i ) % data.length ];
        
        temp[idx++] = val;

        // update current new q
        data = temp;
        front = 0;
        rear = idx;
        size++;

      } else {
        int idx = (front + size) % data.length;
        data[idx] = val;
        size++;
      }
    }

    int remove() {
      if(size == 0){
        System.out.println("Queue underflow");
        return -1;
       } else {
        int val = data[front];

        front = (front + 1) % data.length;
        size--;

        return val;
       }
    }

    int peek() {
       if(size == 0){
        System.out.println("Queue underflow");
        return -1;
       } else {
        int val = data[front];
        return val;
       }
    }
  }

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    CustomQueue qu = new CustomQueue(n);

    String str = br.readLine();
    while(str.equals("quit") == false){
      if(str.startsWith("add")){
        int val = Integer.parseInt(str.split(" ")[1]);
        qu.add(val);
      } else if(str.startsWith("remove")){
        int val = qu.remove();
        if(val != -1){
          System.out.println(val);
        }
      } else if(str.startsWith("peek")){
        int val = qu.peek();
        if(val != -1){
          System.out.println(val);
        }
      } else if(str.startsWith("size")){
        System.out.println(qu.size());
      } else if(str.startsWith("display")){
        qu.display();
      }
      str = br.readLine();
    }
  }
}
