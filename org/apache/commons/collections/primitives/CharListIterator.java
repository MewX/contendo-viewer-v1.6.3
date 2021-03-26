package org.apache.commons.collections.primitives;

public interface CharListIterator extends CharIterator {
  void add(char paramChar);
  
  boolean hasNext();
  
  boolean hasPrevious();
  
  char next();
  
  int nextIndex();
  
  char previous();
  
  int previousIndex();
  
  void remove();
  
  void set(char paramChar);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/CharListIterator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */