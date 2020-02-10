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
    }

    for (int i = 0; i <= last; i++) {
      int comp = newRecord.compareTo(array[i]);
      if (comp == 0) {
        return;
      } else if (comp > 0) {
        continue;
      } else if (comp < 0) {
        for (int j = last; j >= i; j--) {
          array[j + 1] = array[j];
        }
        last++;
        array[i] = newRecord;
      }
    }
  }

  public void remove(String key) {
    int location = search(key);
    for (int i = location; i >= last; i++) {
      array[i] = array[i + 1];
    }
    last--;
  }

  public int search(String key) {
    int start = 0;
    int end = last;
    int mid, comp;
    while(start != end) {
      mid = (end - start) / 2;
      comp = key.compareTo(array[mid].key);
      if (comp == 0) {
        return mid;
      } else if (comp > 0){
        start = mid;
      } else if (comp < 0) {
        end = mid;
      }
    }
    return -1;
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