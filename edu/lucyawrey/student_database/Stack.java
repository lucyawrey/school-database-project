package edu.lucyawrey.student_database;

// Stack used for storing free locations in the database array
public class Stack<T> {
  private Node<T> top;

  // Push a value onto the linked list stack
  public void push(T newValue) {
    Node<T> newNode = new Node<T>(newValue);
    if (top == null) {
      top = newNode;
      return;
    }

    newNode.prev = top;
    top.next = newNode;
    top = newNode;
  }

  // Pop an element from the stack and set the top of the stack to the next value on the stack
  public T pop() {
    T pop = top.value;
    top = top.prev;
    return pop;
  }

  // Look at the top of the stack without changing the top pointer
  public T peek() {
    return top.value;
  }

  // Check that the stack contains any items
  public boolean hasNext() {
    return (top != null);
  }
}