package edu.lucyawrey.student_database;

/* 
 * Dndex record class used by the OrderedArray indexes
 * I have used public variables here because getters and setters
 * are overkill for what is essentially a simple struct.
 */
public class IndexRecord<T extends Comparable<T>> implements Comparable<IndexRecord<T>> {
  public T key;
  public int location;

  public IndexRecord(T key, int location) {
    this.key = key;
    this.location = location;
  }

  public int compareTo(IndexRecord<T> other) {
    return key.compareTo(other.key);
  }
}