package edu.lucyawrey.student_database;

// Ordered linked list used for database indexes
public class OrderedList<T extends Comparable<T>> {
  private Node<IndexRecord<T>> first, last, rover, iterator;

  // Insert a node into the linked list sorted using a rover
  public void insert(IndexRecord<T> newRecord) {
    Node<IndexRecord<T>> newNode = new Node<IndexRecord<T>>(newRecord);
    if (first == null) {
      first = newNode;
      last = newNode;
    } else if (first.value.compareTo(newNode.value) > 0) {
      newNode.next = first;
      newNode.next.prev = newNode;
      first = newNode;
    } else {
      rover = first;

      while (rover.next != null && rover.next.value.compareTo(newNode.value) < 0) {
        rover = rover.next;
      }

      newNode.next = rover.next;
      if (rover.next != null) {
        newNode.next.prev = newNode;
      } else {
        last = newNode;
      }
      rover.next = newNode;
      newNode.prev = rover;
    }
  }

  // Delete an element from the array and shift all elments back by one
  public void delete(T key) {
    Node<IndexRecord<T>> node = search(key);
    if (node == last) {
      last = node.prev;
      if (last == null) {
        first = null;
      } else {
        last.next = null; 
      }
    } else if (node == first) {
      first = node.next;
      first.prev = null;
    } else {
      node.prev.next = node.next;
      node.next.prev = node.prev;
    }
  }

  // Do a linear search through the ordered list
  public Node<IndexRecord<T>> search(T key) {
    rover = first;
    while (rover != null) {
      if (rover.value.key.equals(key)) {
        return rover;
      }
      rover = rover.next;
    }
    return null;
  }

  // Get the value of the index record found using search
  public int getValue(T key) {
    Node<IndexRecord<T>> node = search(key);
    if (node != null) {
      return node.value.location;
    } else {
      return -1;
    }
  }

  // Checks if the array contains a specific key by doing a search and making sure
  // it returns a result
  public boolean containsKey(T key) {
    return (search(key) != null);
  }

  // Iterator functions for iterating over elements of the array
  public void iteratorInitFront() {
    iterator = first;
  }

  public void iteratorInitBack() {
    iterator = last;
  }

  public boolean hasNext() {
    return (iterator != null);
  }

  public boolean hasPrevious() {
    return (iterator != null);
  }

  public int getNext() {
    int temp = iterator.value.location;
    iterator = iterator.next;
    return temp;
  }

  public int getPrevious() {
    int temp = iterator.value.location;
    iterator = iterator.prev;
    return temp;
  }
}
