package edu.lucyawrey.student_database;

// Stack used for storing free locations in the database array
public class Stack {
  private int[] stackArray;
  private int stackPointer;

  public Stack(int size) {
    stackArray = new int[size];
    stackPointer = 0;
  }

  // Push a new item onto the stack and incrmeent the stack pointer
  public void push(int newValue) {
    stackArray[stackPointer] = newValue;
    stackPointer++;
  }

  // Pop the current item from the stack and decrement the stack pointer
  public int pop() {
    stackPointer--;
    return stackArray[stackPointer];
  }

  // Look at the current item on the stack without changing the pointer
  public int peek() {
    return stackArray[stackPointer - 1];
  }

  // Check that the stack contains any items
  public boolean hasNext() {
    return (stackPointer > 0);
  }
}