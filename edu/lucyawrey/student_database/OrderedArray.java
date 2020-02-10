package edu.lucyawrey.student_database;

// Ordered array used for database indexes
public class OrderedArray {
  private IndexRecord[] array;
  private int last;
  private int iterator;

  public OrderedArray(int size) {
    array = new IndexRecord[size];
    last = -1;
    iterator = 0;
  }

  public void insert(IndexRecord newRecord) {
    if (last == -1) {
      array[0] = newRecord;
      last++;
      return;
    }

    for (int i = 0; i <= last; i++) {
      int comp = newRecord.compareTo(array[i]);
      if (comp > 0) {
        continue;
      } else {
        for (int j = last; j >= i; j--) {
          array[j + 1] = array[j];
        }
        last++;
        array[i] = newRecord;
        return;
      }
    }
  }

  public void delete(String key) {
    int location = search(key);
    for (int i = location; i >= last; i++) {
      array[i] = array[i + 1];
    }
    last--;
  }

  public int search(String key) {
    int start = 0;
    int end = last;
    while(start <= end) {
      int mid = 1 + (end - start) / 2;
      int comp = key.compareTo(array[mid].key);
      if (comp == 0) {
        return mid;
      } else if (comp > 0){
        start = mid + 1;
      } else if (comp < 0) {
        end = mid - 1;
      }
    }
    return -1;
  }

  public int getValue(String key) {
    int ref = search(key);
    if (ref != -1) {
      IndexRecord record = array[ref];
      return record.location;
    } else {
      return -1;
    }
  }

  public boolean containsKey(String key) {
    return (search(key) != -1);
  }

  public void iteratorInitFront() {
    iterator = 0;
  }

  public void iteratorInitBack() {
    iterator = last;
  }

  public boolean hasNext() {
    return (iterator <= last);
  }

  public boolean hasPrevious() {
    return (iterator > 0);
  }

  public int getNext() {
    return array[iterator++].location;
  }

  public int getPrevious() {
    return array[iterator--].location;
  }
}