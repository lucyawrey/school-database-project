package edu.lucyawrey.student_database;

// Node class used for the linked lists
public class Node<T> {
  public T value;
  public Node<T> prev, next;

  public Node(T value) {
    this.value = value;
  }

  public Node(T value, Node<T> prev, Node<T> next) {
    this.value = value;
    this.prev = prev;
    this.next = next;
  }
}