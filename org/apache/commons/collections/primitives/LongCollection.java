package org.apache.commons.collections.primitives;

public interface LongCollection {
  boolean add(long paramLong);
  
  boolean addAll(LongCollection paramLongCollection);
  
  void clear();
  
  boolean contains(long paramLong);
  
  boolean containsAll(LongCollection paramLongCollection);
  
  boolean isEmpty();
  
  LongIterator iterator();
  
  boolean removeAll(LongCollection paramLongCollection);
  
  boolean removeElement(long paramLong);
  
  boolean retainAll(LongCollection paramLongCollection);
  
  int size();
  
  long[] toArray();
  
  long[] toArray(long[] paramArrayOflong);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/LongCollection.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */