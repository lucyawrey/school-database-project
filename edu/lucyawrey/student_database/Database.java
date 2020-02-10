package edu.lucyawrey.student_database;

// Student database object
public class DataBase {
  private int insertPointer;
  private StudentRecord[] databaseArray;
  private OrderedArray idIndex;
  private OrderedArray firstNameIndex;
  private OrderedArray lastNameIndex;
  private Stack deleteStack;

  public DataBase() {
    this(100);
  }

  public DataBase(int size) {
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

  public void insert(String id, String firstName, String lastName) {
    if (idIndex.containsKey(id)) {
      return;
    }

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

  public StudentRecord search(String id) {
    int location = idIndex.getValue(id);
    return databaseArray[location];
  }

  public void delete(String id) {
    int location = idIndex.getValue(id);
    StudentRecord record = databaseArray[location];
    idIndex.delete(id);
    firstNameIndex.delete(record.firstName);
    lastNameIndex.delete(record.lastName);
    deleteStack.push(location);
  }

  private void printEntry(int location) {
    StudentRecord record = databaseArray[location];
    System.out.println(record.id + ": " + record.lastName + ", " + record.firstName);
  }

  private void listAscending(OrderedArray array) {
    array.iteratorInitFront();
    while (array.hasNext()) {
      printEntry(array.getNext());
    }
  }

  private void listDescending(OrderedArray array) {
    array.iteratorInitBack();
    while (array.hasPrevious()) {
      printEntry(array.getPrevious());
    }
  }

  public void addIt() {

  }

  public void deleteIt() {

  }

  public void findIt() {

  }

  public void ListByIDAscending() {
    listAscending(idIndex);
  }

  public void ListByFirstAscending() {
    listAscending(firstNameIndex);
  }

  public void ListByLastAscending() {
    listAscending(lastNameIndex);
  }

  public void ListByIDDescending() {
    listDescending(idIndex);
  }

  public void ListByFirstDescending() {
    listDescending(idIndex);
  }

  public void ListByLastDescending() {
    listDescending(idIndex);
  }
}