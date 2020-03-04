package edu.lucyawrey.student_database;

// Ordered linked list used for database indexes
public class OrderedList {
  private Node<IndexRecord<String>> first, last, rover, iterator;

  public void insert(IndexRecord<String> newRecord) {
    Node<IndexRecord<String>> newNode = new Node<IndexRecord<String>>(newRecord);
    if (first == null) {
      first = newNode;
      last = newNode;
      iterator = newNode;
      return;
    }

    rover = first;
    while (rover != null) {
      int comp = newRecord.compareTo(rover.value);
      if (comp > 0) {
        rover = rover.next;
        continue;
      } else {
        rover.prev.next = newNode;
        newNode.prev = rover.prev;
        rover.prev = newNode;
        newNode.next = rover;
        return;
      }
    }
  }

  // Delete an element from the array and shift all elments back by one
  public void delete(String key) {
    Node<IndexRecord<String>> node = search(key);
    if (node == last) {
      last = node.prev;
      last.next = null;
    } else {
      node.prev.next = node.next;
      node.next.prev = node.prev;
    }
  }

  // Do a linear search through the ordered list
  public Node<IndexRecord<String>> search(String key) {
    rover = first;
    while (rover != null) {
      if (rover.value.key == key) {
        return rover;
      }
      rover = rover.next;
    }
    return null;
  }

  // Get the value of the index record found using search
  public int getValue(String key) {
    Node<IndexRecord<String>> node = search(key);
    if (node != null) {
      return node.value.location;
    } else {
      return -1;
    }
  }

  // Checks if the array contains a specific key by doing a search and making sure
  // it returns a result
  public boolean containsKey(String key) {
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
    return (iterator.next != null);
  }

  public boolean hasPrevious() {
    return (iterator.prev != null);
  }

  public int getNext() {
    iterator = iterator.next;
    return iterator.prev.value.location;
  }

  public int getPrevious() {
    iterator = iterator.prev;
    return iterator.next.value.location;
  }
}
