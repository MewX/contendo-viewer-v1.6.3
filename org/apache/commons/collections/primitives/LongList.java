package org.apache.commons.collections.primitives;

public interface LongList extends LongCollection {
  boolean add(long paramLong);
  
  void add(int paramInt, long paramLong);
  
  boolean addAll(int paramInt, LongCollection paramLongCollection);
  
  boolean equals(Object paramObject);
  
  long get(int paramInt);
  
  int hashCode();
  
  int indexOf(long paramLong);
  
  LongIterator iterator();
  
  int lastIndexOf(long paramLong);
  
  LongListIterator listIterator();
  
  LongListIterator listIterator(int paramInt);
  
  long removeElementAt(int paramInt);
  
  long set(int paramInt, long paramLong);
  
  LongList subList(int paramInt1, int paramInt2);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/LongList.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */