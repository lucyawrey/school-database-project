package edu.lucyawrey.student_database;

/* 
 * Dndex record class used by the OrderedArray indexes
 * I have used public variables here because getters and setters
 * are overkill for what is essentially a simple struct.
 */
public class IndexRecord {
  public String key;
  public int location;

  public IndexRecord(String key, int location) {
    this.key = key;
    this.location = location;
  }

  public int compareTo(IndexRecord other) {
    return key.compareTo(other.key);
  }
}