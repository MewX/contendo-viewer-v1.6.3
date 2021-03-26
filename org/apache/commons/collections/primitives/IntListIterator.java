package org.apache.commons.collections.primitives;

public interface IntListIterator extends IntIterator {
  void add(int paramInt);
  
  boolean hasNext();
  
  boolean hasPrevious();
  
  int next();
  
  int nextIndex();
  
  int previous();
  
  int previousIndex();
  
  void remove();
  
  void set(int paramInt);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/IntListIterator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */