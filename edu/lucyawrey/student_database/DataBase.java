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

    try {
      Scanner fileIn = new Scanner(new File("data.txt"));
      int i = 0;
      while (fileIn.hasNextLine()) {
        String line = fileIn.nextLine();
        String[] tokens = line.split(" ");
        databaseArray[i] = new StudentRecord(tokens[2], tokens[1], tokens[0]);
        idIndex.insert(new IndexRecord(tokens[2], i));
        firstNameIndex.insert(new IndexRecord(tokens[1], i));
        lastNameIndex.insert(new IndexRecord(tokens[0], i));
        i++;
      }

      fileIn.close();
    } catch(Exception e) {
      e.printStackTrace();
      System.out.println("\nSkipping initial data!");
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
    System.out.println("Enter student record in the format '{lastName} {firstName} {id}'");
    String line = scanner.nextLine();
    String[] tokens = line.split(" ");
    boolean success = insert(tokens[2], tokens[1], tokens[0]);
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
      System.out.println("Got record");
      System.out.println("Last: "+ record.lastName + ", First: " + record.firstName + ", ID: " + record.id);
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
    listDescending(firstNameIndex);
  }

  public void ListByLastDescending() {
    System.out.println("Listing entire database in descening order by last name...");
    listDescending(lastNameIndex);
  }

  private void printEntry(int location) {
    StudentRecord record = databaseArray[location];
    System.out.println("Last: "+ record.lastName + ", First: " + record.firstName + ", ID: " + record.id);
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