package edu.lucyawrey.student_database;

// Student database object
public class Database {
  private StudentRecord[] databaseArray;
  private OrderedArray idIndex;
  private OrderedArray firstNameIndex;
  private OrderedArray lastNameIndex;
  private Stack deleteStack;

  public Database(int size) {
    this(new StudentRecord[0], size);
  }

  public Database(StudentRecord[] initialData, int size) {
    databaseArray = new StudentRecord[size];
    idIndex = new OrderedArray(size);
    firstNameIndex = new OrderedArray(size);
    lastNameIndex = new OrderedArray(size);
    deleteStack = new Stack(size);

    for (int i = 0; i < initialData.length; i++) {
      StudentRecord record = initialData[i];
      databaseArray[i] = record;
      idIndex.insert(new IndexRecord(record.id, i));
      firstNameIndex.insert(new IndexRecord(record.firstName, i));
      lastNameIndex.insert(new IndexRecord(record.lastName, i));
    }
  }
}