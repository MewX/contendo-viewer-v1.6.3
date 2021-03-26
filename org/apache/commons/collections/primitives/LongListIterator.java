package org.apache.commons.collections.primitives;

public interface LongListIterator extends LongIterator {
  void add(long paramLong);
  
  boolean hasNext();
  
  boolean hasPrevious();
  
  long next();
  
  int nextIndex();
  
  long previous();
  
  int previousIndex();
  
  void remove();
  
  void set(long paramLong);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/LongListIterator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */