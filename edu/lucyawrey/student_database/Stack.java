package edu.lucyawrey.student_database;

// Stack used for storing free locations in the database array
public class Stack<T> {
  private Node<T> top;

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

  public T pop() {
    T pop = top.value;
    top = top.prev;
    return pop;
  }

  public T peek() {
    return top.value;
  }

  public boolean hasNext() {
    return (top != null);
  }
}