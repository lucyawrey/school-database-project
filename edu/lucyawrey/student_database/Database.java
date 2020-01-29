package edu.lucyawrey.student_database;

// Student database object
public class Database {
  private StudentRecord[] dataArray;
  private OrderedArray idIndex;
  private OrderedArray firstNameIndex;
  private OrderedArray lastNameIndex;
  private Stack freeLocations;

  public Database(int size) {
    this(new StudentRecord[0], size);
  }

  public Database(StudentRecord[] initialData, int size) {
    dataArray = new StudentRecord[size];
    idIndex = new OrderedArray(size);
    firstNameIndex = new OrderedArray(size);
    lastNameIndex = new OrderedArray(size);
    freeLocations = new Stack(size);
    for (int i = 0; i < initialData.length; i++) {
      dataArray[i] = initialData[i];

    }
  }
}