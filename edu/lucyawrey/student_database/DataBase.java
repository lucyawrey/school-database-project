package edu.lucyawrey.student_database;

import java.io.File;
import java.util.Scanner;

// Student DataBase class, all of the databsase functionality is contained within this class.
public class DataBase {
  private int insertPointer;
  private StudentRecord[] databaseArray;
  private OrderedList<Integer> idIndex; 
  private OrderedList<String> firstNameIndex, lastNameIndex;
  private Stack<Integer> deleteStack;
  private Scanner scanner;

  // Default constructor passes default size to main constructor.               
  public DataBase() {
    this(200);
  }

  // Main constructor for the DataBase that sets all initial values and reads
  // initial data
  public DataBase(int size) {
    insertPointer = 0;
    databaseArray = new StudentRecord[size];
    idIndex = new OrderedList<Integer>();
    firstNameIndex = new OrderedList<String>();
    lastNameIndex = new OrderedList<String>();
    deleteStack = new Stack<Integer>();
    scanner = new Scanner(System.in);

    // Reads in default data from external file data.txt
    try {
      Scanner fileIn = new Scanner(new File("data.txt"));
      while (fileIn.hasNextLine()) {
        String line = fileIn.nextLine();
        String[] tokens = line.split(" ");
        insert(Integer.parseInt(tokens[2]), tokens[1], tokens[0]);
      }

      fileIn.close();
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("\nSkipping initial data!");
    }
  }

  // Inserts a new database item. The item is only added if it has a unique key.
  public boolean insert(int id, String firstName, String lastName) {
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

    // Adds new item to each of the indexes
    idIndex.insert(new IndexRecord<Integer>(id, location));
    firstNameIndex.insert(new IndexRecord<String>(firstName, location));
    lastNameIndex.insert(new IndexRecord<String>(lastName, location));

    return true;
  }

  // Search for and return a student record by ID
  public StudentRecord search(int id) {
    int location = idIndex.getValue(id);
    if (location != -1) {
      return databaseArray[location];
    } else {
      return null;
    }
  }

  // Delete a student record by removing it from the indexes and adding its
  // location to the delete stack
  public boolean delete(int id) {
    int location = idIndex.getValue(id);
    if (location != -1) {
      StudentRecord record = databaseArray[location];
      idIndex.delete(record.id);
      firstNameIndex.delete(record.firstName);
      lastNameIndex.delete(record.lastName);
      deleteStack.push(location);
      return true;
    } else {
      return false;
    }
  }

  // Interactive method called by the driver program to add new database items
  public void addIt() {
    System.out.println("Enter student record in the format '{lastName} {firstName} {id}'");
    String line = scanner.nextLine();
    while(line.isEmpty()) {
      line = scanner.nextLine();
    }
    String[] tokens = line.split(" ");
    boolean success = insert(Integer.parseInt(tokens[2]), tokens[1], tokens[0]);
    if (success) {
      System.out.println("Added student record");
    } else {
      System.out.println("ID already in use");
    }
  }

  // Interactive function called by the driver program to delete a database items
  public void deleteIt() {
    System.out.println("Enter ID of record to delete:");
    int token = scanner.nextInt();
    boolean success = delete(token);
    if (success) {
      System.out.println("Deleted");
    } else {
      System.out.println("ID not found");
    }
  }

  // Interactive function called by the driver program to search for a database
  // item by ID
  public void findIt() {
    System.out.println("Enter ID of record to look up:");
    int token = scanner.nextInt();
    StudentRecord record = search(token);
    if (record != null) {
      System.out.println("Got record");
      System.out.println("Last: " + record.lastName + ", First: " + record.firstName + ", ID: " + record.id);
    } else {
      System.out.println("ID not found");
    }
  }

  /*
   * Interactive functions called by the driver program to list all database items
   * is various different ways (ascending and descending lists sorted by each of
   * the three possible fields.
   */
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

  private <T extends Comparable<T>> void listAscending(OrderedList<T> list) {
    list.iteratorInitFront();
    while (list.hasNext()) {
      printEntry(list.getNext());
    }
  }

  private <T extends Comparable<T>> void listDescending(OrderedList<T> list) {
    list.iteratorInitBack();
    while (list.hasPrevious()) {
      printEntry(list.getPrevious());
    }
  }

  // Helper function to print a single database entry
  private void printEntry(int location) {
    StudentRecord record = databaseArray[location];
    System.out.println("Last: " + record.lastName + ", First: " + record.firstName + ", ID: " + record.id);
  }
}