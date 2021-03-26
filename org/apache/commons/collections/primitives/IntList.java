package org.apache.commons.collections.primitives;

public interface IntList extends IntCollection {
  boolean add(int paramInt);
  
  void add(int paramInt1, int paramInt2);
  
  boolean addAll(int paramInt, IntCollection paramIntCollection);
  
  boolean equals(Object paramObject);
  
  int get(int paramInt);
  
  int hashCode();
  
  int indexOf(int paramInt);
  
  IntIterator iterator();
  
  int lastIndexOf(int paramInt);
  
  IntListIterator listIterator();
  
  IntListIterator listIterator(int paramInt);
  
  int removeElementAt(int paramInt);
  
  int set(int paramInt1, int paramInt2);
  
  IntList subList(int paramInt1, int paramInt2);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/IntList.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */