package edu.lucyawrey.student_database;

/* 
 * Student record class used to store complete student records 
 * in the database. I have used public variables here because getters
 * and setters are overkill for what is essentially a simple struct.
 */
public class StudentRecord {
  public int id;
  public String firstName;
  public String lastName;

  public StudentRecord(int id, String firstName, String lastName) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
  }
}