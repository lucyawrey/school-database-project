package edu.lucyawrey.student_database;

// Student record class used by the main database array.
// I have used public variables here as getters and setters
// are overkill for what is essentially a struct
public class StudentRecord {
  public String id;
  public String firstName;
  public String lastName;

  public StudentRecord(String id, String firstName, String lastName) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
  }
}