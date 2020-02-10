package edu.lucyawrey.student_database;

import java.io.File;
import java.util.Scanner;

// Student database object
public class DataBase {
  private int insertPointer;
  private StudentRecord[] databaseArray;
  private OrderedArray idIndex;
  private OrderedArray firstNameIndex;
  private OrderedArray lastNameIndex;
  private Stack deleteStack;
  private Scanner scanner;

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
    scanner = new Scanner(System.in);

    StudentRecord[] initialData = getRecordsFromFile("data.txt", size);
    if (initialData != null) {
      for (int i = 0; i < initialData.length; i++) {
        StudentRecord record = initialData[i];
        databaseArray[i] = record;
        idIndex.insert(new IndexRecord(record.id, i));
        firstNameIndex.insert(new IndexRecord(record.firstName, i));
        lastNameIndex.insert(new IndexRecord(record.lastName, i));
      }
    }
  }

  public boolean insert(String id, String firstName, String lastName) {
    if (idIndex.containsKey(id)) {
      return false;
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
    return true;
  }

  public StudentRecord search(String id) {
    int location = idIndex.getValue(id);
    if (location != -1) {
      return databaseArray[location];
    } else {
      return null;
    }
  }

  public boolean delete(String id) {
    int location = idIndex.getValue(id);
    if (location != -1) {
      StudentRecord record = databaseArray[location];
      idIndex.delete(id);
      firstNameIndex.delete(record.firstName);
      lastNameIndex.delete(record.lastName);
      deleteStack.push(location);
      return true;
    } else {
      return false;
    }
  }

  public void addIt() {
    System.out.println("Enter student record in the format '{id} {firstName} {lastName}:'");
    String line = scanner.nextLine();
    String[] tokens = line.split(" ");
    boolean success = insert(tokens[0], tokens[1], tokens[2]);
    if (success) {
      System.out.println("Added student record");
    } else {
      System.out.println("ID already in use");
    }
  }

  public void deleteIt() {
    System.out.println("Enter ID of record to delete:");
    String token = scanner.next();
    boolean success = delete(token);
    if (success) {
      System.out.println("Deleted");
    } else {
      System.out.println("ID not found");
    }
  }

  public void findIt() {
    System.out.println("Enter ID of record to look up:");
    String token = scanner.next();
    StudentRecord record = search(token);
    if (record != null) {
      System.out.println("Retrieved record:" + record.id + ": " + record.lastName + ", " + record.firstName);
    } else {
      System.out.println("ID not found");
    }
  }

  public void ListByIDAscending() {
    System.out.println("Listing entire database in ascending order by ID...");
    listAscending(idIndex);
  }

  public void ListByFirstAscending() {
    System.out.println("Listing entire database in ascending order by first name...");
    listAscending(firstNameIndex);
  }

  public void ListByLastAscending() {
    System.out.println("Listing entire database in ascending order by last name...");
    listAscending(lastNameIndex);
  }

  public void ListByIDDescending() {
    System.out.println("Listing entire database in descening order by ID...");
    listDescending(idIndex);
  }

  public void ListByFirstDescending() {
    System.out.println("Listing entire database in descening order by first name...");
    listDescending(idIndex);
  }

  public void ListByLastDescending() {
    System.out.println("Listing entire database in descening order by last name...");
    listDescending(idIndex);
  }

  private StudentRecord[] getRecordsFromFile(String fileName, int size) {
    try {
      File file = new File(fileName);
      Scanner scanner = new Scanner(file);
      StudentRecord[] records = new StudentRecord[size];
      
      for (int i = 0; scanner.hasNextLine(); i++) {
        String line = scanner.nextLine();
        String[] tokens = line.split(" ");
        records[i] = new StudentRecord(tokens[0], tokens[1], tokens[2]);
      }

      scanner.close();
      return records;
    } catch(Exception e) {
      System.out.println("File not found, skipping initial data!");
      return null;
    }
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
}