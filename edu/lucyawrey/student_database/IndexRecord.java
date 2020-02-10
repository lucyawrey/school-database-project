package edu.lucyawrey.student_database;

// Student record class used by the OrderedArray indexes
// I have used public variables here as getters and setters
// are overkill for what is essentially a struct
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