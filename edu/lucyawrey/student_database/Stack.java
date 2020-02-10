package edu.lucyawrey.student_database;

// Stack used for storing free locations in the database array
public class Stack {
  private int[] stackArray;
  private int stackPointer;

  public Stack(int size) {
    stackArray = new int[size];
    stackPointer = 0;
  }

  public void push(int newValue) {
    stackArray[stackPointer] = newValue;
    stackPointer++;
  }

  public int pop() {
    stackPointer--;
    return stackArray[stackPointer];
  }

  public int peek() {
    return stackArray[stackPointer - 1];
  }

  public boolean hasNext() {
    return (stackPointer > 0);
  }
}