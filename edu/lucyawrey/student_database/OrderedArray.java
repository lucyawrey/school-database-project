package edu.lucyawrey.student_database;

// Ordered array used for database indexes
public class OrderedArray {
  private IndexRecord[] array;
  private int stackPointer;

  public OrderedArray(int size) {
    array = new IndexRecord[size];
    stackPointer = 0;
  }
}