package edu.lucyawrey.student_database;

// Student database object
public class Database {
  private int insertPointer;
  private StudentRecord[] databaseArray;
  private OrderedArray idIndex;
  private OrderedArray firstNameIndex;
  private OrderedArray lastNameIndex;
  private Stack deleteStack;

  public Database(int size) {
    this(new StudentRecord[0], size);
  }

  public Database(StudentRecord[] initialData, int size) {
    insertPointer = 0;
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

  public void insertIt(String id, String firstName, String lastName) {
    int location;
    if (deleteStack.hasNext()) {
      location = deleteStack.pop();
    } else {
      location = insertPointer;
      insertPointer++;
    }
    databaseArray[location] = new StudentRecord(id, firstName, lastName);
    idIndex.insert(new IndexRecord(id, location));
    firstNameIndex.insert(new IndexRecord(firstName, location));
    lastNameIndex.insert(new IndexRecord(lastName, location));
  }

  private StudentRecord search(String key, OrderedArray array) {
    int location = array.getValue(key);
    return databaseArray[location];
  }

  public StudentRecord searchById(String id) {
    return search(id, idIndex);
  }

  public StudentRecord searchByFirstName(String firstName) {
    return search(firstName, firstNameIndex);
  }

  public StudentRecord searchByLastName(String lastName) {
    return search(lastName, lastNameIndex);
  }

  public void delete(String id) {
    int location = idIndex.getValue(id);
    StudentRecord record = databaseArray[location];
    idIndex.delete(id);
    firstNameIndex.delete(record.firstName);
    lastNameIndex.delete(record.lastName);
    deleteStack.push(location);
  }
}